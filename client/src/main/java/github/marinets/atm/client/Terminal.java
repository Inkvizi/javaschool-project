package github.marinets.atm.client;

public interface Terminal {
    boolean startSession();
    boolean checkIsVacant(); //необходимость зависит от задачи, для клиента достаточно видеть на экране приветствие
    void showBalance();
    void stopSession();
}
