package ServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
    ServerSocket serverSocket;
    Socket server;
    private int port;
    Account account = null;

    public Server(int port) throws IOException {
        System.out.println("Starting payement server...");
        serverSocket = new ServerSocket(port);
    }

    public Transaction getTransaction() throws IOException, ClassNotFoundException {
        return (Transaction) (new ObjectInputStream(this.server.getInputStream())).readObject();
    }

    public boolean verifySold() throws IOException {
        Scanner sc = new Scanner(this.server.getInputStream());
        double amount = Double.parseDouble(sc.nextLine());
        String message = amount <= this.account.getSold() ? "ok" : "ko";
        PrintStream ps = new PrintStream(this.server.getOutputStream());
        ps.println(message);
        ps.flush();
        if(amount <= this.account.getSold()){
            this.account.setSold( this.account.getSold() - amount );
            return true;
        } return false;
    }

    @Override
    public void run(){
        try {
            server = serverSocket.accept();
            account = new Account(1, new Bank(1, "CIH Bank"), 1000);
            while (true){
                System.out.println("Waiting for payement requests...");
                if(verifySold()){
                    System.out.println("A payement attemption has been detected!");
                    Transaction transaction = getTransaction();
                    saveTransaction(transaction);
                    PrintStream ps = new PrintStream(this.server.getOutputStream());
                    ps.println("Il vous reste " + this.account.getSold() + " Dh dans votre compte.");
                    ps.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTransaction(Transaction transaction) {
        (new TransactionDAOIMPL()).create(transaction);
    }

    public void startFromOutside() throws IOException {
        Server server = new Server(1997);
        server.start();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(1997);
        server.start();
    }
}
