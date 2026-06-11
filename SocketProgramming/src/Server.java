import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started, waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!\n");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        String received;
        while (true) {
            received = input.readLine();
            if (received == null || received.equalsIgnoreCase("exit")) {
                System.out.println("Client disconnected.");
                break;
            }
            System.out.println("Client: " + received);


            System.out.print("You: ");
            String reply = scanner.nextLine();
            out.println(reply);
            if (reply.equalsIgnoreCase("exit")) {
                System.out.println("You left the chat.");
                break;
            }
        }

        socket.close();
        serverSocket.close();
        scanner.close();
    }
}