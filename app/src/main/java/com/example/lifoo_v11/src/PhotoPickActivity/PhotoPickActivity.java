package com.example.lifoo_v11.src.PhotoPickActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.MainActivity.MainActivity;
import com.example.lifoo_v11.src.PhotoPostActivity.PhotoPostActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.lifoo_v11.ApplicationClass.TAG;
import static com.example.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo_v11.ApplicationClass.sSharedPreferences;

public class PhotoPickActivity extends AppCompatActivity {

    int cam_or_album_value;

    ImageView change_icon_iv;
    ImageView close_btn;
    ImageView user_img;
    TextView next_btn;

    private Boolean isPermission = true;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 0;

    private File tempFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_pick);

        // 권한 요청
        tedPermission();

        user_img= findViewById(R.id.photo_pick_user_img);

        change_icon_iv = findViewById(R.id.photo_pick_change_action_iv);

        change_icon_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                int what = sSharedPreferences.getInt("photo_or_album",10);

                if(what == 0) {
                    change_icon_iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_photo_album));
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove("photo_or_album");
                    editor.putInt("photo_or_album",1);
                    editor.commit();
                    if(isPermission) goToAlbum();

                }else if(what == 1) {
                    change_icon_iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_photo));
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove("photo_or_album");
                    editor.putInt("photo_or_album",0);
                    editor.commit();
                    if(isPermission) takePhoto();
                }



            }
        });

        close_btn =findViewById(R.id.photo_pick_close_iv);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoPickActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        next_btn = findViewById(R.id.photo_pick_tv_next_btn);
        next_btn.bringToFront();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 세팅 된 이미지를 다음 액티비티인 PhotoPostActivity 에서  파이어베이스 올리고 & 서버에 Post
                Intent intent = new Intent(PhotoPickActivity.this, PhotoPostActivity.class);
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
                if(isPermission) takePhoto();
                break;
            case 1:
                if(isPermission) goToAlbum();
                break;
            default:

                break;
        }

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


    /**
     *  앨범에서 이미지 가져오기
     */
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /**
     *  카메라에서 이미지 가져오기
     */
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

            Uri photoUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    /**
     *  폴더 및 파일 만들기
     */
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


    /**
     *  tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
     */
    private void setImage() {


        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        user_img.setImageBitmap(originalBm);

        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null;

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

}