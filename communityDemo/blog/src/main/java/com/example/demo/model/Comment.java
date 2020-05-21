package com.example.demo.model;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int ArticleId; //评论对应的文章的ID
    private String content;
    private Date createdDate;
    private int status; //评论的状态，表明该评论是否生效

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setArticleId(int article_Id) {
        ArticleId = article_Id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getArticleId() {
        return ArticleId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getStatus() {
        return status;
    }
}
