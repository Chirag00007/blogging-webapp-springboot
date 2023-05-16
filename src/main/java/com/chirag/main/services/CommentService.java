package com.chirag.main.services;

import com.chirag.main.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto , Integer postId);
    void deleteComment(Integer commentId);

}
