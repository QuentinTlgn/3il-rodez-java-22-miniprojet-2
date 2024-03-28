# Miniprojet 2 - Repérage et direction sur une carte en 2D

## Question :
### Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?

Ici, j'utilise une matrice d'adjacence. Chaque élément de cette matrice contient le coût de l'arête entre les nœuds correspondants.
Une autre solution aurait pu être d'utiliser une liste d'adjacence, où chaque noeud du graphe est associé à une liste de ses voisins directs, et éventuellement les coûts des arêtes.

## Question :
### Pourquoi pensez-vous que les classes `Noeud` et `Graphe` ont été définies avec des paramètres génériques ?

Cela permet de pouvoir définir plus tard le Type des noeuds. Pour une plus grande fléxibilité et adaptabilité.

## Question :
### Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?

C'est une bonne pratique car cela abstrait l'implémentation, de nouveaux algorithmes peuvent facilement être ajoutés avec différents types de données, et rend le code plus générique.

# TAULEIGNE Quentin