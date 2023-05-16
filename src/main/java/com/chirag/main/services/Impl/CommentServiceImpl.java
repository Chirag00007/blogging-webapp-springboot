package com.chirag.main.services.Impl;

import com.chirag.main.dto.CommentDto;
import com.chirag.main.entities.Comment;
import com.chirag.main.entities.Post;
import com.chirag.main.exceptions.ResourceNotFoundException;
import com.chirag.main.repositiories.CommentRepositry;
import com.chirag.main.repositiories.PostRepositry;
import com.chirag.main.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private PostRepositry postRepositry;
    @Autowired
    private CommentRepositry commentRepositry;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepositry.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Comment comment = this.convertToCommentEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = this.commentRepositry.save(comment);
        return this.convertToCommentDto(savedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {
    Comment comment = this.commentRepositry.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
    this.commentRepositry.delete(comment);
    }

    public CommentDto convertToCommentDto(Comment comment)
    {
        return modelMapper.map(comment, CommentDto.class);

    }
    public Comment convertToCommentEntity(CommentDto commentDto)
    {
        return modelMapper.map(commentDto, Comment.class);
    }
}
