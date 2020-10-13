package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;

        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            Socket socket = new Socket(server, portNumber);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            int input;
            char c;

            while ((input = System.in.read()) != -1){ //while go until it hits -1 or basically null
                outputStream.write(input);
                outputStream.flush();
                System.out.write(inputStream.read());
                System.out.flush();
            }

            inputStream.close();
            outputStream.close();
            socket.close();
        }
        // basic error handling
        catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running or you have given the right IP.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}