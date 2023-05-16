package com.chirag.main.services;

import com.chirag.main.dto.PostDto;
import com.chirag.main.entities.Post;
import com.chirag.main.helpers.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPosts();

    PostResponse getAllPostsByPage(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByUserId(Integer userId);

    List<PostDto> getPostByCategoryId(Integer categoryId);

    List<PostDto> searchPost(String keyword);

    List<PostDto> searchPostByContent(String keyword);
}
