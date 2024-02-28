---
titre: Java
sous-titre: Miniprojet 2 - Repérage et direction sur une carte en 2D
auteur: Philippe \textsc{Roussille}
date: 3iL 1A 2023
---

**Date de rendu du projet : 28/03/2024**

# Bienvenue dans votre projet

## Tests unitaires, `git` et Javadoc

- Il vous est demandé de bien *commenter* et de bien réaliser la documentation (au format Javadoc) de vos fichiers.
- Pensez à commiter & pusher votre travail ***de façon régulière***.
- Les messages de vos commits sont essentiels (pas de "lol ça marche", "ENFIN!!!!", "toto", ou autres). Soyez clairs (vous pouvez vous inspirer de [cette norme](https://buzut.net/cours/versioning-avec-git/bien-nommer-ses-commits)).
- N'oubliez pas de réaliser des tests unitaires afin de valider votre code au fur et à mesure de votre production. Essayez d'en réaliser au moins un par partie demandée.

## Structure du rendu

1. Il vous est demandé un rendu *propre*, c'est à dire qui **compile** à minima.
2. Si votre projet **ne compile pas**, la note est automatiquement plafonnée à 10.
3. Votre projet comportera à la racine un fichier `AUTHORS` qui comporte votre nom et votre adresse 3il (du genre `Philippe ROUSSILLE <philr@3il.fr>`).
4. Ce projet est à faire ***individuellement***. Toutefois, je n'ai rien contre l'entraide entre vous (tant que cela ne se résume pas à "pomper le code du voisin"). Si vous avez été aidé par quelqu'un, réalisez un fichier `HELPERS` à la racine de votre projet où vous indiquez celui qui vous a aidé, et de quelle façon (cela constituera un bonus pour cette personne lors de la notation).
5. Enfin, vous réaliserez un fichier `README.md` à la racine du projet dans lequel vous indiquerez, sous réserve de pertinence :
    - ce que vous avez réalisé du projet ;
    - les réponses aux diverses questions du sujet s'il y en a ;
    - un retour personnel sur les points qui vous ont paru difficiles ;
    - tout autre commentaire que vous jugerez utile...

# Les graphes

## Généralités sur les graphes

Les graphes sont des structures mathématiques puissantes utilisées pour modéliser des relations entre des objets. Formellement, un graphe $G$ est défini comme un ensemble de sommets $V$ et un ensemble d'arêtes $E$, où chaque arête relie un sommet à un autre. Les graphes peuvent être utilisés pour représenter une grande variété de systèmes et de problèmes, allant des réseaux sociaux aux systèmes de transport en passant par les circuits électroniques.

On peut distinguer plusieurs types de graphes :

- **Graphe orienté et non orienté** : Dans un graphe orienté, les arêtes ont une direction spécifique, tandis que dans un graphe non orienté, les arêtes n'ont pas de direction.

- **Graphe pondéré et non pondéré** : Un graphe pondéré attribue un poids à chaque arête, tandis qu'un graphe non pondéré n'a pas de poids associé à ses arêtes.

- **Graphe cyclique et acyclique** : Un graphe cyclique contient au moins un cycle (une séquence d'arêtes qui revient au sommet de départ), tandis qu'un graphe acyclique ne contient pas de cycle.

- **Graphe connexe et non connexe** : Un graphe connexe est un graphe dans lequel il existe un chemin entre chaque paire de sommets, tandis qu'un graphe non connexe est constitué de plusieurs composantes connexes distinctes.

Les graphes offrent un cadre puissant pour résoudre une grande variété de problèmes algorithmiques, notamment la recherche de chemins les plus courts, la détection de cycles, et bien d'autres.

## Utilité des graphes

Les graphes sont des outils puissants utilisés pour modéliser des relations entre des objets. Il existe quelques problèmes classiques qui peuvent être résolus à l'aide des graphes :

1. Trouver un chemin entre deux villes : Imaginez que vous planifiez un voyage et que vous vouliez savoir quel est le chemin le plus court pour aller d'une ville à une autre en passant par différentes routes. C'est un problème classique de recherche de chemin dans un graphe.
2. Recommandation d'amis sur les réseaux sociaux : Sur les réseaux sociaux comme Facebook, il y a souvent des recommandations d'amis basées sur des amis communs ou des intérêts similaires. Cela peut être modélisé comme un problème de recherche de chemins dans un graphe.
3. Détecter les cycles dans un réseau de transport : Dans un réseau de transport public, il est important de détecter les boucles ou les cycles, car cela peut entraîner des problèmes de planification des horaires. La détection de cycles est un problème classique dans les graphes.
4. Trouver le parcours le plus court pour visiter plusieurs lieux touristiques : Si vous planifiez un itinéraire pour visiter plusieurs lieux touristiques dans une ville, vous voudrez peut-être trouver le parcours le plus court qui passe par tous les endroits. C'est un exemple de problème du voyageur de commerce, qui peut être résolu à l'aide des graphes.
5. Recommandation de produits sur les sites de vente en ligne : Sur des sites de vente en ligne comme Amazon, les recommandations de produits sont souvent basées sur les achats précédents des utilisateurs ou sur des produits similaires. Ce problème peut être modélisé comme un problème de recherche de chemins dans un graphe.

Ces problèmes illustrent la diversité des applications des graphes dans le monde réel et montrent comment les concepts des graphes peuvent être utilisés pour résoudre des problèmes concrets.

## Le problème qui nous intéresse ici

### Le chemin le plus court

Dans un graphe, le chemin le plus court entre deux nœuds est le chemin avec le poids total le plus bas entre ces deux nœuds. Le poids d'un chemin peut représenter différentes quantités selon le contexte, comme la distance, le temps, le coût, etc.

### Quelques problèmes associés

- Calcul d'itinéraires dans les applications de navigation.
- Routage dans les réseaux informatiques.
- Planification de trajets dans les systèmes de transport en commun.
- Optimisation de la chaîne logistique dans les entreprises.

## Deux exemples de résolution en algorithmes gloutons

Une approche gloutonne, également appelée algorithme glouton, est une stratégie algorithmique qui prend des décisions locales immédiatement optimales à chaque étape, dans l'espoir de trouver une solution globale optimale. Cependant, contrairement à une approche dynamique programmation qui examine toutes les possibilités pour prendre une décision, l'approche gloutonne fait des choix basés uniquement sur les informations disponibles au moment de la décision, sans considérer les conséquences à long terme.

En d'autres termes, un algorithme glouton choisit à chaque étape la meilleure option locale sans se soucier des choix futurs. L'objectif est d'espérer que ces choix locaux optimaux conduisent à une solution globale optimale. Cependant, cela n'est pas toujours garanti, et il existe des situations où une approche gloutonne peut conduire à une solution sous-optimale voire incorrecte.

### Algorithme de Dijkstra

L'algorithme de Dijkstra est un algorithme classique de recherche de chemin le plus court dans un graphe pondéré et orienté. Son approche repose sur une stratégie gloutonne qui garantit la découverte du chemin le plus court entre un nœud source et tous les autres nœuds du graphe, à condition que les poids des arêtes soient tous positifs.

L'idée principale derrière l'algorithme de Dijkstra est de maintenir une liste des nœuds à explorer, en choisissant à chaque étape le nœud non encore visité le plus proche du nœud source. À chaque étape, l'algorithme met à jour les distances les plus courtes connues depuis le nœud source vers tous les autres nœuds en examinant les arêtes adjacentes au nœud choisi.

Le processus se répète jusqu'à ce que tous les nœuds aient été explorés, en sélectionnant à chaque fois le nœud le plus proche parmi ceux non encore explorés. Cette approche garantit que lorsque tous les nœuds sont explorés, les distances les plus courtes depuis le nœud source vers tous les autres nœuds ont été calculées.

### Algorithme A*

L'algorithme A* est une extension de l'algorithme de Dijkstra, conçu pour être plus efficace dans la recherche de chemin dans des graphes de grande taille. Il ajoute une composante heuristique à la recherche, ce qui lui permet de guider la recherche vers la solution de manière plus efficace.

Contrairement à Dijkstra, qui examine simplement les nœuds les plus proches du nœud source, A* utilise une estimation (ou heuristique) pour évaluer la distance restante jusqu'au nœud cible. Cela lui permet d'explorer en priorité les nœuds qui semblent être les plus prometteurs pour atteindre la solution.

L'algorithme A* combine donc une approche gloutonne (en choisissant le nœud actuel en fonction du coût actuel depuis le nœud source) avec une approche heuristique (en estimant le coût restant jusqu'à la destination). Cette combinaison permet à A* de trouver une solution optimale tout en explorant un espace de recherche beaucoup plus restreint que Dijkstra, ce qui le rend particulièrement efficace dans les environnements où les ressources de calcul sont limitées.

## Un dernier mot

Dans notre projet, nous nous concentrons sur la planification d'un déplacement ou d'un parcours sur une carte de tuiles en 2D. Cependant, les concepts et le fonctionnement que nous allons explorer peuvent être étendus à pratiquement n'importe quel type de carte ou de système de navigation.

Imaginons que notre carte soit une grille de tuiles, où chaque tuile représente une position spécifique dans l'espace. Chaque tuile peut avoir différentes propriétés, telles que des obstacles, des coûts de déplacement variables, des zones de danger, etc.

Notre objectif est de trouver le chemin le plus court ou le chemin le plus efficace pour se déplacer d'un point de départ à un point d'arrivée tout en tenant compte de ces propriétés de tuile. Ceci peut être appliqué à divers contextes :

- *Jeux vidéo* : Dans un jeu vidéo en monde ouvert, les personnages ou les joueurs doivent se déplacer à travers différentes zones, éviter les obstacles, atteindre des objectifs, etc.

- *Systèmes de navigation* : Dans les applications de navigation GPS, les utilisateurs cherchent souvent le chemin le plus court ou le plus rapide pour se rendre d'un endroit à un autre, en tenant compte des routes, du trafic, des restrictions, etc.

- *Robotique mobile* : Les robots autonomes ou les véhicules autonomes doivent planifier leurs mouvements dans des environnements complexes tout en évitant les obstacles et en atteignant leurs destinations de manière efficace.

Ainsi, bien que notre projet se concentre sur une carte de tuiles en 2D, les principes et les concepts que nous allons apprendre peuvent être généralisés et appliqués à une variété de situations et de domaines d'application. Cela montre la polyvalence et l'importance des algorithmes de recherche de chemin dans de nombreux aspects de l'informatique et de l'ingénierie.

# Les différentes classes

## Classes fournies

- `fr.ecole3il.rodez2023.carte.elements.GenerateurCarte` :
    Cette classe est responsable de la génération aléatoire de cartes. Elle contient des méthodes pour générer une carte avec des dimensions spécifiques ou avec des dimensions par défaut, ainsi que pour afficher une carte donnée. La méthode `main` permet de tester la génération et l'affichage d'une carte aléatoire.

- `fr.ecole3il.rodez2023.carte.elements.Carte` :
    La classe `Carte` représente une carte composée de tuiles disposées en une grille bidimensionnelle. Elle contient des méthodes pour accéder à une tuile spécifique sur la carte, ainsi que pour obtenir la largeur et la hauteur de la carte.

- `fr.ecole3il.rodez2023.carte.elements.Case` :
    La classe `Case` représente une case sur une carte, caractérisée par une tuile et des coordonnées (x, y). Elle permet de conserver la position (x, y) d'une tuile sur une carte. La méthode `toString` est redéfinie pour obtenir une représentation textuelle de la case.

- `fr.ecole3il.rodez2023.carte.elements.Chemin` :
    La classe `Chemin` représente un chemin sur une carte, composé d'une liste de cases. Elle fournit des méthodes pour accéder aux cases du chemin et pour afficher le chemin sur la console.

- `fr.ecole3il.rodez2023.carte.elements.Tuile` (énumération) :
    L'énumération `Tuile` représente les différents types de tuiles pouvant être présents sur une carte.
    Chaque type de tuile a une pénalité associée qui indique la difficulté de traverser cette tuile.

## Note de Philibert Roquart

> Salut les gars,
> Alors, écoutez, j'ai une mauvaise nouvelle à vous annoncer... La grand-mère de mon poisson rouge est décédée. Ouais, je sais, c'est triste. Donc, je serai pas dispo ce weekend, faut que je m'occupe de lui, vous comprenez. C'est le genre de trucs qui prennent du temps, vous savez, faut être là pour lui, lui remonter le moral et tout ça.
> Bon, j'ai pondu deux petites classes pour notre projet, comme convenu. Philibert Roquart à la rescousse, comme d'habitude. Alors, genre, la première classe, `fr.ecole3il.rodez2023.carte.application.ExempleCLI`, c'est un truc que vous pouvez utiliser pour tester votre code et voir si ça marche comme prévu. Je veux dire, c'est agaçant de toujours faire des tests, mais bon, faut ce qu'il faut, hein ?
> J'ai pas pris la peine d'utiliser des trucs génériques comme `Noeud<E>` et `Graphe<E>` parce que, franchement, j'ai lu le sujet en diagonale et j'ai pas pigé grand-chose. Et puis, recommencer maintenant, non merci, ça me saoule déjà rien que d'y penser. Mais bon, vous pouvez toujours essayer de les intégrer si ça vous chante, ça ferait genre plus pro, je suppose.
> Ensuite, y a `fr.ecole3il.rodez2023.carte.application.CarteGUI`, une petite interface graphique pour afficher notre carte. C'est pas trop mal foutu, même si j'ai pas eu le temps (ni l'envie) de faire les images pour les tuiles. Mais bon, ça marche quand même, vous verrez.
> Maintenant, ce qu'il reste à faire... Bah, déjà, faut finir ce que j'ai pas fait, vous voyez ? Les images de tuiles, ça serait cool de les ajouter pour rendre ça moins moche. Ah, et j'ai pas bien utilisé la classe `Case` non plus, mais je vous laisse le soin de vous en occuper.
> Et puis, vous pouvez toujours améliorer le truc, vous savez, ajouter des fonctionnalités, rendre l'interface plus user-friendly, tout ça. Perso, j'ai d'autres trucs à faire et franchement, le Java, c'est pas ma tasse de thé.
> Allez, bon courage les gars, et que la force soit avec vous pour finir ce truc. Si vous avez des questions, je suis là, mais j'vous promets rien, hein.
> Enfin, j'ai un petit souci qui va me rendre indisponible les weekends à venir jusqu'à la fin du mois. Figurez-vous que j'ai accepté de participer à un tournoi international de tricot sous-marin. Ouais, je sais, c'est un peu fou, mais c'est ma passion secrète.
> Donc voilà, chaque weekend, je vais être plongé dans les profondeurs marines en train de tricoter avec des poissons et tout le tintouin. C'est une opportunité unique, vous comprenez. J'ai pas pu refuser.
> Je sais que c'est un peu étrange, mais c'est comme ça. Donc, je ne pourrai pas être dispo les weekends à venir. Mais bon, je suis sûr que vous vous débrouillerez sans moi. Si vous avez des questions, envoyez-moi un pigeon voyageur, et je ferai de mon mieux pour vous répondre.
> Bon courage pour la suite du projet, les gars, et désolé pour l'absence.
> À plus,
> Philibert

# Travail à réaliser

## Partie générique

### Classe `Noeud`

La classe `fr.ecole3il.rodez2023.carte.chemin.elements.Noeud` représente un nœud générique dans une structure de graphe. Un nœud possède une valeur de type générique `E` et une liste de nœuds voisins, également de type `E`.

- `public Noeud(E valeur)`: Ce constructeur crée un nouvel objet Noeud avec la valeur spécifiée et initialise la liste de voisins.
- `public E getValeur()`: Cette méthode renvoie la valeur du nœud.
- `public List<Noeud<E>> getVoisins()`: Cette méthode renvoie la liste des nœuds voisins.
- `public void ajouterVoisin(Noeud<E> voisin)`: Cette méthode permet d'ajouter un nœud voisin à la liste des voisins du nœud actuel.

### Classe `Graphe`

La classe `fr.ecole3il.rodez2023.carte.chemin.elements.Graphe` sert à représenter et à manipuler un graphe. Elle offre des méthodes pour ajouter des nœuds au graphe, ajouter des arêtes entre les nœuds avec des coûts associés, obtenir le coût d'une arête entre deux nœuds spécifiques, ainsi que pour obtenir des informations sur les nœuds et leurs voisins.

#### Relations entre les nœuds

En théorie des graphes, l'adjacence fait référence à la relation entre les sommets (ou nœuds) d'un graphe. Plus précisément, elle décrit quel sommet est connecté à quel autre sommet par une arête.

Dans un graphe non orienté, l'adjacence est symétrique, ce qui signifie que si le sommet A est adjacent au sommet B, alors le sommet B est également adjacent au sommet A.

Dans un graphe orienté, l'adjacence peut être asymétrique, ce qui signifie qu'un sommet peut être adjacent à un autre sans que la relation soit réciproque. Par exemple, dans un graphe représentant des villes et des routes, la route peut aller d'une ville A à une ville B sans qu'il y ait forcément une route de B à A.

**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?

- `public void ajouterNoeud(Noeud<E> noeud)`: Cette méthode ajoute un nœud au graphe si le nœud n'existe pas déjà dans le graphe.

- `public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout)`: Cette méthode ajoute une arête pondérée entre deux nœuds du graphe. Elle prend en paramètre le nœud de départ, le nœud d'arrivée et le coût de l'arête. Si les nœuds de départ et d'arrivée n'existent pas encore dans le graphe, ils sont ajoutés en utilisant la méthode `ajouterNoeud`. Ne pas oublier d'initialiser l'arête à la matrice d'adjacence associée au nœud de départ.

- `public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee)`: Cette méthode renvoie le coût de l'arête entre deux nœuds spécifiés.

- `public List<Noeud<E>> getNoeuds()`: Cette méthode renvoie une liste contenant tous les nœuds du graphe.

- `public List<Noeud<E>> getVoisins(Noeud<E> noeud)`: Cette méthode renvoie une liste contenant tous les voisins d'un nœud spécifié. Si le nœud n'existe pas dans le graphe, elle renvoie une liste vide.

**Question :** Pourquoi pensez-vous que les classes `Noeud` et `Graphe` ont été définies avec des paramètres génériques ?

### Interface `AlgorithmeChemin`

L'interface `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin<E>` définit un contrat pour les classes qui implémentent des algorithmes de recherche de chemin dans un graphe :

- `List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee)`: Cette méthode est responsable de trouver un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe donné. Elle prend en paramètres le graphe dans lequel la recherche doit être effectuée, ainsi que les nœuds de départ et d'arrivée. Cette méthode renvoie une liste de nœuds représentant le chemin trouvé entre le nœud de départ et le nœud d'arrivée dans le graphe.


**Question :** Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?

### Classe `AlgorithmeDijkstra`

La classe `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra<E>` implémente l'interface `AlgorithmeChemin<E>` et fournit une implémentation de l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux nœuds dans un graphe pondéré.

Voici les étapes de l'algorithme de Dijkstra pour trouver le chemin le plus court entre un nœud de départ et tous les autres nœuds dans un graphe pondéré :

1. Initialisation des structures de données :
   1. Initialiser un ensemble de coûts pour chaque nœud, avec le coût du nœud de départ mis à zéro et les coûts des autres nœuds mis à l'infini.
   2. Initialiser un ensemble de prédécesseurs pour chaque nœud, avec le prédécesseur de chaque nœud initialisé à null.
   3. Utiliser une file de priorité pour suivre les nœuds à explorer, en les classant par leur coût actuel.
2. Exploration des nœuds :
   1. Tant que la file de priorité n'est pas vide :
      1. Retirer le nœud avec le coût minimum de la file de priorité.
      2. Si ce nœud est le nœud d'arrivée recherché, l'algorithme s'arrête.
      3. Sinon, pour chaque voisin du nœud actuel :
         1. Calculer le coût total pour atteindre ce voisin depuis le nœud actuel.
         2. Si ce coût total est inférieur au coût actuel du voisin, mettre à jour le coût du voisin et son prédécesseur, puis l'ajouter à la file de priorité.
3. Reconstruction du chemin le plus court :
   1. Une fois que le nœud d'arrivée est atteint ou que la file de priorité est vide, l'algorithme s'arrête.
   2. Reconstruire le chemin le plus court en remontant les prédécesseurs à partir du nœud d'arrivée jusqu'au nœud de départ.

Ces étapes permettent à l'algorithme de Dijkstra de trouver efficacement le chemin le plus court entre un nœud de départ et tous les autres nœuds dans un graphe pondéré.

### Classe `AlgorithmeAEtoile`

La classe `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile<E>` implémente l'interface `AlgorithmeChemin<E>`. Elle utilise l'algorithme A* pour trouver un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe donné. 

L'algorithme A* est un algorithme de recherche de chemin dans un graphe qui combine les caractéristiques de l'algorithme de Dijkstra et d'une recherche heuristique. Voici les étapes de l'algorithme A* :

1. Initialisation :
   1. Initialisez deux ensembles :
      - L'ensemble des nœuds à explorer, généralement une file de priorité, triée par le coût total estimé.
      - L'ensemble des nœuds déjà explorés.
    2. Initialisez un tableau pour stocker le coût total estimé pour chaque nœud.
    3. Initialisez un tableau pour stocker les nœuds prédécesseurs.
2. Initialisation des coûts :
    1. Initialisez le coût total estimé de chaque nœud à l'infini, sauf pour le nœud de départ, dont le coût total estimé est défini à partir d'une heuristique spécifique (par exemple, la distance à vol d'oiseau jusqu'à la destination).
    2. Initialisez le coût actuel du nœud de départ à 0.
3. Boucle principale :
    1. Tant que l'ensemble des nœuds à explorer n'est pas vide :
       1. Sélectionnez le nœud avec le coût total estimé le plus bas dans la file de priorité (ou le nœud le plus prometteur).
       2. Si ce nœud est le nœud d'arrivée, arrêtez l'algorithme et reconstruisez le chemin.
       3. Sinon, marquez le nœud comme exploré et examinez ses voisins.
4. Mise à jour des coûts :
   1. Pour chaque voisin non exploré du nœud sélectionné :
      1. Calculez le coût réel pour atteindre ce voisin depuis le nœud sélectionné.
      2. Calculez le coût total estimé pour ce voisin en additionnant son coût réel et l'estimation du coût restant jusqu'à l'arrivée (heuristique).
      3. Si ce coût total estimé est inférieur au coût total estimé actuel pour ce voisin, mettez à jour les coûts et marquez le nœud prédécesseur.
5. Reconstruction du chemin : Une fois que le nœud d'arrivée a été atteint, reconstruisez le chemin en remontant les nœuds prédécesseurs à partir du nœud d'arrivée jusqu'au nœud de départ.

Une fois que le chemin a été reconstruit, il représente le chemin le plus court entre le nœud de départ et le nœud d'arrivée, en tenant compte à la fois des coûts réels et des estimations heuristiques.

### Un adaptateur pour le travail de Philibert

Même s'il a mal travaillé, ce serait dommage de refaire tout ce qu'a fait Philibert !

À la place, on va s'intéresser à une classe supplémentaire, `fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme`. La classe `AdaptateurAlgorithme` agit comme un pont entre le code existant, qui inclut les classes `Carte`, `Noeud` et `Graphe`, et le travail déjà effectué par Philibert sur l'interface graphique. 

Elle fournit une méthode principale, trouverChemin, qui utilise un algorithme spécifié pour trouver le chemin le plus court entre deux cases sur une carte. Pour ce faire, elle crée un graphe représentant la carte à partir des données fournies, puis utilise cet algorithme pour trouver le chemin optimal. Ensuite, elle retourne ce chemin sous forme d'un objet Chemin adapté à l'interface graphique de Philibert.

- `static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee)` : Trouve le chemin le plus court entre deux cases sur la carte en utilisant l'algorithme spécifié.

Je vous conseille quelques méthodes intermédiaires (`KISS`) :

- `static Graphe <Case> creerGraphe(Carte carte)` : Crée un graphe représentant la carte en utilisant les cases comme nœuds et ajoute des arêtes entre les cases voisines.
- `static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur)` : Ajoute des arêtes entre une case donnée et ses cases voisines dans le graphe.
- `static double calculerCout(Case from, Case to)` : Calcule le coût pour se déplacer d'une case à une autre.
- `static afficherChemin(List<Noeud<Case>> chemin)` : Affiche le chemin trouvé dans la console.

Enfin, utilisez cette classe adaptée pour reprendre les fichiers de Philibert et corriger ses erreurs.