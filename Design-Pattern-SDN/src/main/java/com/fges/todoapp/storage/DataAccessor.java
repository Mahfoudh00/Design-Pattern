package com.fges.todoapp.storage;

import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.util.List;

/**
 * Interface pour l'accès aux données, fournissant des méthodes pour insérer et lire des tâches.
 */
public interface DataAccessor {
    /**
     * Insère une tâche dans le système de stockage.
     *
     * @param todo La tâche à insérer.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'insertion de la tâche.
     */
    void insert(Todo todo) throws IOException;

    /**
     * Lit les tâches à partir du système de stockage.
     *
     * @return Une liste de tâches lues depuis le système de stockage.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture des tâches.
     */
    List<Todo> read() throws IOException;
}
