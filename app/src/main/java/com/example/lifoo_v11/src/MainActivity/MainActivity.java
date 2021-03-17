package com.example.lifoo_v11.src.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.AlertFragment.models.AlertFragment;
import com.example.lifoo_v11.src.MypageFragment.models.MypageFragment;
import com.example.lifoo_v11.src.PhotoPickActivity.PhotoPickActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static com.example.lifoo_v11.ApplicationClass.TAG;
import static com.example.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo_v11.ApplicationClass.sSharedPreferences;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Menu menu;
    ImageView plus_btn;


    AlertFragment alertFragment;
    com.example.lifoo_v11.src.FeedFragment.FeedFragment feedFragment;
    MypageFragment mypageFragment;
    com.example.lifoo_v11.src.RankingFragment.RankingFragment rankingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertFragment = new AlertFragment();
        feedFragment = new com.example.lifoo_v11.src.FeedFragment.FeedFragment();
        mypageFragment = new MypageFragment();
        rankingFragment = new com.example.lifoo_v11.src.RankingFragment.RankingFragment();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        menu = bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigation.setSelectedItemId(R.id.home);

        plus_btn = findViewById(R.id.bottom_navigation_iv_plus);

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카메라 버튼, 앨범 버튼 선택 팝업.
                Popup_CAMERA_or_GALLERY popup_camera_or_gallery = new Popup_CAMERA_or_GALLERY(MainActivity.this);
                popup_camera_or_gallery.callFunction();
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottom_navigation_fl, fragment);
        fragmentTransaction.commit();
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.home:
                    menuItem.setIcon(R.drawable.icon_home_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bottom_navigation_fl, feedFragment).commitAllowingStateLoss();
                    break;

                case R.id.ranking:
                    menuItem.setIcon(R.drawable.icon_crown_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bottom_navigation_fl, rankingFragment).commitAllowingStateLoss();
                    break;

                case R.id.alarm:
                    menuItem.setIcon(R.drawable.icon_alarm_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bottom_navigation_fl, alertFragment).commitAllowingStateLoss();
                    break;

                case R.id.user:
                    menuItem.setIcon(R.drawable.icon_user_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bottom_navigation_fl, mypageFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    public void onChangeFragment(int index)
    {
        if(index == 4)
        {
            // 5번일 때는 setting
            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_navigation_fl,mypageFragment).commitAllowingStateLoss();
        }


    }





    public class Popup_CAMERA_or_GALLERY {

        private Context context;

        public Popup_CAMERA_or_GALLERY(Context context) {
            this.context = context;
        }

        //호출할 다이얼로그 함수
        public void callFunction()
        {
            final Dialog dig = new Dialog(context);
            dig.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dig.setContentView(R.layout.dialog_camera_or_gallery);
            dig.show();

            // 커스텀 다이얼로그 위젯 정의
            ImageView camera_Iv = dig.findViewById(R.id.cam_or_alb_iv_camera);
            ImageView album_Iv = dig.findViewById(R.id.cam_or_alb_iv_album);


            // 카메라 아이콘을 클릭 시
            camera_Iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dig.dismiss();
                    // 카메라 에서 사진을 가져올 수 있게

                    // 기존의 sharedpreference 에 저장되어있는  "photo_or_album" 키에 해당하는 값을 지우고 카메라를 의미하는 0 으로 저장.
                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove("photo_or_album");
                    editor.putInt("photo_or_album",0);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, PhotoPickActivity.class);
                    startActivity(intent);
                    finish();


                }
            });


            // 앨범 아이콘을 클릭 시
            album_Iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dig.dismiss();
                    // 앨범 에서 사진을 가져올수 있게

                    // 기존의 sharedpreference 에 저장되어있는  "photo_or_album" 키에 해당하는 값을 지우고 카메라를 의미하는 0 으로 저장.
                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove("photo_or_album");
                    editor.putInt("photo_or_album",1);
                    editor.commit();


                    Intent intent = new Intent(MainActivity.this, PhotoPickActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
        }

    }
}


//    private void getHashKey(){
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (packageInfo == null)
//            Log.e("KeyHash", "KeyHash:null");
//
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            } catch (NoSuchAlgorithmException e) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }
//    }