package stock;

import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class InsertMedicines extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
  
    public InsertMedicines() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        populateCompany();
        populateCategory();
    }
 
    public void clearData(){
       productNameTxt.setText("");
       supplierNameTxt.setSelectedIndex(0);
       productCategoryTxt.setSelectedIndex(0);
       productQuantityTxt.setText("");
       productPriceTxt.setText("");
    }
    
    public void populateCategory(){
        try{
            String sql = "SELECT category FROM product_category";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                productCategoryTxt.addItem(rs.getString("category"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void populateCompany(){
        try{
            String sql = "SELECT name FROM supplier";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
             while(rs.next()){
               supplierNameTxt.addItem(rs.getString("name"));
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private JButton backBtn;
    private JButton insertBtn;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextField productNameTxt;
    private JComboBox supplierNameTxt;
    private JComboBox productCategoryTxt;
    private JTextField productQuantityTxt;
    private JTextField productPriceTxt;
    
    
    private void initComponents() {
    	JFrame frame=new JFrame(" Insert Medicine ");
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        productNameTxt = new JTextField();
        supplierNameTxt = new JComboBox();
        productCategoryTxt =new JComboBox();
        productQuantityTxt = new JTextField();
        productPriceTxt = new JTextField();
       
        insertBtn = new JButton();
        backBtn = new JButton();
        
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
        
        productNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        supplierNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productCategoryTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productQuantityTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productPriceTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        
        backBtn.setFont(new java.awt.Font("Calibri", 0, 18));
        backBtn.setText("Back");
        insertBtn.setFont(new java.awt.Font("Calibri", 0, 18)); 
        insertBtn.setText("Insert");
        
        jLabel2.setBounds(150, 100, 200, 50);
        jLabel3.setBounds(150, 150, 200, 50);
        jLabel4.setBounds(150, 200, 200, 50);
        jLabel5.setBounds(150, 250, 200, 50);
        jLabel6.setBounds(150, 300, 200, 50);
        
        productNameTxt.setBounds(400, 100, 300, 40);
        supplierNameTxt.setBounds(400, 150, 300, 40);
        productCategoryTxt.setBounds(400, 200, 300, 40);
        productQuantityTxt.setBounds(400, 250, 300, 40);
        productPriceTxt.setBounds(400, 300, 300, 40);
        
        insertBtn.setBounds(300, 400, 100, 40);
        backBtn.setBounds(40, 500, 200, 40);
        
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
        
        frame.add(insertBtn);
        frame.add(backBtn);
        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(350, 125, 800, 600);
        frame.setResizable(false);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        supplierNameTxt.setModel(new DefaultComboBoxModel(new String[] { " " }));
        supplierNameTxt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                supplierNameTxtItemStateChanged(evt);
            }
        });

        productCategoryTxt.setModel(new DefaultComboBoxModel(new String[] { " " }));
        productCategoryTxt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                productCategoryTxtItemStateChanged(evt);
            }
        });
        
       
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        MedicinesPanel mp = new MedicinesPanel();
    }

    private void insertBtnActionPerformed(ActionEvent evt) {
            try{
                String sql = "INSERT INTO products (product_name,supplier_name,product_category,quantity,price_per_unit) VALUES (?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1,productNameTxt.getText());
                pst.setString(2,supplierNameTxt.getSelectedItem().toString());
                pst.setString(3,productCategoryTxt.getSelectedItem().toString());
                pst.setString(4,productQuantityTxt.getText());
                pst.setString(5,productPriceTxt.getText());
                pst.execute();
                conn.close();
                JOptionPane.showMessageDialog(null, "Medicines has been added!");
                clearData();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }

    private void supplierNameTxtItemStateChanged(ItemEvent evt) {       
    }
    private void productCategoryTxtItemStateChanged(ItemEvent evt) {
    }
}
