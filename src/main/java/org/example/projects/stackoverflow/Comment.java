package org.example.projects.stackoverflow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {
    private String commentId;
    private User author;
    private String content;
    private LocalDateTime creationDate;
    private List<Vote> voteList;

    public Comment(User author, String content) {
        this.commentId = generateCommentId();
        this.author = author;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.voteList = new ArrayList<>();
    }

    private String generateCommentId() {
        return "COMM-" + UUID.randomUUID().toString().substring(0,4);
    }

    private void addVote(Vote vote){
        this.voteList.add(vote);
    }

    public String getCommentId() {
        return commentId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }
}
