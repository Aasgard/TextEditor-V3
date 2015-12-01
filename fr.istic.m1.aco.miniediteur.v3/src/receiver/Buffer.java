package receiver;


public class Buffer implements Cloneable{
	
	private StringBuffer contenu;
	
	@SuppressWarnings("unused")
	private MoteurEdition moteurEdition;

	public Buffer(){
		super();
		this.contenu = new StringBuffer("");
	}
	
	public void setBuffer(StringBuffer newContenu, int debut, int fin){
		if(fin > 0){
			this.contenu.delete(debut, fin);
		}
		this.contenu.insert(debut, newContenu.toString() );
	}
	
	public StringBuffer getContenu(){
		return this.contenu;
	}

	public Buffer clone(){
		Buffer o = null;
		try{
			o =(Buffer)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		
			o.contenu = new StringBuffer(this.contenu);
		return o;
	}
}

