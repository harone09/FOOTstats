package footstats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;



public class Mapper {
	
	private ArrayList<String> liste;
	
	
	
	
	public Map<Equipe, Integer> MapMatchJouer() {
		Map<Equipe, Integer> tempMap=new LinkedHashMap<Equipe, Integer>();
		String[] a;
		for(String newl: liste) {
			
			a=newl.split(",");
			tempMap.put(new Equipe(a[4],a[53],a[1]), 1);
			tempMap.put(new Equipe(a[5],a[53],a[1]), 1);
			
		}
		
		
		return tempMap;
		
	}

	public Map<Equipe, Integer> MapMatchGagner(){
		Map<Equipe, Integer> tempMap=new LinkedHashMap<Equipe, Integer>();
		String[] a;
		Integer b,c;
		for(String newl: liste) {
			
			a=newl.split(",");
			b=Integer.parseInt(a[10]);c=Integer.parseInt(a[11]);
			if(c<b) {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 1);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 0);
			}
			else if(b<c) {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 0);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 1);
			}
			else {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 0);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 0);
			}
			
		}
		
		
		return tempMap;
	}
	
	public Map<Equipe, Integer> MapMatchPerdu(){
		Map<Equipe, Integer> tempMap=new LinkedHashMap<Equipe, Integer>();
		String[] a;
		Integer b,c;
		for(String newl: liste) {
			
			a=newl.split(",");
			b=Integer.parseInt(a[10]);c=Integer.parseInt(a[11]);
			if(c<b) {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 0);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 1);
			}
			else if(b<c) {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 1);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 0);
			}
			else {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 0);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 0);
			}
			
		}
		
		
		return tempMap;
	}

	public Map<Equipe, Integer> MapMatchNull(){
		Map<Equipe, Integer> tempMap=new LinkedHashMap<Equipe, Integer>();
		String[] a;
		Integer b,c;
		for(String newl: liste) {
			
			a=newl.split(",");
			b=Integer.parseInt(a[10]);c=Integer.parseInt(a[11]);
			if(c==b) {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 1);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 1);
			}
			
			else {
				tempMap.put(new Equipe(a[4],a[53],a[1]), 0);
				tempMap.put(new Equipe(a[5],a[53],a[1]), 0);
			}
			
		}
		
		
		return tempMap;
	}
	
	public Map<Equipe, Integer> MapBut(){
		Map<Equipe, Integer> tempMap=new LinkedHashMap<Equipe, Integer>();
		String[] a;
		Integer b,c;
		for(String newl: liste) {
			
			a=newl.split(",");
			
			b=Integer.parseInt(a[10]);c=Integer.parseInt(a[11]);
			tempMap.put(new Equipe(a[4],a[53],a[1]), b);
			tempMap.put(new Equipe(a[5],a[53],a[1]), c);
			
		}
		
		
		return tempMap;
	}
	
	public LinkedHashMap<String, Integer> Score(Map<String, Integer> p,Map<String, Integer> g,Map<String, Integer> n){
		LinkedHashMap<String, Integer> tempMap=new LinkedHashMap<String, Integer>();
		Integer value;
		for(String s: p.keySet()) {
			tempMap.put(s, 0);
		}
		for(String s: g.keySet()) {
			value=tempMap.get(s);
			value+=g.get(s)*3;
			tempMap.put(s, value);
		}
		for(String s: n.keySet()) {
			value=tempMap.get(s);
			value+=n.get(s)*1;
			tempMap.put(s, value);
		}
		return tempMap;
		
	}
	
	public LinkedHashMap<String, Integer> Classement(Map<String, Integer> sc){
		LinkedHashMap<String, Integer> tempMap = sc.entrySet()

                .stream()

                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())

                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,  LinkedHashMap::new));

		
		return tempMap;

	}
	
	
	public Mapper() {
		
	}
	
	public Mapper(ArrayList<String> l1) {
		liste=new ArrayList<String>(l1);
	}

}
