package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import backend.GameMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAutomatic, btnNext, btnRestart;
	private JTextArea events;
	
	private Box[] boxes;

	private int rows, cols, N, nPlayers;
	private JTextField eventiField;
	private GameMap gameMap;
	private boolean WON=false;
	private JMenuBar menuBar;
	private JButton btnSalva;
	
	/**
	 * Create the frame.
	 */	
	public MainFrame(GameMap gameMap) {
		if(gameMap==null)
			throw new IllegalArgumentException("GameMap cannot be null.");
		rows=gameMap.getnRows(); System.out.println("righe "+rows);
		cols=gameMap.getnCols(); System.out.println("cols "+cols);
		this.gameMap=gameMap;
		nPlayers=gameMap.getPlayersNumber(); System.out.println("players "+nPlayers);
		if(rows<=0 || cols <=0)
			throw new IllegalArgumentException("Rows and Columns can't be negative or zero.");
		if(nPlayers<=0)
			throw new IllegalArgumentException("Players needed");
		this.N=rows*cols;
		setTitle("Scale E Serpenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 548);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int res = jfc.showSaveDialog(null);
				if(res==JFileChooser.APPROVE_OPTION) {
					File f = jfc.getSelectedFile();
					if(f.exists())
						if(JOptionPane.showConfirmDialog(jfc, "Sovrascrivere "+f.getName()+" ?")==JOptionPane.OK_OPTION);
						else return;
					String name= f.getAbsolutePath();
					if(!name.endsWith(".dat")) name+=".dat";
					try {
						FileOutputStream fos= new FileOutputStream(name);
						ObjectOutputStream oos= new ObjectOutputStream(fos);
						oos.writeObject(gameMap.save());
						oos.close(); fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
						
				}
			}
			
		});
		menuBar.add(btnSalva);
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
		
		MapPanel mapPanel = new MapPanel(gameMap);
		events=new JTextArea();
		events.setEditable(false);
		
		JScrollPane scrollEvent= new JScrollPane(events, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JScrollPane scroll=new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		eventiField = new JTextField();
		eventiField.setEditable(false);
		eventiField.setColumns(10);
		
		JLabel lblNumeroDiDadi = new JLabel("Numero di dadi: "+gameMap.getnDaces());
		
		JLabel lblVarianteDoppio = new JLabel();
		if(gameMap.isDoubleSix()) lblVarianteDoppio.setText("Variante Doppio 6: Sì");
		else lblVarianteDoppio.setText("Variante Doppio 6: No");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAutomatic, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRestart, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(scrollEvent, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumeroDiDadi)
						.addComponent(lblVarianteDoppio))
					.addGap(77))
				.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAutomatic)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNext)
									.addGap(29)
									.addComponent(btnRestart)
									.addGap(13))
								.addComponent(scrollEvent, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNumeroDiDadi)
							.addGap(18)
							.addComponent(lblVarianteDoppio))))
		);
		mapPanel.setLayout(new GridLayout(rows, cols, 0, 0));
		contentPane.setLayout(gl_contentPane);
		boxes=mapPanel.getBoxes();
		if (boxes!=null) {
			for (Box b : boxes) {
				if (b == null)
					continue;
				updateBox(b);
			}
			for(int i=0; i<nPlayers; i++) {
				int pos=gameMap.getPlayerPosition(i);
				boxes[pos].addPlayer(i);
			}
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
		int actPlayer=gameMap.getActPlayer();
		int lastPos=gameMap.getPlayerPosition(actPlayer);
		String event=gameMap.playARound();
		int x=actPlayer+1;
		events.append(event);
		events.append(""+'\n');
		int pos=gameMap.getPlayerPosition(actPlayer);
		if(boxes!=null)
			boxes[pos].addPlayer(actPlayer);
		if(pos!=lastPos && boxes!=null)//se non ci sono stati movimenti lascia il giocatore dov'è
			boxes[lastPos].clearPlayer(actPlayer);
		if(pos==N) {
			won();
			JOptionPane.showMessageDialog(contentPane, "Il giocatore "+x+" ha vinto!");
		}
	}
	
	private void automatic() {
		while(!WON) {
			playARound();
		}
	}
	
	private void won() {
		WON=true;
		btnAutomatic.setEnabled(false);
		btnNext.setEnabled(false);
		btnRestart.setEnabled(true);
	}
	
	private void restart() {
		gameMap.restart();
		WON=false;
		btnAutomatic.setEnabled(true);
		btnNext.setEnabled(true);
		btnRestart.setEnabled(false);
		events.setText("");
	}
}
