/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

/**
 *
 * @author y2434
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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


    private void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 512, 348);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setText();
        setDescription();
        setLogID();
        setParameters();
        setDeviation();
        setDaysOutBound();
        setTimeOutOfBound();
        file();

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(315, 193, 117, 29);
        contentPane.add(btnSubmit);

        btnSubmit.addActionListener(new submit());

    }
    /**
     * Create the frame.
     */
    public addNew() {
        init();
    }

    private void setText(){
        txtName = new JTextField();
        txtName.setText("");
        txtName.setBounds(106, 11, 186, 32);
        contentPane.add(txtName);
        txtName.setColumns(10);
    }

    private void setLogID(){
        JLabel lblAddLogId = new JLabel("ADD LOG ID:");
        lblAddLogId.setBounds(13, 19, 89, 16);
        contentPane.add(lblAddLogId);

    }

    private void setParameters(){
        JLabel lbjParameters = new JLabel("PARAMTERS");
        lbjParameters.setBounds(30, 103, 96, 16);
        contentPane.add(lbjParameters);
    }

    private void setDeviation(){
        JLabel lblNewLabel = new JLabel("Standard Deviation");
        lblNewLabel.setBounds(67, 131, 124, 16);
        contentPane.add(lblNewLabel);

        txtDeviation = new JTextField();
        txtDeviation.setText("");
        txtDeviation.setBounds(172, 129, 86, 20);
        contentPane.add(txtDeviation);
        txtDeviation.setColumns(10);
    }

    private void setDaysOutBound(){
        JLabel lblNewLabel_1 = new JLabel("Number of Days");
        lblNewLabel_1.setBounds(67, 163, 111, 16);
        contentPane.add(lblNewLabel_1);

        txtDays = new JTextField();
        txtDays.setText("");
        txtDays.setBounds(172, 161, 86, 20);
        contentPane.add(txtDays);
        txtDays.setColumns(10);
    }

    private void setTimeOutOfBound(){
        JLabel lblOutofboundRange = new JLabel("OutofBound Range");
        lblOutofboundRange.setBounds(67, 198, 124, 16);
        contentPane.add(lblOutofboundRange);

        txtTime = new JTextField();
        txtTime.setText("");
        txtTime.setBounds(172, 196, 86, 20);
        contentPane.add(txtTime);
        txtTime.setColumns(10);
    }

    private void setDescription(){
        JLabel lblLogDescription = new JLabel("LOG DESCRIPTION:");
        lblLogDescription.setBounds(10, 57, 124, 16);
        contentPane.add(lblLogDescription);

        txtAnimalDescription = new JTextField();
        txtAnimalDescription.setText("");
        txtAnimalDescription.setBounds(128, 52, 130, 26);
        contentPane.add(txtAnimalDescription);
        txtAnimalDescription.setColumns(10);
    }

    private void file(){
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
                j.setAcceptAllFileFilterUsed(false);
                j.setFileFilter(new FileNameExtensionFilter("Csv files", "csv"));

                // fileChosen tells you when a file has been chosen
                int fileChosen = j.showOpenDialog(null);
                if (fileChosen == 0); {
                    try {
                        String fileName = j.getSelectedFile().getAbsolutePath();
                        lblFile.setText(fileName);
                    } catch (NullPointerException ex) {
                    }
                }

            }
        });
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
                    String filePath = lblFile.getText();

                    //Object[] data = {animalName, animalDescription, deviation, days, time, filePath};
                    //serialization ser = new serialization(data, "C:\\Users\\jarro\\Documents\\Uni\\Computer Science\\Eclipse\\CITS3200","test");
                    //ser.SerializeObject();

                    JOptionPane.showMessageDialog(null, "Added successfully");
                    //close();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please check number fields only contain numbers");
                }
            }
        }

        private boolean checkInput() {
            AtomicBoolean returnSt = new AtomicBoolean(true);
            if (txtName.getText().equals("")) {
                returnSt.set(false);
            } else if (txtAnimalDescription.getText().equals("") || txtDeviation.getText().equals("")) returnSt.set(false);
            else if (txtDays.getText().equals("")) {
                returnSt.set(false);
            } else if (txtTime.getText().equals("")) {
                returnSt.set(false);
            } else if (lblFile.getText().equals("")) {
                returnSt.set(false);
            }
            if(!returnSt.get()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields and select a file");
            }
            return returnSt.get();
        }

    }
}