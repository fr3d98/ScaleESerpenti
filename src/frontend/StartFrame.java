package frontend;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.Builder;
import backend.BuilderIF;
import backend.GameMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = -3244632887623664140L;
	private JPanel contentPane;
	private JTextField fieldPlayers;
	private JTextField fieldSnakes;
	private JTextField fieldLadders;
	private JTextField fieldColumns;
	private JTextField fieldRows;
	private JTextField fieldBench;
	private JTextField fieldSpring;
	private JTextField fieldRest;
	private JTextField fieldDace;
	private JTextField fieldCards;
	@SuppressWarnings("rawtypes")
	private JComboBox dacesComboBox;
	private JCheckBox randomSnakes, randomLadders, randomBenches, randomCards, randomSprings,
	randomDaces, randomRests, doubleSix;
	
	private BuilderIF builder;
	private int nRows, nCols, nPlayers;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		init();
	}

	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StartFrame() {
		setTitle("Scale E Serpenti");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 476);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton loadBtn = new JButton("Carica da File");
		loadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileFilter ff = new FileNameExtensionFilter("Data File", "dat");
				jfc.setFileFilter(ff);
				int result=jfc.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					try {
						File f = jfc.getSelectedFile();
						FileInputStream fis= new FileInputStream(f);
						ObjectInputStream ois= new ObjectInputStream(fis);
						Object o=ois.readObject();
						ois.close(); fis.close();
						if(!(o instanceof GameMap.GameMapState)) throw new IllegalStateException("File corrupt.");
						GameMap.GameMapState gms= (GameMap.GameMapState) o;
						builder=new Builder();
						GameMap gm= builder.getGameMap();
						gm.restore(gms);
						openMainFrame(gm);
					}catch(IOException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
			
		});
		menuBar.add(loadBtn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrBenvenutoInscale = new JTextArea();
		txtrBenvenutoInscale.setBounds(5, 5, 440, 17);
		txtrBenvenutoInscale.setEditable(false);
		txtrBenvenutoInscale.setText("Benvenuto in \"Scale e Serpenti\"! Scegli i parametri ed avvia la simulazione.");
		contentPane.add(txtrBenvenutoInscale);
		
		JButton start = new JButton("Inizia simulazione");
		start.setBounds(12, 386, 425, 27);
		start.addActionListener(new SimulationStarted());
		contentPane.add(start);
		
		JLabel lblNumeroGiocatori = new JLabel("Numero giocatori");
		lblNumeroGiocatori.setBounds(12, 51, 116, 17);
		contentPane.add(lblNumeroGiocatori);
		
		JLabel lblNumeroSerpenti = new JLabel("Numero Serpenti");
		lblNumeroSerpenti.setBounds(12, 80, 103, 17);
		contentPane.add(lblNumeroSerpenti);
		
		JLabel lblNumeroScale = new JLabel("Numero Scale");
		lblNumeroScale.setBounds(12, 107, 93, 17);
		contentPane.add(lblNumeroScale);
		
		JLabel lblNumeroColonne = new JLabel("Numero Colonne");
		lblNumeroColonne.setBounds(12, 136, 103, 17);
		contentPane.add(lblNumeroColonne);
		
		JLabel lblNumeroRighe = new JLabel("Numero Righe");
		lblNumeroRighe.setBounds(12, 161, 103, 17);
		contentPane.add(lblNumeroRighe);
		
		fieldPlayers = new JTextField("4");
		fieldPlayers.setBounds(124, 49, 58, 21);
		contentPane.add(fieldPlayers);
		fieldPlayers.setColumns(10);
		
		fieldSnakes = new JTextField("10");
		fieldSnakes.setBounds(124, 80, 58, 21);
		contentPane.add(fieldSnakes);
		fieldSnakes.setColumns(10);
		
		fieldLadders = new JTextField("10");
		fieldLadders.setBounds(123, 105, 58, 21);
		contentPane.add(fieldLadders);
		fieldLadders.setColumns(10);
		
		fieldColumns = new JTextField("10");
		fieldColumns.setBounds(123, 134, 59, 21);
		contentPane.add(fieldColumns);
		fieldColumns.setColumns(10);
		
		fieldRows = new JTextField("10");
		fieldRows.setBounds(124, 159, 58, 21);
		contentPane.add(fieldRows);
		fieldRows.setColumns(10);
		
		JLabel lblNumeroDadi = new JLabel("Numero Dadi");
		lblNumeroDadi.setBounds(12, 295, 93, 17);
		contentPane.add(lblNumeroDadi);
		
		dacesComboBox = new JComboBox();
		dacesComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		dacesComboBox.setBounds(106, 295, 42, 17);
		dacesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dacesComboBox.getSelectedItem()=="1") doubleSix.setSelected(false);
			}
			
		});
		contentPane.add(dacesComboBox);
		
		JLabel lblNumero = new JLabel("Numero Caselle Panchine");
		lblNumero.setBounds(225, 51, 154, 17);
		contentPane.add(lblNumero);
		
		JLabel lblNumeroCasellePremio = new JLabel("Numero Caselle Molla");
		lblNumeroCasellePremio.setBounds(12, 190, 150, 17);
		contentPane.add(lblNumeroCasellePremio);
		
		fieldBench = new JTextField("5");
		fieldBench.setBounds(386, 49, 51, 21);
		contentPane.add(fieldBench);
		fieldBench.setColumns(10);
		
		fieldSpring = new JTextField("5");
		fieldSpring.setBounds(149, 188, 50, 21);
		contentPane.add(fieldSpring);
		fieldSpring.setColumns(10);
		
		JLabel lblNumeroCaselleLocande = new JLabel("Numero caselle Locande");
		lblNumeroCaselleLocande.setBounds(225, 80, 154, 17);
		contentPane.add(lblNumeroCaselleLocande);
		
		fieldRest = new JTextField("5");
		fieldRest.setBounds(386, 78, 51, 21);
		contentPane.add(fieldRest);
		fieldRest.setColumns(10);
		
		JLabel lblNumeroCaselleDadi = new JLabel("Numero caselle Dadi");
		lblNumeroCaselleDadi.setBounds(12, 221, 143, 17);
		contentPane.add(lblNumeroCaselleDadi);
		
		fieldDace = new JTextField("5");
		fieldDace.setBounds(149, 219, 58, 21);
		contentPane.add(fieldDace);
		fieldDace.setColumns(10);
		
		JLabel lblNumeroCaselle = new JLabel("Numero caselle Carte");
		lblNumeroCaselle.setBounds(12, 250, 136, 17);
		contentPane.add(lblNumeroCaselle);
		
		fieldCards = new JTextField("5");
		fieldCards.setBounds(151, 250, 58, 21);
		contentPane.add(fieldCards);
		fieldCards.setColumns(10);
		
		randomSnakes = new JCheckBox("Generazione Random Serpenti");
		randomSnakes.setBounds(225, 103, 220, 25);
		contentPane.add(randomSnakes);
		randomSnakes.setSelected(true);
		
		randomLadders = new JCheckBox("Generazione Random Scale");
		randomLadders.setBounds(225, 132, 197, 25);
		contentPane.add(randomLadders);
		randomLadders.setSelected(true);
		
		randomDaces = new JCheckBox("Generazione random Dadi");
		randomDaces.setBounds(225, 157, 197, 25);
		contentPane.add(randomDaces);
		randomDaces.setSelected(true);
		
		randomBenches = new JCheckBox("Generazione random Panchine");
		randomBenches.setBounds(225, 186, 216, 25);
		contentPane.add(randomBenches);
		randomBenches.setSelected(true);
		
		randomRests = new JCheckBox("Generazione random Locande");
		randomRests.setBounds(225, 217, 203, 25);
		contentPane.add(randomRests);
		randomRests.setSelected(true);
		
		randomSprings = new JCheckBox("Generazione random Molle");
		randomSprings.setBounds(225, 246, 197, 25);
		contentPane.add(randomSprings);
		randomSprings.setSelected(true);
		
		randomCards = new JCheckBox("Generazione random Carte");
		randomCards.setBounds(225, 275, 197, 25);
		contentPane.add(randomCards);
		randomCards.setSelected(true);
		
		doubleSix = new JCheckBox("Doppio 6");
		doubleSix.setBounds(12, 332, 114, 25);
		doubleSix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(doubleSix.isSelected()) dacesComboBox.setSelectedIndex(1);
			}
			
		});
		contentPane.add(doubleSix);
	}
	
	
	private void openMainFrame(GameMap gm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mf= new MainFrame(gm);
					mf.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.dispose();
	}
	
	class SimulationStarted implements ActionListener{
		int nSnakes,nLadders,nCards,nSprings,nBenches,nRests,nDaceBoxes, nDaces;
		int [][] snakes, ladders;
		int[] cards,springs,daces,benches,rests;
		
		private int getQuantity(String s) {
			int ris;
			try {
				ris = Integer.parseInt(s.trim());
				if (ris < 0)
					throw new IllegalArgumentException("Non si possono avere quantità negative!");
				return ris;
			} catch (IllegalArgumentException IAE) {
				JOptionPane.showMessageDialog(rootPane, IAE);
			} 
		return 0;
		}
		
		private int[][] takeMoverPositions(String element, int len){
			boolean inError=false;
			do{
				try {
					String s= JOptionPane.showInputDialog("Inserisci le poszioni dei "+
				element+" tra ',' e separate da uno spazio. ").trim();
					String[] a = s.split(" ");
					if(a.length!=len)
						throw new IllegalArgumentException("Elements number mismatch.");
					Set<Integer> values= new HashSet<>();
					int[][] ris=new int[a.length][2];
					for(int i=0; i<a.length; i++) {
						String [] part= a[i].trim().split(",");
						if(part.length!=2)throw new IllegalArgumentException("Elements position in map is badly written.");
						for(int j=0; j<2; j++) {
							int val=Integer.parseInt(part[j]);
							if(val<=0 || val> nRows*nCols)throw new IllegalArgumentException("Must enter a valid value.");
							ris[i][j]=val;
							if(values.contains(val))throw new IllegalArgumentException("Values must be unique.");
							values.add(val);
						}
						Arrays.sort(ris[i]);
					}
					inError=false;
					return ris;
				}catch(IllegalArgumentException IAE) {
					JOptionPane.showMessageDialog(rootPane, IAE);
					inError=true;
				}
				
			}while(inError);
			return null;
		}
		
		private int[] takePositions(String element, int len) {
			boolean inError=false;
			do {
				try {
					String s=JOptionPane.showInputDialog("Inserisci le posizioni di "+element+
							" separate da spazi.").trim();
					String[] a =s.split(" ");
					if(a.length!=len)
						throw new IllegalArgumentException("Length mismatch.");
					Set<Integer> values= new HashSet<>();
					int[] pos=new int[len];
					for(int i=0; i<len; i++) {
						pos[i]=Integer.parseInt(a[i]);
						if(pos[i]<=0 || pos[i]>nRows*nCols) throw new IllegalArgumentException("Enter valid values!");
						if(values.contains(pos[i]))throw new IllegalArgumentException("Values must be unique!");
						values.add(pos[i]);
					}
					inError=false;
					return pos;
				}catch(IllegalArgumentException IAE) {
					inError=true;
					JOptionPane.showMessageDialog(rootPane,IAE);
				}
			}while(inError);
			return null;
		}
		
		private void construct() {
			builder=new Builder();
			builder.buildSpace(nRows, nCols);
			builder.buildDaces(nDaces);
			builder.buildPlayers(nPlayers);
			builder.buildDeck();
			
			if(!randomSnakes.isSelected()) {
				boolean inError=false;
				do {
					try {
						snakes = takeMoverPositions("Serpenti", nSnakes);
						builder.buildSnakes(snakes);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			
			if(!randomLadders.isSelected()) {
				boolean inError=false;
				do {
					try {
						ladders=takeMoverPositions("Scale", nLadders);
						builder.buildLadders(ladders);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			
			if(!randomCards.isSelected()) {
				boolean inError=false;
				do {
					try {
						cards=takePositions("Carte", nCards);
						builder.buildCardBoxes(cards);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			if(!randomSprings.isSelected()) {
				boolean inError=false;
				do {
					try {
						springs=takePositions("Molle", nSprings);
						builder.buildSpringBoxes(springs);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			if(!randomDaces.isSelected()) {
				boolean inError=false;
				do {
					try {
						daces=takePositions("Caselle Dadi", nDaceBoxes);
						builder.buildDaceBoxes(daces);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			if(!randomBenches.isSelected()) {
				boolean inError=false;
				do {
					try {
						benches=takePositions("Panchine", nBenches);
						builder.buildBenches(benches);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			if(!randomRests.isSelected()) {
				boolean inError=false;
				do {
					try {
						rests=takePositions("Locande",nRests);
						builder.buildRests(rests);
						inError=false;
					} catch (IllegalArgumentException IAE) {
						inError=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (inError);
			}
			
			if(randomSnakes.isSelected()) builder.buildSnakesRandom(nSnakes);
			if(randomLadders.isSelected()) builder.buildLaddersRandom(nLadders);
			if(randomCards.isSelected()) builder.buildCardBoxesRandom(nCards);
			if(randomSprings.isSelected())builder.buildSpringBoxesRandom(nSprings);
			if(randomDaces.isSelected()) builder.buildDaceBoxesRandom(nDaceBoxes);
			if(randomBenches.isSelected()) builder.buildBenchesRandom(nBenches);
			if(randomRests.isSelected()) builder.buildRestsRandom(nRests);
			
			if(doubleSix.isSelected()) builder.buildDoubleSix();
			
			openMainFrame(builder.getGameMap());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			nPlayers=getQuantity(fieldPlayers.getText());
			nRows=getQuantity(fieldRows.getText());
			nCols=getQuantity(fieldColumns.getText());
			nSnakes=getQuantity(fieldSnakes.getText());
			nLadders=getQuantity(fieldLadders.getText());
			nSprings=getQuantity(fieldSpring.getText());
			nCards=getQuantity(fieldCards.getText());
			nBenches=getQuantity(fieldBench.getText());
			nRests=getQuantity(fieldRest.getText());
			nDaceBoxes=getQuantity(fieldDace.getText());
			
			nDaces=Integer.parseInt((String)dacesComboBox.getSelectedItem());
			
			int elementsNumber=(nSnakes+nLadders)*2+nSprings+nCards+nBenches+nRests+nDaceBoxes;
			
			if(elementsNumber> nRows*nCols) {
				JOptionPane.showMessageDialog(rootPane, "La mappa non può contenere tutti questi elementi!");
				return;
			}
			
			construct();
		}
		
	}
}
		
	
