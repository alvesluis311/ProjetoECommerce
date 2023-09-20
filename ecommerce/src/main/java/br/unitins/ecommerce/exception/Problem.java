package br.unitins.ecommerce.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class Problem {
    private OffsetDateTime timestamp;
    private int status;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}

