package org.example.projects.stackoverflow;

import java.util.List;

public interface Commentable {
    void addComment(Comment comment);
    List<Comment> getComments();
}
