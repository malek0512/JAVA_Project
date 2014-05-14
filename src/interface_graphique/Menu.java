package interface_graphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.TextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.TextField;

public class Menu extends JFrame {

//	private JPanel contentPane;
//	/**
//	 * @wbp.nonvisual location=209,9
//	 */
//	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
//	private final JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("TP_FINAL - MAMMAR & LE JEAN & ARAME & ADAM");
		getContentPane().setLayout(null);
		
		JButton btnTestOprateur = new JButton("Test Opérateur");
		btnTestOprateur.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnTestOprateur.setBounds(362, 46, 117, 25);
		getContentPane().add(btnTestOprateur);
		
		JButton btnTestSpecification = new JButton("Test Specification");
		btnTestSpecification.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnTestSpecification.setBounds(362, 78, 117, 25);
		getContentPane().add(btnTestSpecification);
		
		JButton btnTestCircuit = new JButton("Test Circuit");
		btnTestCircuit.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnTestCircuit.setBounds(362, 111, 117, 25);
		getContentPane().add(btnTestCircuit);
		
		textField = new JTextField();
		textField.setBounds(80, 15, 399, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnClearAll.setBounds(12, 144, 74, 19);
		getContentPane().add(btnClearAll);
		
		JButton btnClearTexte = new JButton("Clear Texte");
		btnClearTexte.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnClearTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnClearTexte.setBounds(98, 144, 90, 19);
		getContentPane().add(btnClearTexte);
		
		JButton btnClearGraphic = new JButton("Clear Graphic");
		btnClearGraphic.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnClearGraphic.setBounds(200, 144, 100, 19);
		getContentPane().add(btnClearGraphic);
		
		JLabel lblCo = new JLabel("Opérateur");
		lblCo.setFont(new Font("Dialog", Font.BOLD, 10));
		lblCo.setBounds(12, 17, 70, 15);
		getContentPane().add(lblCo);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(12, 182, 467, 158);
		getContentPane().add(textArea_1);
		
		TextField textField_1 = new TextField();
		textField_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		textField_1.setText("                        Entrez votre circuit");
		textField_1.setBounds(12, 46, 332, 90);
		getContentPane().add(textField_1);
		
	}
}
