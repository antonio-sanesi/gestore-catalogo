package dev.anto.gestore.catalogo.error;

import lombok.Data;

@Data
public class ErrorDto {
    private int status;
    private String message;
    private long timeStamp = System.currentTimeMillis();
}