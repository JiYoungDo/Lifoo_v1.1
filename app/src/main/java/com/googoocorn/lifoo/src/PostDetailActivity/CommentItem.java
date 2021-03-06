package com.googoocorn.lifoo.src.PostDetailActivity;

public class CommentItem {

    String comment_nick_name;
    String comment_body;
    int comment_like_count;
    String comment_idx;
    String is_clicked;

    public CommentItem(String comment_nick_name, String comment_body, int comment_like_count,String comment_idx, String is_clicked) {
        this.comment_nick_name = comment_nick_name;
        this.comment_body = comment_body;
        this.comment_like_count = comment_like_count;
        this.comment_idx = comment_idx;
        this.is_clicked = is_clicked;
    }

    public String getComment_nick_name() {
        return comment_nick_name;
    }

    public void setComment_nick_name(String comment_nick_name) {
        this.comment_nick_name = comment_nick_name;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }

    public int getComment_like_count() {
        return comment_like_count;
    }

    public void setComment_like_count(int comment_like_count) {
        this.comment_like_count = comment_like_count;
    }

    public String getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(String comment_idx) {
        this.comment_idx = comment_idx;
    }

    public String getIs_clicked() {
        return is_clicked;
    }

    public void setIs_clicked(String is_clicked) {
        this.is_clicked = is_clicked;
    }
}
