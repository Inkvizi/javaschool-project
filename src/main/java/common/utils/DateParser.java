package common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parseStringToLocalDate(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(source, formatter);
    }
}
