package com.fges.todoapp.util;

import org.apache.commons.cli.CommandLine;

/**
 * Interface pour la validation des arguments de la ligne de commande.
 */
public interface ArgumentValidator {

    /**
     * Valide les arguments de la ligne de commande.
     *
     * @param cmd               La ligne de commande analysée.
     * @param numberOfArguments Le nombre d'arguments requis.
     * @return true si les arguments sont valides, false sinon.
     */
    boolean validateArguments(CommandLine cmd, int numberOfArguments);
}
