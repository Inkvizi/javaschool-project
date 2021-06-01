package github.marinets.atm.common.messages;

import lombok.Value;

@Value
public class Request {
    private final int id;
    private final String data;
    private final RequestTypes type;
}
