package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

/**
 * La classe Chemin représente un chemin sur une carte, composé d'une liste de cases.
 * Elle fournit des méthodes pour accéder aux cases du chemin et afficher le chemin.
 */
public class Chemin {
    private final List<Case> cases; // Liste des cases composant le chemin

    /**
     * Construit un nouveau chemin avec la liste de cases spécifiée.
     * @param cases La liste de cases composant le chemin.
     */
    public Chemin(List<Case> cases) {
        this.cases = cases;
    }

    /**
     * Récupère la liste des cases composant le chemin.
     * @return La liste des cases composant le chemin.
     */
    public List<Case> getCases() {
        return cases;
    }
    
    /**
     * Affiche le chemin sur la console en affichant les coordonnées et les tuiles de chaque case.
     */
    public void afficherChemin() {
        System.out.println("Chemin :");
        for (Case c : cases) {
            System.out.println("[" + c.getX() + ", " + c.getY() + "] : " + c.getTuile());
        }
    }
}
