package com.chirag.main.repositiories;

import com.chirag.main.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositry extends JpaRepository<Comment, Integer>
{
}
