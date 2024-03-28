package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.List;

/**
 * Interface définissant un algorithme de recherche de chemin dans un graphe.
 *
 * @param <E> le type des valeurs associées aux nœuds du graphe
 */
public interface AlgorithmeChemin<E> {

    /**
     * Trouve le chemin optimal entre deux nœuds dans un graphe donné.
     *
     * @param graphe   le graphe dans lequel chercher le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds formant le chemin optimal entre le nœud de départ et le nœud d'arrivée
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
