package footstats;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Traitement {
	

	private static LinkedHashMap<String, Integer> MJouer;
	private static LinkedHashMap<String, Integer> MNull;
	private static LinkedHashMap<String, Integer> MGagner;
	private static LinkedHashMap<String, Integer> MPerdu;
	private static LinkedHashMap<String, Integer> TScore;
	private static LinkedHashMap<String, Integer> TClass;
	private static LinkedHashMap<String, Integer> TBut;
	
	private static ArrayList<String> file1;
	private static ArrayList<String> file2;
	private static ArrayList<String> file3;
	
	Threads t1;
	Threads t2;
	Threads t3;
	
	
	
private Mapper map=new Mapper();
	
	private Shuffler shf1;
	private Reducer  red1;
	public  LinkedHashMap<String, Integer> getMJouer() {
		return MJouer;
	}




	public  LinkedHashMap<String, Integer> getMNull() {
		return MNull;
	}




	public  LinkedHashMap<String, Integer> getMGagner() {
		return MGagner;
	}




	public  LinkedHashMap<String, Integer> getMPerdu() {
		return MPerdu;
	}




	public  LinkedHashMap<String, Integer> getTScore() {
		return TScore;
	}




	public LinkedHashMap<String, Integer> getTClass() {
		return TClass;
	}




	public  LinkedHashMap<String, Integer> getTBut() {
		return TBut;
	}




	private static final void spliter(String p){
		try {
			int b,c0=1,bufer1=0;

			
			
			BufferedInputStream bis= new BufferedInputStream(
					new FileInputStream(
							new File(p)));
			while((b=bis.read())>=0) {
				if(b == '\n')
				c0++;
			}
			bis= new BufferedInputStream(
					new FileInputStream(
							new File(p)));
			
			Scanner scn= new Scanner(bis);
			 file1=new ArrayList<String>();
			 file2=new ArrayList<String>();
			 file3=new ArrayList<String>();
			
			
			
			while(scn.hasNext()) {
				
				if(bufer1<=c0/3) {
					file1.add(scn.nextLine());
				}
				else if(bufer1>c0/3 && bufer1<=(c0/3)*2) {
					file2.add(scn.nextLine());
				}
				else {
					file3.add(scn.nextLine());

				}
				
				bufer1++;
				
			}
			
			scn.close();
			bis.close();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
	
	
	
	
	public void traitement() {
		t1=new Threads(file1);
		t2=new Threads(file2);
		t3=new Threads(file3);

		
		////MAtch jouer 1 
						try {
							shf1= new Shuffler(t1.call().get(0),t2.call().get(0),t3.call().get(0));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							red1=new Reducer(shf1.getMapToShuffle2());
							MJouer= red1.getMapReduced();
						}
					////MAtch gagner 2
						try {
							shf1= new Shuffler(t1.call().get(1),t2.call().get(1),t3.call().get(1));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							red1=new Reducer(shf1.getMapToShuffle2());
							MGagner= red1.getMapReduced();
						}
						
					////MAtch perdu 3
						try {
							shf1= new Shuffler(t1.call().get(2),t2.call().get(2),t3.call().get(2));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							red1=new Reducer(shf1.getMapToShuffle2());
							MPerdu= red1.getMapReduced();
						}
						
					////MAtch null 4
						try {
							shf1= new Shuffler(t1.call().get(3),t2.call().get(3),t3.call().get(3));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							red1=new Reducer(shf1.getMapToShuffle2());
							MNull= red1.getMapReduced();
						}
						
					////nbr But 5
						try {
							shf1= new Shuffler(t1.call().get(4),t2.call().get(4),t3.call().get(4));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							red1=new Reducer(shf1.getMapToShuffle2());
							TBut= red1.getMapReduced();
						}
						
						// Score 6
						
						TScore=map.Score(MPerdu, MGagner, MNull);
						
						
						// Classement 7
						TClass=map.Classement(TScore);
						Integer rang=1;
						for(String s: TClass.keySet()) {
							TClass.replace(s, rang);
							rang++;
						}
						
						

		
		
	}
	
	
	public Traitement(String path) {
		spliter(path);
		traitement();
	}
	
}
