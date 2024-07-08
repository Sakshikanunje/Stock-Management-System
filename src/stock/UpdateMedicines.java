package stock;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateMedicines extends javax.swing.JFrame {
Connection conn = null;
PreparedStatement pst = null;
ResultSet rs = null;
String productName;
    
    public UpdateMedicines() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
           this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
   public void close(){
        WindowEvent we = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }
   
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JLabel jLabel4;
   private JLabel jLabel5;
   private JLabel jLabel6;
   public JTextField productNameTxt;
   public JTextField supplierNameTxt;
   public JTextField productCategoryTxt;
   public JTextField productPriceTxt;
   public JTextField productQuantityTxt;
   private JButton updateBtn;
   private JButton backBtn;

    private void initComponents() {
    	JFrame frame=new JFrame();
    	
        productPriceTxt = new JTextField();
        jLabel6 = new JLabel();
        supplierNameTxt = new JTextField();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        productCategoryTxt = new JTextField();
        updateBtn = new JButton();
        productNameTxt = new JTextField();
        jLabel2 = new JLabel();
        productQuantityTxt = new JTextField();
        jLabel3 = new JLabel();
        backBtn = new JButton();
        
        jLabel2.setBounds(150, 100, 200, 50);
        jLabel3.setBounds(150, 150, 200, 50);
        jLabel4.setBounds(150, 200, 200, 50);
        jLabel5.setBounds(150, 250, 200, 50);
        jLabel6.setBounds(150, 300, 200, 50);
        
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel2.setText("Product Name");
        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel3.setText("Supplier Name");
        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel4.setText("Product Category");
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel5.setText("Product Quantity");
        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel6.setText("Product Price");
        
        productNameTxt.setBounds(400, 100, 300, 40);
        supplierNameTxt.setBounds(400, 150, 300, 40);
        productCategoryTxt.setBounds(400, 200, 300, 40);
        productQuantityTxt.setBounds(400, 250, 300, 40);
        productPriceTxt.setBounds(400, 300, 300, 40);
        
        productNameTxt.setFont(new java.awt.Font("Calibri", 0, 18));
        supplierNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productCategoryTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productQuantityTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productPriceTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        
        updateBtn.setBounds(300, 400, 100, 40);
        backBtn.setBounds(40, 500, 200, 40);
        
        updateBtn.setFont(new java.awt.Font("Calibri", 0, 18)); 
        updateBtn.setText("Update");
        backBtn.setFont(new java.awt.Font("Calibri", 0, 18)); 
        backBtn.setText("Back");
        
        frame.add(jLabel2);
        frame.add(jLabel3);
        frame.add(jLabel4);
        frame.add(jLabel5);
        frame.add(jLabel6);
        
        frame.add(productNameTxt);
        frame.add(supplierNameTxt);
        frame.add(productCategoryTxt);
        frame.add(productQuantityTxt);
        frame.add(productPriceTxt);
        
        frame.add(updateBtn);
        frame.add(backBtn);
        
        frame.setLayout(null);
        frame.setTitle(" Update Medicine ");
        frame.setVisible(true);
        frame.setBounds(350, 125, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

   
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
         try{
             String sql  = "UPDATE products SET product_name='"+ productNameTxt.getText() +"',supplier_name='"+ supplierNameTxt.getText() +"',product_category='"+ productCategoryTxt.getText() +"',quantity='"+ productQuantityTxt.getText() +"',price_per_unit='"+ productPriceTxt.getText() +"' WHERE product_name='"+ productName +"'";
             pst = conn.prepareStatement(sql);
             pst.execute();
             conn.close();
             JOptionPane.showMessageDialog(null, "Medicine has been updated!");
             productName = productNameTxt.getText();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        MedicinesPanel mp = new MedicinesPanel();
    }   
}
