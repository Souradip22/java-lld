package org.example.projects.socialappfacebook;

import java.util.List;

public class FacebookDemo {
    public static void main(String[] args) {
        FacebookSystem facebookSystem = FacebookSystem.getInstance();

        // User registration
        User john = facebookSystem.registerUser("John Doe", "john@example.com", "password1");
        User jane = facebookSystem.registerUser("Jane Smith", "jane@example.com", "password2");
        User alice = facebookSystem.registerUser("Alice Brown", "alice@example.com", "password3");
        User bob = facebookSystem.registerUser("Bob Gray", "bob@example.com", "password4");

        // User login
        User loggedInUser = facebookSystem.loginUser("john@example.com", "password1");
        if (loggedInUser != null) {
            System.out.println("User logged in: " + loggedInUser.getName());
        }

        // Friend requests & acceptances
        facebookSystem.sendFriendRequest(john.getId(), jane.getId());
        facebookSystem.acceptFriendRequest(jane.getId(), john.getId());

        facebookSystem.sendFriendRequest(john.getId(), alice.getId());
        facebookSystem.declineFriendRequest(alice.getId(), john.getId());

        facebookSystem.sendFriendRequest(bob.getId(), john.getId());
        facebookSystem.acceptFriendRequest(john.getId(), bob.getId());

        // Posts
        Post johnPost = facebookSystem.createPost(john.getId(), "John's first post!");
        Post janePost = facebookSystem.createPost(jane.getId(), "Jane's trip to Bali");
        Post alicePost = facebookSystem.createPost(alice.getId(), "Alice's book recommendations");
        Post bobPost = facebookSystem.createPost(bob.getId(), "Bob's coding tips");

        // Likes
        facebookSystem.likePost(jane.getId(), johnPost.getId());
        facebookSystem.likePost(alice.getId(), johnPost.getId());
        facebookSystem.likePost(john.getId(), janePost.getId());
        facebookSystem.likePost(bob.getId(), alicePost.getId());

        // Comments
        facebookSystem.addComment(jane.getId(), "Awesome!", johnPost.getId());
        facebookSystem.addComment(alice.getId(), "Loved this!", johnPost.getId());
        facebookSystem.addComment(john.getId(), "Thanks!", janePost.getId());
        facebookSystem.addComment(bob.getId(), "Great insights", alicePost.getId());

        // Get John's newsfeed
        List<Post> newsfeed = facebookSystem.getNewsFeed(john.getId());
        System.out.println("Newsfeed for " + john.getName() + ":");
        for (Post post : newsfeed) {
            System.out.println("Post: " + post.getContent());
            System.out.println("Likes: " + post.getLikeCount());
            System.out.println("Comments: " + post.getComments().size());
            System.out.println();
        }

        // Get John's notifications
        List<Notification> notifications = facebookSystem.getNotifications(john.getId());
        System.out.println("Notifications for " + john.getName() + ":");
        for (Notification notification : notifications) {
            System.out.println("Type: " + notification.getType());
            System.out.println("Content: " + notification.getContent());
        }

    }
}
