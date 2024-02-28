package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe représente un nœud dans un graphe générique.
 *
 * @param <E> Le type de la valeur stockée dans le nœud.
 */
public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;
    
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Obtient la valeur stockée dans le nœud.
     *
     * @return La valeur du nœud.
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Obtient la liste des nœuds voisins du nœud actuel.
     *
     * @return La liste des nœuds voisins.
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un nœud voisin au nœud actuel.
     *
     * @param voisin Le nœud voisin à ajouter.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        voisins.add(voisin);
    }
}
