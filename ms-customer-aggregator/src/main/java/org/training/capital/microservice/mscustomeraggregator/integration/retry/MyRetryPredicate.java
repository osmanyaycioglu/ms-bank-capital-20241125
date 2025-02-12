package org.training.capital.microservice.mscustomeraggregator.integration.retry;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.training.capital.microservice.mscommon.error.ErrorObj;

import java.io.IOException;
import java.util.function.Predicate;

public class MyRetryPredicate implements Predicate<Throwable> {
    private static final Logger logger = LoggerFactory.getLogger(MyRetryPredicate.class);
    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof HttpClientErrorException){
            HttpClientErrorException he = (HttpClientErrorException) throwableParam;
            HttpStatusCode           statusCodeLoc = he.getStatusCode();
            if (statusCodeLoc.value() == 404){
                return false;
            }
            ObjectMapper objectMapperLoc = new ObjectMapper();
            try {
                ErrorObj errorObjLoc = objectMapperLoc.readValue(he.getResponseBodyAsByteArray(),
                                                                 ErrorObj.class);
                Integer errorCodeLoc = errorObjLoc.getErrorCode();
                if (errorCodeLoc > 1000 && errorCodeLoc < 2000){
                    return true;
                }
            } catch (Exception eParam) {
                logger.error("[MyRetryPredicate][test]-> *Error* : " + eParam.getMessage(),eParam);
            }
        } else if (throwableParam instanceof IllegalArgumentException) {
            return true;
        }
        return true;
    }
}
