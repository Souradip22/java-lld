package org.example.projects.socialappfacebook;

public class FriendRequest {
    private User from;
    private User to;
    private FriendRequestStatus status;

    public FriendRequest(User from, User to) {
        this.from = from;
        this.to = to;
        this.status = FriendRequestStatus.PENDING;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public FriendRequestStatus getStatus() {
        return status;
    }

    public void setStatus(FriendRequestStatus status) {
        this.status = status;
    }

    public void acceptRequest() {
        this.status = FriendRequestStatus.ACCEPTED;
    }

    public void declineRequest() {
        this.status = FriendRequestStatus.DECLINED;
    }
}
