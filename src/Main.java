import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6969);
            Scanner scanner = new Scanner(System.in)) {
            // read the result received from server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // send the data to server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String inputString, response;
            do {
                System.out.println("Enter string to be normalized: ");
                // read string from client via console
                inputString = scanner.nextLine();
                // send string to server
                output.println(inputString);
                // check whether client wants to exit
                if (!inputString.equals("quit")) {
                    // get data received from server
                    response = input.readLine();
                    System.out.println("Result from server: " + response);
                }
            } while (!inputString.equals("quit"));
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
