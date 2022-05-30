package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Builder;
import backend.BuilderIF;
import backend.GameMap;

public class GUIc implements GUI {
	
	private JFrame frame;
	
	private JPanel panel;
	
	private GameMap map;
	
	private BuilderIF builder;
	
	private int Nplayers;
	
	private void initialize() {
		builder=new Builder();
		map=builder.getGameMap();
	}
	
	

}
