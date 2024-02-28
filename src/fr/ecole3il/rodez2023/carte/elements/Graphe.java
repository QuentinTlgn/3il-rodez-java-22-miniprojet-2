package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un graphe générique.
 *
 * @param <E> Le type de la valeur stockée dans les noeuds du graphe.
 */
public class Graphe<E> {
    private double[][] matriceAdjacence; // Matrice d'adjacence représentant les arêtes entre les noeuds
    private ArrayList<Noeud<E>> listeNoeuds; // Liste des noeuds présents dans le graphe

    /**
     * Constructeur de la classe Graphe.
     *
     * @param nombreDeNoeuds Le nombre initial de noeuds dans le graphe.
     */
    public Graphe(int nombreDeNoeuds) {
        this.listeNoeuds = new ArrayList<>();
        this.matriceAdjacence = new double[nombreDeNoeuds][nombreDeNoeuds];
    }

    /**
     * Ajoute un noeud au graphe s'il n'existe pas déjà.
     *
     * @param noeud Le noeud à ajouter.
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (listeNoeuds.indexOf(noeud) == -1) {
            listeNoeuds.add(noeud);
        }
    }

    /**
     * Ajoute une arête entre deux noeuds avec un coût associé.
     *
     * @param noeud1 Le premier noeud.
     * @param noeud2 Le deuxième noeud.
     * @param cout   Le coût de l'arête entre les deux noeuds.
     */
    public void ajouterArete(Noeud<E> noeud1, Noeud<E> noeud2, double cout) {
        int indiceNoeud1 = listeNoeuds.indexOf(noeud1);
        int indiceNoeud2 = listeNoeuds.indexOf(noeud2);

        //Si le noeud 1 n'existe pas, l'ajouter au graphe
        if (indiceNoeud1 == -1) {
            listeNoeuds.add(noeud1);
            indiceNoeud1 = listeNoeuds.indexOf(noeud1);
        }

        //Si le noeud 2 n'existe pas, l'ajouter au graphe
        if (indiceNoeud2 == -1) {
            listeNoeuds.add(noeud2);
            indiceNoeud2 = listeNoeuds.indexOf(noeud2);
        }

        matriceAdjacence[indiceNoeud1][indiceNoeud2] = cout;
        matriceAdjacence[indiceNoeud2][indiceNoeud1] = cout; // Car c'est un graphe non orienté

        noeud1.ajouterVoisin(noeud2);
        noeud2.ajouterVoisin(noeud1);
    }

    /**
     * Obtient le coût d'une arête entre deux noeuds spécifiés.
     * Attention : @param depart et @param arrivee doivent être des noeuds présents dans le graphe et avoir une arete
     *
     * @param depart  Le noeud de départ.
     * @param arrivee Le noeud d'arrivée.
     * @return Le coût de l'arête entre les deux noeuds, ou 0 si l'arête n'existe pas.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        int indiceDepart = listeNoeuds.indexOf(depart);
        int indiceArrivee = listeNoeuds.indexOf(arrivee);

        return matriceAdjacence[indiceDepart][indiceArrivee];
    }

    /**
     * Obtient la liste de tous les noeuds du graphe.
     *
     * @return Une liste contenant tous les noeuds du graphe.
     */
    public ArrayList<Noeud<E>> getNoeuds() {
        return new ArrayList<>(listeNoeuds);
    }

    /**
     * Obtient la liste de tous les voisins d'un noeud spécifié.
     *
     * @param noeud Le noeud dont on souhaite obtenir les voisins.
     * @return Une liste contenant tous les voisins du noeud spécifié.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeud.getVoisins();
    }

    /**
     * Méthode principale pour tester le code du graphe.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cet exemple).
     */
    public static void main(String[] args) {
        // Création du graphe
        Graphe<String> graphe = new Graphe<>(4);

        // Ajout des noeuds
        Noeud<String> noeudA = new Noeud<>("A");
        Noeud<String> noeudB = new Noeud<>("B");
        Noeud<String> noeudC = new Noeud<>("C");
        Noeud<String> noeudD = new Noeud<>("D");

        graphe.ajouterNoeud(noeudA);
        graphe.ajouterNoeud(noeudB);
        graphe.ajouterNoeud(noeudC);
        graphe.ajouterNoeud(noeudD);

        // Ajout des arêtes avec coût associé
        graphe.ajouterArete(noeudA, noeudB, 2);
        graphe.ajouterArete(noeudA, noeudC, 1);
        graphe.ajouterArete(noeudB, noeudD, 4);
        graphe.ajouterArete(noeudC, noeudD, 3);

        // Test de la méthode getCoutArete
        double coutAB = graphe.getCoutArete(noeudA, noeudB);
        System.out.println("Coût de l'arête AB : " + coutAB);

        // Test de la méthode getCoutArete
        double coutAD = graphe.getCoutArete(noeudA, noeudD);
        System.out.println("Coût de l'arête AD : " + coutAD);

        // Test de la méthode getNoeuds
        List<Noeud<String>> listeNoeuds = graphe.getNoeuds();
        System.out.println("Liste des noeuds du graphe : " + listeNoeuds);

        // Test de la méthode getVoisins
        List<Noeud<String>> voisinsA = graphe.getVoisins(noeudA);
        System.out.println("Voisins du noeud A : " + voisinsA);
    }

}
