package common.authentication;

public class Decoder {
    public static String[] decodeCardData(String encodedData) {
        return encodedData.split(";");
    }
}
