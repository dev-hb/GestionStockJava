package ServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    ServerSocket serverSocket;
    Socket server;
    private int port;

    public Server(int port) throws IOException {
        System.out.println("Starting payement server...");
        serverSocket = new ServerSocket(port);
    }

    public Transaction getTransaction() throws IOException, ClassNotFoundException {
        return (Transaction) (new ObjectInputStream(this.server.getInputStream())).readObject();
    }

    @Override
    public void run(){
        try {
            server = serverSocket.accept();
            System.out.println("A payement attemption has been detected!");
            Transaction transaction = getTransaction();
            saveTransaction(transaction);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTransaction(Transaction transaction) {

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(1997);
        server.start();
    }
}
