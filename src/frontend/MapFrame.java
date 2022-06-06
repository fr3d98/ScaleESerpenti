package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import java.awt.GridLayout;

public class MapFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapFrame frame = new MapFrame();
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
	public MapFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAutomatic = new JButton("Automatic");
		btnAutomatic.setBounds(12, 232, 105, 27);
		contentPane.add(btnAutomatic);
		
		JButton btnNext = new JButton("Next>");
		btnNext.setBounds(316, 232, 105, 27);
		contentPane.add(btnNext);
		
		JButton btnRestert = new JButton("Restart");
		btnRestert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRestert.setBounds(158, 232, 105, 27);
		contentPane.add(btnRestert);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(71, 163, 1, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 426, 227);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
	}
}
