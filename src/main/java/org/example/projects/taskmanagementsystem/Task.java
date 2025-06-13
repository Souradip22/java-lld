package org.example.projects.taskmanagementsystem;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private User createdBy;
    private User assignedTo;
    private TaskPriority priority;
    private TaskStatus status;

    public Task(String title, String description, LocalDate dueDate, User createdBy, TaskPriority priority) {
        this.id = generateTaskId();
        this.title = title;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.dueDate = dueDate;
        this.createdBy = createdBy;
        this.assignedTo = null;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    private String generateTaskId() {
        return "TASK-" + UUID.randomUUID().toString().substring(0, 6);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task {\n" +
                "  id='" + id + "',\n" +
                "  title='" + title + "',\n" +
                "  description='" + description + "',\n" +
                "  creationDate=" + creationDate + ",\n" +
                "  dueDate=" + dueDate + ",\n" +
                "  createdBy='" + (createdBy != null ? createdBy.getName() : "N/A") + "',\n" +
                "  assignedTo='" + (assignedTo != null ? assignedTo.getName() : "N/A") + "',\n" +
                "  priority=" + priority + ",\n" +
                "  status=" + status + "\n" +
                '}';
    }
}
