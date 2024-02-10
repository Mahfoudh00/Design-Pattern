# L3 design pattern report

- **Firstname**: [Mahfoudh]
- **Lastname**: [LAHMER]


> Add your thoughts on every TP bellow, everything is interresting but no need to right a book.
> 
> Keep it short simple and efficient:
> 
> - What you did and why
> - What helped you and why
> - What did you find difficult
> - What did not help you
> - What did you need to change
> - Anything relevant
> 
> Add a link to schemas describing your architecture (UML or not but add a legend)
> 
> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

---
...# Rapport sur les Modifications de Code

## Objectif
L'objectif était d'améliorer la lisibilité et la maintenabilité du code en appliquant les principes SOLID.

## Travaux Réalisés
- **Classe `TodoFileManager`:** Ajoutée pour gérer les opérations liées aux fichiers de todos.
- **Restructuration du Code:** Méthodes réorganisées pour un flux logique.

## Ce qui a Aidé
- **Principes SOLID:** Guidés par les principes SOLID pour une conception plus propre.
- **Classe `Options`:** Utilisée pour gérer les options de la ligne de commande.

## Difficultés Rencontrées
- **Gestion des Fichiers JSON:** Complexité accrue lors de la manipulation des fichiers JSON.

## Ce qui n'a pas Aidé
- **Fichiers CSV:** Manipulation directe de chaînes de caractères peut être améliorée.

## Changements Apportés
- **Variable `fileContent`:** Retiré le mot-clé `final` pour permettre les mises à jour.

## Architecture


## Conclusion
Ces modifications visent à rendre le code plus lisible et à faciliter la gestion des opérations liées aux fichiers. Des ajustements ont été faits pour mieux respecter les principes SOLID.


# Rapport sur les Modifications de Code

## Objectif
L'objectif de cette itération était d'ajouter la fonctionnalité permettant de marquer une tâche comme terminée, ainsi que de filtrer l'affichage des tâches en fonction de leur statut.

## Travaux Réalisés
- **Ajout du Paramètre `--done`:** Un nouveau paramètre en ligne de commande, `-d` ou `--done`, a été introduit pour indiquer si une tâche est terminée lors de son ajout.
- **Affichage des Tâches Terminées:** Lors de l'affichage de toutes les tâches (`list`), celles marquées comme terminées sont préfixées par "Done: ".
- **Filtrage par Statut:** L'utilisation du paramètre `--done` lors de la commande `list` permet de n'afficher que les tâches terminées.

## Ce qui a Aidé
- **Clarté du Paramètre `--done`:** L'introduction d'un paramètre distinct pour indiquer le statut de la tâche a simplifié la logique.
- **Principes SOLID:** Les principes SOLID ont continué à guider le processus d'amélioration du code.

## Difficultés Rencontrées
- **Gestion de l'État des Tâches:** La gestion de l'état des tâches a ajouté une complexité supplémentaire, notamment dans la logique d'insertion.

## Ce qui n'a pas Aidé
- **Réutilisation de Code:** La logique d'insertion aurait pu être réutilisée pour les fichiers JSON et CSV, mais elle nécessitait des ajustements supplémentaires pour prendre en compte le nouveau paramètre.

## Changements Apportés
- **Méthode `handleInsertCommand`:** Ajustée pour prendre en compte le paramètre `--done`.
- **Méthode `listTodos`:** Modifiée pour afficher le préfixe "Done: " devant les tâches terminées.

## Commentaires dans le Code
Le code a été commenté pour expliquer les modifications apportées, en particulier dans les méthodes `handleInsertCommand` et `listTodos`.

## Architecture
<!-- Insérez le lien vers le diagramme UML ici -->

## Conclusion
Cette itération a ajouté une fonctionnalité importante au programme, permettant de marquer les tâches comme terminées et de les afficher séparément si nécessaire. La clarté du paramètre `--done` et les principes SOLID ont été maintenus tout au long du processus d'amélioration du code.



# Rapport sur les Modifications de Code

## Objectif
L'objectif principal des modifications était d'ajouter la fonctionnalité de marquer une tâche comme terminée et de filtrer les tâches effectuées lors de l'affichage. De plus, des ajustements ont été apportés pour respecter les bonnes pratiques de conception Java.

## Travaux Réalisés
- **Ajout de la fonctionnalité `--done`:** Un nouveau paramètre CLI a été ajouté pour indiquer si une tâche est terminée lors de l'ajout.
- **Filtrage lors de l'affichage:** Lors de l'affichage des tâches, l'option `--done` permet de montrer uniquement les tâches effectuées.
- **Restructuration de la classe `TodoFileManager`:** Des modifications ont été apportées pour améliorer la lisibilité et la maintenabilité, tout en respectant les principes SOLID.

## Ce qui a Aidé
- **Options de la ligne de commande:** L'utilisation de la bibliothèque Apache Commons CLI a facilité la gestion des arguments de la ligne de commande.
- **Principes SOLID:** Les principes SOLID ont guidé la restructuration de la classe `TodoFileManager`, contribuant à une conception plus propre.

## Difficultés Rencontrées
- **Gestion des fichiers JSON:** Bien que la gestion des fichiers JSON ait été étendue pour inclure l'état des tâches, elle a ajouté une complexité supplémentaire.

## Ce qui n'a pas Aidé
- **Utilisation de méthodes statiques:** L'utilisation excessive de méthodes statiques a été conservée dans certaines parties du code, ce qui peut nuire à la flexibilité et à l'extensibilité.

## Changements Apportés
- **Classe `TodoFileManager`:** Des améliorations ont été apportées pour mieux respecter les principes SOLID, mais des opportunités subsistent pour réduire davantage la complexité.

## Architecture

## Conclusion
Ces modifications visent à étendre les fonctionnalités de l'application de gestion de tâches en permettant le marquage des tâches comme terminées et en fournissant un filtrage basé sur l'état des tâches. Des efforts ont été faits pour améliorer la qualité du code en respectant les principes de conception Java.

