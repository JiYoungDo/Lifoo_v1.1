package com.GoogooCorn.lifoo_v11.src.MypageFragment.Decorations;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceGridDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceGridDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = 35;
        outRect.right = 35;
        outRect.bottom = 60;

    }
}