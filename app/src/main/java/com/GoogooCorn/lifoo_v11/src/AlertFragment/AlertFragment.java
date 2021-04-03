package com.GoogooCorn.lifoo_v11.src.AlertFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.interfaces.AlertFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.AlertFragment.models.AlertFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlertFragment extends Fragment implements AlertFragmentActivityView {

    MainActivity mainActivity;
    ViewGroup viewGroup;

    AlertAdapter alertAdapter;
    RecyclerView recyclerView;
    ArrayList alert_list;

    String currentTime;


//    SwipeRefreshLayout mSwipeRefreshLayout;


    AlertService alertService = new AlertService(this);

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_alert,container,false);

        recyclerView = viewGroup.findViewById(R.id.alert_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        alert_list = new ArrayList<AlertItem>();

//        // 더미 데이터 넣기
//        Drawable dummy = getResources().getDrawable(R.drawable.frame_2);
//        Drawable dummy_2 = getResources().getDrawable(R.drawable.frame_3);
//
//        AlertItem alertItem = new AlertItem(1, "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg");
//        AlertItem alertItem_1 = new AlertItem(1, "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg");
//        AlertItem alertItem_2 = new AlertItem(1, "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg");
//
//
//        alert_list.add(alertItem);
//        alert_list.add(alertItem_1);
//        alert_list.add(alertItem_2);

        // refresh_ll


        //통신으로 받아오기
        alertService.GetAlarms();

        // 어댑터
        alertAdapter = new AlertAdapter(alert_list);
        recyclerView.setAdapter(alertAdapter);
        alertAdapter.notifyDataSetChanged();


        alertAdapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //이모지 인덱스 전달해줘야함
//                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
//                startActivity(intent);
            }
        });

        return viewGroup;
    }

    @Override
    public void GetAlertFailure(String message, int code) {
        Log.d("알림 받아오기 실패 ", message+ "&& " + String.valueOf(code));
    }

    @Override
    public void GetAlertSuccess(AlertFragmentResponse alertFragmentResponse, int code) {
        Log.d("알림 받아오기 성공 ",  "&& " + String.valueOf(code));

        if(code == 2000){
            Log.d("알림 받아오기 성공 ",  "&& " + String.valueOf(code));

            long mNow = System.currentTimeMillis();
            Date mReDate = new Date(mNow);
            SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatDate = mFormat.format(mReDate);
            currentTime = formatDate;

            String postTime;

            for(int i = 0; i < alertFragmentResponse.getResult().getAlarmList().size() ; i++) {
                postTime = Calc_time(alertFragmentResponse.getResult().getAlarmList().get(i).getCreatedAt(), currentTime);

                AlertItem alertItem = new AlertItem(alertFragmentResponse.getResult().getAlarmList().get(i).getImogeIdx(),
                        alertFragmentResponse.getResult().getAlarmList().get(i).getAlarmText(),
                        postTime,
                        alertFragmentResponse.getResult().getAlarmList().get(i).getPostUrl(),
                        alertFragmentResponse.getResult().getAlarmList().get(i).getPostIdx());
                alert_list.add(alertItem);
                alertAdapter.notifyDataSetChanged();
            }
        }

        else if(code == 3209){
            Log.d("알림 목록 없음", String.valueOf(code));
        }

        else{
            Log.d("알림 리스폰스 오류 : ", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }

    }

    @NotNull
    private String Calc_time(String input_time, String formatDate)
    {

        int result;
        String str_result;

        int i_year, i_month, i_date, i_hour, i_min, i_sec;
        int n_year, n_month, n_date, n_hour, n_min, n_sec;


        // 2021-03-10T10:06:10.000+00:00
        // 2021 03 10 10 06 10.000+00:00
        String Input_0 = input_time.replaceAll("-","");
        String Input_1 = Input_0.replaceAll("T","");
        String Input = Input_1.replaceAll(":","");
        i_year = Integer.parseInt(Input.substring(0,4));
        i_month = Integer.parseInt(Input.substring(4,6));
        i_date = Integer.parseInt(Input.substring(6,8));
        i_hour = Integer.parseInt(Input.substring(8,10));
        i_min = Integer.parseInt(Input.substring(10,12));
        i_sec = Integer.parseInt(Input.substring(12,14));




        String now_1 = formatDate.replaceAll("-","");
        String now_2 = now_1.replaceAll(" ","");
        String NOW = now_2.replaceAll(":","");
        n_year = Integer.parseInt(NOW.substring(0,4));
        n_month = Integer.parseInt(NOW.substring(4,6));
        n_date = Integer.parseInt(NOW.substring(6,8));
        n_hour = Integer.parseInt(NOW.substring(8,10));
        n_min = Integer.parseInt(NOW.substring(10,12));
        n_sec = Integer.parseInt(NOW.substring(12,13));


        if(i_year != n_year) {
            str_result = String.valueOf(n_year - i_year) + "년 전";
        }else if(i_month != n_month){
            str_result = String.valueOf(n_month - i_month) +"개월 전";
        }else if(i_date != n_date){
            str_result = String.valueOf(n_date - i_date)+"일 전";
        }else if(i_hour != n_hour) {
            str_result = String.valueOf(n_hour - i_hour) + "시간 전";
        }else if(i_min != n_min){
            str_result = String.valueOf(n_min - i_min) +"분 전";
        }else{
            str_result = String.valueOf(n_sec-i_sec) +"초 전";
        }


        return str_result;
    }
}

