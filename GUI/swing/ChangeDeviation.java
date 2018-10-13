/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ChangeDeviation extends javax.swing.JFrame {

    /**
     * Creates new form standanddeviationchange
     */
    public ChangeDeviation() {
    	home = null;
        initComponents();
    }
    
    public ChangeDeviation(Home h) {
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
        greendeviation = new javax.swing.JLabel();
        orangedeviation = new javax.swing.JLabel();
        reddeviation = new javax.swing.JLabel();
        greenField = new javax.swing.JTextField();
        orangeField = new javax.swing.JTextField();
        redField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        standarddeviationlevel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        standarddeviationlevel.setForeground(new java.awt.Color(14, 14, 14));
        standarddeviationlevel.setText("Standard Deviation Change");

        greendeviation.setText("Green (1 s.t.d)");

        orangedeviation.setText("Orange (2 s.t.d)");

        reddeviation.setText("Red (3 s.t.d)");

        greenField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose Animal File ");
        
        change.readAnimals("C:\\Users\\jarro\\Documents\\Uni\\Computer Science\\Eclipse\\TempGUI\\src/animals");
        AnimalDropdown = new JComboBox();
        for (Animal a : change.animals) {
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
        							.addComponent(greendeviation)
        							.addComponent(orangedeviation)
        							.addComponent(reddeviation))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addGap(9)))
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(170)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(greenField, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        								.addComponent(orangeField)
        								.addComponent(redField)))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(130)
        							.addComponent(AnimalDropdown, 0, 119, Short.MAX_VALUE)))))
        			.addGap(28))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(0, 187, Short.MAX_VALUE)
        			.addComponent(text, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
        			.addGap(76))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(278, Short.MAX_VALUE)
        			.addComponent(btnSubmit)
        			.addGap(86))
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
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(greendeviation, Alignment.TRAILING)
        				.addComponent(greenField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(orangedeviation)
        				.addComponent(orangeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(reddeviation)
        				.addComponent(redField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnSubmit)
        			.addGap(4))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void setfileName(String fileName){
        text = new JLabel(fileName);
        text.setBounds(394, 99, 92, 26);
        getContentPane().add(text);
    }
    
    private void updateParameters() {
    	if (checkFields()) {
    		try {
		    	int selected = AnimalDropdown.getSelectedIndex();
		    	Animal a = change.animals.get(selected);
		    	a.deviation = Double.valueOf(greenField.getText());
		    	a.orangeDev = Double.valueOf(orangeField.getText());
		    	a.redDev = Double.valueOf(redField.getText());
		    	
		    	Object[] data = {a.animalName, a.animalDescription, a.deviation, a.days, a.duration, a.filePath, a.orangeDev, a.redDev};
		        serialization ser = new serialization(data, "C:\\Users\\jarro\\Documents\\Uni\\Computer Science\\Eclipse\\TempGUI\\src/animals", a.animalName);
		        ser.SerializeObject();
		        
		        if (home != null) {
			        int row = change.animals.indexOf(a);
			        home.changeRow(a,row);
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
    	if (greenField.getText() == "");
    	else if (orangeField.getText() == "");
    	else if (redField.getText() == "");
    	else {
    		return true;
    	}
    	return false;
    }
    
    private void animalSelected() {
    	int selected = AnimalDropdown.getSelectedIndex();
    	Animal a = change.animals.get(selected);
    	greenField.setText(Double.toString(a.deviation));
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
            java.util.logging.Logger.getLogger(ChangeDeviation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeDeviation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeDeviation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeDeviation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeDeviation().setVisible(true);
            }
        });
    }
    private Home home;
    private JComboBox AnimalDropdown;
    private javax.swing.JLabel greendeviation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField greenField;
    private javax.swing.JTextField orangeField;
    private javax.swing.JTextField redField;
    private javax.swing.JLabel orangedeviation;
    private javax.swing.JLabel reddeviation;
    private javax.swing.JLabel standarddeviationlevel;
    private javax.swing.JLabel text;
}