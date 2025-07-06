package org.example.projects.socialappfacebook;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FacebookSystem {
    public static FacebookSystem instance;
    private final Map<String, User> userMap;
    private final Map<String, Post> postMap;

    private FacebookSystem(){
        userMap = new ConcurrentHashMap<>();
        postMap = new ConcurrentHashMap<>();
    }
    public static FacebookSystem getInstance(){
        if (instance == null){
            instance = new FacebookSystem();
        }
        return instance;
    }

    public User registerUser(String name, String email, String password){
        User newUser = new User(name, email, password);
        userMap.putIfAbsent(newUser.getId(), newUser);
        return newUser;
    }

    public User loginUser(String email, String password){
        for (User user: userMap.values()){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public Post createPost(String userId, String content){
        if (userMap.containsKey(userId)) {
            User author = userMap.get(userId);
            Post newPost = new Post(content, author);
            author.addPost(newPost);
            postMap.putIfAbsent(newPost.getId(), newPost);

            return newPost;
        }
        throw new RuntimeException("Failed to create post, user id is not " +
                "valid: " + userId);
    }

    public void addComment(String userId, String content, String postId) {
        if (!userMap.containsKey(userId)) {
            throw new RuntimeException("Failed to add comment, user id is not" +
                    " valid: " + userId);
        }
        if (!postMap.containsKey(postId)) {
            throw new RuntimeException("Failed to add comment, post id is not" +
                    " valid: " + postId);
        }
        Post post = postMap.get(postId);
        User user = userMap.get(userId);
        Comment newComment = new Comment(user, content, post);
        post.addComment(newComment);
        sendNotification(post.getAuthor(), NotificationType.COMMENTS_ADDED,
                user.getName() + " added comment on your post: " + post.getId());

    }
    public void likePost(String userId, String postId){
        if (!userMap.containsKey(userId)) throw new IllegalArgumentException(
                "Like post failed, user id is not valid: " + userId);
        if (!postMap.containsKey(postId)) throw new IllegalArgumentException(
                "Like post failed, post id is not valid: " + userId);
        Post post = postMap.get(postId);
        User user = userMap.get(userId);
        Like newLike = new Like(user);
        post.addLike(newLike);
        sendNotification(post.getAuthor(), NotificationType.POST_LIKE,
                user.getName() + " liked your post: " + post.getId());

    }

    public void sendFriendRequest(String fromId, String toId){
        if (!userMap.containsKey(fromId) || !userMap.containsKey(toId)){
            throw new IllegalArgumentException("Failed to send friend " +
                    "request, user id is not valid");
        }
        User fromUser = userMap.get(fromId);
        User toUser = userMap.get(toId);

        toUser.addFriendRequest(new FriendRequest(fromUser, toUser));
        sendNotification(toUser, NotificationType.FRIEND_REQUEST_RECEIVED,
                "New friend request from: " + fromUser.getName());
    }

    public void acceptFriendRequest(String toId, String fromId){
        if (!userMap.containsKey(fromId) || !userMap.containsKey(toId)){
            throw new IllegalArgumentException("Failed to accept friend " +
                    "request, user id is not valid");
        }
        User toUser = userMap.get(toId); // toUser has to accept the request
        User fromUser = userMap.get(fromId);

        Optional<FriendRequest> friendRequest = toUser.getFriendRequests()
                .stream()
                .filter((FriendRequest fq) -> fq.getFrom().getId().equals(fromId))
                .findFirst();

        if (friendRequest.isEmpty() ){
            throw new RuntimeException("There is no fried request pending " +
                    "from user: " + fromId + ", to user: " + toId);
        }
        friendRequest.get().acceptRequest();
        toUser.addFriend(fromUser);
        fromUser.addFriend(toUser);
        sendNotification(fromUser, NotificationType.FRIEND_REQUEST_ACCEPTED,
                "You are now friends with: " + toUser.getName());
    }

    public void declineFriendRequest(String toId, String fromId){
        if (!userMap.containsKey(fromId) || !userMap.containsKey(toId)){
            throw new IllegalArgumentException("Failed to accept friend " +
                    "request, user id is not valid");
        }
        User toUser = userMap.get(toId); // toUser has to decline the request
        User fromUser = userMap.get(fromId);

        Optional<FriendRequest> friendRequest = toUser.getFriendRequests()
                .stream()
                .filter((FriendRequest fq) -> fq.getFrom().getId().equals(fromId))
                .findFirst();

        if (friendRequest.isEmpty() ){
            throw new RuntimeException("There is no fried request pending " +
                    "from user: " + fromId + ", to user: " + toId);
        }
        friendRequest.get().declineRequest();
//        toUser.addFriend(fromUser);
//        fromUser.addFriend(toUser);
        sendNotification(fromUser, NotificationType.FRIEND_REQUEST_DECLINED,
                "Your friend request has been declined with user: " + toUser.getName());
    }

    public List<Post> getNewsFeed(String userId){
        if (!userMap.containsKey(userId)){
            throw new IllegalArgumentException("Failed to get news feed, user" +
                    "id is not valid: " + userId);
        }

        return userMap.get(userId).getFriends()
                .stream()
                .flatMap(friend -> friend.getPostMap().values().stream())
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(10)
                .toList();
    }


    private void sendNotification(User user,
                                  NotificationType notificationType,
                                  String content) {
        Notification notif = new Notification(user, notificationType,
                content);
        user.addNotification(notif);
    }

    public List<Notification> getNotifications(String userId){
        if (!userMap.containsKey(userId)){
            throw new IllegalArgumentException("Failed to get news feed, user" +
                    "id is not valid: " + userId);
        }
        return userMap.get(userId).getNotifications();
    }

}
