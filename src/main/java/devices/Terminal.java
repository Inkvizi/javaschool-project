package devices;

import authentication.Card;

public interface Terminal {
    void startSession(Card card);
    boolean checkIsVacant(); //необходимость зависит от задачи, для клиента достаточно видеть на экране приветствие
    void showBalance();
    void stopSession();
}
