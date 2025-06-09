package org.example.projects.stackoverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String email;
    private long reputation;

    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public User(String username, String email) {
        this.id = generateUserId();
        this.username = username;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Question askQuestion(String title, String content,
                                List<String> tags){
        Question question = new Question(title, content, this, tags);
        this.questions.add(question);
        this.updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Comment addComment(Commentable target, String content){
        Comment comment = new Comment(this, content);
        target.addComment(comment);
        this.comments.add(comment);
        this.updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public Answer addAnswer(Question question, String content){
        Answer answer = new Answer(content, this, question);
        question.addAnswer(answer);
        this.answers.add(answer);
        this.updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    private void updateReputation(int value){
        this.reputation += value;
    }

    private String generateUserId() {
        return "USER-" +UUID.randomUUID().toString().substring(0,6);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }
}
