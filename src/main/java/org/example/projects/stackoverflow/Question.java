package org.example.projects.stackoverflow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question implements Commentable, Votable{
    private String questionId;
    private String title;
    private String content;
    private User author;
    private List<Answer> answerList;
    private List<Comment> commentList;
    private List<Tag> tagList;
    private List<Vote> voteList;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private Answer acceptedAnswer;

    public Question(String title, String content, User author,
                    List<String> tags) {
        this.questionId = generateQuestionId();
        this.title = title;
        this.content = content;
        this.author = author;
        this.answerList = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.tagList = new ArrayList<>();
        this.voteList = new ArrayList<>();
        this.creationDate = LocalDateTime.now();

        for (String tagName : tags) {
            this.tagList.add(new Tag(tagName));
        }
    }


    public void showDetails() {
        System.out.println("\n========== QUESTION ==========");
        System.out.println("ID: " + questionId);
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Author: " + author.getUsername());
        System.out.println("Created At: " + creationDate);
        System.out.println("Last Updated: " + lastUpdateDate);
        showVoteDetails();

        showTags();
        showComments();
        showAnswers();
    }

    private void showVoteDetails() {
        long upvotes =
                voteList.stream().filter(v -> v.getVoteType() == VoteType.UPVOTE).count();
        long downvotes = voteList.stream().filter(v -> v.getVoteType() == VoteType.DOWNVOTE).count();

        System.out.println("\n--- Votes ---");
        System.out.println("Upvotes: " + upvotes);
        System.out.println("Downvotes: " + downvotes);
    }


    private void showTags() {
        System.out.println("\n--- Tags ---");
        for (Tag tag : tagList) {
            System.out.println("#" + tag.getTagName());
        }
    }

    private void showComments() {
        System.out.println("\n--- Comments ---");
        for (Comment comment : commentList) {
            System.out.println(comment.getAuthor().getUsername() + ": " + comment.getContent());
        }
    }

    private void showAnswers() {
        System.out.println("\n--- Answers (" + answerList.size() + ") ---");
        for (Answer answer : answerList) {
            System.out.println("\nAnswer ID: " + answer.getAnswerId());
            System.out.println("By: " + answer.getAuthor().getUsername());
            System.out.println("Content: " + answer.getContent());
            if (answer.isAccepted()) {
                System.out.println("✔ Accepted Answer");
            }
            answer.showVoteDetails();


            System.out.println("Comments:");
            for (Comment comment : answer.getComments()) {
                System.out.println("  - " + comment.getAuthor().getUsername() + ": " + comment.getContent());
            }
        }
    }

    @Override
    public List<Vote> getVotes() {
        return this.voteList;
    }

    private String generateQuestionId() {
        return "QUES-" + UUID.randomUUID().toString().substring(0,6);
    }

    @Override
    public void addVote(User voter, VoteType type) {
        this.voteList.add(new Vote(voter, type));
    }


    public void addAnswer(Answer answer){
        this.answerList.add(answer);
    }
    @Override
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }
    @Override
    public List<Comment> getComments() {
        return this.commentList;
    }

    public void addTag(Tag tag){
        this.tagList.add(tag);
    }


    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void markAcceptedAnswer(Answer answer) {
        acceptedAnswer = answer;
        answer.markAsAccepted();
    }
}
