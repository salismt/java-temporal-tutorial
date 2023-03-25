package org.example.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"entity", "message", "error_code", "metadata"})
public class GenericError {

    public static final String ERROR_ARRAY_STRING_KEY = "error_array_string";
    public static final String ERROR_HASH_ARRAY_STRING_KEY = "error_hash_array_string";

    public GenericError(String entity, String message) {
        this.entity = entity;
        this.message = message;
        this.errorCode = 500;
    }

    public GenericError(String entity, String message, Integer errorCode) {
        this.entity = entity;
        this.message = message;
        this.errorCode = errorCode;
    }

    private String entity;
    private String message;
    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonIgnore
    private String errorKeyMessage;

    @JsonIgnore
    private List<Object> errorKeyMessageArgs;

    @Builder.Default
    private Map<String, Object> metadata = null;

}
