package authentication;

public class CardMagnet implements Card{
    @Override
    public String getAuthenticationData() {
        return "abracadabraData";
    }
}
