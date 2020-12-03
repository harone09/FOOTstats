package footstats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6085586985121498674L;
	LinkedHashMap<String, Integer> map;
	List<String> l= new ArrayList<String>();
	private static String[] headers=new String[2];
	
	public ModelTable(LinkedHashMap<String, Integer> m,String a,String b) {
		headers[0]=a;headers[1]=b;
		map=m;
		for(String s : map.keySet()) {
			l.add(s);
		}
		
	}
	

	@Override
	public int getRowCount() {
		return map.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
        return headers[columnIndex];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0 :
			return l.get(rowIndex);
		case 1 :
			return map.get(l.get(rowIndex));
		}
		return null;
		
		
		
	}

}
