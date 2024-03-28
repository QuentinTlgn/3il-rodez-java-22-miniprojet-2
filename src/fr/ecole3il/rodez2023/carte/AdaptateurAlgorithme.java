package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {

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

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> noeudActuel, int x, int y, int largeur, int hauteur) {

        int[][] offsets = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

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

    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(currentNoeud);
            }
        }

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = graphe.getNoeud(x, y);
                ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }

    private static double calculerCout(Case from, Case to) {
        if (to == null || from == null) {
            throw new IllegalArgumentException("Les cases ne doivent pas être nulles");
        }

        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    private static List<Case> afficherChemin(List<Noeud<Case>> chemin) {

        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé");
            return new ArrayList<>();
        }

        System.out.print("Chemin : ");
        List<Case> cheminCases = new ArrayList<>();

        for (Noeud<Case> noeud : chemin) {
            Case caseNoeud = noeud.getValeur();
            cheminCases.add(caseNoeud);

            System.out.print("\n --> " + caseNoeud.toString());
        }
        System.out.println();

        return cheminCases;
    }
}