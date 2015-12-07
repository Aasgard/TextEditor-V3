package originator;

import caretaker.Enregistreur;
import command.Saisir;
import invoker.IHM;
import memento.EtatMemento;
import memento.Memento;
import memento.MementoSaisir;
import receiver.MoteurEdition;
import receiver.PressePapier;
import receiver.Selection;

public class SaisirEnregistrable extends Saisir implements CommandEnregistrable {

	private Enregistreur enregistreur;
	
	/**
	 * Constructeur de la commande enregistrable Saisir.
	 * Hérite de la commande Saisir.
	 * Initialise l'enregistreur.
	 * @param newem
	 * @param enregistreur
	 */
	public SaisirEnregistrable(MoteurEdition newem, IHM newihm, Enregistreur enregistreur) {
		super(newem, newihm);
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel à la fonction execute de la classe mère : Saisir.
	 * Puis appel la fonction enregistrer(CommandeEnregistrable) de l'enregistreur.
	 */
	@Override
	public void execute() {
		super.execute();
		enregistreur.enregistrer(this);
	}
	
	/**
	 * Récupère le memento correspondant à la commande Enregistrable.
	 * Créer le Memento Saisir avec le caractère de l'ihm.
	 * En initialisant l'EtatMemento avec des clones du Pressepapier et de la selection.
	 * @return MementoSaisir
	 */
	@Override
	public Memento getMemento() {
		MementoSaisir mem = new MementoSaisir(String.valueOf(ihm.getCar()));
		EtatMemento eM = new EtatMemento();
		PressePapier pp = (PressePapier) me.getPressePapier().clone();
		eM.setPressepapier(pp);
		Selection sel = (Selection) me.getSelection().clone();
		eM.setSelection(sel);
		eM.getSelection().setContenu(me.getSelection().getContenu());
		mem.setEtatMemento(eM);
		return mem;
	}

	/**
	 * Permet de rejouer la commande du Memento en appelant la fonction execute de la classe mère : saisir.
	 * En modifiant la selection du moteur edition avec celle enregistrée dans le memento.
	 * Le texte est récupérer dans le Memento avec getTexte().
	 * @param Memento
	 */
	@Override
	public void setMemento(Memento m) {
		MementoSaisir mem = ((MementoSaisir) m);
		int debut = mem.getEtatMemento().getSelection().getDebut();
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		System.out.println("longueur dans l'entrée de saisir : "+longueur);
		me.getSelection().setSelection(debut-1, longueur);
		String texte = mem.getTexte();
		me.saisir(texte);
	}


}
