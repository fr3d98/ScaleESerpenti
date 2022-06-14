package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Box extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int cardinal;
	private JPanel number, player, element;
	private JLabel[] players;
	
	
	public Box(int nPlayers) {
		super();
		this.setSize(10, 10);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(3,1,0,0));
		players=new JLabel[nPlayers];
		number=new JPanel();
		element=new JPanel();
		player=new JPanel();
		this.add(number); this.add(player); this.add(element);
		for(int i=0; i< nPlayers; i++) {
			int x=i+1;
			players[i]=new JLabel("Giocatore "+x);
			player.add(players[i]);
			players[i].setVisible(false);
		}
	}

	public int getCardinal() {
		return cardinal;
	}

	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
		number.add(new JLabel(""+cardinal));
	}
	
	
	public void addPlayer(int i) {
		players[i].setVisible(true);
		player.revalidate();
	}
	
	public void clearPlayer(int i) {
		players[i].setVisible(false);
		player.revalidate();
	}
	
	public void addElement(String txt) {
		element.add(new JLabel(txt));
	}
	
	public void removeElement() {
		element.removeAll();
	}
	
	public void addAllPlayers() {
		for(JLabel player : players)
			player.setVisible(true);
		player.revalidate();
	}
	

}
