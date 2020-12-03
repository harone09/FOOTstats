package footstats;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class Shuffler {
	

	private Multimap<String, Integer> mapToShuffle1=ArrayListMultimap.create();
	private Multimap<String, Integer> mapToShuffle2=ArrayListMultimap.create();

	

	public Multimap<String, Integer> getMapToShuffle1() {
		return mapToShuffle1;
	}
	public Multimap<String, Integer> getMapToShuffle2() {
		return mapToShuffle2;
	}


	public Shuffler(Map<Equipe, Integer> m) {
		Iterator<Equipe> it=m.keySet().iterator();
		Equipe eq;
		while(it.hasNext()) {
			eq=it.next();
			mapToShuffle1.put(eq.getNom(),m.get(eq));
			
		}
		
	}
	
	public Shuffler(Map<String, Integer> m1,Map<String, Integer> m2,Map<String, Integer> m3) {
		Iterator<String> it1=m1.keySet().iterator();
		Iterator<String> it2=m2.keySet().iterator();
		Iterator<String> it3=m3.keySet().iterator();
		String eq;
		while(it1.hasNext()) {
			eq=it1.next();
			mapToShuffle2.put(eq,m1.get(eq));
		}
		while(it2.hasNext()) {
			eq=it2.next();
			mapToShuffle2.put(eq,m2.get(eq));
		}
		while(it3.hasNext()) {
			eq=it3.next();
			mapToShuffle2.put(eq,m3.get(eq));
		}
		
	}
	

}
