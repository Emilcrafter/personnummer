package personnummer;


import java.io.*;

public class Main {
    /**
     * Main method for the application.
     * The application expects any number of lines of input from the user.
     * Each line of the input is classified as a personal, coordination, organization or invalid number.
     *
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        do {
            try {
                line = reader.readLine();
                if (line != null) {
                    String classification = NumberClassifier.classify(line);
                    writer.write(String.format("%s is a(n) %s number\n", line, classification));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (line != null);

        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}