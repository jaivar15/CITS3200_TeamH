package swing;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 *
 * @author varunjain
 */
public class ChangeDev extends javax.swing.JFrame {

    /**
     * Creates new form standanddeviationchange
     */
    public ChangeDev() {
    	home = null;
        initComponents();
    }
    
    public ChangeDev(Home h) {
    	home = h;
    	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        standarddeviationlevel = new javax.swing.JLabel();

        orangedeviation = new javax.swing.JLabel();
        reddeviation = new javax.swing.JLabel();
        
        orangeField = new javax.swing.JTextField();
        redField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        standarddeviationlevel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        standarddeviationlevel.setForeground(new java.awt.Color(14, 14, 14));
        standarddeviationlevel.setText("Standard Deviation Change");



        orangedeviation.setText("Orange ");

        reddeviation.setText("Red ");


        jLabel1.setText("Choose Animal File ");
        
        AnimalDropdown = new JComboBox<String>();
        for (Animal a : change.getAnimals().values()) {
            AnimalDropdown.addItem(a.animalName);
        }
        AnimalDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				animalSelected();
			}
        });
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateParameters();
				
			}
        	
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(standarddeviationlevel))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(35)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(orangedeviation)
        							.addComponent(reddeviation))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addGap(9)))
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        							.addGap(130)
        							.addComponent(AnimalDropdown, 0, 178, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(66)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(redField, Alignment.TRAILING)
        								.addComponent(orangeField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
        							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
        							.addComponent(btnSubmit)))))
        			.addGap(28))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(0, 187, Short.MAX_VALUE)
        			.addComponent(text, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
        			.addGap(76))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(27)
        			.addComponent(standarddeviationlevel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        				.addComponent(AnimalDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(text, GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(orangedeviation)
        							.addGap(16)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(reddeviation)
        								.addComponent(redField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        						.addComponent(orangeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(39))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnSubmit)
        					.addGap(17))))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    
    private void updateParameters() {
    	if (checkFields()) {
    		try {
		    	String selected = (String)AnimalDropdown.getSelectedItem();
		    	Animal a = change.getAnimals().get(selected);
		    	a.orangeDev = Double.valueOf(orangeField.getText());
		    	a.redDev = Double.valueOf(redField.getText());
		    	
		    	Object[] data = {a.animalName, a.animalDescription, a.deviation, a.days, a.duration, a.filePath, a.orangeDev, a.redDev};
		        serialization ser = new serialization(data, Home.animalsPath, a.animalName);
		        ser.SerializeObject();

             	int dataCounter = 0;
             	for (Animal b : change.getAnimals().values()) {
             		if (b.equals(a)) {
             			home.changeRow(a, dataCounter);
             		}
             		dataCounter++;
             	}
		      
		        
		        JOptionPane.showMessageDialog(null, "Changed successfully");
    		} catch (NumberFormatException e) {
    			JOptionPane.showMessageDialog(null, "Please make sure all fields contain only numbers");
    		}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Please fill in all fields");
    	}

    }
    
    private boolean checkFields() {
    	if (orangeField.getText() == "");
    	else if (redField.getText() == "");
    	else {
    		return true;
    	}
    	return false;
    }
    
    private void animalSelected() {
    	String selected = (String)AnimalDropdown.getSelectedItem();
    	Animal a = change.getAnimals().get(selected);
    	orangeField.setText(Double.toString(a.orangeDev));
    	redField.setText(Double.toString(a.redDev));
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangeDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeDev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeDev().setVisible(true);
            }
        });
    }
    private Home home;
    private JComboBox AnimalDropdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField orangeField;
    private javax.swing.JTextField redField;
    private javax.swing.JLabel orangedeviation;
    private javax.swing.JLabel reddeviation;
    private javax.swing.JLabel standarddeviationlevel;
    private javax.swing.JLabel text;
}
