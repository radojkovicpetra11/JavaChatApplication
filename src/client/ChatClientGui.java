package client;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class ChatClientGui extends JFrame {
    private JTextArea messageInputField;
    private JTextArea chatArea;
    private JButton sendButton;
    private String nickname;
   
    public ChatClientGui(String nickname) {
        this.nickname = nickname;

        setTitle("Chat Client - " + nickname);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 521, 415);
        getContentPane().setLayout(null);

        chatArea = new JTextArea();
        chatArea.setBounds(10, 11, 640, 274);
        chatArea.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBounds(10, 10, 414, 200);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(chatScrollPane);
        
        messageInputField = new JTextArea();
        messageInputField.setForeground(new Color(255, 51, 102));
        messageInputField.setLineWrap(true);
        messageInputField.setWrapStyleWord(true);
        
        JScrollPane inputScrollPane = new JScrollPane(messageInputField);
        inputScrollPane.setBounds(10, 230, 300, 80);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(inputScrollPane);
        sendButton = new JButton("Send");
        sendButton.setBounds(347, 275, 143, 30);
        sendButton.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
        getContentPane().add(sendButton);
        
        JLabel lblNewLabel = new JLabel("Za slanje privatnih poruka koristite sledeci format: @nickname");
        lblNewLabel.setForeground(new Color(165, 42, 42));
        lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel.setBounds(20, 322, 470, 30);
        getContentPane().add(lblNewLabel);
        
    }

    public JButton getSendButton() {
        return sendButton;
    }

    
    public JTextArea getMessageInputField() {
		return messageInputField;
	}

	public void setMessageInputField(JTextArea messageInputField) {
		this.messageInputField = messageInputField;
	}

	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}

	public void setSendButton(JButton sendButton) {
		this.sendButton = sendButton;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}

	public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }
   
    public String getNickname() {
        return nickname;
    }
}
