package com.salis.order.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GenericResponse {

    private boolean success;
    private List<GenericError> errors;
    private String errorMessage;
    private Object data;

    public static GenericResponse createResponse(Object data) {
        return new GenericResponse(true, new ArrayList<>(), "", data);
    }

    public static GenericResponse createErrorResponse(String errorMessage, GenericError error) {
        return new GenericResponse(false, Collections.singletonList(error), errorMessage, null);
    }
}
