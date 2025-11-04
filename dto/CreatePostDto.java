package com.sidalifetoumi.blog.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostDto {

    @NotBlank(message = "title is required")
    @Size(min = 5,max = 50,message = "title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 10, max = 5000, message = "content must be between 10 and 5000 characters")
    private String content;
}
