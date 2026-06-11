import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5000;
        Socket socket = new Socket(ip, port);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Connected to server! Type 'exit' to quit.\n");

        String reply;
        while (true) {
            System.out.print("You: ");
            String message = scanner.nextLine();
            out.println(message);
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("You left the chat.");
                break;
            }

            reply = input.readLine();
            if (reply == null || reply.equalsIgnoreCase("exit")) {
                System.out.println("Server disconnected.");
                break;
            }
            System.out.println("Server: " + reply);
        }

        socket.close();
        scanner.close();
    }
}