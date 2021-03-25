package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertItem;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.AlertService;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.Decorations.SpaceDecoration;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.interfaces.MypageFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.ImogeResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class MypageFragment extends Fragment implements MypageFragmentActivityView {

    MainActivity mainActivity;
    ViewGroup viewGroup;
    MypageAdapter mypageAdapter;

    RecyclerView recyclerView;
    ArrayList mypost_list;
    LinearLayoutManager linearLayoutManager;
    List<Integer> count;
    int i = 0;

    ImageView btnViewAll;
    ImageView btnModifyNickname;
    TextView firstLetter;
    TextView secondLetter;
    TextView thirdLetter;
    TextView fourthLetter;
    TextView fifthLetter;
    TextView sixthLetter;

    String user_nick_name ="";

    TextView heart_cnt;
    TextView wow_cnt;
    TextView cheer_cnt;
    TextView money_cnt;
    TextView ques_cnt;
    TextView celebrate_cnt;
    TextView disgusting_cnt;
    TextView fire_cnt;
    TextView happysad_cnt;

    int page_num;

    // 서비스 선언
    MypageService mypageService = new MypageService(this);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public void update(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_mypage,container,false);

        recyclerView = viewGroup.findViewById(R.id.mypagefragment_recyclerview);
        linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        SpaceDecoration spaceItemDecoration = new SpaceDecoration();
        recyclerView.addItemDecoration(spaceItemDecoration);

        firstLetter = viewGroup.findViewById(R.id.firstLetter);
        secondLetter = viewGroup.findViewById(R.id.secondLetter);
        thirdLetter = viewGroup.findViewById(R.id.thirdLetter);
        fourthLetter = viewGroup.findViewById(R.id.fourthLetter);
        fifthLetter = viewGroup.findViewById(R.id.fifthLetter);
        sixthLetter = viewGroup.findViewById(R.id.sixthLetter);

        heart_cnt = viewGroup.findViewById(R.id.heart_cnt);
        wow_cnt = viewGroup.findViewById(R.id.wow_cnt);
        cheer_cnt = viewGroup.findViewById(R.id.cheer_cnt);
        money_cnt = viewGroup.findViewById(R.id.money_cnt);
        ques_cnt =  viewGroup.findViewById(R.id.ques_cnt);
        celebrate_cnt = viewGroup.findViewById(R.id.celebrate_cnt);
        disgusting_cnt = viewGroup.findViewById(R.id.disgusting_cnt);
        fire_cnt = viewGroup.findViewById(R.id.fire_cnt);
        happysad_cnt = viewGroup.findViewById(R.id.happysad_cnt);



        btnModifyNickname = viewGroup.findViewById(R.id.btnModifyNickname);
        btnModifyNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModifyNickname.class);
                intent.putExtra("Nickname", user_nick_name);
                startActivity(intent);

            }
        });

        btnViewAll = viewGroup.findViewById(R.id.btnViewAll);
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllPost.class);
                startActivity(intent);
            }
        });

        mypost_list = new ArrayList<MypageItem>();

//        Drawable drawable1 = getResources().getDrawable(R.drawable.food_image);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.food_image2);
//
//        Drawable drawable3 = getResources().getDrawable(R.drawable.badge_blue_40);
//        Drawable drawable4 = getResources().getDrawable(R.drawable.badge_red_40);
//
//        MypageItem mypageItem1 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem2 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//        MypageItem mypageItem3 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem4 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//
//        mypost_list.add(mypageItem1);
//        mypost_list.add(mypageItem2);
//        mypost_list.add(mypageItem3);
//        mypost_list.add(mypageItem4);


        // 통신으로 내 게시물 받아오기
