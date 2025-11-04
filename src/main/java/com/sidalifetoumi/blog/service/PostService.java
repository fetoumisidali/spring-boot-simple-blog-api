package com.sidalifetoumi.blog.service;

import com.sidalifetoumi.blog.dto.CreatePostDto;
import com.sidalifetoumi.blog.dto.PostResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto createPost(CreatePostDto createPostDto);
    PostResponseDto findPostById(Long id);
    List<PostResponseDto> findAllPosts();
    List<PostResponseDto> searchByTitle(String title);
    PostResponseDto updatePost(Long id,CreatePostDto createPostDto);
    void deletePost(Long id);
}
