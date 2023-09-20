package br.unitins.ecommerce.exception;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Value;
@Value
public class ApiError {
    @JsonbProperty("type")
    String type;

    @JsonbProperty("title")
    String title;

    @JsonbProperty("status")
    int status;

    @JsonbProperty("detail")
    String detail;
}
