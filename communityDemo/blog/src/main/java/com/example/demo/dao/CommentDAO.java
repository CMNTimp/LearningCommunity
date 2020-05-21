package com.example.demo.dao;


import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDAO {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = "user_id,content,created_date,article_id,status";
    String SELECT_FIELDS = "id" + INSERT_FIELDS;

    //新评论入表
    @Insert({"insert into ",TABLE_NAME, "(" ,INSERT_FIELDS,
            ") values (#{userId},#{content},#{createdDate},#{articleId},#{status})"})
    int addComment(Comment comment);

    //根据评论id检索出评论
    @Select({"select" , SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    Comment getCommentById(int id);


    //检索出一篇文章对应的所有评论
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,
    "where article_id = #{articleId} order by created_date desc"})
    List<Comment> selectCommentByArticle(int articleId);


    //通过articleId计算一篇文章对应的评论总数
    @Select({"select count(id) from" , TABLE_NAME,"where article_id = #{articleId}"})
    int getCommentCount(int articleId);


    //更新一个评论的状态，当删除一个评论时，将评论状态由0更新为1
    @Update({"update comment set status=#{status} where id=#{id"})
    int updateStatus(@Param("id") int id, @Param("status") int status);





}
