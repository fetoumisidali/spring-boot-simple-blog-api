package com.sidalifetoumi.blog.mapper;


import com.sidalifetoumi.blog.dto.CreatePostDto;
import com.sidalifetoumi.blog.dto.PostResponseDto;
import com.sidalifetoumi.blog.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(CreatePostDto createPostDto){
        Post post = new Post();
        post.setTitle(createPostDto.getTitle());
        post.setContent(createPostDto.getContent());
        return post;
    }

    public PostResponseDto toResponse(Post post){
        return PostResponseDto.builder().
                id(post.getId()).
                title(post.getTitle()).
                content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
