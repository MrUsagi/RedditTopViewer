package com.skydan.reddittopviewer;

import java.util.Date;

public class RedditPost {
    private String _author;
    private Date _dateOfPost;
    private String _thumbnailImageURL;
    private int _postNumber;
    private String _fullImageURL;

    public RedditPost(String author, Date datePost,
                      String thumbnailImageURL, int postNumber, String fullImageURL){
        this._author = author;
        this._dateOfPost = datePost;
        this._thumbnailImageURL = thumbnailImageURL;
        this._postNumber = postNumber;
        this._fullImageURL = fullImageURL;
    }

    public String getAuthor() {
        return _author;
    }

    public Date getDateOfPost() {
        return _dateOfPost;
    }

    public int getPostNumber() {
        return _postNumber;
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

    public void setDateOfPost(Date _dateOfPost) {
        this._dateOfPost = _dateOfPost;
    }

    public void setFullImageURL(String _fullImageURL) {
        this._fullImageURL = _fullImageURL;
    }

    public void setPostNumber(int _postNumber) {
        this._postNumber = _postNumber;
    }

    public void setThumbnailImageURL(String _thumbnailImageURL) {
        this._thumbnailImageURL = _thumbnailImageURL;
    }
}
