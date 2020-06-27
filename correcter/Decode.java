package correcter;

import java.io.IOException;

public class Decode extends FileHandling{

    Decode() throws IOException {
        byteToBinary("received.txt");
        showDataInfo();
        binaryToByte(super.str, "decoded.txt");
    }

    @Override
    void showDataInfo() {
        System.out.println("received.txt:");
        String binary = RadixUtil.stringToBinary(super.str);
        System.out.println("hex view: " + RadixUtil.binaryToHex(binary));
        System.out.println("bin view: " + binary);
        System.out.println();
        System.out.println("decode.txt");
        String correctBytes = RadixUtil.correctError(binary);
        System.out.println("correct: " + correctBytes);

        String decodedStr = RadixUtil.decode(correctBytes);
        System.out.println("decode: " + decodedStr);
        binary = decodedStr;
        System.out.println("hex view: " + RadixUtil.binaryToHex(binary));
        System.out.println("text view: " + RadixUtil.binaryToString(binary));
        super.str = binary;
    }
}
