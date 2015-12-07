package caretaker;

import java.util.HashMap;
import java.util.Stack;

import memento.ConcreteMemento;
import memento.Memento;
import memento.MementoCouper;
import memento.MementoSaisir;
import originator.CommandEnregistrable;

public class Enregistreur {


	private boolean aDefait = false;
	private HashMap<String, CommandEnregistrable> commandesEnregistrable;

	private Stack<Memento> pileRefaire;
	private Stack<Memento> pileDefaire;

	/**
	 * Constructeur de l'Enregistreur.
	 * Initialise les piles refaire et defaire.
	 * Ainsi que les commandes enregistrables.
	 */
	public Enregistreur(){
		this.pileDefaire = new Stack<Memento>();
		this.pileRefaire = new Stack<Memento>();
		this.commandesEnregistrable = new HashMap<String, CommandEnregistrable>();
	}

	/**
	 * Ajoute le Memento passé en paramètre à la pile defaire de l'Enregistreur.
	 * @param memento : Un memento
	 */
	public void addMemento(Memento memento){
		pileDefaire.push(memento);
	}

	/**
	 * Enregistre la commande enregistrable.
	 * Si on a defait précédemment on réinitialise la pile refaire et on passe defaire à faux.
	 * Récupere son Memento puis l'ajoute à la pile  de l'Enregistreur.
	 * @param commandEnregistrable : CommandeEnregistrable
	 */
	public void enregistrer(CommandEnregistrable commandEnregistrable){
		if(aDefait){
			pileRefaire.clear();
			aDefait = false;
		}
		addMemento(commandEnregistrable.getMemento());
	}

	public void refaire(){
		if(!pileRefaire.isEmpty()){
			Memento memento = pileRefaire.pop();
			String test = memento.getClass().toString().substring(21);
			test = test.substring(0, 1).toLowerCase() + test.substring(1);
			pileDefaire.push(memento);
			commandesEnregistrable.get(test).setMemento(memento);
		}
	}

	public void defaire(){
		aDefait = true;
		if(!pileDefaire.isEmpty()){
			Memento memento = pileDefaire.peek();
			ConcreteMemento mem = (ConcreteMemento) memento;
			pileDefaire.pop();
			String test = memento.getClass().toString().substring(21);
			switch(test){
			case "Saisir" :
				commandesEnregistrable.get("effacer").setMemento(memento);
				break;
			case "Couper" :
				mem.getEtatMemento().getSelection().setLongueur(0);
				commandesEnregistrable.get("coller").setMemento(mem);
				mem.getEtatMemento().getSelection().setLongueur(mem.getEtatMemento().getPressepapier().getContenu().length());;
				break;
			case "Coller" :
				MementoCouper couper = new MementoCouper();
				couper.getEtatMemento().getPressepapier().setContenu(mem.getEtatMemento().getPressepapier().getContenu());
				couper.getEtatMemento().getSelection().setLongueur(mem.getEtatMemento().getPressepapier().getContenu().length()+1);
				couper.getEtatMemento().getSelection().setDebut(mem.getEtatMemento().getSelection().getDebut());
				commandesEnregistrable.get("couper").setMemento(couper);
				if(mem.getEtatMemento().getSelection().getLongueur() > mem.getEtatMemento().getPressepapier().getContenu().length()){
					MementoSaisir saisir = new MementoSaisir(mem.getEtatMemento().getBufferCopie().getContenu().substring(mem.getEtatMemento().getSelection().getDebut(),mem.getEtatMemento().getSelection().getLongueur()+mem.getEtatMemento().getSelection().getDebut()));
					System.out.println(mem.getEtatMemento().getBufferCopie().getContenu().substring(mem.getEtatMemento().getSelection().getDebut(),mem.getEtatMemento().getSelection().getLongueur()+mem.getEtatMemento().getSelection().getDebut()));
					saisir.getEtatMemento().getSelection().setDebut(mem.getEtatMemento().getSelection().getDebut()+1);
					saisir.getEtatMemento().getSelection().setLongueur(0);
					commandesEnregistrable.get("saisir").setMemento(saisir);
				}
				break;

			case "Effacer" :
				if(mem.getEtatMemento().getSelection().getLongueur() != 0){
					MementoSaisir saisie = new MementoSaisir(mem.getEtatMemento().getBufferCopie().getContenu().substring(mem.getEtatMemento().getSelection().getDebut(),mem.getEtatMemento().getSelection().getLongueur()+mem.getEtatMemento().getSelection().getDebut()));
					saisie.getEtatMemento().getSelection().setDebut(mem.getEtatMemento().getSelection().getDebut()+1);
					saisie.getEtatMemento().getSelection().setLongueur(0);
					commandesEnregistrable.get("saisir").setMemento(saisie);
				}
				else{
				MementoSaisir saisie = new MementoSaisir(mem.getEtatMemento().getBufferCopie().getContenu().substring(mem.getEtatMemento().getSelection().getDebut()-1,mem.getEtatMemento().getSelection().getLongueur()+mem.getEtatMemento().getSelection().getDebut()));
				saisie.getEtatMemento().setSelection(mem.getEtatMemento().getSelection());	
				saisie.getEtatMemento().getSelection().setLongueur(0);
				commandesEnregistrable.get("saisir").setMemento(saisie);
				}
				break;
			}
			pileRefaire.push(memento);

		}
	}

	public void setCommandesEnregistrable(HashMap<String, CommandEnregistrable> h)
			throws Exception {
		if (h.get("couper") == null)
			throw new Exception("commande \"couper\" manquante");
		if (h.get("copier") == null)
			throw new Exception("commande \"copier\" manquante");
		if (h.get("coller") == null)
			throw new Exception("commande \"coller\" manquante");
		if (h.get("saisir") == null)
			throw new Exception("commande \"saisir\" manquante");
		if (h.get("selectionner") == null)
			throw new Exception("commande \"selectionner\" manquante");
		if (h.get("effacer") == null)
			throw new Exception("commande \"effacer\" manquante");
		commandesEnregistrable = h;
	}
}
