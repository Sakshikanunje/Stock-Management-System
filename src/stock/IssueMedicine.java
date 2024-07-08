package stock;

import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class IssueMedicine extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private JButton backBtn;
    private JButton issueBtn;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;

    private JTextField customerNameTxt;
    private JComboBox productNameTxt;
    private JTextField productCategoryTxt;
    private JTextField availQuantityTxt;
    private JTextField pricePerQtyTxt;
    private JTextField issueQuantityTxt;
    private JTextField totalPriceTxt;
  
    public IssueMedicine() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        populateItem();
    }
 
    public void clearData(){
       customerNameTxt.setText("");
       productNameTxt.setSelectedIndex(0);
       productCategoryTxt.setText("");
       availQuantityTxt.setText("");
       pricePerQtyTxt.setText("");
       issueQuantityTxt.setText("");
       totalPriceTxt.setText("");
    }
    
    public void populateItem(){
        try{
            String sql = "SELECT product_name FROM products";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	productNameTxt.addItem(rs.getString("product_name"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void initComponents() {
    	JFrame frame=new JFrame(" Issue Medicine ");
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();


        customerNameTxt = new JTextField();
        productNameTxt = new JComboBox();
        productCategoryTxt =new JTextField();
        availQuantityTxt = new JTextField();
        pricePerQtyTxt = new JTextField();
        issueQuantityTxt = new JTextField();
        totalPriceTxt = new JTextField();
       
        issueBtn = new JButton();
        backBtn = new JButton();
        
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel1.setText("Customer Name");
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel2.setText("Product Name");
        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel3.setText("Product Category");       
        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel4.setText("Available Quantity");
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel5.setText("Price Per Quantity");
        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel6.setText("Issue Quantity");
        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); 
        jLabel7.setText("Product Price");
        
        customerNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productNameTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        productCategoryTxt.setFont(new java.awt.Font("Calibri", 0, 18));
        availQuantityTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        pricePerQtyTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        issueQuantityTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        totalPriceTxt.setFont(new java.awt.Font("Calibri", 0, 18)); 
        
        backBtn.setFont(new java.awt.Font("Calibri", 0, 18));
        backBtn.setText("Back");
        issueBtn.setFont(new java.awt.Font("Calibri", 0, 18)); 
        issueBtn.setText("Issue Medicine");
        
        jLabel1.setBounds(150, 100, 200, 50);
        jLabel2.setBounds(150, 150, 200, 50);
        jLabel3.setBounds(150, 200, 200, 50);
        jLabel4.setBounds(150, 250, 200, 50);
        jLabel5.setBounds(150, 300, 200, 50);
        jLabel6.setBounds(150, 350, 200, 50);
        jLabel7.setBounds(150, 400, 200, 50);

       
        customerNameTxt.setBounds(400, 100, 300, 40);
        productNameTxt.setBounds(400, 150, 300, 40);
        productCategoryTxt.setBounds(400, 200, 300, 40);
        availQuantityTxt.setBounds(400, 250, 300, 40);
        pricePerQtyTxt.setBounds(400, 300, 300, 40);
        issueQuantityTxt.setBounds(400, 350, 300, 40);
        totalPriceTxt.setBounds(400, 400, 300, 40);
        
        issueBtn.setBounds(500, 450, 200, 40);
        backBtn.setBounds(40, 500, 200, 40);
        
        frame.add(jLabel1);
        frame.add(jLabel2);
        frame.add(jLabel3);
        frame.add(jLabel4);
        frame.add(jLabel5);
        frame.add(jLabel6);
        frame.add(jLabel7);
        
        frame.add(customerNameTxt);
        frame.add(productNameTxt);
        frame.add(productCategoryTxt);
        frame.add(availQuantityTxt);
        frame.add(pricePerQtyTxt);
        frame.add(issueQuantityTxt);
        frame.add(totalPriceTxt);
        
        frame.add(issueBtn);
        frame.add(backBtn);
        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(350, 125, 800, 600);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        issueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Medicines has been issued!");
                clearData();
            }
        });
        
        productNameTxt.setModel(new DefaultComboBoxModel(new String[] { " " }));
        productNameTxt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                productNameTxtItemStateChanged(evt);
            }
        });
        
        issueQuantityTxt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
            	int issueQty = Integer.parseInt(issueQuantityTxt.getText());
            	int pricePerQty = Integer.parseInt(pricePerQtyTxt.getText());
            	
            	int totalPrice = issueQty * pricePerQty;
            	
            	totalPriceTxt.setText(String.valueOf(totalPrice));
            }
         });
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        MainPanel mp = new MainPanel();
    }

    private void customerNameTxtItemStateChanged(ItemEvent evt) {       
    }
    
    private void productNameTxtItemStateChanged(ItemEvent evt) {
    	productCategoryTxt.enable(true);

    	try{
    		String product = productNameTxt.getSelectedItem().toString();
            String sql = "SELECT * FROM products WHERE product_name =  '"+ product +"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){     
                productCategoryTxt.setText(rs.getString("product_category"));
                availQuantityTxt.setText(rs.getString("quantity"));
                pricePerQtyTxt.setText(rs.getString("price_per_unit"));    
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   	
    }
}
