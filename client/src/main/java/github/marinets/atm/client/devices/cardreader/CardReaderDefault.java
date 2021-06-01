package github.marinets.atm.client.devices.cardreader;

import github.marinets.atm.common.authentication.Encoder;
import github.marinets.atm.common.utils.DateParser;

import java.time.LocalDate;
import java.util.Scanner;

public class CardReaderDefault implements CardReader {
    private String cardNumber;
    private LocalDate expirationDate;

    @Override
    public String readAuthenticationData() {
        System.out.println("Введите номер карты из 16 цифр: ");
        cardNumber = readNonEmptyString();
        System.out.println("Введите дату окончания срока действия карты (формат ДД.ММ.ГГГГ): ");
        expirationDate = DateParser.parseStringToLocalDate(readAuthenticationData());
        return getAuthenticationData();
    }

    @Override
    public void extractCard() {
        //вытаскиваем карту и зачищаем данные
        cardNumber = null;
        expirationDate = null;
    }

    private String readNonEmptyString() {
        String result;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            result = scanner.nextLine();
            if (!result.isEmpty()) {
                break;
            }
        }
        return result;
    }

    private String getAuthenticationData() {
        return Encoder.EncodeCardData(cardNumber, expirationDate);
    }
}
