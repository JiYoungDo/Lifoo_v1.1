package com.example.lifoo_v11.src.MypageFragment;

import android.graphics.drawable.Drawable;

public class MypageItem {
    Drawable myPost;
    Drawable myBadge;

    public MypageItem(Drawable myPost, Drawable myBadge){
        this.myPost = myPost;
        this.myBadge = myBadge;
    }

    public Drawable getMyPost() {
        return myPost;
    }
    public void setMyPost(Drawable MyPost) {
        this.myPost = myPost;
    }

    public Drawable getMyBadge() {
        return myBadge;
    }
    public void setMyBadge(Drawable MyBadge) {
        this.myBadge = myBadge;
    }
}
