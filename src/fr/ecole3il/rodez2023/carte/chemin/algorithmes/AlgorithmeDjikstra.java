package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;


public class AlgorithmeDjikstra<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));

        // Initialisation des coûts et des prédécesseurs
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);
        filePriorite.add(depart);
        // Exploration des nœuds
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }
        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudCourant = arrivee;
        while (noeudCourant != null) {
            chemin.add(noeudCourant);
            noeudCourant = predecesseurs.get(noeudCourant);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public static void main(String[] args) {
        // Création d'un graphe de test
        Graphe<String> graphe = new Graphe<>();
        Noeud<String> a = new Noeud<>("A");
        Noeud<String> b = new Noeud<>("B");
        Noeud<String> c = new Noeud<>("C");
        Noeud<String> d = new Noeud<>("D");
        Noeud<String> e = new Noeud<>("E");

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);
        graphe.ajouterNoeud(d);
        graphe.ajouterNoeud(e);

        graphe.ajouterArete(a, b, 4.0);
        graphe.ajouterArete(a, c, 2.0);
        graphe.ajouterArete(b, c, 5.0);
        graphe.ajouterArete(b, d, 10.0);
        graphe.ajouterArete(c, d, 3.0);
        graphe.ajouterArete(c, e, 8.0);
        graphe.ajouterArete(d, e, 7.0);

        // Création de l'algorithme de Dijkstra
        AlgorithmeDjikstra<String> dijkstra = new AlgorithmeDjikstra<>();

        // Noeud de départ et d'arrivée
        Noeud<String> depart = a;
        Noeud<String> arrivee = e;

        // Recherche du chemin le plus court
        List<Noeud<String>> cheminPlusCourt = dijkstra.trouverChemin(graphe, depart, arrivee);

        // Affichage du chemin trouvé
        if (cheminPlusCourt != null) {
            System.out.println("Chemin le plus court de " + depart + " à " + arrivee + ":");
            for (Noeud<String> noeud : cheminPlusCourt) {
                System.out.print(noeud + " -> ");
            }
            System.out.println("FIN");
        } else {
            System.out.println("Aucun chemin trouvé de " + depart + " à " + arrivee);
        }
    }
}
