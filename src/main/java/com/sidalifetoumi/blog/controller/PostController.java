package com.sidalifetoumi.blog.controller;


import com.sidalifetoumi.blog.dto.CreatePostDto;
import com.sidalifetoumi.blog.dto.PostResponseDto;
import com.sidalifetoumi.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDto> findAllPosts(){
        return postService.findAllPosts();
    }

    @GetMapping(params = "title")
    public List<PostResponseDto> searchByTitle(@RequestParam String title){
        return postService.searchByTitle(title);
    }

    @GetMapping("/{id}")
    public PostResponseDto findPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }


    @PostMapping
    public PostResponseDto createPost(@Valid @RequestBody CreatePostDto createPostDto){
        return postService.createPost(createPostDto);
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,@RequestBody CreatePostDto createPostDto){
        return postService.updatePost(id,createPostDto);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id){
        postService.deletePost(id);
    }





}
