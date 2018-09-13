import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class selectID extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectID frame = new selectID();
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
	public selectID() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(112, 18, 186, 32);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAnimalId = new JLabel("Animal ID:");
		lblAnimalId.setBounds(30, 26, 98, 16);
		contentPane.add(lblAnimalId);
		
		textField = new JTextField();
		textField.setBounds(112, 77, 299, 66);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(30, 62, 98, 16);
		contentPane.add(lblDescription);
		
		JLabel lblDeviation = new JLabel("Deviation ");
		lblDeviation.setBounds(41, 168, 61, 16);
		contentPane.add(lblDeviation);
		
		JLabel lblNumberOfDays = new JLabel("Number of Days ");
		lblNumberOfDays.setBounds(30, 196, 61, 16);
		contentPane.add(lblNumberOfDays);
		
		JLabel lblTimeOutOf = new JLabel("TIme Out of Bound");
		lblTimeOutOf.setBounds(30, 222, 146, 16);
		contentPane.add(lblTimeOutOf);
		
		JLabel lblExecutationTime = new JLabel("Executation Time:");
		lblExecutationTime.setBounds(30, 240, 123, 16);
		contentPane.add(lblExecutationTime);
		
		textField_1 = new JTextField();
		textField_1.setBounds(154, 163, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(154, 191, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(154, 217, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(154, 250, 130, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
	}
}

