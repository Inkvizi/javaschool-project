package authentication;

public class CardMagnetic implements Card{
    @Override
    public String getAuthenticationData() {
        return "abracadabraData";
    }
}
