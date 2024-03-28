package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * La classe Graphe représente un graphe composé de noeuds reliés par des arêtes pondérées.
 * @param <E> Le type d'objet contenu dans les noeuds du graphe.
 */
public class Graphe<E> {

    private final Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdj; // Matrice d'adjacence représentant les arêtes du graphe
    private final List<Noeud<E>> noeuds; // Liste des noeuds du graphe

    /**
     * Constructeur de la classe Graphe.
     * Initialise une nouvelle instance de graphe avec une matrice d'adjacence vide et une liste de noeuds vide.
     */
    public Graphe() {
        this.matriceAdj = new HashMap<>();
        this.noeuds = new ArrayList<>();
    }

    /**
     * Ajoute un nouveau noeud au graphe.
     * @param noeud Le noeud à ajouter.
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            matriceAdj.put(noeud, new HashMap<>());
        }
    }

    /**
     * Ajoute une arête pondérée entre deux noeuds du graphe.
     * @param depart Le noeud de départ de l'arête.
     * @param arrivee Le noeud d'arrivée de l'arête.
     * @param cout Le poids de l'arête (coût pour se déplacer entre les noeuds).
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        this.matriceAdj.get(depart).put(arrivee, cout);
    }

    /**
     * Récupère le poids de l'arête entre deux noeuds spécifiés.
     * @param depart Le noeud de départ de l'arête.
     * @param arrivee Le noeud d'arrivée de l'arête.
     * @return Le poids de l'arête s'il existe, sinon renvoie une valeur infinie.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (matriceAdj.containsKey(depart)) {
            Map<Noeud<E>, Double> aretesAdjacentes = matriceAdj.get(depart);
            if (aretesAdjacentes.containsKey(arrivee))
                return aretesAdjacentes.get(arrivee);
        }
        // Renvoie une valeur infinie si aucune arête n'existe entre les noeuds
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Récupère la liste des noeuds du graphe.
     * @return La liste des noeuds du graphe.
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(noeuds);
    }

    /**
     * Récupère la liste des noeuds voisins d'un noeud spécifié.
     * @param noeud Le noeud pour lequel récupérer les voisins.
     * @return La liste des noeuds voisins du noeud spécifié.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!this.matriceAdj.containsKey(noeud))
            return new ArrayList<>();
        return new ArrayList<>(this.matriceAdj.get(noeud).keySet());
    }

    /**
     * Récupère le noeud situé aux coordonnées spécifiées.
     * @param x La coordonnée x du noeud.
     * @param y La coordonnée y du noeud.
     * @return Le noeud situé aux coordonnées spécifiées, ou null s'il n'existe pas.
     */
    public Noeud<E> getNoeud(int x, int y) {
        for (Noeud<E> noeud : noeuds) {
            if (noeud.getValeur() instanceof Case caseValue) {
                if (caseValue.getX() == x && caseValue.getY() == y) {
                    return noeud;
                }
            }
        }
        return null;
    }
}
