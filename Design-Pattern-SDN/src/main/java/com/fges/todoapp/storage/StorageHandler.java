package com.fges.todoapp.storage;

import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface pour les gestionnaires de stockage, fournissant des méthodes pour écrire et lire des listes de tâches.
 */
public interface StorageHandler {

    /**
     * Écrit une liste de tâches dans le système de stockage.
     *
     * @param todos    La liste de tâches à écrire.
     * @param filePath Le chemin du fichier où écrire les tâches.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'écriture des tâches.
     */
    void write(List<Todo> todos, Path filePath) throws IOException;

    /**
     * Lit les tâches à partir du système de stockage.
     *
     * @param filePath Le chemin du fichier à partir duquel lire les tâches.
     * @return Une liste de tâches lues depuis le système de stockage.
     */
    List<Todo> read(Path filePath);
}
