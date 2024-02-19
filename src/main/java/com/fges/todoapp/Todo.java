// Todo.java

package com.fges.todoapp;

// Classe représentant une tâche
public class Todo {

    private final String task;
    private final boolean isDone;

    // Constructeur de Todo
    public Todo(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    // Getter pour la tâche
    public String getTask() {
        return task;
    }

    // Getter pour l'état de la tâche (terminée ou non)
    public boolean isDone() {
        return isDone;
    }
}
