package common.authentication;

import java.util.ArrayList;

public class Decoder {
    public static String[] decodeCardData(String encodedData) {
        return encodedData.split(";");
    }
}
