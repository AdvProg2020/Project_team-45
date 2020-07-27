package server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class handles initiating connection to bankAPI ,sending requests to Bank server
 * and also responses from Bank server.
 */
public class BankSocket {
    public static final int PORT = 8888;
    public static final String IP = "127.0.0.1";

//    public static final int PORT = 11072;
//    public static final String IP = "2.tcp.ngrok.io";

    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    /**
     * This method is used to add initiating socket and IN/OUT data stream .
     *
     * @throws IOException when IP/PORT hasn't been set up properly.
     */
    public static void ConnectToBankServer() throws IOException {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

    /**
     * This method is used to start a Thread ,listening on IN data stream.
     */
    public static void StartListeningOnInput() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    System.out.println("disconnected");
                    System.exit(0);
                }
            }
        }).start();
    }

    /**
     * This method is used to send message with value
     *
     * @param msg to Bank server.
     * @throws IOException when OUT data stream been interrupted.
     */
    public static String SendMessage(String msg) throws IOException {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
            return inputStream.readUTF();
        } catch (IOException e) {
            throw new IOException("Exception while sending message");
        }
    }

    /**
     * This method is used to illustrate an example of using methods of this class.
     */
    public static void main(String[] args) {
        try {
            ConnectToBankServer();
            StartListeningOnInput();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                SendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    // my methods

    public static Integer createAccount(String firstName, String lastName, String username, String password) throws IOException {
        return Integer.parseInt(SendMessage("create_account " + firstName + " " + lastName + " " + username + " " + password + " " + password));
    }

    public static String getToken(String username, String password) {
        try {
            return SendMessage("get_token " + username + " " + password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer createDepositReceipt(String token, int money, int destId) {
        try {
            return Integer.parseInt(SendMessage("create_receipt " + token + " deposit " + money + " -1 " + destId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer createWithdrawReceipt(String token, int money, int sourceId) {
        try {
            return Integer.parseInt(SendMessage("create_receipt " + token + " withdraw " + money + " " + sourceId + " -1"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer createMoveReceipt(String token, int money, int sourceId, int destId) {
        try {
            return Integer.parseInt(SendMessage("create_receipt " + token + " move " + money + " " + sourceId + " " + destId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean payReceipt(int receiptId) {
        try {
            return SendMessage("pay " + receiptId).equals("done successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer getBalance(String token) {
        try {
            String answer = SendMessage("get_balance " + token);
            System.out.println(token + answer);
            return Integer.parseInt(answer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void exit() {
        try {
            SendMessage("exit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
