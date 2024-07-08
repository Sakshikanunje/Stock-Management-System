package stock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener{
	// ------------------------------Login Page No 1 Design starts----------------------------------------------
		Container container = getContentPane();
		JLabel title = new JLabel("        Welcome to HealthCare Medical Store", JLabel.LEFT);
		ImageIcon icon = new ImageIcon(getClass().getResource("/stock/MedicalIcon.png"));
		JLabel i1 = new JLabel(icon);
		JLabel lts = new JLabel(" Login to system");
		JLabel userLabel = new JLabel("USERNAME");
		JLabel passwordLabel = new JLabel("PASSWORD");
		JTextField userTextField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JButton loginButton = new JButton("LOGIN");
		JButton resetButton = new JButton("RESET");
		JCheckBox showPassword = new JCheckBox("Show Password");
      
		
		login() {
			setLayoutManager();
			setLocationAndSize();
			addComponentsToContainer();
			addActionEvent();
			//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		}

		public void setLayoutManager() {
			container.setLayout(null);
		}
		
		public void close(){
	        WindowEvent we = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
	        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
	    }
		
		public void setLocationAndSize() {
			// ------------------------------Login Page No 1 Component Size and Location starts----------------------------------------------
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			setPreferredSize(new Dimension(w, h));
			title.setBounds(0, 0, 1000, 75);
			title.setFont(new Font("Serif", Font.BOLD, 48));
			title.setForeground(Color.black);
			title.setOpaque(true);
			title.setBackground(Color.orange); // MAGENTA , darkGray ,
			i1.setBounds(15, 100, 400, 500);
			i1.setBackground(Color.white);
			lts.setBounds(400, 130, 500, 50);
			lts.setForeground(Color.darkGray);
			lts.setFont(new Font("Serif", Font.BOLD, 40));
			userLabel.setBounds(425, 240, 150, 50);
			userLabel.setFont(new Font("Serif", Font.BOLD, 22));
			passwordLabel.setBounds(425, 310, 150, 50);
			passwordLabel.setFont(new Font("Serif", Font.BOLD, 22));
			userTextField.setBounds(600, 240, 300, 50);
			userTextField.setFont(new Font("Serif", Font.PLAIN, 22));
			passwordField.setBounds(600, 315, 300, 50);
			passwordField.setFont(new Font("Serif", Font.PLAIN, 22));
			showPassword.setBounds(600, 380, 300, 40);
			showPassword.setFont(new Font("Serif", Font.PLAIN, 18));
			loginButton.setBounds(610, 450, 100, 40);
			loginButton.setFont(new Font("Serif", Font.PLAIN, 18));
			resetButton.setBounds(770, 450, 100, 40);
			resetButton.setFont(new Font("Serif", Font.PLAIN, 18));
		}
		public void addComponentsToContainer() {
			container.add(lts);
			container.add(i1);
			container.add(title);
			container.add(userLabel);
			container.add(passwordLabel);
			container.add(userTextField);
			container.add(passwordField);
			container.add(showPassword);
			container.add(loginButton);
			container.add(resetButton);
		}

		public void addActionEvent() {
			loginButton.addActionListener(this);
			resetButton.addActionListener(this);
			showPassword.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
				if (e.getSource() == loginButton) {
					String userText;
					String pwdText;
					userText = userTextField.getText();
					pwdText = passwordField.getText();
					if (userText.equalsIgnoreCase("a") && pwdText.equalsIgnoreCase("a")) {
						JOptionPane.showMessageDialog(this, "Login Successful");
						
						MainPanel mp = new MainPanel();
					} // login button ends
					else {
						JOptionPane.showMessageDialog(this, "Invalid Username or Password");
					}
				}
				// ------------------------------Coding Part of RESET button starts---------------------------------------------------
				if (e.getSource() == resetButton) {
					userTextField.setText("");
					passwordField.setText("");
				}
				// -------------------------------Coding Part of RESET button ends-----------------------------------------------------
				// -------------------------------Coding Part of showPassword JCheckBox starts-----------------------------------------
				if (e.getSource() == showPassword) {
					if (showPassword.isSelected()) {
						passwordField.setEchoChar((char) 0);
					} else {
						passwordField.setEchoChar('*');
					}
				}
				// -------------------------------Coding Part of showPassword JCheckBox ends-----------------------------------------
		}
		
		public static void main(String[] a) {
			login frame = new login();
			frame.setTitle("Login");
			frame.setVisible(true);
			frame.setBounds(450, 175, 1000, 650);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
		}
}