# L3 design pattern report

- **Firstname**: Mahfoudh
- **Lastname**: Lahmer

---

## TP1

- **Ce que j'ai fait et pourquoi :** J'ai commencé par gérer les options passées en ligne de commande et renvoyer des erreurs en fonction des résultats. J'ai séparé l'analyse des arguments de la gestion des erreurs en utilisant la classe `CommandLineProcessor` et `MyCommandProcessor`. J'ai également ajouté des classes et interfaces pour gérer la validation des arguments et la récupération du contenu du fichier, suivant ainsi les principes SOLID pour améliorer la lisibilité et la maintenabilité.

- **Ce qui m'a aidé et pourquoi :** La recherche de solutions alternatives sur la gestion des commandes m'a permis de découvrir des méthodes plus efficaces, comme l'utilisation d'une table de correspondance pour remplacer les structures conditionnelles.

- **Ce que j'ai trouvé difficile :** J'ai rencontré des difficultés lors de la gestion des fichiers JSON, ce qui a ajouté une complexité supplémentaire à la tâche.

- **Ce qui ne m'a pas aidé :** L'utilisation excessive de méthodes statiques a rendu certaines parties du code moins flexibles et extensibles.

- **Ce que j'ai dû changer :** J'ai dû restructurer certaines parties du code pour mieux respecter les principes SOLID et réduire la complexité globale.

- **Tout ce qui est pertinent :** J'ai décidé de séparer le code final en différents packages pour le rendre plus organisé et plus facile à maintenir.

---

## TP2

- **Ce que j'ai fait et pourquoi :** J'ai ajouté une fonctionnalité permettant de marquer une tâche comme `done`, en introduisant une nouvelle option dans la méthode `execute`. J'ai utilisé un nouveau format pour les fichiers JSON afin de stocker l'état des tâches.

- **Ce qui m'a aidé et pourquoi :** La mise en place d'un diagramme a clarifié le fonctionnement de la nouvelle fonctionnalité et m'a aidé à structurer le code en conséquence.

- **Ce que j'ai trouvé difficile :** Certains tests ont échoué en raison de différences dans le format de sortie, ce qui m'a amené à revoir la manière dont les données étaient manipulées.

- **Ce qui ne m'a pas aidé :** L'absence d'une méthodologie claire pour gérer les tests et les problèmes de format de sortie a compliqué le processus de développement.

- **Ce que j'ai dû changer :** J'ai dû revoir la manière dont les commandes étaient exécutées et le format de sortie pour garantir la compatibilité avec les tests.

---

## TP2 (mise à jour)

- **Ce que j'ai fait et pourquoi :** J'ai revu et modifié plusieurs parties du code pour le rendre plus viable. J'ai déplacé la logique de vérification des fichiers JSON ou CSV des classes `InsertCommand` et `ListCommand` vers une interface `FileHandler`.

- **Ce qui m'a aidé et pourquoi :** La réflexion sur l'amélioration de la structure du code m'a permis de mieux comprendre les concepts de conception et d'organisation du code.

- **Ce que j'ai trouvé difficile :** Je me suis retrouvé confronté à des choix de conception et à des compromis entre la simplicité et la complexité du code.

- **Ce qui ne m'a pas aidé :** Certaines parties du code étaient encore trop complexes et difficiles à maintenir en raison de leur architecture initiale.

- **Ce que j'ai dû changer :** J'ai dû réorganiser la gestion des commandes et revoir l'architecture du code pour améliorer sa flexibilité et sa maintenabilité.

---

## TP3 (Ajout de la commande `migrate`)

- **Ce que j'ai fait et pourquoi :** J'ai ajouté une nouvelle commande `migrate` pour transférer les données d'un fichier A vers un fichier B. J'ai créé une classe `MigrateCommand` pour gérer cette fonctionnalité.

- **Ce qui m'a aidé et pourquoi :** La conception modulaire du code m'a permis d'ajouter facilement de nouvelles fonctionnalités sans affecter les parties existantes du code.

- **Ce que j'ai trouvé difficile :** La gestion des options et des arguments pour la nouvelle commande a nécessité une réflexion approfondie sur la manière de les intégrer de manière cohérente dans l'architecture existante.

- **Ce qui ne m'a pas aidé :** L'absence de documentation claire sur les exigences de la nouvelle fonctionnalité a rendu difficile la définition de ses spécifications et de son comportement attendu.

- **Ce que j'ai dû changer :** J'ai dû modifier la méthode `execute()` pour prendre en compte les nouveaux paramètres de la commande `migrate` et revoir la logique de traitement des arguments.

---

