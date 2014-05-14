package interface_graphique;
import circuit.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import parser.EG1;
import parser.InstantiateToken;
import parser.ParseException;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.InputStream;




public class Main extends JFrame {

	private Ferme circuit = new Ferme();
	private JPanel contentPane;
	
//	InputStream is = new ByteArrayInputStream( ";".getBytes() );
//    EG1 parser = new EG1(is);
    
	/**
	 * @wbp.nonvisual location=209,9
	 */
	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
	private final JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("TP_FINAL - MAMMAR & LE JEAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final TextField textField_fichier = new TextField();
		textField_fichier.setText("Nom de Fichier");
		textField_fichier.setBounds(10, 10, 365, 19);
		contentPane.add(textField_fichier);
		
		final TextArea textArea_affichage = new TextArea();
		textArea_affichage.setEditable(false);
		textArea_affichage.setBounds(10, 241, 505, 167);
		contentPane.add(textArea_affichage);
		lblNewJgoodiesLabel.setBounds(159, -21, 168, 31);
		contentPane.add(lblNewJgoodiesLabel);
		
		JButton btnNewButton = new JButton("Charger fichier");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(383, 10, 132, 19);
		contentPane.add(btnNewButton);
		
		final TextArea textArea = new TextArea();
		textArea.setBounds(10, 35, 505, 167);
		contentPane.add(textArea);
		
		JButton btnCharger = new JButton("Charger circuit");
		btnCharger.setFont(new Font("Dialog", Font.BOLD, 10));
		btnCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ss= textArea.getText();//"circuit Test <1|Itr{haut}->#1(2#1)> <2|Led>;";
				InstantiateToken parser = new InstantiateToken(ss);
				circuit = parser.parser();
//				InputStream is = new ByteArrayInputStream( ss.getBytes() );
//			    EG1 parser = new EG1(is);
//			    try {
//					circuit = EG1.DEF_CIRCUIT();
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			    circuit.connectAllFromList();
//			    circuit.execute();
//			    System.out.println(circuit.toString2());
			}
		});
		btnCharger.setBounds(12, 208, 117, 25);
		contentPane.add(btnCharger);
		
		JButton btnExecuter = new JButton("Executer");
		btnExecuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    circuit.connectAllFromList();
			    circuit.execute();
			    textArea_affichage.setText(circuit.toString2());
			}
		});
		btnExecuter.setBounds(398, 208, 117, 25);
		contentPane.add(btnExecuter);
		
		JButton btnClearTexte = new JButton("Clear Texte");
		btnClearTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_affichage.setText("");
			}
		});
		btnClearTexte.setBounds(141, 208, 117, 25);
		contentPane.add(btnClearTexte);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(269, 208, 117, 25);
		contentPane.add(btnNewButton_1);
		

		
		
	}
}
