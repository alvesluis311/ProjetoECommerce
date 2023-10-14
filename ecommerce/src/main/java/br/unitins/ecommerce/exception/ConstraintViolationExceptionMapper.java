package br.unitins.ecommerce.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;
import org.jboss.logging.Logger;
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOG.error("Erro de validação: " + exception.getMessage());

        String details = exception.getConstraintViolations().stream()
                .map(v -> {
                    String path = v.getPropertyPath().toString();
                    String refinedPath = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1) : path;
                    return refinedPath + ": " + v.getMessage();
                })
                .filter(str -> !str.trim().isEmpty())
                .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError(
                "https://localhost:8080/validation-error",
                "Erro de validação",
                Response.Status.BAD_REQUEST.getStatusCode(),
                details
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(apiError)
                .build();
    }

}
