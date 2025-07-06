package org.example.projects.socialappfacebook;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post {
    private String id;
    private String content;
    private User author;
    private List<Comment> comments;
    private Set<Like> likes;
    private LocalDateTime creationDate;

    public Post(String content, User author) {
        this.id = "post-" + UUID.randomUUID().toString().substring(0,5);
        this.content = content;
        this.author = author;
        this.comments = new CopyOnWriteArrayList<>();
        this.likes = new HashSet<>();
        this.creationDate = LocalDateTime.now();
    }

    public void addComment(Comment comm){
        this.comments.add(comm);
    }

    public void addLike(Like like){
        this.likes.add(like);
    }
    public int getLikeCount() {
        return this.likes.size();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }


}
