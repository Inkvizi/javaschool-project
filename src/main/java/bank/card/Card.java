package bank.card;

import sun.util.calendar.LocalGregorianCalendar;

public interface Card {
    String getNumber();
    LocalGregorianCalendar.Date getExpirationDate();
    String getPinCode();

    boolean CheckPinCode();
    boolean CheckExpirationDate();
}
