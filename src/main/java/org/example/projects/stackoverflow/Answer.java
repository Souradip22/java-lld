package org.example.projects.stackoverflow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer implements Commentable, Votable{
    private String answerId;
    private String content;
    private User author;
    private LocalDateTime creationDate;
    private Question question;
    private List<Vote> voteList;
    private List<Comment> commentList;
    private boolean acceptedAnswer;

    public Answer(String content, User author, Question question) {
        this.answerId = generateAnswerId();
        this.content = content;
        this.author = author;
        this.creationDate = LocalDateTime.now();
        this.question = question;
        this.voteList = new ArrayList<>();
        this.commentList = new ArrayList<>();
    }

    private String generateAnswerId() {
        return "ANS-" + UUID.randomUUID().toString().substring(0,4);
    }

    @Override
    public void addVote(User voter, VoteType type) {
        this.voteList.add(new Vote(voter, type));
    }

    @Override
    public List<Vote> getVotes() {
        return this.voteList;
    }

    @Override
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return this.commentList;
    }

    public String getAnswerId() {
        return answerId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void showVoteDetails() {
        long upvotes =
                voteList.stream().filter(v -> v.getVoteType() == VoteType.UPVOTE).count();
        long downvotes = voteList.stream().filter(v -> v.getVoteType() == VoteType.DOWNVOTE).count();

        System.out.println("\n--- Votes ---");
        System.out.println("Upvotes: " + upvotes);
        System.out.println("Downvotes: " + downvotes);
    }

    public void markAsAccepted() {
        this.acceptedAnswer = true;
    }

    public boolean isAccepted() {
        return this.acceptedAnswer;
    }
}
