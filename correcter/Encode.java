package correcter;

import java.io.IOException;

class Encode extends FileHandling{
    private String parity;

    Encode() throws IOException {
        byteToBinary("send.txt");
        showDataInfo();
        binaryToByte(parity, "encoded.txt");
    }

    @Override
    void showDataInfo() {
        System.out.println("send.txt:");
        System.out.println("text view: " + super.str);
        System.out.println("hex view: " + RadixUtil.stringToHex(super.str));
        String binary = RadixUtil.stringToBinary(super.str);
        System.out.println("bin view: " + binary);
        System.out.println();
        System.out.println("encoded.txt:");
        String parityShow = RadixUtil.hammingEncodeShow(binary);
        System.out.println("expand: " + parityShow);
        parity = RadixUtil.hammingEncode(parityShow);
        System.out.println("parity: " + parity);
        System.out.println("hex view: " + RadixUtil.binaryToHex(parity));
    }
}