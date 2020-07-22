package server.network.sendFileExample.server;

import java.net.*;
import java.io.*;

public class Server {
    public static void main (String [] args ) throws IOException {
        ServerSocket serverSocket = new ServerSocket(13267);
        System.out.println("Waiting...");

        Socket socket = serverSocket.accept();
        System.out.println("Accepted connection : " + socket);

        sendFile(socket, "src/main/java/server/network/sendFileExample/server/server.txt");

        socket.close();
        serverSocket.close();
    }

    // todo: nedaei, pass file type first
    private static void sendFile(Socket socket, String filePath) {
        try {
            int bufferSize = 1024;

            File file = new File (filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis, bufferSize);
            byte[] bytes = new byte[bufferSize];
            bis.read(bytes);
            bis.close();
            fis.close();

            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os, bufferSize);
            bos.write(bytes);
            bos.flush();
            bos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}