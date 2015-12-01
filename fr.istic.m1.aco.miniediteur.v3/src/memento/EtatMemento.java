package memento;
import receiver.Buffer;
import receiver.PressePapier;
import receiver.Selection;

public class EtatMemento {

	private Selection selection;
	private PressePapier pressepapier;
	private Buffer buffer;
	
	public EtatMemento(){
		this.selection = new Selection();
		this.pressepapier = new PressePapier("");
	}
	
	public EtatMemento(EtatMemento em){
		this.selection = em.getSelection();
		this.pressepapier = em.getPressepapier();
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public PressePapier getPressepapier() {
		return pressepapier;
	}

	public void setPressepapier(PressePapier pressepapier) {
		this.pressepapier = pressepapier;
	}

	public Buffer getBufferCopie() {
		return buffer;
	}

	public void setBufferCopie(Buffer b) {
		this.buffer = b;
	}
	
}
