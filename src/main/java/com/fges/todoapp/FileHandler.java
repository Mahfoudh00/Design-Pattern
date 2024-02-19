// FileHandler.java

package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Classe pour gérer les opérations liées aux fichiers
public class FileHandler {

    // Méthode pour lire le contenu d'un fichier
    public static String readFileContent(Path filePath) throws IOException {
        String fileContent = "";
        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }
        return fileContent;
    }

    // Méthode pour écrire le contenu dans un fichier
    public static void writeFileContent(Path filePath, String fileContent) throws IOException {
        Files.writeString(filePath, fileContent);
    }
}
