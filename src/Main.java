
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969)) {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                //Read string from clients
                echoString = scanner.nextLine();
                // Send string to server

                output.println(echoString);
                // Check exit or not
                if (!echoString.equals("exit")) {
                    // Get data from Input
                    response = input.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());

        }
    }
}
