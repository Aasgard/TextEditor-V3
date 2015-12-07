package memento;

public class MementoSelectionner extends ConcreteMemento implements Memento {

	private int debutSelection;
	private int longueurSelection;
	
	/**
	 * Constructeur du mementoSelectionner.
	 * @param debutSelection
	 * @param longueurSelection
	 */
	public MementoSelectionner(int debutSelection, int longueurSelection){
		this.debutSelection = debutSelection;
		this.longueurSelection = longueurSelection;
	}
	
	/**
	 * Retourne le debut de la s�lection du memento.
	 * @return debutSelection
	 */
	public int getDebutSelection() {
		return debutSelection;
	}
	
	/**
	 * Modifie le debut de la s�lection.
	 * @param debutSelection
	 */
	public void setDebutSelection(int debutSelection) {
		this.debutSelection = debutSelection;
	}
	
	/**
	 * Retourne la longueur de la s�lection.
	 * @return longueurSelection
	 */
	public int getLongueurSelection() {
		return longueurSelection;
	}
	
	/**
	 * Modifie la longueur de la s�lection.
	 * @param longueurSelection
	 */
	public void setLongueurSelection(int longueurSelection) {
		this.longueurSelection = longueurSelection;
	}
}
