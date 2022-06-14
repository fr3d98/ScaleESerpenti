package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import backend.GameMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAutomatic, btnNext, btnRestart;
	private JTextArea events;
	
	private Box[] boxes;

	private int rows, cols, N, nPlayers, rounds=1;
	private JTextField eventiField;
	private GameMap gameMap;
	private int toPlay=0;
	private boolean WON;
	/**
	 * Create the frame.
	 */
	
	public static void main(String...args) {
		try {
			MainFrame mf= new MainFrame(10,10,4, null);
			mf.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public MainFrame(int rows, int cols, int nPlayers, GameMap gameMap) {
		if(rows<=0 || cols <=0)
			throw new IllegalArgumentException("Rows and Columns can't be negative or zero.");
		if(gameMap==null)
			throw new IllegalArgumentException("GameMap cannot be null.");
		if(nPlayers<=0)
			throw new IllegalArgumentException("Players needed");
		this.rows=rows; this.cols=cols; this.gameMap=gameMap;
		this.N=rows*cols; this.nPlayers=nPlayers;
		setTitle("Scale E Serpenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnAutomatic = new JButton("Automatic");
		btnNext = new JButton("Next>");
		
		btnAutomatic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnNext.setEnabled(false);
				automatic();
			}
			
		});
		
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playARound();
			}
			
		});
		
		btnRestart = new JButton("Restart");
		btnRestart.setEnabled(false);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		
		MapPanel mapPanel = new MapPanel(rows, cols, nPlayers);
		JPanel eventsPanel= new JPanel();
		JScrollPane scrollEvent= new JScrollPane(eventsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JScrollPane scroll=new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		eventiField = new JTextField();
		eventiField.setEditable(false);
		eventiField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAutomatic, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRestart, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(scrollEvent, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAutomatic)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNext)
							.addGap(29)
							.addComponent(btnRestart)
							.addGap(13))
						.addComponent(scrollEvent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(0))
		);
		events = new JTextArea();
		events.setRows(7);
		events.setColumns(40);
		events.setEditable(false);
		eventsPanel.add(events);
		mapPanel.setLayout(new GridLayout(cols, rows, 0, 0));
		contentPane.setLayout(gl_contentPane);
		boxes=mapPanel.getBoxes();
		if (boxes!=null) {
			for (Box b : boxes) {
				if (b == null)
					continue;
				updateBox(b);
			}
			boxes[1].addAllPlayers();
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
		int lastPos=gameMap.getPlayerPosition(toPlay);
		String event=gameMap.playARound(toPlay);
		int x=toPlay+1;
		if(toPlay==0) {
			events.append("ROUND "+rounds+'\n');
			rounds++;
		}
		events.append(event);
		events.append(""+'\n');
		int pos=gameMap.getPlayerPosition(toPlay);
		if(boxes!=null)
			boxes[pos].addPlayer(toPlay);
		if(pos!=lastPos && boxes!=null)//se non ci sono stati movimenti lascia il giocatore dov'è
			boxes[lastPos].clearPlayer(toPlay);
		if(pos==N) {
			won();
			JOptionPane.showMessageDialog(contentPane, "Il giocatore "+x+" ha vinto!");
		}
		toPlay=(++toPlay)%nPlayers;
	}
	
	private void automatic() {
		while(!WON) {
			playARound();
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
	private void won() {
		WON=true;
		btnAutomatic.setEnabled(false);
		btnNext.setEnabled(false);
		btnRestart.setEnabled(true);
	}
	
	private void restart() {
		
	}
	
}
