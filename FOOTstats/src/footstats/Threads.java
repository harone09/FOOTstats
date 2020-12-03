package footstats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

public class Threads implements Callable<ArrayList<LinkedHashMap<String, Integer>>> {
	
	private Mapper map;
	private Shuffler shf1;
	private Reducer  red1;
	private LinkedHashMap<String, Integer> MJouer,MNull,MGagner,MPerdu,TBut;
	
	


	public Threads(ArrayList<String> f){
		
		map=new Mapper(f);
		
	}
	
	
		
		
	
	@Override
	public ArrayList<LinkedHashMap<String, Integer>> call() throws Exception {
	////MAtch jouer 1 
				shf1= new Shuffler(map.MapMatchJouer());
				red1=new Reducer(shf1.getMapToShuffle1());
				MJouer= red1.getMapReduced();
				
				////MAtch gagner 2
				
				 shf1= new Shuffler(map.MapMatchGagner());
				 red1=new Reducer(shf1.getMapToShuffle1());
				 MGagner= red1.getMapReduced();
				 
				////MAtch perdu 3
				
				shf1= new Shuffler(map.MapMatchPerdu());
				red1=new Reducer(shf1.getMapToShuffle1());
				MPerdu=red1.getMapReduced();
				
				////MAtch null 4
				shf1= new Shuffler(map.MapMatchNull());
				red1=new Reducer(shf1.getMapToShuffle1());
				MNull=red1.getMapReduced();
				
				///NBR But 5
				shf1= new Shuffler(map.MapBut());
				red1=new Reducer(shf1.getMapToShuffle1());
				TBut= red1.getMapReduced();
		

		
		ArrayList<LinkedHashMap<String, Integer>> temp= new ArrayList<LinkedHashMap<String, Integer>>();
		temp.add(MJouer);
		temp.add(MGagner);
		temp.add(MPerdu);
		temp.add(MNull);
		temp.add(TBut);


		return temp;
	}
	
}
