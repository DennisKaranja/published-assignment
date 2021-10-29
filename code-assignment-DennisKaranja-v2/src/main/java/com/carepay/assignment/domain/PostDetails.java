package com.carepay.assignment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=false)
@Data
@ToString
public class PostDetails extends PostInfo {
    private String content;
}
