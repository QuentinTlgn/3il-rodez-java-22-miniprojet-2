package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AdaptateurAlgorithme fournit une interface pour utiliser différents algorithmes de recherche de chemin
 * sur une carte.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points spécifiés sur une carte en utilisant l'algorithme fourni.
     *
     * @param algorithme L'algorithme de recherche de chemin à utiliser.
     * @param carte      La carte sur laquelle chercher le chemin.
     * @param xDepart    La coordonnée x du point de départ.
     * @param yDepart    La coordonnée y du point de départ.
     * @param xArrivee   La coordonnée x du point d'arrivée.
     * @param yArrivee   La coordonnée y du point d'arrivée.
     * @return Le chemin trouvé entre les deux points, ou un chemin vide s'il n'y a pas de chemin possible.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {

        Graphe<Case> g = creerGraphe(carte);
        Noeud<Case> depart = g.getNoeud(xDepart, yDepart);
        Noeud<Case> arrivee = g.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(g, depart, arrivee);

        if (noeudsChemin == null || noeudsChemin.isEmpty()) {
            return new Chemin(new ArrayList<>());
        }
        return new Chemin(afficherChemin(noeudsChemin));
    }

    /**
     * Ajoute les arêtes voisines pour un nœud donné dans le graphe, en utilisant les coordonnées spécifiées.
     *
     * @param graphe       Le graphe auquel ajouter les arêtes voisines.
     * @param noeudActuel  Le nœud actuel pour lequel ajouter des arêtes voisines.
     * @param x            La coordonnée x du nœud actuel.
     * @param y            La coordonnée y du nœud actuel.
     * @param largeur      La largeur de la carte ou du graphe.
     * @param hauteur      La hauteur de la carte ou du graphe.
     */
    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> noeudActuel, int x, int y, int largeur, int hauteur) {

        int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                Noeud<Case> noeudVoisin = graphe.getNoeud(newX, newY);

                if (noeudVoisin != null) {
                    double cout = calculerCout(noeudActuel.getValeur(), noeudVoisin.getValeur());
                    graphe.ajouterArete(noeudActuel, noeudVoisin, cout);
                    noeudActuel.ajouterVoisin(noeudVoisin);
                }
            }
        }
    }

    /**
     * Crée un graphe à partir d'une carte spécifiée en ajoutant des nœuds pour chaque case et en établissant
     * des arêtes entre les nœuds voisins.
     *
     * @param carte La carte à partir de laquelle créer le graphe.
     * @return Le graphe créé à partir de la carte.
     */
    private static Graphe<Case> creerGraphe(Carte carte) {
        // Création d'un nouveau graphe.
        Graphe<Case> graphe = new Graphe<>();
        // Obtention de la largeur et de la hauteur de la carte.
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Parcours de toutes les cases de la carte pour créer un nœud pour chaque case et l'ajouter au graphe.
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(currentNoeud);
            }
        }

        // Parcours de toutes les cases de la carte pour ajouter des arêtes voisines entre les nœuds correspondants.
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = graphe.getNoeud(x, y);
                // Ajout des arêtes voisines pour le nœud actuel.
                ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur);
            }
        }
        // Retour du graphe créé.
        return graphe;
    }

    /**
     * Calcule le coût pour se déplacer d'une case source à une case cible.
     *
     * @param from La case source.
     * @param to   La case cible.
     * @return Le coût pour se déplacer de la case source à la case cible.
     * @throws IllegalArgumentException Si l'une des cases est nulle.
     */
    private static double calculerCout(Case from, Case to) {
        // Vérification si l'une des cases est nulle.
        if (to == null || from == null) {
            throw new IllegalArgumentException("Les cases ne doivent pas être nulles");
        }
        // Calcul du coût en additionnant les pénalités des tuiles des cases source et cible.
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    /**
     * Affiche le chemin trouvé à l'aide d'une liste de nœuds de case et retourne une liste de cases correspondante.
     *
     * @param chemin La liste de nœuds représentant le chemin trouvé.
     * @return Une liste de cases représentant le chemin trouvé.
     */
    private static List<Case> afficherChemin(List<Noeud<Case>> chemin) {
        // Vérification si le chemin est vide.
        if (chemin.isEmpty()) {
            // Affichage d'un message indiquant qu'aucun chemin n'a été trouvé.
            System.out.println("Aucun chemin trouvé");
            // Retour d'une nouvelle liste vide.
            return new ArrayList<>();
        }

        // Affichage du titre du chemin.
        System.out.print("Chemin : ");
        // Initialisation d'une nouvelle liste pour stocker les cases du chemin.
        List<Case> cheminCases = new ArrayList<>();

        // Parcours de chaque nœud dans le chemin.
        for (Noeud<Case> noeud : chemin) {
            // Obtention de la case associée au nœud.
            Case caseNoeud = noeud.getValeur();
            // Ajout de la case à la liste de cases du chemin.
            cheminCases.add(caseNoeud);
            // Affichage de la case.
            System.out.print("\n --> " + caseNoeud.toString());
        }
        // Saut de ligne pour une meilleure présentation.
        System.out.println();

        // Retour de la liste de cases représentant le chemin.
        return cheminCases;
    }
}
