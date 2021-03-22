package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class MypageItem implements Serializable {
    String myPost;
    Drawable myBadge;
    Integer postIdx;

    public MypageItem(String myPost, Drawable myBadge, Integer postIdx){
        this.myPost = myPost;
        this.myBadge = myBadge;
        this.postIdx = postIdx;
    }

    public String getMyPost() {
        return myPost;
    }
    public void setMyPost(String MyPost) {
        this.myPost = myPost;
    }

    public Drawable getMyBadge() {
        return myBadge;
    }
    public void setMyBadge(Drawable MyBadge) {
        this.myBadge = myBadge;
    }

    public Integer getPostIdx(){
        return postIdx;
    }
    public void setPostIdx(Integer postIdx) {
        this.postIdx = postIdx;
    }
}
