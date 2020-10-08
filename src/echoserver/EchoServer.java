package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try{
            ServerSocket sock = new ServerSocket(portNumber);

            while (true) {
                Socket client = sock.accept();
                System.out.println("Got a request!");

                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(new byte[1]);
                ByteArrayOutputStream byteOutputSteam = new ByteArrayOutputStream();

                int inputByte;
                while (true) {
                    inputByte = byteInputStream.read();
                    if (inputByte != -1){
                        byteOutputSteam.write(inputByte);
                    } else {
                        break;
                    }
                }

                client.close();
            } 
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}