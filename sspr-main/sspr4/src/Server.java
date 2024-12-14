import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is running...");

            try (Socket socket = serverSocket.accept();
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                int[][] matrix = (int[][]) in.readObject();
                int minAboveDiagonal = Integer.MAX_VALUE;

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = i + 1; j < matrix[i].length; j++) {
                        if (matrix[i][j] < minAboveDiagonal) {
                            minAboveDiagonal = matrix[i][j];
                        }
                    }
                }

                double[][] result = new double[1][1];
                result[0][0] = minAboveDiagonal;

                out.writeObject(result);
                System.out.println("Minimum element above the main diagonal: " + minAboveDiagonal);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
