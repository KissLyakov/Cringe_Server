import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static ServerSocket serverSocket;
    static Socket socket;
    static BufferedReader in;
    static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try{
            serverSocket = new ServerSocket(4004);
            socket = serverSocket.accept();
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                //приём и вывод строки с клиента
                String result = in.readLine();
                System.out.println(result);

                out.write("Ответ сервера");
                out.flush();
            }catch (IOException error){
                System.err.println(error);
            }
        }catch (IOException error) {
            System.err.println(error);
        }finally {
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        }
    }
}