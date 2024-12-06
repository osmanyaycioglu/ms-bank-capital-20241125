package org.training.capital.microservice.msorder.input.models;

import lombok.Data;

@Data
public class Response<T> {
    private boolean errorOccurred;
    private String errorStr;
    private Integer errorCode;
    private T response;
}
