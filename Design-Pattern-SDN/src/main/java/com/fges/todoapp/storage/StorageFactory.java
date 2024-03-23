package com.fges.todoapp.storage;

import com.fges.todoapp.storage.files.csv.CsvFileHandler;
import com.fges.todoapp.storage.files.json.JsonFileHandler;
import com.fges.todoapp.storage.nonfiles.DatabaseFileHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory pour créer des gestionnaires de stockage en fonction du type de source.
 */
public class StorageFactory {
    private final Map<String, StorageHandler> registry = new HashMap<>();

    /**
     * Initialise les gestionnaires de stockage pris en charge.
     */
    private void initialiseHandlers() {
        // Ajoute les gestionnaires de stockage pour les types de fichiers supportés
        registry.put("csv", new CsvFileHandler());
        registry.put("json", new JsonFileHandler());

        // Ajoute le gestionnaire de stockage pour les bases de données
        registry.put("api", new DatabaseFileHandler());
    }

    /**
     * Constructeur de la factory, initialise les gestionnaires de stockage.
     */
    public StorageFactory() {
        this.initialiseHandlers();
    }

    /**
     * Détecte et renvoie le gestionnaire de stockage approprié en fonction du type de source.
     *
     * @param sourceType Le type de source (peut être un type de fichier ou un type de source de données).
     * @return Le gestionnaire de stockage approprié ou null si aucun gestionnaire correspondant n'est trouvé.
     */
    public StorageHandler detectHandler(String sourceType) {
        String fileExtension = this.getFileExtension(sourceType);
        StorageHandler handler;

        // Recherche du gestionnaire de stockage correspondant
        if (fileExtension == null) {
            handler = this.registry.get(sourceType);
        } else {
            handler = this.registry.get(fileExtension);
        }

        // Vérifie si un gestionnaire de stockage approprié a été trouvé
        if (handler == null) {
            System.err.println("Unsupported data source type: " + sourceType);
            return null;
        }
        return handler;
    }

    /**
     * Obtient l'extension de fichier à partir du nom de fichier.
     *
     * @param filename Le nom de fichier.
     * @return L'extension de fichier ou null si le nom de fichier est null ou s'il n'y a pas d'extension.
     */
    private String getFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return null;
        }
        return filename.substring(lastDotIndex + 1).toLowerCase();
    }
}
