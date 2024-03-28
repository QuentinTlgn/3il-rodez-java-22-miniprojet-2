package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    private double estimationHeuristique(Noeud<E> noeudCourant, Noeud<E> arrivee) {
        Case caseNoeudCourant = (Case) noeudCourant.getValeur(); // Récupération de la case associée au nœud
        Case caseArrivee = (Case) arrivee.getValeur(); // Récupération de la case associée à la cible

        double deltaX = Math.abs(caseNoeudCourant.getX() - caseArrivee.getX());
        double deltaY = Math.abs(caseNoeudCourant.getY() - caseArrivee.getY());
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> debut, Noeud<E> cible) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> precedent = new HashMap<>();
        Set<Noeud<E>> explore = new HashSet<>();
        PriorityQueue<Noeud<E>> filePrioritaire = new PriorityQueue<>(Comparator.comparingDouble(coutTotalEstime::get));

        // Initialisation des coûts
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            precedent.put(noeud, null);
        }
        coutActuel.put(debut, 0.0);
        coutTotalEstime.put(debut, estimationHeuristique(debut, cible));
        filePrioritaire.add(debut);

        // Algorithme A*
        while (!filePrioritaire.isEmpty()) {
            Noeud<E> courant = filePrioritaire.poll();
            if (courant.equals(cible))
                break;
            explore.add(courant);

            // Exploration des voisins
            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                if (explore.contains(voisin))
                    continue;

                double nouveauCout = coutActuel.get(courant) + graphe.getCoutArete(courant, voisin);
                if (nouveauCout < coutActuel.get(voisin)) {
                    precedent.put(voisin, courant);
                    coutActuel.put(voisin, nouveauCout);
                    coutTotalEstime.put(voisin, coutActuel.get(voisin) + estimationHeuristique(voisin, cible));
                    if (!filePrioritaire.contains(voisin))
                        filePrioritaire.add(voisin);
                }
            }
        }

        // Reconstruction du chemin
        LinkedList<Noeud<E>> chemin = reconstruireChemin(precedent, cible, debut);
        Collections.reverse(chemin);

        return new ArrayList<>(chemin);
    }

    private LinkedList<Noeud<E>> reconstruireChemin(Map<Noeud<E>, Noeud<E>> predecesseur, Noeud<E> cible, Noeud<E> debut) {
        LinkedList<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> courant = cible;
        while (courant != null) {
            chemin.add(courant);
            courant = predecesseur.get(courant);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}