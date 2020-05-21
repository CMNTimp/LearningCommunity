package com.example.demo.Service;


import com.example.demo.dao.CommentDAO;
import com.example.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    public List<Comment> getCommentByArticle(int articleId){
        return commentDAO.selectCommentByArticle(articleId);
    }

    public int addComment(Comment comment){
        return commentDAO.addComment(comment) > 0 ? comment.getId() : 0;
    }

    public int getCommentCount(int articleId){
        return commentDAO.getCommentCount(articleId);
    }

    public boolean deleteComment(int commentId){
        return commentDAO.updateStatus(commentId,1) > 0;
    }

    public Comment getCommentById(int id){
        return commentDAO.getCommentById(id);
    }

}