//        mypageService.GetMyPost();
        TryGetMyPosts(0);


        // 통신으로 닉네임 받아오기
        Context context = getContext();
        sSharedPreferences = context.getSharedPreferences(TAG, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        String userIdx = sSharedPreferences.getString("user_idx",null);

        mypageService.GetNicknames(userIdx);

        // 통신으로 이모지 받아오기
        mypageService.GetImoge();


        mypageAdapter = new MypageAdapter(mypost_list);
        recyclerView.setAdapter(mypageAdapter);
        mypageAdapter.notifyDataSetChanged();

//        mypageAdapter.setOnItemClickListener(new MypageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int pos) {
//                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
        recyclerView.scrollToPosition(mypageAdapter.getItemCount() - 1);

        return viewGroup;
    }

    private void TryGetMyPosts(int page_num)
    {
        mypageService.GetMyPost(page_num);
    }

    @Override
    public void GetProfileFailure(String message, int code) {
        Log.d("회원 정보 받아오기 실패 ", message + "&&" + String.valueOf(code));
    }

    @Override
    public void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code) {
        Log.d("회원 정보 받아오기 성공 ",  String.valueOf(code));

        if(code == 2000){
            Log.d("회원 정보 받아오기 성공 ", String.valueOf(code));
            Log.d("회원 닉네임 ", mypageFragmentResponse.getResult().getNickname());
            user_nick_name = mypageFragmentResponse.getResult().getNickname();
            String temp_str = user_nick_name.replaceAll(" ","");
            Log.d("빈칸 없나 확인", temp_str);
            int temp_str_size = temp_str.length();
            Log.d("닉넴 사이즈 확인" , String.valueOf(temp_str_size));

            if(temp_str_size >= 6)
            {
                firstLetter.setText(temp_str.substring(0,1));
                secondLetter.setText(temp_str.substring(1,2));
                thirdLetter.setText(temp_str.substring(2,3));
                fourthLetter.setText(temp_str.substring(3,4));
                fifthLetter.setText(temp_str.substring(4,5));
                sixthLetter.setText(temp_str.substring(5,6));

            } else if(temp_str_size == 5) {

                firstLetter.setText(temp_str.substring(0,1));
                secondLetter.setText(temp_str.substring(1,2));
                thirdLetter.setText(temp_str.substring(2,3));
                fourthLetter.setText(temp_str.substring(3,4));
                fifthLetter.setText(temp_str.substring(4,5));

            } else if(temp_str_size == 4) {

                firstLetter.setText(temp_str.substring(0,1));
                secondLetter.setText(temp_str.substring(1,2));
                thirdLetter.setText(temp_str.substring(2,3));
                fourthLetter.setText(temp_str.substring(3,4));

            }else if(temp_str_size == 3) {

                firstLetter.setText(temp_str.substring(0,1));
                secondLetter.setText(temp_str.substring(1,2));
                thirdLetter.setText(temp_str.substring(2,3));

            }else if(temp_str_size == 2) {

                firstLetter.setText(temp_str.substring(0,1));
                secondLetter.setText(temp_str.substring(1,2));
            }


        }

        else{
            Log.d("회원 정보 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void EditProfileFailure(String message, int code) {

    }

    @Override
    public void EditProfileSuccess(NicknameResponse nicknameResponse, int code) {

    }


    @Override
    public void GetImogeFailure(String message, int code) {
        Log.d("이모지 받아오기 실패 ", message + "&&" + String.valueOf(code));
    }

    @Override
    public void GetImogeSuccess(ImogeResponse imogeResponse, int code) {
        Log.d("이모지 받아오기 성공 ",  String.valueOf(code));

        if(code == 2000){
            Log.d("이모지 받아오기 성공 ", String.valueOf(code));
            Log.d("하트 이모지 개수", imogeResponse.getResult().getImogeList().get(0).getimogeNum());

            heart_cnt.setText(imogeResponse.getResult().getImogeList().get(0).getimogeNum());
            wow_cnt.setText(imogeResponse.getResult().getImogeList().get(1).getimogeNum());
            cheer_cnt.setText(imogeResponse.getResult().getImogeList().get(2).getimogeNum());
            money_cnt.setText(imogeResponse.getResult().getImogeList().get(3).getimogeNum());
            ques_cnt.setText(imogeResponse.getResult().getImogeList().get(4).getimogeNum());
            celebrate_cnt.setText(imogeResponse.getResult().getImogeList().get(5).getimogeNum());
            disgusting_cnt.setText(imogeResponse.getResult().getImogeList().get(6).getimogeNum());
            fire_cnt.setText(imogeResponse.getResult().getImogeList().get(7).getimogeNum());
            happysad_cnt.setText(imogeResponse.getResult().getImogeList().get(7).getimogeNum());
        }

        else{
            Log.d("이모지 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void GetMyPostFailure(String message, int code) {
        Log.d("내 게시물 받아오기 실패 ", message + "&&" + String.valueOf(code));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void GetMyPostSuccess(MyPostResponse myPostResponse, int code) {
        Log.d("내 게시물 받아오기 성공 ", String.valueOf(code));

        if(code == 2000){
            Log.d("내 게시물 받아오기 성공 ", String.valueOf(code));
            Log.d("postList 사이즈", String.valueOf(myPostResponse.getResult().getPostList().size()));
            MyPostResponse.Result result = myPostResponse.getResult();
            List<MyPostResponse.Post> postList = result.getPostList();
            MypageItem mypageItem;

            for(int i = 0; i < postList.size(); i++) {
                Drawable badge = null;

                if(postList.get(i).getTotalImoge() >= 20
                        && postList.get(i).getTotalImoge() < 100){
                    badge = getResources().getDrawable(R.drawable.badge_red_40);
                }
                else if(postList.get(i).getTotalImoge() >= 100
                        && postList.get(i).getTotalImoge() < 500){
                    badge = getResources().getDrawable(R.drawable.badge_yellow_40);
                }
                else if(postList.get(i).getTotalImoge() >= 500
                        && postList.get(i).getTotalImoge() < 1000){
                    badge = getResources().getDrawable(R.drawable.badge_green_40);
                }
                else if(postList.get(i).getTotalImoge() >= 1000){
                    badge = getResources().getDrawable(R.drawable.badge_blue_40);
                }
                mypageItem = new MypageItem(postList.get(i).getPostUrl(), badge, postList.get(i).getPostIdx());
                mypost_list.add(mypageItem);
//                page_num += 1;
//
//                if(page_num <= 10)
//                    TryGetMyPosts(page_num);
                mypageAdapter.notifyDataSetChanged();

                if(i == 9){
                    page_num += 1;
                    TryGetMyPosts(page_num);
                }
            }
            page_num = 0;
        }

        else if(code == 3208){
            Log.d("게시물 목록 없음", String.valueOf(code));
        }

        else{
            Log.d("내 게시물 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }

    }


}

