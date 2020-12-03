package footstats;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.google.common.collect.Multimap;

public class Reducer {
	
	LinkedHashMap<String, Integer> mapReduced=new  LinkedHashMap<String, Integer>();
	
	

	public LinkedHashMap<String, Integer> getMapReduced() {
		return mapReduced;
	}
	public Reducer(Multimap<String, Integer> m) {
		String key;
		Integer value;
		
		Iterator<String> it=m.keySet().iterator();
		while(it.hasNext()) {
			key=it.next();
			value=0;
			for(Integer i: m.get(key)) {
				value+=i;
			}
			
			mapReduced.put(key,value);
		}
		
		
		
	}
	
	
	

}
