package com.fges.todoapp.util;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Valideur de chemin pour vérifier si un fichier existe.
 */
public class PathValidator {

    /**
     * Valide si le chemin du fichier existe.
     *
     * @param filePath Le chemin du fichier à valider.
     * @return true si le fichier existe, sinon false.
     */
    public boolean validatePath(Path filePath) {
        return Files.exists(filePath);
    }
}
