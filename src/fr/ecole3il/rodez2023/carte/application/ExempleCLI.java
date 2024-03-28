package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDjikstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * @author p.roquart
 *genre on peut tester le code
 *c'est agaçant tous ces tests
 *
 *de toute façon quand ça compile c'est bon
 */
public class ExempleCLI {

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