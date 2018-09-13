import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class addNew extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAnimalDescription;
	private JTextField txtDeviation;
	private JTextField txtDays;
	private JTextField txtTime;
	private JLabel lblFile;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addNew frame = new addNew();
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
	public addNew() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setText("");
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
		txtAnimalDescription.setText("");
		txtAnimalDescription.setBounds(128, 52, 130, 26);
		contentPane.add(txtAnimalDescription);
		txtAnimalDescription.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Standard Deviation");
		lblNewLabel.setBounds(67, 131, 124, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblParamters = new JLabel("PARAMTERS");
		lblParamters.setBounds(30, 103, 96, 16);
		contentPane.add(lblParamters);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Days");
		lblNewLabel_1.setBounds(67, 163, 111, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblOutofboundRange = new JLabel("OutofBound Range");
		lblOutofboundRange.setBounds(67, 198, 124, 16);
		contentPane.add(lblOutofboundRange);
		
		JButton btnFileInput = new JButton("Choose a File:");
		btnFileInput.setBounds(267, 98, 117, 29);
		contentPane.add(btnFileInput);
		
		String fileName = "";
		lblFile = new JLabel(fileName);
		lblFile.setBounds(394, 99, 92, 26);
		contentPane.add(lblFile);
		
		JFileChooser j = new JFileChooser(); 
		btnFileInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// fileChosen tells you when a file has been chosen
				int fileChosen = j.showOpenDialog(null);
				
				// TODO fix closing without selecting a file
				// TODO change to only accept csv files
	            if (fileChosen == 0); {
	            	String fileName = j.getSelectedFile().getAbsolutePath();
	            	lblFile.setText(fileName);
	            }

			}
		});
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(315, 193, 117, 29);
		contentPane.add(btnSubmit);
		
		txtDeviation = new JTextField();
		txtDeviation.setText("");
		txtDeviation.setBounds(172, 129, 86, 20);
		contentPane.add(txtDeviation);
		txtDeviation.setColumns(10);
		
		txtDays = new JTextField();
		txtDays.setText("");
		txtDays.setBounds(172, 161, 86, 20);
		contentPane.add(txtDays);
		txtDays.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setText("");
		txtTime.setBounds(172, 196, 86, 20);
		contentPane.add(txtTime);
		txtTime.setColumns(10);
		
		
		btnSubmit.addActionListener(new submit());
	}
	
	private class submit extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkInput()) {
				String animalName = txtName.getText();
				String animalDescription = txtAnimalDescription.getText();
				double deviation = Double.valueOf(txtDeviation.getText());
				int days = Integer.valueOf(txtDays.getText());
				double time = Double.valueOf(txtTime.getText());
				String filePath = lblFile.getText();
			}
		}
		
		// TODO check they are numbers
		private boolean checkInput() {
			if (txtName.getText() == "");
			else if (txtAnimalDescription.getText() == "");
			else if (txtDeviation.getText() == "");
			else if (txtDays.getText() == "");
			else if (txtTime.getText() == "");
			else if (lblFile.getText() == "");
			else {
				return true;
			}
			JOptionPane.showMessageDialog(null, "Please fill in all fields");
			return false;
		}
		
	}
}
