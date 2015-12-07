package memento;
import receiver.Buffer;
import receiver.PressePapier;
import receiver.Selection;

public class EtatMemento {

	private Selection selection;
	private PressePapier pressepapier;
	private Buffer buffer;
	
	/**
	 * Constructeur de l'EtatMemento.
	 * Initialise la selection et le pressepapier.
	 */
	public EtatMemento(){
		this.selection = new Selection();
		this.pressepapier = new PressePapier("");
	}
	
	/**
	 * Constructeur de l'EtatMemento � partir d'un EtatMemento pass� en param�tre.
	 * @param EtatMemento
	 */
	public EtatMemento(EtatMemento em){
		this.selection = em.getSelection();
		this.pressepapier = em.getPressepapier();
	}

	/**
	 * Retourne la selection de l'EtatMemento.
	 * @return Selection
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 * Modifie la selection de l'EtatMemento avec celle pass� en param�tre.
	 * @param selection
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	/**
	 * Retourne le pressepapier de l'EtatMemento.
	 * @return PressePapier
	 */
	public PressePapier getPressepapier() {
		return pressepapier;
	}

	/**
	 * Modifie le pressepapier de l'EtatMemento avec celui pass� en param�tre.
	 * @param pressepapier
	 */
	public void setPressepapier(PressePapier pressepapier) {
		this.pressepapier = pressepapier;
	}

	/**
	 * Retourne le buffer de l'EtatMemento.
	 * @return Buffer
	 */
	public Buffer getBufferCopie() {
		return buffer;
	}

	/**
	 * Modifie le buffer de l'EtatMemento avec celui pass� en param�tre.
	 * @param buffer
	 */
	public void setBufferCopie(Buffer b) {
		this.buffer = b;
	}
	
}
