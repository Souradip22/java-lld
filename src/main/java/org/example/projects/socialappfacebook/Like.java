package org.example.projects.socialappfacebook;

import java.time.LocalDateTime;
import java.util.Objects;

public class Like {
    private User user;
    private LocalDateTime likeTime;

    public Like(User user) {
        this.user = user;
        this.likeTime = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Like)) return false;
        Like like = (Like) obj;
        return Objects.equals(user, like.getUser());
    }
}
