package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

public class StartFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fieldPlayers;
	private JTextField fieldSnakes;
	private JTextField fielsLadders;
	private JTextField fieldColumns;
	private JTextField fieldRows;
	private JTextField stopField;
	private JTextField prizeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	public StartFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		start.setBounds(5, 239, 440, 27);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		
		fieldPlayers = new JTextField();
		fieldPlayers.setBounds(124, 49, 58, 21);
		contentPane.add(fieldPlayers);
		fieldPlayers.setColumns(10);
		
		fieldSnakes = new JTextField();
		fieldSnakes.setBounds(124, 80, 58, 21);
		contentPane.add(fieldSnakes);
		fieldSnakes.setColumns(10);
		
		fielsLadders = new JTextField();
		fielsLadders.setBounds(123, 105, 58, 21);
		contentPane.add(fielsLadders);
		fielsLadders.setColumns(10);
		
		fieldColumns = new JTextField();
		fieldColumns.setBounds(123, 134, 59, 21);
		contentPane.add(fieldColumns);
		fieldColumns.setColumns(10);
		
		fieldRows = new JTextField();
		fieldRows.setBounds(124, 159, 58, 21);
		contentPane.add(fieldRows);
		fieldRows.setColumns(10);
		
		JLabel lblNumeroDadi = new JLabel("Numero Dadi");
		lblNumeroDadi.setBounds(12, 190, 93, 17);
		contentPane.add(lblNumeroDadi);
		
		JComboBox dacesComboBox = new JComboBox();
		dacesComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		dacesComboBox.setBounds(124, 192, 42, 17);
		contentPane.add(dacesComboBox);
		
		JLabel lblNumero = new JLabel("Numero Caselle Sosta");
		lblNumero.setBounds(225, 51, 138, 17);
		contentPane.add(lblNumero);
		
		JLabel lblNumeroCasellePremio = new JLabel("Numero Caselle Premio");
		lblNumeroCasellePremio.setBounds(225, 80, 150, 17);
		contentPane.add(lblNumeroCasellePremio);
		
		stopField = new JTextField();
		stopField.setBounds(372, 49, 50, 21);
		contentPane.add(stopField);
		stopField.setColumns(10);
		
		prizeField = new JTextField();
		prizeField.setBounds(372, 78, 50, 21);
		contentPane.add(prizeField);
		prizeField.setColumns(10);
	}
}
