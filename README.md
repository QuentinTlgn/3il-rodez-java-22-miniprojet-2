# Miniprojet 2 - Repérage et direction sur une carte en 2D - TAULEIGNE Quentin


### Classe `Graphe`
**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?

**Réponse :** Ici, j'utilise une matrice d'adjacence. Chaque élément de cette matrice contient le coût de l'arête entre les nœuds correspondants.
Une autre solution aurait pu être d'utiliser une liste d'adjacence, où chaque noeud du graphe est associé à une liste de ses voisins directs, et éventuellement les coûts des arêtes.