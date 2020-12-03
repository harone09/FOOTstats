package footstats;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Results extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262207061137844632L;
	
	private JTable table;
	
	void initTable(LinkedHashMap<String, Integer> map,String a,String b) {
		table=new JTable(new ModelTable(map,a,b));
		 DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); 
		  for (int i=0 ; i < table.getColumnCount() ; i++) 
			  table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}
	
	
	
	public Results(String name,LinkedHashMap<String, Integer> map0,String a0,String b0) {
		setTitle(name);
		setSize(400,300);
		setLocationRelativeTo(null);
		initTable(map0, a0, b0);
		Container cp=getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new JScrollPane(table), BorderLayout.CENTER);
		setVisible(true);
		
	}

}
