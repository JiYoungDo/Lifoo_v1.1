package com.googoocorncorn.lifoo_v11.src.PostDetailActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.googoocorncorn.lifoo_v11.R;
import com.googoocorncorn.lifoo_v11.src.MainActivity.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Share_kakao_bottom_sheet extends BottomSheetDialogFragment {

    MainActivity mainActivity;
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.share_kakao_bottom_sheet_dialog,container,false);
        return viewGroup;
    }
}
