package server.network.sendFileExample.client;

import java.net.*;
import java.io.*;

public class Client{
    public static void main (String [] args ) throws IOException {
        Socket socket = new Socket("127.0.0.1",13267);
        System.out.println("Connecting...");

        receiveFile(socket, "src/main/java/server/network/sendFileExample/client/client.txt");

        socket.close();
    }

    private static void receiveFile(Socket socket, String destinationPath) {
        try {
            int bufferSize = 1024;

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, bufferSize);
            byte[] bytes = new byte[bufferSize];
            bis.read(bytes);
            bis.close();
            is.close();

            FileOutputStream fos = new FileOutputStream(destinationPath);
            BufferedOutputStream bos = new BufferedOutputStream(fos, bufferSize);
            bos.write(bytes);
            bos.flush();
            bos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}