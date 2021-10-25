package com.carepay.assignment.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreatePostRequest {
    private String title;
    private String content;

}
