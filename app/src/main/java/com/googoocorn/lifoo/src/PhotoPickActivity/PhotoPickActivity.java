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

        // 권한 요청
        tedPermission();

        user_img = findViewById(R.id.photo_pick_user_img);
        user_title = findViewById(R.id.photo_pick_et_title);
        user_contents = findViewById(R.id.photo_pick_et_contents);
        action_change_btn = findViewById(R.id.photo_pick_change_action_iv);

        post_confirm = findViewById(R.id.photo_pick_tv_complete_bottom);
        post_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 파이어 베이스 올리고, 받아온 url 엮기!
                uploadFile();



            }
        });

        // 뒤로가기 버튼
        back_btn = findViewById(R.id.photo_pick_back_iv);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoPickActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //  "photo_or_album" 키에 해당하는 값을 가져옴 (0 = 카메라, 1= 앨범)
        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        cam_or_album_value =sSharedPreferences.getInt("photo_or_album",10);

        switch(cam_or_album_value)
        {
            case 0:
                action_change_btn.setImageResource(R.drawable.ic_photo);
                AlertDialog.Builder builder = new AlertDialog.Builder(PhotoPickActivity.this);
                builder.setTitle("").setMessage("카메라로 사진을 찍겠어요?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermission) takePhoto();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        // 내부에 저장 되어 있는 jwt 값을 지움
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
                builder2.setTitle("").setMessage("앨범에서 사진을 가져오겠어요?");
                builder2.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isPermission) goToAlbum();
                    }
                });
                builder2.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        // 내부에 저장 되어 있는 jwt 값을 지움
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


        // 카메라? 앨범 ? 전환 버튼

       action_change_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               // 0 이면 1로 바꾸기 , 1이면 0으로 바꾸
               //  "photo_or_album" 키에 해당하는 값을 가져옴 (0 = 카메라, 1= 앨범)
               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
               cam_or_album_value =sSharedPreferences.getInt("photo_or_album",10);

               switch(cam_or_album_value)
               {
                   case 0:
                       action_change_btn.setImageResource(R.drawable.ic_photo_album);
                       AlertDialog.Builder builder = new AlertDialog.Builder(PhotoPickActivity.this);
                       builder.setTitle("").setMessage("앨범에서 사진을 가져오겠어요?");
                       builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                               SharedPreferences.Editor editor = sSharedPreferences.edit();
                               // 내부에 저장 되어 있는 jwt 값을 지움
                               editor.remove("photo_or_album");
                               editor.putInt("photo_or_album",1);
                               editor.commit();
                               if(isPermission) goToAlbum();
                           }
                       });
                       builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
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
                       builder2.setTitle("").setMessage("카메라로 사진을 찍겠어요?");
                       builder2.setPositiveButton("네", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                               SharedPreferences.Editor editor = sSharedPreferences.edit();
                               // 내부에 저장 되어 있는 jwt 값을 지움
                               editor.remove("photo_or_album");
                               editor.putInt("photo_or_album",1);
                               editor.commit();
                               if(isPermission)takePhoto();
                           }
                       });
                       builder2.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
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
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
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
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
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



    // 앨범에서 이미지 가져오기
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    // 카메라에서 이미지 가져오기
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            Uri photoUri = FileProvider.getUriForFile(this, "com.GoogooCorn.lifoo_v11.src.PhotoPickActivity", tempFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }


    //폴더 및 파일 만들기
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "lifoo_" + timeStamp + "_";

        // 이미지가 저장될 폴더 이름 ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/lifoo/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }


    //  tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
    private void setImage() {


        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        /**
         * 앨범이든 카메라든 setImage 를 할때 filePath 를 가져옴!!
          */

        filePath =  Uri.fromFile(tempFile);

        user_img.setImageBitmap(originalBm);

        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null;

    }


    //파이어 베이스 업로드
    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://lifoo-c93c6.appspot.com").child("images/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();

                            // 업로드가 완료 되면, url 을 가져오고..
                            FirebaseStorage storage = FirebaseStorage.getInstance("gs://lifoo-c93c6.appspot.com");
                            StorageReference storageRef = storage.getReference();
                            storageRef.child("images/" + filename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //이미지 로드 성공시

                                    String url = uri.toString();
                                    Log.d("파이어 베이스에서 오는 유알엘 확인", url);
                                    String title = user_title.getText().toString();
                                    String contents = user_contents.getText().toString();

                                    /** 우리 서버 통신!! */
                                    TryPhotoPost(url,title,contents);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //이미지 로드 실패시
                                    Toast.makeText(getApplicationContext(), "url 가져오기 실패", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다.
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }





    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
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
        Log.d("포스트 업로드 실패", message + String.valueOf(code));
    }

    @Override
    public void PhotoPostSuccess(String message, int code) {
        Log.d("포스트 업로드 성공", message + String.valueOf(code));

        Intent intent = new Intent(PhotoPickActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}