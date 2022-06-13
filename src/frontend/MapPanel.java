package frontend;

import javax.swing.JPanel;

import backend.Builder;
import backend.BuilderIF;
import backend.GameMap;

import java.awt.GridLayout;

public class MapPanel extends JPanel {
	
	private static final long serialVersionUID = -3216255672223570071L;
	private Box[] boxes;
	private int players, N;
	
	public MapPanel(int rows, int cols) {
		super();
		setLayout(new GridLayout(rows, cols, 0, 0));
		N=rows*cols;
		boxes=new Box[N+1];
		for(int i=1; i<=N; i++) {
			boxes[i]=new Box();
			boxes[i].setCardinal(i);
			this.add(boxes[i]);
		}
	}
	
	public void addPlayer(int pos, int player) {
		if(player>=players)throw new IllegalArgumentException("No such player");
		boxes[pos].addPlayer(player);
	}
	
	public void removePlayer(int pos) {
		boxes[pos].clearPlayer();
	}
	



	public void setPlayers(int players) {
		this.players = players;
	}
	
	public Box[] getBoxes() {
		return boxes;
	}

	
	
	

}
