package com.fges.todoapp.storage.nonfiles;

import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de stockage pour la lecture et l'écriture des tâches dans une base de données.
 */
public class DatabaseFileHandler implements StorageHandler {

    /**
     * Lit les données depuis la base de données.
     *
     * @param filePath Le chemin (non utilisé dans ce cas) - juste pour respecter l'interface.
     * @return Une liste vide car la lecture depuis une base de données n'est pas implémentée dans ce cas.
     */
    @Override
    public List<Todo> read(Path filePath) {
        // Logique pour lire les données depuis la base de données
        // Exemple : SELECT * FROM todos;
        return new ArrayList<>(); // Retourne une liste vide par défaut
    }

    /**
     * Écrit les données dans la base de données.
     *
     * @param todos    La liste des tâches à écrire dans la base de données.
     * @param filePath Le chemin (non utilisé dans ce cas) - juste pour respecter l'interface.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'écriture des données dans la base de données.
     */
    @Override
    public void write(List<Todo> todos, Path filePath) throws IOException {
        // Logique pour écrire les données dans la base de données
        // Exemple : INSERT INTO todos (name, status) VALUES (?, ?);
        // Cette méthode ne fait rien dans ce cas car l'écriture dans une base de données n'est pas implémentée
    }
}
