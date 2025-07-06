package org.example.projects.socialappfacebook;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private String id;
    private User author;
    private String content;
    private LocalDateTime commentTime;
    private Post post;

    public Comment(User author, String content, Post post) {
        this.id = "comm-" + UUID.randomUUID().toString().substring(0,6);
        this.author = author;
        this.content = content;
        this.commentTime = LocalDateTime.now();
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
