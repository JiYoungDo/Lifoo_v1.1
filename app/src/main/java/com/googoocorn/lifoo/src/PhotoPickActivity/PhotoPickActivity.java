package com.googoocorn.lifoo.src.PhotoPickActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.PhotoPickActivity.interfaces.PhotoPostActivityView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

public class PhotoPickActivity extends AppCompatActivity implements PhotoPostActivityView {

    PhotoPostService photoPostService = new PhotoPostService(this);

    int cam_or_album_value;

    private Boolean isPermission = true;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 0;

    private File tempFile;
    private Uri filePath;

    ImageView back_btn;
    ImageView action_change_btn;
    ImageView user_img;
    EditText user_title;
    EditText user_contents;
    TextView post_confirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_pick);

        // ?????? ??????
        tedPermission();

        user_img = findViewById(R.id.photo_pick_user_img);
        user_title = findViewById(R.id.photo_pick_et_title);
        user_contents = findViewById(R.id.photo_pick_et_contents);
        action_change_btn = findViewById(R.id.photo_pick_change_action_iv);

        post_confirm = findViewById(R.id.photo_pick_tv_complete_bottom);
        post_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ????????? ????????? ?????????, ????????? url ??????!
                uploadFile();



            }
        });

        // ???????????? ??????
        back_btn = findViewById(R.id.photo_pick_back_iv);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoPickActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //  "photo_or_album" ?????? ???????????? ?????? ????????? (0 = ?????????, 1= ??????)
        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        cam_or_album_value =sSharedPreferences.getInt("photo_or_album",10);

        switch(cam_or_album_value)
        {
            case 0:
                action_change_btn.setImageResource(R.drawable.ic_photo);
                AlertDialog.Builder builder = new AlertDialog.Builder(PhotoPickActivity.this);
                builder.setTitle("").setMessage("???????????? ????????? ?????????????");
                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermission) takePhoto();
                    }
                });
                builder.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        // ????????? ?????? ?????? ?????? jwt ?????? ??????
                        editor.remove("photo_or_album");
                        editor.putInt("photo_or_album",1);
                        editor.commit();
                        action_change_btn.setImageResource(R.drawable.ic_photo_album);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case 1:
                action_change_btn.setImageResource(R.drawable.ic_photo_album);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(PhotoPickActivity.this);
                builder2.setTitle("").setMessage("???????????? ????????? ???????????????????");
                builder2.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermission) goToAlbum();
                    }
                });
                builder2.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        // ????????? ?????? ?????? ?????? jwt ?????? ??????
                        editor.remove("photo_or_album");
                        editor.putInt("photo_or_album",0);
                        editor.commit();
                        action_change_btn.setImageResource(R.drawable.ic_photo);
                    }
                });
                AlertDialog alertDialog2 = builder2.create();
                alertDialog2.show();

                break;
            default:

                break;
        }


        // ?????????? ?????? ? ?????? ??????

       action_change_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               // 0 ?????? 1??? ????????? , 1?????? 0?????? ??????
               //  "photo_or_album" ?????? ???????????? ?????? ????????? (0 = ?????????, 1= ??????)
               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
               cam_or_album_value =sSharedPreferences.getInt("photo_or_album",10);

               switch(cam_or_album_value)
               {
                   case 0:
                       action_change_btn.setImageResource(R.drawable.ic_photo_album);
                       AlertDialog.Builder builder = new AlertDialog.Builder(PhotoPickActivity.this);
                       builder.setTitle("").setMessage("???????????? ????????? ???????????????????");
                       builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                               SharedPreferences.Editor editor = sSharedPreferences.edit();
                               // ????????? ?????? ?????? ?????? jwt ?????? ??????
                               editor.remove("photo_or_album");
                               editor.putInt("photo_or_album",1);
                               editor.commit();
                               if(isPermission) goToAlbum();
                           }
                       });
                       builder.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });
                       AlertDialog alertDialog = builder.create();
                       alertDialog.show();
                       break;
                   case 1:
                       action_change_btn.setImageResource(R.drawable.ic_photo);
                       AlertDialog.Builder builder2 = new AlertDialog.Builder(PhotoPickActivity.this);
                       builder2.setTitle("").setMessage("???????????? ????????? ?????????????");
                       builder2.setPositiveButton("???", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                               SharedPreferences.Editor editor = sSharedPreferences.edit();
                               // ????????? ?????? ?????? ?????? jwt ?????? ??????
                               editor.remove("photo_or_album");
                               editor.putInt("photo_or_album",1);
                               editor.commit();
                               if(isPermission)takePhoto();
                           }
                       });
                       builder2.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });
                       AlertDialog alertDialog2 = builder2.create();
                       alertDialog2.show();

                       break;
                   default:

                       break;
               }

           }
       });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "?????? ???????????????.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " ?????? ??????");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                /*
                 *  Uri ????????????
                 *  content:/// ?????? file:/// ???  ????????????.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));


            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA) {

            setImage();

        }
    }



    // ???????????? ????????? ????????????
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    // ??????????????? ????????? ????????????
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "????????? ?????? ??????! ?????? ??????????????????.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            Uri photoUri = FileProvider.getUriForFile(this, "com.googoocorn.lifoo.src.PhotoPickActivity", tempFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }


    //?????? ??? ?????? ?????????
    private File createImageFile() throws IOException {

        // ????????? ?????? ?????? ( blackJin_{??????}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "lifoo_" + timeStamp + "_";

        // ???????????? ????????? ?????? ?????? ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/lifoo/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // ?????? ??????
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }


    //  tempFile ??? bitmap ?????? ?????? ??? ImageView ??? ????????????.
    private void setImage() {


        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        /**
         * ???????????? ???????????? setImage ??? ?????? filePath ??? ?????????!!
          */

        filePath =  Uri.fromFile(tempFile);

        user_img.setImageBitmap(originalBm);

        /**
         *  tempFile ?????? ??? null ????????? ????????? ?????????.
         *  (resultCode != RESULT_OK) ??? ??? tempFile ??? ???????????? ?????????
         *  ????????? ???????????? ?????? ?????? ?????? ?????? ?????? ????????? ???????????????.
         */
        tempFile = null;

    }


    //????????? ????????? ?????????
    private void uploadFile() {
        //???????????? ????????? ????????? ??????
        if (filePath != null) {
            //????????? ?????? Dialog ?????????
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("????????????...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique??? ???????????? ?????????.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";
            //storage ????????? ?????? ???????????? ????????? ??????.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://lifoo-c93c6.appspot.com").child("images/" + filename);
            //???????????????...
            storageRef.putFile(filePath)
                    //?????????
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //????????? ?????? Dialog ?????? ??????
                            Toast.makeText(getApplicationContext(), "????????? ??????!", Toast.LENGTH_SHORT).show();

                            // ???????????? ?????? ??????, url ??? ????????????..
                            FirebaseStorage storage = FirebaseStorage.getInstance("gs://lifoo-c93c6.appspot.com");
                            StorageReference storageRef = storage.getReference();
                            storageRef.child("images/" + filename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //????????? ?????? ?????????

                                    String url = uri.toString();
                                    Log.d("????????? ??????????????? ?????? ????????? ??????", url);
                                    String title = user_title.getText().toString();
                                    String contents = user_contents.getText().toString();

                                    /** ?????? ?????? ??????!! */
                                    TryPhotoPost(url,title,contents);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //????????? ?????? ?????????
                                    Toast.makeText(getApplicationContext(), "url ???????????? ??????", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    })
                    //?????????
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "????????? ??????!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //?????????
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //?????? ?????? ?????? ???????????? ????????? ????????????.
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            //dialog??? ???????????? ???????????? ????????? ??????
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
        }
    }





    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // ?????? ?????? ??????
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // ?????? ?????? ??????
                isPermission = false;

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }


    private void TryPhotoPost(String postUrl,String postTitle, String postBody)
    {
        photoPostService.postPosts(postUrl,postTitle,postBody);
    }


    @Override
    public void PhotoPostFailure(String message, int code) {
        Log.d("????????? ????????? ??????", message + String.valueOf(code));
    }

    @Override
    public void PhotoPostSuccess(String message, int code) {
        Log.d("????????? ????????? ??????", message + String.valueOf(code));

        Intent intent = new Intent(PhotoPickActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}