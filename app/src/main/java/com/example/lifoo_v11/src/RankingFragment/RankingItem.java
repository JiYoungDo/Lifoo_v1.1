package com.example.lifoo_v11.src.RankingFragment;

import android.graphics.drawable.Drawable;

public class RankingItem {
    Drawable rankingPost;
    Drawable rankingBadge;
    String rankingPostTitle;
    String rankingPostTime;
    String rankingPostEmoji;


    public RankingItem(Drawable rankingPost, Drawable rankingBadge, String rankingPostTitle, String rankingPostTime, String rankingPostEmoji) {
        this.rankingPost = rankingPost;
        this.rankingBadge = rankingBadge;
        this.rankingPostTitle = rankingPostTitle;
        this.rankingPostTime = rankingPostTime;
        this.rankingPostEmoji = rankingPostEmoji;
    }

    public Drawable getRankingPost() {
        return rankingPost;
    }
    public void setRankingPost(Drawable rankingPost) {
        this.rankingPost = rankingPost;
    }

    public Drawable getRankingBadge() {
        return rankingBadge;
    }
    public void setRankingBadge(Drawable rankingBadge) {
        this.rankingBadge = rankingBadge;
    }

    public String getRankingPostTitle() {
        return rankingPostTitle;
    }
    public void setRankingPostTitle(String rankingPostTitle) {
        this.rankingPostTitle = rankingPostTitle; }

    public String getRankingPostTime() {
        return rankingPostTime;
    }
    public void setRankingPostTime(String rankingPostTime) {
        this.rankingPostTime = rankingPostTime; }

    public String getRankingPostEmoji() {
        return rankingPostEmoji;
    }
    public void setRankingPostEmoji(String rankingPostEmoji) {
        this.rankingPostEmoji = rankingPostEmoji;
    }


}
