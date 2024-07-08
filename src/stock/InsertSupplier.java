package stock;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class InsertSupplier extends JFrame {
	
Connection conn = null;
PreparedStatement pst = null;
ResultSet rs = null;
   
    public InsertSupplier() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    
   public void close(){
        WindowEvent we = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }
   
   private JButton backBtn;
   private JButton insertBtn;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JLabel jLabel4;
   private JLabel jLabel5;
   private JLabel jLabel6;
   private JTextField supplierIdTxt;
   private JTextField supplierNameTxt;
   private JTextField supplieraddTxt;
   private JTextField supplierMobTxt;
   private JTextField suppliermailTxt;
 
    public void clearData(){
       supplierIdTxt.setText("");
       supplierNameTxt.setText("");
       supplieraddTxt.setText("");
       suppliermailTxt.setText("");
       supplierMobTxt.setText("");
    }
 
    private void initComponents() {
    	JFrame frame=new JFrame();
    	
        backBtn = new JButton();
        insertBtn = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        supplierIdTxt = new JTextField();
        supplierNameTxt = new JTextField();
        supplieraddTxt =new JTextField();
        suppliermailTxt = new JTextField();
        supplierMobTxt = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        jLabel2.setBounds(150, 100, 200, 50);
        jLabel3.setBounds(150, 150, 200, 50);
        jLabel4.setBounds(150, 200, 200, 50);
        jLabel5.setBounds(150, 250, 200, 50);
        jLabel6.setBounds(150, 300, 200, 50);
        
        jLabel2.setFont(new Font("Calibri", 0, 18)); 
        jLabel2.setText("Supplier Id");
        jLabel3.setFont(new Font("Calibri", 0, 18)); 
        jLabel3.setText("Supplier Name");
        jLabel4.setFont(new Font("Calibri", 0, 18)); 
        jLabel4.setText("Supplier Address");
        jLabel5.setFont(new Font("Calibri", 0, 18)); 
        jLabel5.setText("Supplier email");
        jLabel6.setFont(new Font("Calibri", 0, 18)); 
        jLabel6.setText("Supplier Mobile No");
        
        supplierIdTxt.setBounds(400, 100, 300, 40);
        supplierNameTxt.setBounds(400, 150, 300, 40);
        supplieraddTxt.setBounds(400, 200, 300, 40);
        suppliermailTxt.setBounds(400, 250, 300, 40);
        supplierMobTxt.setBounds(400, 300, 300, 40);
        
        supplierIdTxt.setFont(new Font("Calibri", 0, 18));
        supplierNameTxt.setFont(new Font("Calibri", 0, 18)); 
        supplieraddTxt.setFont(new Font("Calibri", 0, 18)); 
        supplierMobTxt.setFont(new Font("Calibri", 0, 18)); 
        suppliermailTxt.setFont(new Font("Calibri", 0, 18));
        
        insertBtn.setBounds(300, 400, 100, 40);
        backBtn.setBounds(40, 500, 200, 40);
        
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
        
        frame.add(insertBtn);
        frame.add(backBtn);
        
        insertBtn.setFont(new Font("Calibri", 0, 20)); 
        insertBtn.setText("Insert");
        backBtn.setFont(new Font("Calibri", 0, 20)); 
        backBtn.setText("Back");
        
        frame.setLayout(null);
        frame.setTitle(" Insert Supplier ");
        frame.setVisible(true);
        frame.setBounds(350, 125, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
       
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
    }
        
    private void backBtnActionPerformed(ActionEvent evt) {
        SupplierPanel mp = new SupplierPanel();
      
    }

    private void insertBtnActionPerformed(ActionEvent evt) {
            try{
                String sql = "INSERT INTO supplier (id,name,address,email,mobileNo) VALUES (?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1,supplierIdTxt.getText());
                pst.setString(2,supplierNameTxt.getText());
                pst.setString(3,supplieraddTxt.getText());
                pst.setString(4,suppliermailTxt.getText());
                pst.setString(5,supplierMobTxt.getText());
                pst.execute();
                conn.close();
                JOptionPane.showMessageDialog(null, "Supplier has been added!");
                clearData();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
   
}
