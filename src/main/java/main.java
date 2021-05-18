import atm.Terminal;
import atm.TerminalDefault;
import atm.devices.cardreader.CardReader;
import atm.devices.cardreader.CardReaderDefault;
import atm.devices.display.Display;
import atm.devices.display.DisplayLED;
import atm.devices.input.InputKeyBoard;
import atm.devices.input.InputReader;

public class main {
    public static void main(String[] args) {
        CardReader cardReader = new CardReaderDefault();
        Display display = new DisplayLED();
        InputReader inputReader = new InputKeyBoard();
        Terminal terminal = new TerminalDefault(cardReader, display, inputReader);
        if (terminal.checkIsVacant()) {
            if (terminal.startSession()) {
                terminal.showBalance();
                terminal.stopSession();
            }
        }
    }
}
