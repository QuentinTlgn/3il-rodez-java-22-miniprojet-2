package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDjikstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * Cette classe représente un exemple d'utilisation en ligne de commande (CLI) pour tester la recherche de chemin
 * sur une carte à l'aide d'un algorithme spécifié.
 * Elle génère une carte, exécute l'algorithme de recherche de chemin deux fois avec les mêmes paramètres,
 * puis affiche le chemin trouvé dans la console.
 * @author p.roquart
 */
public class ExempleCLI {

	/**
	 * Méthode principale pour exécuter l'exemple en ligne de commande.
	 * @param args Les arguments de la ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {
		GenerateurCarte generateur = new GenerateurCarte();
		Carte test = generateur.genererCarte(100, 100);
		AlgorithmeChemin<Case> algorithmeChemin = new AlgorithmeDjikstra<>();

		Chemin chemin = AdaptateurAlgorithme.trouverChemin(algorithmeChemin, test, 0, 0, 50, 50);
		chemin.afficherChemin();

		chemin = AdaptateurAlgorithme.trouverChemin(algorithmeChemin, test, 0, 0, 50, 50);
		chemin.afficherChemin();
	}
}
