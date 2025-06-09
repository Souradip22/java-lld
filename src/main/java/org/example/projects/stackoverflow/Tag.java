package org.example.projects.stackoverflow;

import java.time.LocalDateTime;
import java.util.UUID;

public class Tag {
    private String tagId;
    private String tagName;

    private LocalDateTime creationDate;

    public Tag(String tagName) {
        this.tagId = generateTagId();
        this.tagName = tagName;
        this.creationDate = LocalDateTime.now();
    }

    private String generateTagId() {
        return "TAG-" + UUID.randomUUID().toString().substring(0,4);
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
