package com.fges.todoapp.commands;

import java.io.IOException;
import java.util.List;

/**
 * Interface représentant une commande dans l'application.
 * Chaque commande implémentant cette interface doit définir sa propre logique
 * d'exécution, potentiellement en utilisant des arguments positionnels.
 */
public interface Command {

    /**
     * Exécute la commande avec les arguments positionnels fournis.
     *
     * @param positionalArgs Une liste de chaînes de caractères représentant les arguments
     *                       positionnels passés à la commande. Ces arguments peuvent être
     *                       utilisés pour influencer le comportement de la commande.
     * @return Un code d'état indiquant le résultat de l'exécution de la commande. Typiquement,
     *         un code de 0 indique un succès, tandis qu'un code non nul indique une erreur.
     * @throws IOException Si une erreur d'entrée/sortie se produit pendant l'exécution de la commande.
     *                     Cela peut être dû à des opérations de lecture/écriture sur le système de fichiers,
     *                     des communications réseau, etc.
     */
    int execute(List<String> positionalArgs) throws IOException;
}
