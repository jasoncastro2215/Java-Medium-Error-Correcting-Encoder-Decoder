package correcter;
import java.io.*;

public abstract class FileHandling {
    String str;

    void byteToBinary(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileInputStream inputstream = new FileInputStream(file);
        int currentByte = inputstream.read();
        while (currentByte != -1) {
            sb.append((char) currentByte);
            currentByte = inputstream.read();
        }
        inputstream.close();
        this.str = sb.toString();
    }

    void binaryToByte(String str, String file) {
        String[] arr = str.split(" ");
        try (OutputStream outputStream = new FileOutputStream(file, false)) {
            for (int i = 0; i < arr.length; i++) {
                outputStream.write((byte)Integer.parseInt(arr[i], 2));
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

    abstract void showDataInfo();
}

