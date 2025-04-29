import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    public static Map<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Novo cliente conectado: " + socket.getInetAddress());

            ClientHandler handler = new ClientHandler(socket);
            new Thread(handler).start();
        }
    }

    public static void broadcast(String sender, String receiver, String message) {
        ClientHandler recipient = clients.get(receiver);
        if (recipient != null) {
            recipient.sendMessage(sender + ": " + message);
        }
    }

    public static void addClient(String name, ClientHandler handler) {
        clients.put(name, handler);
    }

    public static void removeClient(String name) {
        clients.remove(name);
    }

    public static Set<String> getConnectedUsers() {
        return clients.keySet();
    }
}
