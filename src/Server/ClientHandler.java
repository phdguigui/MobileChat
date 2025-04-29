import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);

            username = in.readLine();
            ChatServer.addClient(username, this);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Recebido de " + username + ": " + message);

                if (message.equalsIgnoreCase("/users")) {
                    sendUserList();
                } else if (message.startsWith("/send message")) {
                    handleSendMessage(message);
                } else {
                    out.println("Comando não reconhecido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro com cliente " + username);
        } finally {
            ChatServer.removeClient(username);
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private void sendUserList() {
        StringBuilder userList = new StringBuilder("Usuários conectados:\n");
        for (String user : ChatServer.getConnectedUsers()) {
            userList.append("- ").append(user).append("\n");
        }
        out.println(userList.toString());
    }

    private void handleSendMessage(String message) {
        // Formato esperado: /send message <destinatario> <mensagem>
        String[] parts = message.split(" ", 4); // 4 partes: /send + message + destinatario + mensagem

        if (parts.length < 4) {
            out.println("Formato inválido. Use: /send message <destinatario> <mensagem>");
            return;
        }

        String receiver = parts[2];
        String msgContent = parts[3];

        ClientHandler recipient = ChatServer.clients.get(receiver);

        if (recipient != null) {
            recipient.sendMessage(username + ": " + msgContent);
            out.println("Mensagem enviada para " + receiver + ".");
        } else {
            out.println("Usuário " + receiver + " não encontrado.");
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }
}
