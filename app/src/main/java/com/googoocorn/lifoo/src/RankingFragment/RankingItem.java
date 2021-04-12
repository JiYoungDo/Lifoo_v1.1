package com.googoocorn.lifoo.src.RankingFragment;

import android.graphics.drawable.Drawable;

public class RankingItem {
    String rankingPost;
    Drawable rankingBadge;
    String rankingPostTitle;
    String rankingPostTime;
    Integer rankingPostEmoji;
    Integer rankingPostIdx;


    public RankingItem(String rankingPost, Drawable rankingBadge, String rankingPostTitle, String rankingPostTime, Integer rankingPostEmoji, Integer rankingPostIdx) {
        this.rankingPost = rankingPost;
        this.rankingBadge = rankingBadge;
        this.rankingPostTitle = rankingPostTitle;
        this.rankingPostTime = rankingPostTime;
        this.rankingPostEmoji = rankingPostEmoji;
        this.rankingPostIdx = rankingPostIdx;
    }

    public String getRankingPost() {
        return rankingPost;
    }
    public void setRankingPost(String rankingPost) {
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

    public Integer getRankingPostEmoji() {
        return rankingPostEmoji;
    }
    public void setRankingPostEmoji(Integer rankingPostEmoji) {
        this.rankingPostEmoji = rankingPostEmoji;
    }

    public Integer getRankingPostIdx() {
        return rankingPostIdx;
    }
    public void setRankingPostIdx(Integer rankingPostIdx) {
        this.rankingPostIdx = rankingPostIdx;
    }

}
