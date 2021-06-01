package github.marinets.atm.client.controller;

import exception.InvalidSessionException;
import github.marinets.atm.client.domain.Card;
import github.marinets.atm.client.domain.Client;
import github.marinets.atm.client.mappers.BalanceMapper;
import github.marinets.atm.client.mappers.CardMapper;
import github.marinets.atm.client.mappers.ClientMapper;
import github.marinets.atm.client.services.TerminalService;
import github.marinets.atm.common.dto.BalanceDTO;
import github.marinets.atm.common.dto.CardDTO;
import github.marinets.atm.common.dto.ClientDTO;
import github.marinets.atm.common.dto.Responce;
import github.marinets.atm.common.messages.Request;
import github.marinets.atm.common.messages.RequestTypes;
import github.marinets.atm.client.domain.Balance;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Log
public class TerminalRestController {
    private TerminalService terminalService;
    private final CardMapper cardMapper;
    private final ClientMapper clientMapper;
    private final BalanceMapper balanceMapper;

    @GetMapping("/ATMs")
    public String getATMsStatus() {
        if (terminalService.checkIsVacant("")) {
            return "ready";
        } else {
            return "busy";
        }
    }

    @PostMapping("/ATMs/startSession")
    public String startSession(@RequestParam ("authenticationData") String authenticationData,
                               @RequestParam ("pin") String pinCode) {
        if (!terminalService.checkIsVacant("")) {
            return "ErrorMessage: terminal is busy";
        }

        int hostId = getReadyHostID();
        if (hostId == 0) {
            return "ErrorMessage: all hosts are busy";
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(new Request(hostId,
                "{\"authenticationData\":" + authenticationData + ",\"pinCode\":" + pinCode + "}", RequestTypes.JSON));
        log.info("request.toString()" + request.toString());
        ResponseEntity<Responce<CardDTO>> authenticationResponse = restTemplate.
                exchange("http://127.0.0.1:8181/hosts/" + String.valueOf(hostId) +
                                "/decodeAuthenticationData/?authenticationData=" + authenticationData + "&pinCode=" + pinCode,
                        HttpMethod.POST, request, new ParameterizedTypeReference<Responce<CardDTO>>(){});
        log.info("responseEntity: " + authenticationResponse.getBody().toString());
        if (authenticationResponse.getBody().isError()) {
            return "Error message: " + authenticationResponse.getBody().getErrorMessage();
        }
        Card card = cardMapper.toModel(authenticationResponse.getBody().getResponseValue());

        HttpEntity<CardDTO> requestClient = new HttpEntity<>(cardMapper.toDTO(card));
        ResponseEntity<Responce<ClientDTO>> clientResponse = restTemplate.
                exchange("http://127.0.0.1:8181/hosts/" + String.valueOf(hostId) + "/getClientByCard/",
                        HttpMethod.POST, requestClient, new ParameterizedTypeReference<Responce<ClientDTO>>(){});
        if (clientResponse.getBody().isError()) {
            return "Error message: " + clientResponse.getBody().getErrorMessage();
        }
        Client client = clientMapper.toModel(clientResponse.getBody().getResponseValue());

        String sessionID = terminalService.startSession(card, client);
        return "{sessionId: \"" + sessionID + "\", card: \"" + card.getNumber() + "\"}";
    }

    @GetMapping("/ATMs/getBalance/")
    public String getClientBalance(@RequestParam ("sessionId") String sessionId) {

        log.info("sessionId " + sessionId);

        if (!terminalService.checkIsVacant(sessionId)) {
            return "ErrorMessage: terminal is busy";
        }

        Card card = terminalService.loadCardBySession(sessionId).orElseThrow(InvalidSessionException::new);

        int hostId = getReadyHostID();
        if (hostId == 0) {
            return "ErrorMessage: all hosts are busy";
        }

        HttpEntity<CardDTO> requestBalance = new HttpEntity<>(cardMapper.toDTO(card));
        log.info("requestBalance.toString()" + requestBalance.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Responce<BalanceDTO>> balanceResponse = restTemplate.
                exchange("http://127.0.0.1:8181/hosts/" + String.valueOf(hostId) + "/getBalance/",
                        HttpMethod.POST, requestBalance, new ParameterizedTypeReference<Responce<BalanceDTO>>(){});

        if (balanceResponse.getBody().isError()) {
            return "Error message: " + balanceResponse.getBody().getErrorMessage();
        }
        Balance balance = balanceMapper.toModel(balanceResponse.getBody().getResponseValue());
        return "{account: \"" + balance.getAccountNumber() +
                "\", amount: \"" + balance.getAmount() +
                "\", currency: \"" + balance.getCurrency() + "\"}";
    }

    @PostMapping("/ATMs/stopSession/")
    public String stopSession(@RequestParam ("sessionId") String sessionId) {
        log.info("stop session: " + sessionId);
        terminalService.stopSession(sessionId);
        return "OK";
    }


        private int getReadyHostID() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Check for avaible hosts");
        ResponseEntity<String> responseGetHosts = restTemplate.
                getForEntity("http://127.0.0.1:8181/hosts", String.class);
        log.info("responseEntityStr.getBody()" + responseGetHosts.getBody());
        if (responseGetHosts.getBody().contains("host available")) {
            Integer hostId = Integer.parseInt(responseGetHosts.getBody().
                    replace("{data: \"", "").
                    replace(" host available\"}", "")
            );
            log.info("Check for host " + hostId.toString() + " for ready");
            ResponseEntity<String> responseHostReady = restTemplate.
                    getForEntity("http://127.0.0.1:8181/hosts/" + hostId.toString(), String.class);
            log.info("responseEntityStr.getBody()" + responseHostReady.getBody());
            if (responseHostReady.getBody().contains("Host " + hostId.toString() + " ready")) {
                return hostId;
            } else
            {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
