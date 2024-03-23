package com.fges.todoapp.storage.files;

import com.fges.todoapp.storage.DataAccessor;
import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe d'implémentation de l'interface DataAccessor pour l'accès aux données via des fichiers.
 * Cette classe utilise un StorageHandler pour effectuer les opérations d'écriture et de lecture des tâches dans un fichier.
 */
public class FileDataAccessor implements DataAccessor {
    private final StorageHandler fileHandler; // Gestionnaire de stockage
    private final Path filePath; // Chemin du fichier

    /**
     * Constructeur de la classe FileDataAccessor.
     *
     * @param fileHandler Le gestionnaire de stockage à utiliser.
     * @param filePath    Le chemin du fichier à accéder.
     */
    public FileDataAccessor(StorageHandler fileHandler, Path filePath) {
        this.fileHandler = fileHandler;
        this.filePath = filePath;
    }

    /**
     * Insère une tâche dans le fichier.
     *
     * @param todo La tâche à insérer.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'insertion de la tâche.
     */
    @Override
    public void insert(Todo todo) throws IOException {
        List<Todo> todos = new ArrayList<>(Collections.singletonList(todo));
        this.fileHandler.write(todos, this.filePath);
    }

    /**
     * Lit les tâches à partir du fichier.
     *
     * @return Une liste de tâches lues depuis le fichier.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     */
    @Override
    public List<Todo> read() throws IOException {
        return this.fileHandler.read(this.filePath);
    }
}
