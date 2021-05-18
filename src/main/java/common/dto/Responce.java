package common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Responce<T> {
    private ResponceErrorCodes errorCode;
    private String errorMessage;
    private T responseValue;

    public Responce(T value) {
        this.responseValue = value;
        errorCode = ResponceErrorCodes.ERROR_NONE;
        errorMessage = "";
    }

    public boolean isError() {
        return errorCode != ResponceErrorCodes.ERROR_NONE;
    }
}
