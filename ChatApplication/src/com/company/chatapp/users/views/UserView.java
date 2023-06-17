package com.company.chatapp.users.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.company.chatapp.users.dao.UserDAO;
import com.company.chatapp.users.dto.UserDTO;
import com.company.chatapp.utils.UserInfo;

public class UserView {

	private JFrame userViewFrame;
	private JTextField useridTxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {
					UserView window = new UserView();
					window.userViewFrame.setVisible(true);
	}
	public UserView() {
		initialize();
	}
	
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = useridTxt.getText();
		char password[] = passwordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String msg = "";
			if(userDAO.isLogin(userDTO)) {
				msg = "Welcome " +userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(null, msg,"Message",JOptionPane.INFORMATION_MESSAGE);
				userViewFrame.setVisible(false);
				userViewFrame.dispose();
				DashBoard dashBoard = new DashBoard(msg);
				dashBoard.setVisible(true);
			}
			else {
				msg = "Invalid userid or password";
				JOptionPane.showMessageDialog(null, msg,"Message",JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void register() {
		String userid = useridTxt.getText();
		char password[] = passwordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(null, "Registration Successful","Message",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Registrtation Failed ","Message",JOptionPane.WARNING_MESSAGE);
		}
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue..");
			ex.printStackTrace();
		}catch(Exception e) {
			System.out.println("Some generic exception raised ...");
			e.printStackTrace();
		}
	}
	private void initialize() {
		userViewFrame = new JFrame();
		userViewFrame.setResizable(false);
		userViewFrame.setTitle("Login/Register Form");
		userViewFrame.getContentPane().setBackground(new Color(255, 255, 255));
		userViewFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register/Login");
		lblNewLabel.setForeground(new Color(64, 0, 64));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(198, 23, 209, 35);
		userViewFrame.getContentPane().add(lblNewLabel);
		
		JLabel useridLbl = new JLabel("User Id ");
		useridLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		useridLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		useridLbl.setBounds(68, 136, 77, 13);
		userViewFrame.getContentPane().add(useridLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLbl.setBounds(68, 181, 77, 21);
		userViewFrame.getContentPane().add(passwordLbl);
		
		useridTxt = new JTextField();
		useridTxt.setBorder(new LineBorder(new Color(64, 0, 128), 2, true));
		useridTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		useridTxt.setBounds(185, 129, 237, 26);
		userViewFrame.getContentPane().add(useridTxt);
		useridTxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(64, 0, 128), 2, true));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(185, 178, 237, 26);
		userViewFrame.getContentPane().add(passwordField);
		
		JButton loginBt = new JButton("Login");
		loginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginBt.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		loginBt.setIcon(new ImageIcon(UserView.class.getResource("/assets/loginIcon.png")));
		loginBt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginBt.setBounds(68, 258, 184, 86);
		userViewFrame.getContentPane().add(loginBt);
		
		JButton registerBt = new JButton("Register");
		registerBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerBt.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		registerBt.setIcon(new ImageIcon(UserView.class.getResource("/assets/register.png")));
		registerBt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerBt.setBounds(262, 258, 197, 86);
		userViewFrame.getContentPane().add(registerBt);
		
		JLabel iconLbl = new JLabel("");
		iconLbl.setIcon(new ImageIcon(UserView.class.getResource("/assets/user.jpg")));
		iconLbl.setBounds(463, 74, 209, 270);
		userViewFrame.getContentPane().add(iconLbl);
		userViewFrame.setBounds(100, 100, 703, 429);
		userViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userViewFrame.setLocationRelativeTo(null);
	}
}
