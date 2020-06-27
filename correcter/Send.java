package correcter;

import java.io.IOException;

public class Send extends FileHandling {

    Send() throws IOException {
        byteToBinary("encoded.txt");
        showDataInfo();
        binaryToByte(super.str, "received.txt");
    }

    @Override
    void showDataInfo() {
        System.out.println("encoded.txt:");
        String binary = RadixUtil.stringToBinary(super.str);
        System.out.println("hex view: " + RadixUtil.binaryToHex(binary));
        System.out.println("bin view: " + binary);
        System.out.println();
        System.out.println("received.txt");
        String errorBytes = RadixUtil.giveError(binary);
        super.str = errorBytes;
        System.out.println("bin view: " + errorBytes);
        System.out.println("hex view: " + RadixUtil.binaryToHex(errorBytes));
    }
}
