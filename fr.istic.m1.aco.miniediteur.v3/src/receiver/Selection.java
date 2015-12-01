package receiver;

public class Selection implements Cloneable{

	private int debut;
	private int longueur;
	private String contenu;
	@SuppressWarnings("unused")
	private MoteurEdition moteurEdition;
	
	public void setSelection(int debut, int longueur){
		this.debut = debut;
		this.longueur = longueur;
	}
	
	public int getDebut() {
		return debut;
	}
	
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	public String getContenu() {
		return this.contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public void initSelection(){
		this.longueur = 0;
		this.contenu = "";
	}
	
	public Object clone(){
		Object o = null;
		try{
			o = super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		
		return o;
	}
	
}
