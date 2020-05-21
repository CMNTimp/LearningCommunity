package com.example.demo.dao;

import com.example.demo.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ArticleDAO {
    String TABLE_NAME = "question";
    String INSERT_FIELDS = "title,content,add_time,user_id,comment_count";
    String SELECT_FIELDS = "id, " + INSERT_FIELDS;



    //向数据库中插入新文章
    @Insert({"insert into" , TABLE_NAME, "(" ,INSERT_FIELDS, ") " +
            "values (#{title),#{content},#{createdDate},#{userId},#{commentCount})"})
    int addArticle(Article article);

    //检索某一个作者的所有文章并从中拿出一部分，返回的是一个文章列表
    @Select({"select" ,SELECT_FIELDS,"from",TABLE_NAME,
            "where user_id = #{userId} order by id desc limit #{offset},#{limit}"})
    List<Article> selectLatestArticle(@Param("userId") int userId,@Param("offset") int offset,
                                      @Param("limit") int limit);

    //通过文章的id取出一篇文章
    @Select({"select" ,SELECT_FIELDS, "from" ,TABLE_NAME,"where id=#{id}"})
    Article getById(int id);

    //更新某篇文章的comment_Count
    @Update({"update",TABLE_NAME,"set comment_count = #{commentCount} where id = #{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

}
