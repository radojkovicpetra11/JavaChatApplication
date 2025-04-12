package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatServer {
	private static Set<PrintWriter> clientWriters = new HashSet<>();
	private static Map<Socket, String> clientNicknames = new HashMap<>();
	private static Map<String, PrintWriter> nicknameWriters = new HashMap<>();

	public static void main(String[] args) throws Exception {
		System.out.println("Chat server pokrenut!");
		ServerSocket listener = new ServerSocket(12345);
		try {
			while (true) {
				new ClientHandler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		private String nickname;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				nickname = in.readLine();
				clientNicknames.put(socket, nickname);
				clientWriters.add(out);
				nicknameWriters.put(nickname, out);

				broadcast("Server: " + nickname + " se upravo prikljucio/la!");

				String message;
				while ((message = in.readLine()) != null) {
					if (message.startsWith("/private")) {
						handlePrivateMessage(message);
					} else {
						broadcast(nickname + ": " + message);
					}
				}
			} catch (IOException e) {
				System.out.println("Error handling client: " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Error: Ne mogu zatvoriti socket!");
				}
				clientWriters.remove(out);
				clientNicknames.remove(socket);
				nicknameWriters.remove(nickname);
				broadcast("Server: " + nickname + " je upravo napustio/la chat");
			}
		}

		private void broadcast(String message) {
			for (PrintWriter writer : clientWriters) {
				writer.println(message);
			}
		}

		private void handlePrivateMessage(String message) {
			String[] messageParts = message.split(" ", 3);
			if (messageParts.length >= 3) {
				String targetNickname = messageParts[1].substring(1); 
				String privateMessage = messageParts[2];

				
				PrintWriter targetWriter = nicknameWriters.get(targetNickname);
				if (targetWriter != null) {
					targetWriter.println("(Private) " + nickname + ": " + privateMessage);
					out.println("(Private) To " + targetNickname + ": " + privateMessage); 
				} else {
					out.println("Server: Korisnik " + targetNickname + " nije pronadjen!");
				}
			}
		}
	}
}
