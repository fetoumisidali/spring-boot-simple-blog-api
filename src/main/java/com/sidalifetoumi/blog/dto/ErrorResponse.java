package com.sidalifetoumi.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Object message; // Can be String or List<String>
    private String path;


    public static ErrorResponse of(int status, String error, String message, String path) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status);
        response.setError(error);
        response.setMessage(message);
        response.setPath(cleanPath(path));
        return response;
    }

    public static ErrorResponse of(int status, String error, Map<String, String> messages, String path) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status);
        response.setError(error);
        response.setMessage(messages);
        response.setPath(cleanPath(path));
        return response;
    }

    // Helper method to clean the path
    private static String cleanPath(String path) {
        return path.replace("uri=", "");
    }

}
