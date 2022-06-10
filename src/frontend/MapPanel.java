package frontend;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class MapPanel extends JPanel{
	
	private static final long serialVersionUID = -3216255672223570071L;
	private Box[] boxes;
	private int rows,cols, N;
	
	public MapPanel(int rows, int cols) {
		super();
		this.rows=rows; this.cols=cols;
		setLayout(new GridLayout(rows, cols, 0, 0));
		N=rows*cols;
		boxes=new Box[N];
		for(int i=0; i<N; i++) {
			boxes[i]=new Box();
			boxes[i].setCardinal(i+1);
			this.add(boxes[i]);
		}
	}
	
	public void addPlayer(int pos, int player) {
		boxes[pos].addPlayer(player);
	}
	
	public void removePlayer(int pos) {
		boxes[pos].clearPlayer();
	}
	
	public void addElement(int pos, String element) {
		boxes[pos].addElement(element);
	}
	
	

}
