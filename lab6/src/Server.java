import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");

                try (Socket clientSocket = server.accept()) {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine();
                    System.out.println("Пришло сообщение от клиента: " + word + "\n");

                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    out.flush();
                } finally {
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}