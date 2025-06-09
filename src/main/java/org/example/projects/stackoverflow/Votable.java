package org.example.projects.stackoverflow;

import java.util.List;

public interface Votable {
    void addVote(User voter, VoteType type);
    List<Vote> getVotes();
}
