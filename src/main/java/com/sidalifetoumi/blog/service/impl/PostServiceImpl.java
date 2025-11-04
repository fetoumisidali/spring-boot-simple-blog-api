package com.sidalifetoumi.blog.service.impl;

import com.sidalifetoumi.blog.dto.CreatePostDto;
import com.sidalifetoumi.blog.dto.PostResponseDto;
import com.sidalifetoumi.blog.entity.Post;
import com.sidalifetoumi.blog.exception.PostNotFoundException;
import com.sidalifetoumi.blog.mapper.PostMapper;
import com.sidalifetoumi.blog.repository.PostRepository;
import com.sidalifetoumi.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public PostResponseDto createPost(CreatePostDto createPostDto) {
        Post post = postRepository.save(postMapper.toEntity(createPostDto));
        return postMapper.toResponse(post);
    }


    @Override
    public PostResponseDto findPostById(Long id) {
        return postMapper.toResponse(findPostEntityById(id));
    }

    @Override
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toResponse)
                .toList();
    }

    @Override
    public List<PostResponseDto> searchByTitle(String title) {
        return postRepository.searchByTitle(title).stream().
                map(postMapper::toResponse).toList();
    }

    @Override
    public PostResponseDto updatePost(Long id, CreatePostDto createPostDto) {
        Post post = findPostEntityById(id);

        String title = createPostDto.getTitle();
        String content = createPostDto.getContent();

        if(title != null && !title.isBlank()){
            post.setTitle(title);
        }
        if(content != null && !content.isBlank()){
            post.setContent(content);
        }

        return postMapper.toResponse(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(findPostEntityById(id));
    }

    private Post findPostEntityById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}
