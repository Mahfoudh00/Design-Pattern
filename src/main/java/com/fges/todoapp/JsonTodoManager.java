// JsonTodoManager.java

package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

// Classe pour gérer les tâches au format JSON
public class JsonTodoManager implements TodoManager {

    private final Path filePath;

    // Constructeur de JsonTodoManager
    public JsonTodoManager(String fileName) {
        this.filePath = Path.of(fileName);
    }

    // Méthode pour insérer une tâche dans le fichier JSON
    @Override
    public void insertTodo(String todo, boolean isDone) throws IOException {
        // Implémentation de l'insertion pour les fichiers JSON
    }

    // Méthode pour lister les tâches du fichier JSON
    @Override
    public void listTodos(boolean showDone) {
        // Implémentation de la liste pour les fichiers JSON
    }

    // Méthode pour migrer les tâches d'un gestionnaire vers un autre
    @Override
    public void migrateFrom(TodoManager sourceManager) throws IOException {
        // Implémentation de la migration pour les fichiers JSON
    }

    // Méthode privée pour lire et parser le contenu JSON
    private JsonNode parseJsonNode(String fileContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(fileContent);
    }
}
