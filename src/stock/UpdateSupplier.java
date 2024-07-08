package stock;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateSupplier extends JFrame {
Connection conn = null;
PreparedStatement pst = null;
ResultSet rs = null;
String id;
    
    public UpdateSupplier() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
           this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
   public void close(){
        WindowEvent we = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }

   private JButton backBtn;
   public JTextField supplierNameTxt;
   public JLabel jLabel2;
   public JLabel jLabel3;
   public JLabel jLabel4;
   public JLabel jLabel5;
   public JLabel jLabel6;
   public JTextField supplieraddTxt;
   public JTextField supplierIdTxt;
   public JTextField supplierMobTxt;
   public JTextField suppliermailTxt;
   private JButton updateBtn;
   
    private void initComponents() {
    	JFrame frame=new JFrame();
    	
        supplierIdTxt = new JTextField();
        supplierNameTxt = new JTextField();
        supplieraddTxt = new JTextField();
        suppliermailTxt = new JTextField();
        supplierMobTxt = new JTextField();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        updateBtn = new JButton();
        backBtn = new JButton();
        
        jLabel2.setBounds(150, 100, 200, 50);
        jLabel3.setBounds(150, 150, 200, 50);
        jLabel4.setBounds(150, 200, 200, 50);
        jLabel5.setBounds(150, 250, 200, 50);
        jLabel6.setBounds(150, 300, 200, 50);
        
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel2.setText("Supplier Id");
        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel3.setText("Supplier Name");
        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel4.setText("Supplier Address");
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel5.setText("Supplier email");
        
        supplierIdTxt.setBounds(400, 100, 300, 40);
        supplierNameTxt.setBounds(400, 150, 300, 40);
        supplieraddTxt.setBounds(400, 200, 300, 40);
        suppliermailTxt.setBounds(400, 250, 300, 40);
        supplierMobTxt.setBounds(400, 300, 300, 40);
        
        supplierIdTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        supplierNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        supplieraddTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        suppliermailTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        supplierMobTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 

        updateBtn.setBounds(300, 400, 100, 40);
        backBtn.setBounds(40, 500, 200, 40);
        
        backBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        backBtn.setText("Back");
        updateBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        updateBtn.setText("Update");
        
        frame.add(jLabel2);
        frame.add(jLabel3);
        frame.add(jLabel4);
        frame.add(jLabel5);
        frame.add(jLabel6);
        
        frame.add(supplierIdTxt);
        frame.add(supplierNameTxt);
        frame.add(supplieraddTxt);
        frame.add(suppliermailTxt);
        frame.add(supplierMobTxt);
        
        frame.add(updateBtn);
        frame.add(backBtn);
        
        frame.setLayout(null);
        frame.setTitle(" Update Supplier ");
        frame.setVisible(true);
        frame.setBounds(350, 125, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });  
    }

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed//id,name,address,email,mobileNo
         try{
             String sql  = "UPDATE supplier SET id='"+ supplierIdTxt.getText() +"',name='"+ supplierNameTxt.getText() +"',address='"+ supplieraddTxt.getText() +"',email='"+ suppliermailTxt.getText() +"',mobileNo='"+ supplierMobTxt.getText() +"' WHERE id='"+ id +"'";
             pst = conn.prepareStatement(sql);
             pst.execute();
             conn.close();
             JOptionPane.showMessageDialog(null, "Supplier has been updated!");
             id = supplierIdTxt.getText();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        SupplierPanel mp = new SupplierPanel();
    }
}
