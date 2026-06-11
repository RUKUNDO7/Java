import java.io.*;
import java.net.*;


public class ClientThreads {
    private Socket socket;

    public ClientThreads(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while(true) {
               message = input.readLine();
               if(message == null) {
                   break;
               }
               out.println("hello " +message);
               if(message.equalsIgnoreCase("bye")) {
                   System.out.println("Goodbye...");
                   break;
               }
            }
            socket.close();
        } catch (IOException e) {

        }

    }
}
