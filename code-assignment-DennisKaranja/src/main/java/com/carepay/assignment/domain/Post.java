package com.carepay.assignment.domain;

import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;


@Data
@ToString
@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "post", orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
