package org.example.projects.stackoverflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowSystem {

    private Map<String, Question> questionMap;
    private Map<String, Answer>  answerMap;
    private Map<String, User> userMap;

    public StackOverflowSystem() {
        this.questionMap = new ConcurrentHashMap<>();
        this.answerMap = new ConcurrentHashMap<>();
        this.userMap = new ConcurrentHashMap<>();
    }

    public User createUser(String username, String email){
        User newUser = new User(username, email);
        userMap.put(newUser.getId(), newUser);
        return newUser;
    }

    public Question postQuestion(String title, String content, User user,
                                 List<String> tags){
        Question newQuestion = user.askQuestion(title, content, tags);
        questionMap.put(newQuestion.getQuestionId(), newQuestion);
        return newQuestion;
    }

    public Comment addComment(Commentable target, User user, String content){
        Comment comment = user.addComment(target, content);
        return comment;
    }

    public Answer addAnswer(String content, User user, Question question){
        Answer answer = user.addAnswer(question, content);
        answerMap.put(answer.getAnswerId(), answer);
        return answer;
    }

    public void addVote(Votable target, User voter, VoteType type){
        target.addVote(voter, type);
    }

}
