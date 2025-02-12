package org.training.capital.microservice.mscommon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {
    @Autowired
    private MsProps msProps;

    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    public ErrorObj fillMsProps(ErrorObj errorObjParam) {
        errorObjParam.setMicroservice(msProps.getMicroservice());
        errorObjParam.setBoundedContext(msProps.getBoundedContext());
        return errorObjParam;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handle(IllegalStateException exp) {
        return fillMsProps(ErrorObj.builder()
                                   .withErrorDescription(exp.getMessage())
                                   .withErrorCode(1001)
                                   .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handle(IllegalArgumentException exp) {
        return fillMsProps(ErrorObj.builder()
                                   .withErrorDescription(exp.getMessage())
                                   .withErrorCode(1002)
                                   .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MethodArgumentNotValidException exp) {
        ErrorObj validationErrorLoc = ErrorObj.builder()
                                              .withErrorDescription("Validation error")
                                              .withErrorCode(1003)
                                              .withSubErrors(exp.getAllErrors()
                                                                .stream()
                                                                .map(e -> fillMsProps(ErrorObj.builder()
                                                                                              .withErrorDescription(e.toString())
                                                                                              .withErrorCode(1005)
                                                                                              .build()))
                                                                .collect(Collectors.toList()))
                                              .build();
        return fillMsProps(validationErrorLoc);
    }




    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorObj handle(AuthorizationDeniedException exp) {
        return fillMsProps(ErrorObj.builder()
                                   .withErrorDescription(exp.getMessage())
                                   .withErrorCode(4003)
                                   .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorObj handle(AccessDeniedException exp) {
        return fillMsProps(ErrorObj.builder()
                                   .withErrorDescription(exp.getMessage())
                                   .withErrorCode(4003)
                                   .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorObj handle(Exception exp) {
        logger.error("[ErrorAdvice][handle]-> *Error* : " + exp.getMessage(),exp);
        return fillMsProps(ErrorObj.builder()
                                   .withErrorDescription(exp.getMessage())
                                   .withErrorCode(5001)
                                   .build());
    }

    private List<ErrorObj> getErrorsFromMANVE(MethodArgumentNotValidException exp) {
        return exp.getAllErrors()
                  .stream()
                  .peek(e -> System.out.println("Error : " + e.getClass()))
                  .map(e -> (FieldError) e)
                  .map(this::generateErrorObjFromFieldError)
                  .collect(Collectors.toList());
    }

    private ErrorObj generateErrorObjFromFieldError(FieldError e) {
        return ErrorObj.builder()
                       .withErrorDescription(e.getDefaultMessage()
                                             + " rejected : "
                                             + e.getRejectedValue())
                       .withErrorCode(1005)
                       .build();
    }
}
