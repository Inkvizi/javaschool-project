package github.marinets.atm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
