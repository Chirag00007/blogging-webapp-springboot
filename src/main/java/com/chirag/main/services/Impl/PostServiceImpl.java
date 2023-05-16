package com.chirag.main.services.Impl;

import com.chirag.main.dto.PostDto;
import com.chirag.main.entities.Category;
import com.chirag.main.entities.Post;
import com.chirag.main.entities.User;
import com.chirag.main.exceptions.ResourceNotFoundException;
import com.chirag.main.helpers.PostResponse;
import com.chirag.main.repositiories.CategoyRepositry;
import com.chirag.main.repositiories.PostRepositry;
import com.chirag.main.repositiories.UserRepositry;
import com.chirag.main.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepositry postRepositry;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private CategoyRepositry categoryRepositry;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepositry.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        Post post = convertToPost(postDto);
        post.setPPic("default.png");
        post.setPDate(new java.util.Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepositry.save(post);
        return convertToPostDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepositry.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        post.setPTitle(postDto.getPTitle());
        post.setPContent(postDto.getPContent());
        post.setPDate(new java.util.Date());
        post.setPPic(postDto.getPPic());
        Post updatedPost = postRepositry.save(post);
        return convertToPostDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepositry.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        postRepositry.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepositry.findAll();
        return posts.stream().map(this::convertToPostDto).collect(java.util.stream.Collectors.toList());
    }

    // Pagination
    //getAllPostsByPage is getting all the posts by page
    //Pageable is an interface that represents a page request
    //PageRequest is an implementation of Pageable
    //Page is a sublist of a list of objects
    //PostResponse is a helper class that contains the content, page number, page size, total elements and total pages
    @Override
    public PostResponse getAllPostsByPage(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        //sort by asc or desc
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<PostDto> pagedResult = postRepositry.findAll(paging).map(this::convertToPostDto);
        PostResponse response = new PostResponse();
        response.setContent(pagedResult.getContent());
        response.setPageNumber(pagedResult.getNumber());
        response.setPageSize(pagedResult.getSize());
        response.setTotalElements(pagedResult.getTotalElements());
        response.setTotalPages(pagedResult.getTotalPages());
        response.setLast(pagedResult.isLast());
        return response;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepositry.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        return convertToPostDto(post);
    }

    @Override
    public List<PostDto> getPostByUserId(Integer userId) {
        User user = userRepositry.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        List<Post> posts = postRepositry.findByUser(user);
        return posts.stream().map(this::convertToPostDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategoryId(Integer categoryId) {

        Category cat = this.categoryRepositry.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        List<Post> posts = this.postRepositry.findByCategory(cat);
        return posts.stream().map(this::convertToPostDto).collect(java.util.stream.Collectors.toList());

    }

    //searchPost is searching the post by keyword
    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = postRepositry.findBypTitleContaining("%"+keyword+"%");
        return posts.stream().map(this::convertToPostDto).collect(java.util.stream.Collectors.toList());
    }

    public List<PostDto> searchPostByContent(String keyword) {
        List<Post> posts = postRepositry.findBypContentContaining("%"+keyword+"%");
        return posts.stream().map(this::convertToPostDto).collect(java.util.stream.Collectors.toList());
    }

    public PostDto convertToPostDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    public Post convertToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
