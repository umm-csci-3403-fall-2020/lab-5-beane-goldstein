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
            Socket socket = new Socket(server, portNumber); //connects to the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            int input;

            while ((input = System.in.read()) != -1){ //while go until it hits -1 or basically null
                //writing work
                outputStream.write(input); //writes the data to be sent to the server
                outputStream.flush(); //flushes the stream and forces bytes to written out
                //prints the data that is sent back from the server
                System.out.write(inputStream.read()); //what should be sent back to the client
                System.out.flush(); //prints the flush
            }

            inputStream.close(); //closes the inputStream
            outputStream.close(); //closes the outputStream
            socket.close(); //closes the socket
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