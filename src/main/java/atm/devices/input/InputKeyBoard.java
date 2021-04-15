package atm.devices.input;

import java.util.Scanner;

public class InputKeyBoard implements InputReader {
    @Override
    public String readPin() {
        System.out.println("Введите пин-код: ");
        Scanner scanner = new Scanner(System.in);
        String pin;
        while (true) {
            pin = scanner.nextLine();
            if (!pin.isEmpty()) {
                break;
            }
        }
        return pin;
    }
}
