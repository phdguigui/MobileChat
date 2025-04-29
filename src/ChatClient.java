import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        System.out.println("Conectado ao servidor.");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Digite seu nome de usuário: ");
        String name = scanner.nextLine();
        out.println(name); // Envia nome ao servidor

        // Thread para receber mensagens
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                System.out.println("Conexão encerrada.");
            }
        }).start();

        // Envio de mensagens
        while (true) {
            String input = scanner.nextLine();
            out.println(input);
            if (input.equalsIgnoreCase("/sair")) {
                break;
            }
        }

        socket.close();
        scanner.close();
    }
}
