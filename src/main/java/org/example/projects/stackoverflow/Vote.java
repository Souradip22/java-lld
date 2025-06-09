package org.example.projects.stackoverflow;

public class Vote {
    private User voter;
    private VoteType voteType;

    public Vote(User voter, VoteType voteType) {
        this.voter = voter;
        this.voteType = voteType;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
}
