package frontend;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Box extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int cardinal;
	private JPanel number, player, element;
	
	
	public Box() {
		super();
		this.setSize(10, 10);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(3,1,0,0));
		number=new JPanel();
		element=new JPanel();
		player=new JPanel();
		this.add(number); this.add(player); this.add(element);
		
	}

	public int getCardinal() {
		return cardinal;
	}

	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
		number.add(new JLabel(""+cardinal));
	}
	
	
	public void addPlayer(int i) {
		player.add(new JLabel("Player "+i));
	}
	
	public void clearPlayer() {
		player.removeAll();
	}
	
	public void addElement(String txt) {
		element.add(new JLabel(txt));
	}
	
	public void removeElement() {
		element.removeAll();
	}
	

}
