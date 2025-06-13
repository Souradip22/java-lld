package org.example.projects.taskmanagementsystem;

import java.time.LocalDate;
import java.util.List;

public class TaskManagerDemo {

    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        User user1 = taskManager.registerUser("John Doe", "john@email.com");
        User user2 = taskManager.registerUser("Bob", "bob@gmail.com");
        User user3 = taskManager.registerUser("Adam", "adam@gmail.com");

        Task task1 = taskManager.createTask("Task 1", "Task 1 description",
                LocalDate.of(2025, 06, 17), user1, TaskPriority.LOW);

        Task task2 = taskManager.createTask("Task 2", "Task 2 description",
                LocalDate.of(2025, 06, 22), user2, TaskPriority.HIGH);

        Task task3 = taskManager.createTask("Task 3", "Task 3 description",
                LocalDate.of(2025, 06, 27), user2, TaskPriority.HIGH);

        taskManager.updateAssign(task1, user2);
        taskManager.updateAssign(task2, user1);
        taskManager.updateAssign(task3, user3);

        taskManager.updateStatus(task3, TaskStatus.COMPLETED);

        List<Task> tasks = taskManager.searchTasks("Task ");
        tasks.forEach((Task task) -> System.out.println(task));
        System.out.println("-------- List tasks by Users ------------");
        List<Task> listTasksByUsers = taskManager.listTasksByUser(user2);
        listTasksByUsers.forEach((Task task) -> System.out.println(task));

        System.out.println("-------- List tasks by Status ------------");
        List<Task> listTasksByStatus =
                taskManager.listTasksByStatus(TaskStatus.COMPLETED);
        listTasksByStatus.forEach((Task task) -> System.out.println(task));

        System.out.println("-------- List tasks by Priority ------------");
        List<Task> listTasksByPriority =
                taskManager.listTaskByPriority(TaskPriority.HIGH);
        listTasksByPriority.forEach((Task task) -> System.out.println(task));

    }







}
