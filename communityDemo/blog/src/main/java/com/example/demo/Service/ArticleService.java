package com.example.demo.Service;


import com.example.demo.dao.ArticleDAO;
import com.example.demo.model.Article;
import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public Article getById(int id){
        return articleDAO.getById(id);
    }

    public int addArticle(Article article){
        return articleDAO.addArticle(article) > 0 ? article.getId() : 0;
    }

    public List<Article> getLatestArticles(int userId,int offset,int limit){
        return articleDAO.selectLatestArticle(userId,offset,limit);
    }

    public int updateCommentCount(int id,int count){
        return articleDAO.updateCommentCount(id,count);
    }



}
