// com.fges.todoapp.files.FileReader
package com.fges.todoapp.storage.files;

import com.fges.todoapp.util.PathValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Classe utilitaire pour la lecture du contenu d'un fichier.
 */
public class FileReader {

    /**
     * Lit le contenu d'un fichier.
     *
     * @param filePath      Le chemin du fichier à lire.
     * @param pathValidator Le validateur de chemin pour valider le chemin du fichier.
     * @return Le contenu du fichier sous forme de chaîne de caractères, ou une chaîne vide si le fichier n'existe pas ou ne peut pas être lu.
     */
    public static String readFileContent(Path filePath, PathValidator pathValidator) {
        try {
            // Vérifie si le chemin du fichier est valide
            if (pathValidator.validatePath(filePath)) {
                // Lecture du contenu du fichier et le retourne
                return Files.readString(filePath);
            } else {
                // Le chemin du fichier n'est pas valide, retourne une chaîne vide
                return "";
            }
        } catch (IOException ex) {
            // Gestion des exceptions en cas d'erreur lors de la lecture du fichier
            System.err.println("Impossible de lire le fichier: " + ex.getMessage());
            return "";
        }
    }
}
