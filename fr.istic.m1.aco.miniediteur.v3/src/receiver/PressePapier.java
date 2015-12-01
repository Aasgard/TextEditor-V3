package receiver;


public class PressePapier implements Cloneable{
	
	public String contenu;
	public MoteurEdition moteurEdition;
	

	public PressePapier(String contenu){
		this.contenu = contenu;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public PressePapier clone(){
		PressePapier o = null;
		try{
			o = (PressePapier)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		o.contenu = new String(this.contenu);
		System.out.println(o.contenu);
		return o;
	}
}

