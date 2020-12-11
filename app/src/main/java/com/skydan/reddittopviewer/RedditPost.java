package com.skydan.reddittopviewer;

import java.util.Date;

public class RedditPost {
    private String _author;
    private long _dateOfPost;
    private String _thumbnailImageURL;
    private int _commentNumber;
    private String _fullImageURL;

    public RedditPost(){}
    public RedditPost(String author, long datePost,
                      String thumbnailImageURL, int commentNumber, String fullImageURL){
        this._author = author;
        this._dateOfPost = datePost;
        this._thumbnailImageURL = thumbnailImageURL;
        this._commentNumber = commentNumber;
        this._fullImageURL = fullImageURL;
    }

    public String getAuthor() {
        return _author;
    }

    public long getDateOfPost() {
        return _dateOfPost;
    }

    public int getCommentNumber() {
        return _commentNumber;
    }

    public String getThumbnailImageURL() {
        return _thumbnailImageURL;
    }

    public String getFullImageURL() {
        return _fullImageURL;
    }

    public void setAuthor(String _author) {
        this._author = _author;
    }

    public void setDateOfPost(long _dateOfPost) {
        this._dateOfPost = _dateOfPost;
    }

    public void setFullImageURL(String _fullImageURL) {
        this._fullImageURL = _fullImageURL;
    }

    public void setCommentNumber(int _commentNumber) {
        this._commentNumber = _commentNumber;
    }

    public void setThumbnailImageURL(String _thumbnailImageURL) {
        this._thumbnailImageURL = _thumbnailImageURL;
    }
}
