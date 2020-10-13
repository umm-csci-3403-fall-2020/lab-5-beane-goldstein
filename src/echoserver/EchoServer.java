package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        ServerSocket sock = null;
        try{
             sock = new ServerSocket(portNumber);

            while (true) {
                Socket client = sock.accept();
                System.out.println("Got a request!");


                InputStream byteInputStream = client.getInputStream();
                OutputStream byteOutputStream = client.getOutputStream();

                int inputByte;
                while (true) {
                    inputByte = byteInputStream.read(); //gives -1 if there's nothing left to read
                    if (inputByte != -1){
                        byteOutputStream.write(inputByte);
                    } else {
                        break;
                    }
                }

                client.close();
            } 
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        } finally {
            try {
                if (sock != null) {
                    sock.close();
                }
            } catch (IOException ioe) {
                System.out.println("Unexpected error when trying to close the socket");
                System.err.println(ioe);
            }
        }
    }
}