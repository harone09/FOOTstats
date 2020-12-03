package footstats;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;



public class MainGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8482537327969215012L;
	//Dimension Ecran=Toolkit.getDefaultToolkit().getScreenSize();
	JPanel NP,WP,CP,NWP,NCP,CNP;
	JButton btn_Browse,btn_Analyse,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_download,btn_search;
	JTextField txt_Search=new JTextField();
	JScrollPane scrollResult;
	Results result;
    Image icon ;
	JLabel BackgroundImage;
	Traitement traitements;
	private JTable table;
	int choice=0;

	
	String path;
	
	
	ImageIcon getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    ImageIcon ic=new ImageIcon(resizedImg);

	    return ic;
	}
	
	
	void browser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select your File");
		jfc.setAcceptAllFileFilterUsed(false);
		
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
		jfc.addChoosableFileFilter(filter);
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			path=jfc.getSelectedFile().getPath();
			btn_Analyse.setEnabled(true);
			JOptionPane.showMessageDialog(new JFrame(), "le fichier doit respecter les norme internationnal sinon les resultats serons erronee", "Caution",
			        JOptionPane.ERROR_MESSAGE);
		}

	}
	void saver() {		
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			 
			int userSelection = fileChooser.showSaveDialog(null);
			
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			      File file = fileChooser.getSelectedFile();
			      
			      if (!file.getName().toLowerCase().endsWith(".csv")) {
			        file = new File(file.getParentFile(), file.getName() + ".csv");
			      }
			      try {
			    	  FileWriter w1=new FileWriter(file);
			    	  w1.write("Equipes,Matchs_jouer,Matchs_gagner,Matchs_perdus,Match_null,Nombre_Buts,Scores,Classements"+ System.lineSeparator());
						
						for(String s: traitements.getTClass().keySet()) {
							w1.write(s+","+traitements.getMJouer().get(s)+","+traitements.getMGagner().get(s)+","+
									traitements.getMPerdu().get(s)+","+traitements.getMNull().get(s)+","+traitements.getTBut().get(s)+
									","+traitements.getTScore().get(s)+","+traitements.getTClass().get(s)+ System.lineSeparator());
						}
						
						w1.close();
			        
			        Desktop.getDesktop().open(file);
			      } catch (Exception e) {
			        e.printStackTrace();
			      }
			      }
			
	}

	void initButtons() {
		
		btn_Analyse=new JButton();
		btn_Analyse.setText("Analyser...");
		btn_Analyse.addActionListener(this);
		btn_Analyse.setEnabled(false);

		btn_Browse=new JButton();
		btn_Browse.setBorder(new EmptyBorder(10,10,10,10));
		btn_Browse.setText("Charger...");
		btn_Browse.addActionListener(this);
		
		btn_1=new JButton();
		btn_1.setText("Matchs Joués");
		btn_1.addActionListener(this);
		btn_1.setEnabled(false);

		
		btn_2=new JButton();
		btn_2.setText("Matchs Gagnés");
		btn_2.addActionListener(this);
		btn_2.setEnabled(false);

		
		btn_3=new JButton();
		btn_3.setText("Match Perdus");
		btn_3.addActionListener(this);
		btn_3.setEnabled(false);

		
		btn_4=new JButton();
		btn_4.setText("Match null");
		btn_4.addActionListener(this);
		btn_4.setEnabled(false);

		
		btn_5=new JButton();
		btn_5.setText("Nombre de Buts");
		btn_5.addActionListener(this);
		btn_5.setEnabled(false);

		
		btn_6=new JButton();
		btn_6.setText("Score");
		btn_6.addActionListener(this);
		btn_6.setEnabled(false);

		
		btn_7=new JButton();
		btn_7.setText("Classement");
		btn_7.addActionListener(this);
		btn_7.setEnabled(false);
		
		btn_download=new JButton();
		btn_download.setText("Telecharger...");
		btn_download.addActionListener(this);
		btn_download.setEnabled(false);
		
		
		btn_search=new JButton();
		btn_search.setText("Recherche");
		btn_search.addActionListener(this);
		
		

		
		
	
	}
	
	void enableBtns() {
		btn_1.setEnabled(true);
		btn_2.setEnabled(true);
		btn_3.setEnabled(true);
		btn_4.setEnabled(true);
		btn_5.setEnabled(true);
		btn_6.setEnabled(true);
		btn_7.setEnabled(true);
		btn_download.setEnabled(true);
		JOptionPane.showMessageDialog(new JFrame(), "traitement reussi", "Caution",
		        JOptionPane.WARNING_MESSAGE);
		
	}
	
	void initPanels() {
		
		
		
		
		initButtons();
		
		NWP=new JPanel();
		NWP.setLayout(new BoxLayout(NWP, BoxLayout.Y_AXIS));
		//NWP.add(Box.createRigidArea());
		try {
			icon=ImageIO.read(new File("Ressources/STATSFOOT.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BackgroundImage=new JLabel(getScaledImage(icon, 131, 120));
		NWP.add(BackgroundImage);

		
		
		
		NCP=new JPanel();
		NCP.setLayout(new GridLayout(1,2));
		NCP.setBorder(new EmptyBorder(40,10,40,10));
		NCP.setBackground(new Color(51,120,255));
		NCP.add(btn_Browse);
		NCP.add(btn_Analyse);
		
		
		NP=new JPanel();
		NP.setLayout(new BorderLayout());
		NP.setBackground(new Color(51,120,255));
		//NP.setBorder(new EmptyBorder(50,10,50,10));
		NP.add(NWP, BorderLayout.WEST);
		NP.add(NCP, BorderLayout.CENTER);
		

		
		WP=new JPanel();
		GridLayout l=new GridLayout(8,1);
		WP.setLayout(l);
		WP.setBackground(new Color(51,150,255));
		//WP.setSize(new Dimension(150, 10));
		

		//WP.add(Box.createRigidArea(new Dimension(150, 10)));
		WP.add(btn_1);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_2);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_3);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_4);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_5);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_6);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_7);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		WP.add(btn_download);
		//WP.add(Box.createRigidArea(new Dimension(10, 10)));
		
		CNP=new JPanel();
		CNP.setBackground(Color.lightGray);
		CNP.setLayout(new BorderLayout());
		CNP.add(btn_search, BorderLayout.EAST);
		CNP.add(txt_Search, BorderLayout.CENTER);
		
		
		CP=new JPanel();
		CP.setBackground(Color.lightGray);
		CP.setLayout(new BorderLayout());
		//CP.add(CNP, BorderLayout.NORTH);
		//CP.add(table,BorderLayout.CENTER);
		//BackgroundImage=new JLabel(getScaledImage(icon, 750, 400));
		//CP.add(BackgroundImage);
		//initTable(traitements.getMJouer(), "Equipes", "Nbr de Matchs");
		//CP.add(table);
		//CP.setBackground(new Color(51,170,255));

	}
	
	
	void initTable(LinkedHashMap<String, Integer> map,String a,String b) {
		table=new JTable(new ModelTable(map,a,b));
		table.setBackground(Color.LIGHT_GRAY);
		 DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); 
		  for (int i=0 ; i < table.getColumnCount() ; i++) 
			  table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}
	
	
	MainGUI() {
		
		initPanels();
		///////////////////////////// Content pane config
		Container cp=getContentPane();
		cp.setBackground(Color.BLACK);
		cp.setLayout(new BorderLayout());
		cp.add(NP, BorderLayout.NORTH);
		cp.add(new JScrollPane(WP), BorderLayout.WEST);
		cp.add(CP, BorderLayout.CENTER);

		////////////////////////////// JFrame config
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,500);
		this.setResizable(false);
		setLocationRelativeTo(null);
		setTitle("FOOTStats");

		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
	}

	public static void main(String[] args) {
		new MainGUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	
		
		if(e.getSource()==btn_Browse) {
			browser();			
		}
		if (e.getSource()==btn_download) {
			saver();
			
	
}
		
		if (e.getSource()==btn_Analyse) {
			
			traitements=new Traitement(path);
			
				enableBtns();
			
			
		}
		
		if (e.getSource()==btn_1) {
			choice=1;
			//result=new Results("Match Jouer", traitements.getMJouer(), "Equipes", "Nbr de Matchs");
			initTable(traitements.getMJouer(), "Equipes", "Nbr de Matchs");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();				
			
		}
		if (e.getSource()==btn_2) {
			choice=2;
			//result=new Results("Match Gagner", traitements.getMGagner(), "Equipes", "Nbr de Matchs");
			initTable(traitements.getMGagner(), "Equipes", "Nbr de Matchs");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();							
			
			
		}
		if (e.getSource()==btn_3) {
			choice=3;
			//result=new Results("Match Perdus", traitements.getMPerdu(), "Equipes", "Nbr de Matchs");
			initTable(traitements.getMPerdu(), "Equipes", "Nbr de Matchs");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();					
			
}
		if (e.getSource()==btn_4) {
			choice=4;
			//result=new Results("Match Null", traitements.getMNull(), "Equipes", "Nbr de Matchs");
			initTable(traitements.getMNull(), "Equipes", "Nbr de Matchs");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();			
			
			}
		if (e.getSource()==btn_5) {
			choice=5;
			//result=new Results("But Marquer", traitements.getTBut(), "Equipes", "Nbr de Buts");
			initTable(traitements.getTBut(), "Equipes", "Nbr de Buts");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();				
			
}
		if (e.getSource()==btn_6) {
			choice=6;
			//result=new Results("Scores du Tournoi", traitements.getTScore(), "Equipes", "Scores");
			initTable(traitements.getTScore(), "Equipes", "Scores");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();			
}
		if (e.getSource()==btn_7) {
			choice=7;
			//result=new Results("Classements du Tournoi", traitements.getTClass(), "Equipes", "Classements");
			initTable(traitements.getTClass(), "Equipes", "Classements");
			Container cp=getContentPane();
			cp.remove(CNP);
			cp.remove(CP);
			CNP=new JPanel();
			CNP.setBackground(Color.lightGray);
			CNP.setLayout(new BorderLayout());
			CNP.add(btn_search, BorderLayout.EAST);
			CNP.add(txt_Search, BorderLayout.CENTER);
			
			CP=new JPanel();
			CP.setBackground(Color.lightGray);
			CP.setLayout(new BorderLayout());
			CP.add(CNP, BorderLayout.NORTH);
			CP.add(new JScrollPane(table), BorderLayout.CENTER);
			cp.add(BorderLayout.CENTER, CP);
			cp.repaint();
			cp.revalidate();						
			
}
		
if(e.getSource()==btn_search) {
			if(choice!=0) {
				Container cp=getContentPane();
				LinkedHashMap<String, Integer> searchedMap;
			
				switch (choice) {
				case 1: searchedMap=search(traitements.getMJouer());
				initTable(searchedMap, "Equipes", "Nbr de Matchs");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();					
				break;
				case 2: searchedMap=search(traitements.getMGagner()) ;
				initTable(searchedMap, "Equipes", "Nbr de Matchs");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;
				case 3: searchedMap=search(traitements.getMPerdu()) ;
				initTable(searchedMap, "Equipes", "Nbr de Matchs");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;
				case 4: searchedMap=search(traitements.getMNull()) ;
				initTable(searchedMap, "Equipes", "Nbr de Matchs");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;
				case 5: searchedMap=search(traitements.getTBut()) ;
				initTable(searchedMap, "Equipes", "Nbr de Buts");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;
				case 6: searchedMap=search(traitements.getTScore()) ;
				initTable(searchedMap, "Equipes", "Score");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;
				case 7: searchedMap=search(traitements.getTClass()) ;
				initTable(searchedMap, "Equipes", "Classements");
				cp.remove(CNP);
				cp.remove(CP);
				CNP=new JPanel();
				CNP.setBackground(Color.lightGray);
				CNP.setLayout(new BorderLayout());
				CNP.add(btn_search, BorderLayout.EAST);
				CNP.add(txt_Search, BorderLayout.CENTER);
				
				CP=new JPanel();
				CP.setBackground(Color.lightGray);
				CP.setLayout(new BorderLayout());
				CP.add(CNP, BorderLayout.NORTH);
				CP.add(new JScrollPane(table), BorderLayout.CENTER);
				cp.add(BorderLayout.CENTER, CP);
				cp.repaint();
				cp.revalidate();	
				break;

				}
				
				
				
			}
			
		}
		
		
		
		
	}
	
	
	LinkedHashMap<String, Integer> search(LinkedHashMap<String, Integer> toSearchedMap){
		LinkedHashMap<String, Integer> searchedMap=new LinkedHashMap<String, Integer>();
		for(String s: toSearchedMap.keySet()) {
			if(s.toLowerCase().contains(txt_Search.getText().toLowerCase().trim())) {
				searchedMap.put(s,toSearchedMap.get(s));
			}
		}
		
		
		return searchedMap;
		
	}
	
	

}
