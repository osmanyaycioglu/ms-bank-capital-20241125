package org.training.capital.microservice.mscommon.error;

import java.util.List;

public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String         boundedContext;
    private String         microservice;
    private String         errorDescription;
    private Integer        errorCode;

    public ErrorObj() {
    }

    public ErrorObj(List<ErrorObj> subErrors,
                    String boundedContext,
                    String microservice,
                    String errorDescription,
                    Integer errorCode) {
        this.subErrors        = subErrors;
        this.boundedContext   = boundedContext;
        this.microservice     = microservice;
        this.errorDescription = errorDescription;
        this.errorCode        = errorCode;
    }


    public List<ErrorObj> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(final List<ErrorObj> subErrorsParam) {
        subErrors = subErrorsParam;
    }

    public String getBoundedContext() {
        return boundedContext;
    }

    public void setBoundedContext(final String boundedContextParam) {
        boundedContext = boundedContextParam;
    }

    public String getMicroservice() {
        return microservice;
    }

    public void setMicroservice(final String microserviceParam) {
        microservice = microserviceParam;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(final String errorDescriptionParam) {
        errorDescription = errorDescriptionParam;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final Integer errorCodeParam) {
        errorCode = errorCodeParam;
    }

    public static ErrorObjBuilder builder() {
        return new ErrorObjBuilder();
    }

    public static class ErrorObjBuilder {
        private List<ErrorObj> subErrors;
        private String         boundedContext;
        private String         microservice;
        private String         errorDescription;
        private Integer        errorCode;

        ErrorObjBuilder() {
        }

        public ErrorObjBuilder withSubErrors(List<ErrorObj> subErrors) {
            this.subErrors = subErrors;
            return this;
        }

        public ErrorObjBuilder withBoundedContext(String boundedContext) {
            this.boundedContext = boundedContext;
            return this;
        }

        public ErrorObjBuilder withMicroservice(String microservice) {
            this.microservice = microservice;
            return this;
        }

        public ErrorObjBuilder withErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public ErrorObjBuilder withErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorObj build() {
            return new ErrorObj(this.subErrors,
                                this.boundedContext,
                                this.microservice,
                                this.errorDescription,
                                this.errorCode);
        }

        public String toString() {
            return "ErrorObj.ErrorObjBuilder(subErrors="
                   + this.subErrors
                   + ", boundedContext="
                   + this.boundedContext
                   + ", microservice="
                   + this.microservice
                   + ", errorDescription="
                   + this.errorDescription
                   + ", errorCode="
                   + this.errorCode
                   + ")";
        }
    }
}
