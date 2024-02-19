// CsvTodoManager.java

package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;

// Classe pour gérer les tâches au format CSV
public class CsvTodoManager implements TodoManager {

    private final Path filePath;

    // Constructeur de CsvTodoManager
    public CsvTodoManager(String fileName) {
        this.filePath = Path.of(fileName);
    }

    // Méthode pour insérer une tâche dans le fichier CSV
    @Override
    public void insertTodo(String todo, boolean isDone) throws IOException {
        // Implémentation de l'insertion pour les fichiers CSV
    }

    // Méthode pour lister les tâches du fichier CSV
    @Override
    public void listTodos(boolean showDone) {
        // Implémentation de la liste pour les fichiers CSV
    }

    // Méthode pour migrer les tâches d'un gestionnaire vers un autre (non implémentée pour le CSV)
    @Override
    public void migrateFrom(TodoManager sourceManager) throws IOException {
        throw new UnsupportedOperationException("Migration non prise en charge pour CSV");
    }
}
