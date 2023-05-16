package com.chirag.main.controllers;

import com.chirag.main.dto.CommentDto;
import com.chirag.main.entities.Comment;
import com.chirag.main.helpers.ApiResponse;
import com.chirag.main.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/comment/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
        CommentDto commentResponse = this.commentService.createComment(comment, postId);
        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse(true, "Comment deleted successfully"));
    }
}
