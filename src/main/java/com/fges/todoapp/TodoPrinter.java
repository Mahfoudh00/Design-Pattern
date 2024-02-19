// TodoPrinter.java

package com.fges.todoapp;

// Classe pour imprimer les tâches
public class TodoPrinter {

    // Méthode pour imprimer une tâche
    public static void printTodo(Todo todo) {
        String status = todo.isDone() ? "[Done]" : "";
        System.out.println(status + " " + todo.getTask());
    }
}
