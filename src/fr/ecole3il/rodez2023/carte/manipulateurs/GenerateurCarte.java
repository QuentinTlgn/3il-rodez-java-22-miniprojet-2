/**
 * La classe GenerateurCarte est responsable de la génération de cartes en utilisant des tuiles aléatoires.
 * Elle fournit des méthodes pour générer une carte aléatoire avec des dimensions spécifiées ou avec des dimensions par défaut,
 * ainsi que pour afficher une carte donnée.
 * 
 * @author proussille
 */
package fr.ecole3il.rodez2023.carte.manipulateurs;

import java.util.Random;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

/**
 * La classe GenerateurCarte est responsable de la génération de cartes en utilisant des tuiles aléatoires.
 * Elle fournit des méthodes pour générer une carte aléatoire avec des dimensions spécifiées ou avec des dimensions par défaut,
 * ainsi que pour afficher une carte donnée.
 *
 * @author proussille
 */
public class GenerateurCarte {
    private static final int LARGEUR_PAR_DEFAUT = 10; // Largeur par défaut de la carte
    private static final int HAUTEUR_PAR_DEFAUT = 10; // Hauteur par défaut de la carte
    private static final Tuile[] TUILES = Tuile.values(); // Ensemble de tuiles disponibles
    private static final Random RANDOM = new Random(); // Générateur de nombres aléatoires

    /**
     * Génère une carte aléatoire avec des dimensions par défaut.
     * @return Une nouvelle instance de Carte générée aléatoirement.
     */
    public static Carte genererCarte() {
        return genererCarte(LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
    }

    /**
     * Génère une carte aléatoire avec les dimensions spécifiées.
     * @param largeur La largeur de la carte à générer.
     * @param hauteur La hauteur de la carte à générer.
     * @return Une nouvelle instance de Carte générée aléatoirement avec les dimensions spécifiées.
     */
    public static Carte genererCarte(int largeur, int hauteur) {
        Tuile[][] tuiles = new Tuile[largeur][hauteur];
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                // Sélectionner une tuile aléatoire
                Tuile tuileAleatoire = TUILES[RANDOM.nextInt(TUILES.length)];
                tuiles[x][y] = tuileAleatoire;
            }
        }
        return new Carte(tuiles);
    }

    /**
     * Affiche la carte spécifiée sur la console.
     * @param carte La carte à afficher.
     */
    public static void afficherCarte(Carte carte) {
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                System.out.print(carte.getTuile(x, y) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Méthode principale pour tester la génération et l'affichage d'une carte aléatoire.
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        Carte carte = genererCarte();
        System.out.println("Carte générée aléatoirement :");
        afficherCarte(carte);
    }
}
