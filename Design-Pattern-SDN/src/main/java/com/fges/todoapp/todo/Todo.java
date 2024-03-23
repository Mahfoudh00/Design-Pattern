package com.fges.todoapp.todo;

/**
 * Représente une tâche avec un nom et un état.
 */
public class Todo {
    private String name;
    private TaskState taskState;

    /**
     * Constructeur par défaut.
     */
    public Todo() {}

    /**
     * Constructeur avec nom et état de la tâche.
     *
     * @param name      Le nom de la tâche.
     * @param taskState L'état de la tâche.
     */
    public Todo(String name, TaskState taskState) {
        this.name = name;
        this.taskState = taskState;
    }

    /**
     * Constructeur avec nom et statut de la tâche.
     *
     * @param name   Le nom de la tâche.
     * @param status Le statut de la tâche (en tant que chaîne).
     */
    public Todo(String name, String status) {
        this.name = name;
        this.taskState = (status.equals("DONE")) ? TaskState.DONE : TaskState.NOT_DONE;
    }

    /**
     * Récupère le nom de la tâche.
     *
     * @return Le nom de la tâche.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la tâche.
     *
     * @param name Le nom de la tâche à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère l'état de la tâche.
     *
     * @return L'état de la tâche.
     */
    public TaskState getTaskState() {
        return taskState;
    }

    /**
     * Définit l'état de la tâche.
     *
     * @param taskState L'état de la tâche à définir.
     */
    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    /**
     * Récupère le statut de la tâche en tant que chaîne.
     *
     * @return Le statut de la tâche ("DONE" ou "NOT_DONE").
     */
    public String getStatus() {
        return (this.taskState == TaskState.DONE) ? "DONE" : "NOT_DONE";
    }
}
