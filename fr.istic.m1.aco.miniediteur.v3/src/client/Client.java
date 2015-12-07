package client;

import java.util.HashMap;

import caretaker.Enregistreur;
import command.*;
import observer.IHMObserver;
import receiver.MoteurEdition;
import originator.*;

public class Client {

	private static MoteurEdition em;
	private static IHMObserver ihm;
	private static Enregistreur enregistreur;
	private static HashMap<String, Command> commandes;
	private static HashMap<String, CommandEnregistrable> commandEnregistrable;

	/**
	 * Classe principale de lancement.
	 * Création d'un MoteurEdition et de l'IHM avec le ME en paramètre (attribution).
	 * Création d'un enregistreur.
	 * Création des commandes ainsi que des commandes enregistrables
	 * @param args : Paramètre de base
	 * @throws Exception : Exception lancée si erreur
	 */
	
	public static void main(String[] args) throws Exception {
		// initialisation
		em = new MoteurEdition();
		ihm = new IHMObserver(em);

		//Création de l'enregistreur
		enregistreur = new Enregistreur();
		
		// Création des commandes
		commandes = new HashMap<String, Command>();
		
		CouperEnregistrable couper = new CouperEnregistrable(em, enregistreur);
		CopierEnregistrable copier = new CopierEnregistrable(em, enregistreur);
		CollerEnregistrable coller = new CollerEnregistrable(em, enregistreur);
		SaisirEnregistrable saisir = new SaisirEnregistrable(em, ihm, enregistreur);
		SelectionnerEnregistrable selectionner =  new SelectionnerEnregistrable(em, ihm, enregistreur);
		EffacerEnregistrable effacer = new EffacerEnregistrable(em, enregistreur);
		
		commandes.put("couper", couper);
		commandes.put("copier", copier);
		commandes.put("coller", coller);
		commandes.put("selectionner", selectionner);
		commandes.put("saisir", saisir);
		commandes.put("effacer", effacer);
		commandes.put("refaire", new Refaire(enregistreur));
		commandes.put("defaire", new Defaire(enregistreur));
		ihm.setCommands(commandes);

		
		// Création des commandes enregistrables
		
		commandEnregistrable = new HashMap<String, CommandEnregistrable>();
		commandEnregistrable.put("couper", couper);
		commandEnregistrable.put("copier", copier);
		commandEnregistrable.put("coller", coller);
		commandEnregistrable.put("saisir", saisir);
		commandEnregistrable.put("selectionner", selectionner);
		commandEnregistrable.put("effacer", effacer);
		enregistreur.setCommandesEnregistrable(commandEnregistrable);
		
		// mise en place de l'observer
		em.registerObserver(ihm);

		/* Finalisation de l'IHM */
		ihm.createTextArea();
		ihm.loadButtons();
		ihm.launch();
	}

}
