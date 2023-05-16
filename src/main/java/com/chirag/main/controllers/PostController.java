package com.chirag.main.controllers;

import com.chirag.main.dto.PostDto;
import com.chirag.main.dto.UserDto;
import com.chirag.main.entities.Post;
import com.chirag.main.helpers.ApiResponse;
import com.chirag.main.helpers.PostResponse;
import com.chirag.main.repositiories.PostRepositry;
import com.chirag.main.services.FileService;
import com.chirag.main.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.images}")
    private String path;

    @PostMapping("user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable(value = "userId") Integer userId,
                                              @PathVariable(value = "categoryId") Integer categoryId) {
        PostDto postResponse = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateUser(@RequestBody PostDto postDto, @PathVariable Integer id) {
        return new ResponseEntity<>(this.postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Integer id) {
        this.postService.deletePost(id);
        return new ApiResponse(true, "Post deleted successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllUsers() {
        return new ResponseEntity<>(this.postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.postService.getPostById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts/")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(this.postService.getPostByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts/")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(this.postService.getPostByCategoryId(categoryId), HttpStatus.OK);
    }


    //Pagination
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPostsByPage(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "pId", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder
    ) {
        return new ResponseEntity<>(this.postService.getAllPostsByPage(pageNo, pageSize, sortBy, sortOrder), HttpStatus.OK);
    }

    //Searching
    @GetMapping("/posts/search/title/{pTitle}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String pTitle) {
        return new ResponseEntity<>(this.postService.searchPost(pTitle), HttpStatus.OK);
    }

    @GetMapping("/posts/search/content/{pContent}")
    public ResponseEntity<List<PostDto>> searchByContent(@PathVariable String pContent) {
        return new ResponseEntity<>(this.postService.searchPostByContent(pContent), HttpStatus.OK);
    }

    //File Upload
    @PostMapping("posts/image/upload/{postId}")
    public ResponseEntity<PostDto> upLoadPostImage(@RequestParam("image")MultipartFile image,
                                                         @PathVariable Integer postId) throws IOException {
        String imageUrl = this.fileService.uploadImage(path, image);
      PostDto postDto =  this.postService.getPostById(postId);
      postDto.setPPic(imageUrl);
      PostDto updatePost =  this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @GetMapping(value = "posts/image/{imageName}" ,produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@PathVariable String imageName
                         ,HttpServletResponse response
    ) throws IOException {
        InputStream resourceAsStream = this.fileService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourceAsStream,response.getOutputStream());
    }


    public PostDto convertToPostDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    public Post convertToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

}
