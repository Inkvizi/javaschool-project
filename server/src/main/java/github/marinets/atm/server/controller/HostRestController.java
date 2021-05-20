package github.marinets.atm.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import github.marinets.atm.common.dto.CardDTO;
import github.marinets.atm.common.dto.ClientDTO;
import github.marinets.atm.common.dto.Responce;
import github.marinets.atm.server.Processing;
import github.marinets.atm.server.exception.HostNotFoundException;

@RestController
@AllArgsConstructor
@Log
public class HostRestController {

    private Processing processing;

    @GetMapping("/hosts")
    public String getHostsInfo() {
        return "{data: \"1 host available\"}";
    }

    @GetMapping("/hosts/{hostId}")
    public String getHostInfo(@PathVariable Long hostId) {
        if (hostId == 1) {
            return "{data: \"Host " + hostId + " ready\"}";
        }else{
            return "{data: \"Host " + hostId + " not ready\"}";
        }
    }

    @PostMapping("/hosts/{hostId}/decodeAuthenticationData/{authenticationData}{pinCode}")
    public Responce<CardDTO> decodeAuthenticationData(@PathVariable Long hostId,
                                                      @PathVariable String authenticationData,
                                                      @PathVariable String pinCode) {
        if (hostId != 1) {
            throw new HostNotFoundException();
        }
        log.info(hostId.toString() + ' ' + authenticationData);
        Responce<CardDTO> responce = processing.decodeAuthenticationData(authenticationData, pinCode);
        log.info(responce.toString());
        return responce;

    }

    @PostMapping("/hosts/{hostId}/getClientByCard/")
    public Responce<ClientDTO> getClientByCard(@PathVariable("hostId") Long hostId,
                                               @RequestBody CardDTO cardDTO) {
        if (hostId != 1) {
            throw new RuntimeException("Host " + hostId + " is not ready!");
        }

        log.info(cardDTO.toString());
        Responce<ClientDTO> response = processing.getClientByCard(cardDTO);
        log.info(response.toString());
        return response;
    }
}
