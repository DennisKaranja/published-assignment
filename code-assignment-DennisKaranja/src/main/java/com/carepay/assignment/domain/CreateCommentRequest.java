package com.carepay.assignment.domain;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class CreateCommentRequest {
    private Long postId;
    private String comment;
}
