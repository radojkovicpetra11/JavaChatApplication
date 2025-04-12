package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private ChatClientGui gui;
    private String nickname;

    public ChatClient(String serverAddress, String nickname, ChatClientGui gui) throws IOException {
        this.gui = gui;
        this.nickname = nickname;
        this.socket = new Socket(serverAddress, 12345);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);

        out.println(nickname);
    }

    public void sendMessage(String message) {
    	 if (message.startsWith("@")) {
    		 out.println("/private " + message);
         } else {
        out.println(message);}
    }

    public void start() {
        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    gui.appendMessage(message);
                }
            } catch (IOException e) {
                System.out.println("Error in receiving messages");
            }
        }).start();

        gui.getSendButton().addActionListener(e -> {
            String message = gui.getMessageInputField().getText().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                gui.getMessageInputField().setText(""); 
               
            }
        });
    }
}
