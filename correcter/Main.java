package correcter;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("\nWrite a mode (encode, send, decode, exit): ");
            String mode = scanner.nextLine();
            System.out.println();
            switch (mode) {
                case "encode":
                    new Encode();
                    break;
                case "send":
                    new Send();
                    break;
                case "decode":
                    new Decode();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input please try again");
            }
        }
    }
}