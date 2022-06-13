package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import backend.GameMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private Box[] boxes;

	private int rows, cols, N, nPlayers;
	private JTextField dadiTxt;
	private JTextField eventiField;
	private GameMap gameMap;
	/**
	 * Create the frame.
	 */
	public MainFrame(int rows, int cols, int nPlayers, GameMap gameMap) {
		if(rows<=0 || cols <=0)
			throw new IllegalArgumentException("Rows and Columns can't be negative or zero.");
		if(gameMap==null)
			throw new IllegalArgumentException("GameMap cannot be null.");
		if(nPlayers<=0)
			throw new IllegalArgumentException("Players needed");
		this.rows=rows; this.cols=cols; this.gameMap=gameMap;
		this.N=rows*cols;
		setTitle("Scale E Serpenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAutomatic = new JButton("Automatic");
		
		JButton btnNext = new JButton("Next>");
		btnNext.addActionListener(new Next());
		
		JButton btnRestert = new JButton("Restart");
		btnRestert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		MapPanel mapPanel = new MapPanel(rows, cols);
		
		JScrollPane scroll=new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		dadiTxt = new JTextField();
		dadiTxt.setEditable(false);
		dadiTxt.setColumns(10);
		
		JLabel lblDadoi = new JLabel("Dado/i:");
		
		JLabel lblEvento = new JLabel("Evento:");
		
		eventiField = new JTextField();
		eventiField.setEditable(false);
		eventiField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(btnRestert, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblDadoi, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dadiTxt, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
							.addComponent(btnAutomatic, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(126))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblEvento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(eventiField, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
					.addGap(2))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDadoi, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(dadiTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEvento)
								.addComponent(eventiField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAutomatic)
						.addComponent(btnRestert)
						.addComponent(btnNext))
					.addContainerGap())
		);
		mapPanel.setLayout(new GridLayout(cols, rows, 0, 0));
		contentPane.setLayout(gl_contentPane);
		boxes=mapPanel.getBoxes();
		for(Box b : boxes) {
			if(b==null)continue;
			updateBox(b);
		}
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	public void setRows(int rows) {
		if(rows<=0) throw new IllegalArgumentException("Rows can't be negative or zero.");
		this.rows=rows;
	}
	public void setCols(int cols) {
		if(cols<=0)throw new IllegalArgumentException("Columns can't be negative or zero.");
		this.cols=cols;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}
	
	public void updateBox(Box box) {
		int cardinal=box.getCardinal();
		String element=gameMap.getElementIn(cardinal);
		box.addElement(element);
	}
	
	private void playARound() {
		int player=gameMap.getActualPlayer();
		boxes[gameMap.getPlayerPosition(player)].clearPlayer();
		gameMap.playARound();
		eventiField.setText("Il giocatore numero "+player+ " lancia i dadi : "+gameMap.getDaceRis());
		int pos=gameMap.getPlayerPosition(player);
		boxes[pos].addPlayer(player);
		boxes[pos].repaint();
	}
	
	private void automatic() {
		
	}
	
	class Next implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			playARound();
		}
		
	}
}
