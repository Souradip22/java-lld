package org.example.projects.stackoverflow;

import java.util.List;

public class StackOverflowDemo {

    public static void main(String[] args) {
        StackOverflowSystem system = new StackOverflowSystem();

        User user1 = system.createUser("Anil", "anil01@gmail.com");
        User user2 = system.createUser("Prakash", "prakash01@gmail.com");
        User user3 = system.createUser("Sourav", "sourav@gmail.com");
        User user4 = system.createUser("Ravi", "ravi@gmail.com");
        User user5 = system.createUser("Sushant", "sushant@gmail.com");

        // User1 posts a question
        Question question1 = system.postQuestion(
                "How to reverse a string in Java?",
                "I want to reverse a string using built-in or manual methods.",
                user1,
                List.of("java", "string", "interview")
        );

        // User2 adds a comment to the question
        system.addComment(question1, user2, "Have you tried using StringBuilder?");
        system.addComment(question1, user5, "This is really very interesting " +
                "question");

        // User3 answers the question
        Answer answer1 = system.addAnswer(
                "You can use new StringBuilder(str).reverse().toString()",
                user3,
                question1
        );
        Answer answer2 = system.addAnswer(
                "Try using Brute force approach",
                user2,
                question1
        );

        // User4 adds a comment to the answer
        system.addComment(answer1, user4, "Nice and concise answer!");

        question1.markAcceptedAnswer(answer1);

        // Voting
        system.addVote(question1, user2, VoteType.UPVOTE);
        system.addVote(answer1, user1, VoteType.UPVOTE);
        system.addVote(answer1, user4, VoteType.UPVOTE);
        system.addVote(question1, user3, VoteType.DOWNVOTE);

        // Show question with all details
        question1.showDetails();


        System.out.println("------------------- SCENARIO 2 " +
                "-------------------");


        // New Users
        User userA = system.createUser("Priya", "priya@gmail.com");
        User userB = system.createUser("Deepak", "deepak@gmail.com");
        User userC = system.createUser("Karan", "karan@gmail.com");
        User userD = system.createUser("Meera", "meera@gmail.com");

        // UserA posts a new question
        Question question2 = system.postQuestion(
                "What is the difference between HashMap and TreeMap?",
                "I am confused between when to use HashMap and TreeMap in Java.",
                userA,
                List.of("java", "collections", "map")
        );

        // Comments on question
        system.addComment(question2, userB, "TreeMap keeps keys sorted, HashMap doesn't.");
        system.addComment(question2, userC, "TreeMap is slower but ordered.");

        // Answers
        Answer answer3 = system.addAnswer(
                "HashMap is faster, but TreeMap maintains natural ordering.",
                userB,
                question2
        );

        Answer answer4 = system.addAnswer(
                "Use HashMap when order doesn't matter. Use TreeMap if you need sorted keys.",
                userC,
                question2
        );

        // Comments on answers
        system.addComment(answer3, userD, "Good to know about performance.");
        system.addComment(answer4, userA, "Thanks, that makes it clear!");

        // Accept answer
        question2.markAcceptedAnswer(answer4);

        // Voting
        system.addVote(question2, userD, VoteType.UPVOTE);
        system.addVote(question2, userB, VoteType.UPVOTE);
        system.addVote(answer3, userA, VoteType.UPVOTE);
        system.addVote(answer4, userD, VoteType.UPVOTE);
        system.addVote(answer4, userB, VoteType.UPVOTE);

        question2.showDetails();

    }
}
