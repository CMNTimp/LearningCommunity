package com.example.demo.controller;


import com.example.demo.Service.ArticleService;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.LikeService;
import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.model.EntityType;
import com.example.demo.model.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @GetMapping("/{aid}")
    //这个方法返回的是一个ViewObJect列表，列表第一个元素记录了文章对象本身以及点赞信息及该该文章对应的作者对象
    //列表中后面的元素每一个元素中存储该文章的一个评论对象及该对象对应的点赞信息及该评论对应的作者对象
    public List<ViewObject> articleDetail(@PathVariable("aid") int aid){
        Article article = articleService.getById(aid);
        List<Comment> commentList = commentService.getCommentByArticle(aid);
        List<ViewObject> res = new ArrayList<>();
        ViewObject va = new ViewObject();
        va.set("article",article);
        //记录当前用户是否已经给该文章点过赞，这里飘红是因为我没有写获取当前userId的方法
        va.set("liked",likeService.getLikeStatus(userId, EntityType.ENTITY_ARTICLE,article.getId()));
        //记录文章的点赞数
        va.set("likeCount",likeService.getLikeCount(EntityType.ENTITY_ARTICLE,article.getId()));
        //把文章的作者对象也传到ViewObject中
        va.set("user",userService.getUser(article.getUserId()));
        for(Comment comment : commentList){
            ViewObject vc = new ViewObject();
            vc.set("comment",comment);
            //记录当前用户是否已经给该评论点过赞
            vc.set("liked",likeService.getLikeStatus(userId, EntityType.ENTITY_COMMENT,comment.getId()));
            vc.set("likeCount",likeService.getLikeCount(EntityType.ENTITY_COMMENT,comment.getId()));
            vc.set("user",userService.getUser(comment.getUserId()));
            res.add(vc);
        }
        return res;
    }

    @PostMapping("/add")
    //该方法用于发布新的文章，发布成功返回"success",失败也返回失败信息
    public String addArticle(@RequestParam("title") String title,@RequestParam("content") String content){
        Article article = new Article();
        article.setContent(content);
        article.setTitle(title);
        article.setCreatedDate(new Date());
        article.setUserId(需要登陆注册接口提供的服务获取用户ID);
        if(articleService.addArticle(article) > 0){
            return "success";
        }
        return "failed to add new article"
    }

    @PostMapping("/{aid}/addComment")
    //该方法用于在一个文章下发表新的评论
    public String addComment(@PathVariable("aid") int aid,@RequestParam("content") String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(需要登陆注册接口提供的服务来获取用户ID);
        comment.setCreatedDate(new Date());
        comment.setArticleId(aid);
        //向comment表中添加新的comment
        commentService.addComment(comment);
        int count = commentService.getCommentCount(comment.getArticleId());
        //将评论对应文章的commentCount值更新
        articleService.updateCommentCount(comment.getArticleId(),count);
        return "success";
    }

    @PostMapping("/articlelike")
    //该方法用于给一篇文章点赞
    public String likeArticle(@RequestParam("articleId") int articleId){
        Article article = articleService.getById(articleId);
        long likeCount = likeService.like(userId,EntityType.ENTITY_ARTICLE,articleId);
        return "success";
    }

    @PostMapping("/commentlike")
    //该方法用于给一个评论点赞
    public String likeComment(@RequestParam("commentId") int commentId){
        Comment comment = commentService.getCommentById(commentId);
        long likeCount = likeService.like(userId,EntityType.ENTITY_COMMENT,commentId);
        return "success";
    }


}
