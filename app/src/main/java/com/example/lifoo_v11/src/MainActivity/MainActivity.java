package com.example.lifoo_v11.src.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.lifoo_v11.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Menu menu;
    ImageView plus_btn;


    com.example.lifoo_v11.src.AlertFragment.AlertFragment alertFragment;
    com.example.lifoo_v11.src.FeedFragment.FeedFragment feedFragment;
    com.example.lifoo_v11.src.MypageFragment.MypageFragment mypageFragment;
    com.example.lifoo_v11.src.RankingFragment.RankingFragment rankingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertFragment = new com.example.lifoo_v11.src.AlertFragment.AlertFragment();
        feedFragment = new com.example.lifoo_v11.src.FeedFragment.FeedFragment();
        mypageFragment = new com.example.lifoo_v11.src.MypageFragment.MypageFragment();
        rankingFragment = new com.example.lifoo_v11.src.RankingFragment.RankingFragment();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        menu = bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigation.setSelectedItemId(R.id.home);

        plus_btn = findViewById(R.id.bottom_navigation_iv_plus);

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진 올리기
            }
        });
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


}