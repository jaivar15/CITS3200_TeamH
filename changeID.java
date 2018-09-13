import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class changeID extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAnimalDescription;
	private JTextField txtDeviation;
	private JTextField txtDays;
	private JTextField txtTime;
	
	private String animalName;
	private String animalDescription;
	private double deviation;
	private int days;
	private double time;
	private String filePath;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String filename = "C:\\\\Users\\\\jarro\\\\Documents\\\\Uni\\\\Computer Science\\\\Eclipse\\\\CITS3200/test.dat";
					changeID frame = new changeID(filename);
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
	public changeID(String filename) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		readFile(filename);
		
		txtName = new JTextField();
		txtName.setText(animalName);
		txtName.setBounds(106, 11, 186, 32);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		
		JLabel lblAddLogId = new JLabel("ADD LOG ID:");
		lblAddLogId.setBounds(13, 19, 89, 16);
		contentPane.add(lblAddLogId);
		
		JLabel lblLogDescription = new JLabel("LOG DESCRIPTION:");
		lblLogDescription.setBounds(10, 57, 124, 16);
		contentPane.add(lblLogDescription);
		
		txtAnimalDescription = new JTextField();
		txtAnimalDescription.setText(animalDescription);
		txtAnimalDescription.setBounds(128, 52, 130, 26);
		contentPane.add(txtAnimalDescription);
		txtAnimalDescription.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Standard Deviation");
		lblNewLabel.setBounds(67, 131, 124, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblParamters = new JLabel("PARAMETERS");
		lblParamters.setBounds(30, 103, 96, 16);
		contentPane.add(lblParamters);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Days");
		lblNewLabel_1.setBounds(67, 163, 111, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblOutofboundRange = new JLabel("OutofBound Range");
		lblOutofboundRange.setBounds(67, 198, 124, 16);
		contentPane.add(lblOutofboundRange);
		
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(315, 193, 117, 29);
		contentPane.add(btnSubmit);
		
		txtDeviation = new JTextField();
		txtDeviation.setText(Double.toString(deviation));
		txtDeviation.setBounds(172, 129, 86, 20);
		contentPane.add(txtDeviation);
		txtDeviation.setColumns(10);
		
		txtDays = new JTextField();
		txtDays.setText(Integer.toString(days));
		txtDays.setBounds(172, 161, 86, 20);
		contentPane.add(txtDays);
		txtDays.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setText(Double.toString(time));
		txtTime.setBounds(172, 196, 86, 20);
		contentPane.add(txtTime);
		txtTime.setColumns(10);
		
		
		btnSubmit.addActionListener(new submit());
	}
	
	private class submit extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkInput()) {
				try {
					String animalName = txtName.getText();
					String animalDescription = txtAnimalDescription.getText();
					double deviation = Double.valueOf(txtDeviation.getText());
					int days = Integer.valueOf(txtDays.getText());
					double time = Double.valueOf(txtTime.getText());
					Object[] data = {animalName, animalDescription, deviation, days, time, filePath};
					serialization ser = new serialization(data, "C:\\\\Users\\\\jarro\\\\Documents\\\\Uni\\\\Computer Science\\\\Eclipse\\\\CITS3200","test");
					ser.SerializeObject();
					JOptionPane.showMessageDialog(null, "Updated successfully");
					close();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please check number fields only contain numbers");
				}
			}
		}
		
		private boolean checkInput() {
			if (txtName.getText() == "");
			else if (txtAnimalDescription.getText() == "");
			else if (txtDeviation.getText() == "");
			else if (txtDays.getText() == "");
			else if (txtTime.getText() == "");
			else {
				return true;
			}
			JOptionPane.showMessageDialog(null, "Please fill in all fields");
			return false;
		}
		
	}
	
	private void close() {
		this.dispose();
	}
	
	private void readFile(String filename) {
		Object[] o = null;
		deserialization deser = new deserialization(filename);
		deser.deserializeObject();
		o = deser.getData();
		if( o == null) {
			System.out.println("error");
		}
		animalName = (String)o[0];
		animalDescription = (String)o[1];
		deviation = (double)o[2];
		days = (int)o[3];
		time = (double)o[4];
		filePath = (String)o[5];
	}
}