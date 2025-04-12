package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class NicknameWindowGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	 private JTextField nicknameField;
	    private JButton enterChatButton;
	    private ChatClient chatClient;

	    public NicknameWindowGUI() {
	        setTitle("Chat Aplikacija");
	        setSize(550, 316);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(null);

	        JLabel nicknameLabel = new JLabel("Unesite korisnicko ime:");
	        nicknameLabel.setForeground(new Color(0, 0, 204));
	        nicknameLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
	        nicknameLabel.setBounds(161, 44, 195, 60);
	        getContentPane().add(nicknameLabel);

	        nicknameField = new JTextField();
	        nicknameField.setBounds(97, 104, 305, 30);
	        getContentPane().add(nicknameField);

	        enterChatButton = new JButton("Pristupi chat-u!");
	        enterChatButton.setForeground(new Color(0, 0, 204));
	        enterChatButton.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
	        enterChatButton.setBounds(161, 157, 152, 55);
	        getContentPane().add(enterChatButton);

	        enterChatButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String nickname = nicknameField.getText().trim();
	                if (!nickname.isEmpty()) {
	                    ChatClientGui chatClientGui = new ChatClientGui(nickname);
	                    chatClientGui.setVisible(true);

	                    try {
							chatClient = new ChatClient("localhost", nickname, chatClientGui);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
	                    chatClient.start();

	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Nickname ne sme biti prazan!");
	                }
	            }
	        });
	    }

	    public static void main(String[] args) {
	        NicknameWindowGUI NicknameWindowGUI = new NicknameWindowGUI();
	        NicknameWindowGUI.setVisible(true);
	    }
}
