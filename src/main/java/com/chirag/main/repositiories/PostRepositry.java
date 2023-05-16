package com.chirag.main.repositiories;

import com.chirag.main.entities.Category;
import com.chirag.main.entities.Post;
import com.chirag.main.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepositry extends JpaRepository<Post,Integer>
{

   List<Post> findByUser(User user);
   List<Post> findByCategory(Category category);

   @Query("select p from Post p where p.pTitle like :key")
   List<Post> findBypTitleContaining(@Param("key")String title);

    @Query("select p from Post p where p.pContent like :key")
   List<Post> findBypContentContaining(@Param("key")String content);



}
