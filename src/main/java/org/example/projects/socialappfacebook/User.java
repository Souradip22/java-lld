package org.example.projects.socialappfacebook;

import java.util.*;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private Map<String, Post> postMap;
    private Set<User> friends;
    private List<FriendRequest> friendRequests;
    private List<Notification> notifications;

    public User(String name, String email, String password) {
        this.id = "user-" + UUID.randomUUID().toString().substring(0,5);
        this.name = name;
        this.email = email;
        this.password = password;
        this.postMap = new HashMap<>();
        this.friends = new HashSet<>();
        this.friendRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void addNotification(Notification notif) {
        this.notifications.add(notif);
    }
    public void addPost(Post newPost) {
        postMap.putIfAbsent(newPost.getId(), newPost);
    }
    public void addFriendRequest(FriendRequest request){
        friendRequests.add(request);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Post> getPostMap() {
        return postMap;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (! (obj instanceof  User)) return false;
        User user = (User) obj;
        return Objects.equals(this.id, user.id);
    }


}
