import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ClientServerInteraction {
    public static void main(String[] args) {
        ServerThread server = new ServerThread();
        ClientThread client = new ClientThread(server);

        server.start();
        client.start();
    }
}

class ServerThread extends Thread {
    private static final Map<String, String> replies = new HashMap<>();

    static {
        replies.put("1", "hello");
        replies.put("2", "I am fine, thank you!");
        // Add more replies here
    }

    private String request;

    public synchronized String getRequest() {
        return request;
    }

    public synchronized void setRequest(String request) {
        this.request = request;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    wait(); // Wait for the client to notify
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (request.equalsIgnoreCase("exit")) {
                    System.out.println("Server: Client requested to exit.");
                    break;
                }

                // Process the request
                System.out.println("Server: Received request from client - " + request);
                String reply = replies.getOrDefault(request, "Invalid choice.");
                System.out.println("Server: Sending reply to client - " + reply);

                ClientThread.setReply(reply);

                request = null; // Reset the request after processing
                notify(); // Notify the client that the response is sent
            }
        }
    }
}

class ClientThread extends Thread {
    private static String reply;
    private static boolean isRunning = true;
    private ServerThread server;

    public ClientThread(ServerThread server) {
        this.server = server;
    }

    public static void setReply(String reply) {
        ClientThread.reply = reply;
    }

    public static void stopRunning() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); // Wait for the server to start

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (isRunning) {
                System.out.println("\nClient: Choose a message from the options below:");
                System.out.println("1. Say hi");
                System.out.println("2. Ask how are you");
                System.out.println("Type 'exit' to stop the client.");

                String choice = userInput.readLine();
                if (choice.equalsIgnoreCase("exit")) {
                    System.out.println("Client: Exiting.");
                    server.setRequest("exit"); // Notify the server to stop
                    break;
                }

                server.setRequest(choice);
                synchronized (server) {
                    server.notify(); // Notify the server to process the request
                    try {
                        server.wait(); // Wait for the server to send the response
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Server's Reply: " + reply);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
