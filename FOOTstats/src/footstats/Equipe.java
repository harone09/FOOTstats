package footstats;

public class Equipe implements Comparable<Equipe>{
	////
	private String nom,stade,dates;
	
	public String getNom() {
		return nom;
	}

	public String getStade() {
		return stade;
	}

	public String getDates() {
		return dates;
	}

	@Override
	public int hashCode() {
		return nom.hashCode()+stade.hashCode()+dates.hashCode();		
	}
	
	
	public Equipe(String n,String s,String d) {
		nom=n;stade=s;dates=d;
	}

	@Override
	public int compareTo(Equipe o) {
		return this.nom.compareTo(o.nom);

	}
	

}
