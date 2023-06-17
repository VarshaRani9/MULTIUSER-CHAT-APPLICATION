package com.company.chatapp.users.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserViewSelf extends JFrame {
	int counter ;
	public UserViewSelf() {
		counter = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,40));
		Container container = this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100,70,200,100);
		container.add(welcome);
		JButton button = new JButton("Count");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("counter"+counter);
			}
			
		});
		button.setBounds(100,300,200,50);
		container.add(button);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		UserViewSelf userViewSelf = new UserViewSelf();
	}

}
