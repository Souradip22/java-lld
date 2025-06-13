package org.example.projects.taskmanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskManager {
    private static TaskManager instance;
    private Map<String, User> userMap;
    private Map<String, Task> taskMap;

    private TaskManager() {
        this.userMap = new ConcurrentHashMap<>();
        this.taskMap = new ConcurrentHashMap<>();
    }

    public static TaskManager getInstance(){
        if (instance == null){
            instance = new TaskManager();
        }
        return instance;
    }

    public User registerUser(String name, String email){
        User user = new User(name, email);
        userMap.put(user.getId(), user);
        return user;
    }
    public Task createTask(String title, String description,
                           LocalDate dueDate, User createdBy,
                           TaskPriority priority){
        Task task = new Task(title, description, dueDate, createdBy, priority);
        taskMap.put(task.getId(), task);
        return task;
    }

    public void updateTitle(Task task, String title){
        task.setTitle(title);
    }
    public void updateDescription(Task task, String description){
        task.setDescription(description);
    }
    public void updateDueDate(Task task, LocalDate date){
        task.setDueDate(date);
    }
    public void updateStatus(Task task, TaskStatus status){
        task.setStatus(status);
    }
    public void updatePriority(Task task, TaskPriority priority){
        task.setPriority(priority);
    }
    public void updateAssign(Task task, User assign){
        task.setAssignedTo(assign);
    }
    public List<Task> searchTasks(String keyword){
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
    public List<Task> listTasksByUser(User user){
        return taskMap.values().stream()
                .filter((Task task)-> task.getAssignedTo().equals(user))
                .collect(Collectors.toList());
    }
    public List<Task> listTasksByStatus(TaskStatus status){
        return taskMap.values().stream()
                .filter((Task task)-> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }
    public List<Task> listTaskByPriority(TaskPriority priority){
        return taskMap.values().stream()
                .filter((Task task) -> task.getPriority().equals(priority))
                .collect(Collectors.toList());
    }
    public void deleteTask(Task task){
        taskMap.remove(task);
    }

}
