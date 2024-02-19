// TodoManager.java

package com.fges.todoapp;

import java.io.IOException;

// Interface pour gérer les tâches
public interface TodoManager {

    // Méthode pour insérer une tâche
    void insertTodo(String todo, boolean isDone) throws IOException;

    // Méthode pour lister les tâches
    void listTodos(boolean showDone);

    // Méthode pour migrer les tâches d'un gestionnaire vers un autre
    void migrateFrom(TodoManager sourceManager) throws IOException;
}
