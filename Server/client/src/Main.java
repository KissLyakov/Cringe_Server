import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Socket clientSocket; //сокет для общения
    static BufferedReader reader; //ридер читающий с консоли
    static BufferedReader in; //чтение из сокета
    static BufferedWriter out; //запись в сокет

    public static void main(String[] args) throws IOException {
        try {
            try {
                clientSocket = new Socket("localhost", 4004); //порт такой же как у сервера; запрашиваем соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //читать с сервера
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); //писать на сервер

                System.out.println("Введите два числа на сумму: ");
                String line = scanner.nextLine() + "/n";
                out.write(line);
                out.flush();

                //получаем ответ с свервера и выводим
                String serverAnswer = in.readLine();
                System.out.println(serverAnswer);

            } catch (IOException error) {
                System.err.println(error);
            }
        } finally {
            in.close();
            out.close();
            clientSocket.close();
            reader.close();
        }
    }
}