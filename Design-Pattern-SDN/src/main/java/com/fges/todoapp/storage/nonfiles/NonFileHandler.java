package com.fges.todoapp.storage.nonfiles;

import com.fges.todoapp.storage.StorageHandler;

/**
 * Interface pour les gestionnaires de stockage qui n'utilisent pas de fichiers.
 * Étend l'interface StorageHandler pour garantir une cohérence dans les méthodes de lecture et d'écriture.
 */
public interface NonFileHandler extends StorageHandler {
    // Cette interface n'ajoute aucune nouvelle méthode par rapport à StorageHandler
    // Elle sert simplement de marqueur pour les gestionnaires de stockage qui n'utilisent pas de fichiers
}
