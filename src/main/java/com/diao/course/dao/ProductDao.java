package com.diao.course.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.diao.course.bean.Product;

import java.util.List;

/**
 * 和商品相关的DAO操作
 *
 */
@Repository
public interface ProductDao {
    @Insert("insert into content(title ,icon,abstract,text,price) value" +
            "(#{title},#{image},#{summary},#{detail},#{price})")
    @Options(useGeneratedKeys = true)
    int submit(Product data);

    @Results(value = {
            @Result(column = "abstract", property = "summary"),
            @Result(column = "text", property = "detail", javaType = String.class, jdbcType = JdbcType.BLOB, typeHandler = BlobStringTypeHandler.class),
            @Result(column = "icon", property = "image", javaType = String.class, jdbcType = JdbcType.BLOB, typeHandler = BlobStringTypeHandler.class),
    })
    @Select("SELECT id,abstract,icon,price,text,title ,(SELECT count(*) from trx WHERE trx.contentId=#{id} ) as trxCount,(SELECT trx.price FROM  trx WHERE contentId=#{id} ) as buyPrice FROM content WHERE id = #{id} ")
    Product get(int id);

    @Update("update  content set title=#{title } ,icon=#{image},abstract=#{summary},text=#{detail},price=#{price} where id = #{id}")
    boolean update(Product data);

    @Results(value = {
            @Result(column = "abstract", property = "summary"),
            @Result(column = "text", property = "detail", javaType = String.class, jdbcType = JdbcType.BLOB, typeHandler = BlobStringTypeHandler.class),
            @Result(column = "icon", property = "image", javaType = String.class, jdbcType = JdbcType.BLOB, typeHandler = BlobStringTypeHandler.class)
    })
    @Select("SELECT content.*,(SELECT count(*) FROM trx WHERE content.id = trx.contentId) AS trxCount FROM content")
    List<Product> listProducts();

    @Delete("DELETE FROM content WHERE id = #{id} AND NOT exists(SELECT * FROM trx WHERE contentId = #{id})")
    boolean delete(int id);

    @Select("select count(id) from content")
    int getCount();
}
