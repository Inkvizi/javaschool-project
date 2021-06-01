//package github.marinets.atm.client;
//
//import github.marinets.atm.client.domain.Card;
//import github.marinets.atm.client.domain.Client;
//import github.marinets.atm.client.devices.cardreader.CardReader;
//import github.marinets.atm.client.devices.display.Display;
//import github.marinets.atm.client.devices.input.InputReader;
//import github.marinets.atm.client.mappers.BalanceMapper;
//import github.marinets.atm.client.mappers.CardMapper;
//import github.marinets.atm.client.mappers.ClientMapper;
//import github.marinets.atm.server.Processing;
//import github.marinets.atm.common.dto.BalanceDTO;
//import github.marinets.atm.common.dto.ClientDTO;
//import github.marinets.atm.common.dto.Responce;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TerminalDefault {
//    private final CardReader cardReader;
//    private final Display display;
//    private final InputReader input;
//    private final Processing processing;
//    private final ClientMapper clientMapper;
//    private final BalanceMapper balanceMapper;
//    private boolean isVacant;
//    private Card card;
//    private Client client;
//
//    public TerminalDefault(CardReader cardReader, Display display, InputReader input) {
//        this.cardReader = cardReader;
//        this.display = display;
//        this.input = input;
//        this.clientMapper = new ClientMapper();
//        this.balanceMapper = new BalanceMapper();
//        isVacant = true;
//        display.showWelcome();
//    }
//
//    @Override
//    public void stopSession() {
//        stopInternal();
//    }
//
//    @Override
//    public void showBalance() {
//        Responce<BalanceDTO> balanceResponse = processing.getBalance(cardMapper.toDTO(card));
//        if (balanceResponse.isError()) {
//            display.showError(balanceResponse.getErrorMessage());
//        } else {
//            display.showBalance(balanceMapper.toModel(balanceResponse.getResponseValue()));
//        }
//        stopInternal(); //возможно не нужно делать так как показываться баланс будет не долго
//    }
//
//    private void stopInternal() {
//        cardReader.extractCard();
//        client = null;
//        display.showWelcome();
//        isVacant = true;
//    }
//}
