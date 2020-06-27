package correcter;

class RadixUtil {

    static String stringToHex(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length()) {
                sb.append(Integer.toHexString((int) str.charAt(i)));
                break;
            }
            sb.append(Integer.toHexString((int) str.charAt(i)) + " ");
        }
        return sb.toString();
    }

    static String stringToBinary(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(fillBinary(Integer.toBinaryString((int) str.charAt(i))));
            if (i == str.length() - 1)
                break;
            sb.append(" ");
        }
        return sb.toString();
    }

    static String fillBinary(String str) {
        int len = str.length();
        String newStr = "";
        for (int i = 0; i < 8-len; i++) {
            newStr += "0";
        }
        newStr += str;
        return newStr;
    }

    private static String expand(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            ++count;
            sb.append(str.charAt(i) + "" + str.charAt(i));
            if (count == 3 && i != str.length()){
                count = 0;
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static String parityMethod(String[] arr, int len, String str) {
        switch (arr[len].length()) {
            case 2:
                str += "0000" + (strToBool(arr[len].charAt(0)) ? "11" : "00");
                break;
            case 4:
                str += "00" + ((strToBool(arr[len].charAt(0)) ^ strToBool(arr[len].charAt(2))
                        ? "11" : "00"));
                break;
            case 6:
                str += ((strToBool(arr[len].charAt(0)) ^ strToBool(arr[len].charAt(2))
                        ^ strToBool(arr[len].charAt(4))) ? "11" : "00");
        }
        return str;
    }

    private static boolean strToBool(char c) {
        if (c == '1') {
            return true;
        }
        return false;
    }

    static String binaryToHex(String str) {
        StringBuilder sb = new StringBuilder();
        String arr[] = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("00000000"))
                sb.append("00");
            else{
                String hex = Integer.toHexString(Integer.parseInt(arr[i], 2));
                sb.append(hex.length() == 1 ? "0" + hex : hex);
            }
            if (i == arr.length - 1)
                break;
            sb.append(" ");
        }
        return sb.toString();
    }

    static String giveError(String str) {
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split(" ");
        StringBuilder[] sbArr = new StringBuilder[arr.length];
        for (int i = 0; i < sbArr.length; i++) {
            sbArr[i] = new StringBuilder(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = (int) (Math.random() * 5);
            if (arr[i].charAt(randomIndex) == '1')
                sbArr[i].setCharAt(randomIndex, '0');
            else
                sbArr[i].setCharAt(randomIndex, '1');
            if (i == arr.length - 1)
                break;
        }
        for (int i = 0; i < sbArr.length; i++) {
            sb.append(sbArr[i].toString());
            if (i == sbArr.length)
                break;
            sb.append(" ");
        }
        return sb.toString();
    }

    static String correctError(String str) {
        String[] arr = str.split(" ");
        int len = arr.length;
        StringBuilder[] sb = new StringBuilder[len];
        for (int i = 0; i < len; i++) {
            sb[i] = new StringBuilder(arr[i]);
        }
        for (int i = 0; i < len; i++) {
            int errorIndex = 0;
            errorIndex+=fillParity(sb[i], 1) == sb[i].charAt(0) ? 0 : 1;
            errorIndex+=fillParity(sb[i], 2) == sb[i].charAt(1) ? 0 : 2;
            errorIndex+=fillParity(sb[i], 4) == sb[i].charAt(3) ? 0 : 4;
            sb[i].setCharAt(errorIndex-1,
                    sb[i].charAt(errorIndex-1) == '1' ? '0' : '1');
        }

        return refactorMethod(sb).toString();
    }

    static StringBuilder refactorMethod(StringBuilder[] sb) {
        StringBuilder newSb = new StringBuilder();

        for (int i = 0; i < sb.length; i++) {
            newSb.append(sb[i].toString());
            if (i == sb.length)
                break;
            newSb.append(" ");
        }
        return newSb;
    }

    static String decode(String str) {
        StringBuilder sb = new StringBuilder();
        StringBuilder[] sbArr = refactorMethod2(str);
        for (int i = 0; i < sbArr.length; i++) {
            sb.append(sbArr[i].charAt(2) +""+ sbArr[i].charAt(4) +""+
                    sbArr[i].charAt(5) +""+ sbArr[i].charAt(6));
        }

        StringBuilder newSb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < sb.toString().length(); i++) {
            ++count;
            newSb.append(sb.charAt(i));
            if (count == 8 && i != sb.toString().length()) {
                newSb.append(" ");
                count = 0;
            }
        }
        return newSb.toString();
    }

    private static StringBuilder[] refactorMethod2(String str){
        String[] arr = str.split(" ");
        StringBuilder[] sbArr = new StringBuilder[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sbArr[i] = new StringBuilder(arr[i]);
        }
        return sbArr;
    }

    static String removeParity(String str) {
        String[] arr = str.split(" ");
        int lastByteLen = arr[arr.length - 1].length();
        return lastByteLen != 8 ? (str.substring(0, str.length() - 1 - lastByteLen)) : str;
    }

    static String hammingEncodeShow(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.replaceAll(" ", "");
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            ++count;
            switch (count) {
                case 1:
                    sb.append(".." + str.charAt(i));
                    break;
                case 2:
                    sb.append("." + str.charAt(i));
                    break;
                case 3:
                    sb.append(str.charAt(i));
                    break;
                case 4:
                    sb.append(str.charAt(i) + "." + " ");
                    count = 0;
                    break;
            }
        }
        return sb.toString();
    }

    static String hammingEncode(String str){
        String[] arr = str.split(" ");
        int len = arr.length;
        StringBuilder[] sb = new StringBuilder[len];
        for (int i = 0; i < len; i++) {
            sb[i] = new StringBuilder(arr[i]);
        }
        for (int i = 0; i < len; i++) {
            sb[i].setCharAt(0, fillParity(sb[i], 1));
            sb[i].setCharAt(1, fillParity(sb[i], 2));
            sb[i].setCharAt(3, fillParity(sb[i], 4));
            sb[i].setCharAt(sb[i].length()-1, '0');
        }

        return refactorMethod(sb).toString();
    }

    private static char fillParity(StringBuilder sb, int skipStep) {
        boolean skip = false;
        String join = "";
        int count = 0;
        for (int i = skipStep; i < sb.length(); i++) {
            ++count;
            if (count >= skipStep) {
                skip = skip ? false : true;
                count = 0;
            }
            join += skip ? "" : sb.charAt(i);
        }
        boolean parityValue = join.charAt(0) == '1' ? true : false;
        for (int i = 0; i < join.length() - 1; i++) {
            parityValue = parityValue ^ join.charAt(i+1) == '1' ? true : false ;
        }

        return parityValue ? '1' : '0';
    }

    static String binaryToString(String str) {
        String[] arr = str.split(" ");
        String text = "";
        for (int i = 0; i < arr.length; i++) {
            text += (char) Integer.parseInt(arr[i], 2);
        }
        return text;
    }
}
