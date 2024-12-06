package org.training.capital.microservice.mscommon.error;

import java.util.List;

public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String         errorDescription;
    private Integer        errorCode;

    public ErrorObj() {
    }

    public ErrorObj(List<ErrorObj> subErrors,
                    String errorDescription,
                    Integer errorCode) {
        this.subErrors        = subErrors;
        this.errorDescription = errorDescription;
        this.errorCode        = errorCode;
    }

    public static ErrorObjBuilder builder() {
        return new ErrorObjBuilder();
    }

    public List<ErrorObj> getSubErrors() {
        return this.subErrors;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setSubErrors(List<ErrorObj> subErrors) {
        this.subErrors = subErrors;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ErrorObj)) {
            return false;
        }
        final ErrorObj other = (ErrorObj) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object this$subErrors  = this.getSubErrors();
        final Object other$subErrors = other.getSubErrors();
        if (this$subErrors == null ? other$subErrors != null : !this$subErrors.equals(other$subErrors)) {
            return false;
        }
        final Object this$errorDescription  = this.getErrorDescription();
        final Object other$errorDescription = other.getErrorDescription();
        if (this$errorDescription == null ? other$errorDescription
                                            != null : !this$errorDescription.equals(other$errorDescription)) {
            return false;
        }
        final Object this$errorCode  = this.getErrorCode();
        final Object other$errorCode = other.getErrorCode();
        if (this$errorCode == null ? other$errorCode != null : !this$errorCode.equals(other$errorCode)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorObj;
    }

    public int hashCode() {
        final int    PRIME      = 59;
        int          result     = 1;
        final Object $subErrors = this.getSubErrors();
        result = result * PRIME + ($subErrors == null ? 43 : $subErrors.hashCode());
        final Object $errorDescription = this.getErrorDescription();
        result = result * PRIME + ($errorDescription == null ? 43 : $errorDescription.hashCode());
        final Object $errorCode = this.getErrorCode();
        result = result * PRIME + ($errorCode == null ? 43 : $errorCode.hashCode());
        return result;
    }

    public String toString() {
        return "ErrorObj(subErrors="
               + this.getSubErrors()
               + ", errorDescription="
               + this.getErrorDescription()
               + ", errorCode="
               + this.getErrorCode()
               + ")";
    }

    @com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "with", buildMethodName = "build")
    public static class ErrorObjBuilder {
        private List<ErrorObj> subErrors;
        private String         errorDescription;
        private Integer        errorCode;

        ErrorObjBuilder() {
        }

        public ErrorObjBuilder withSubErrors(List<ErrorObj> subErrors) {
            this.subErrors = subErrors;
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
                                this.errorDescription,
                                this.errorCode);
        }

        public String toString() {
            return "ErrorObj.ErrorObjBuilder(subErrors="
                   + this.subErrors
                   + ", errorDescription="
                   + this.errorDescription
                   + ", errorCode="
                   + this.errorCode
                   + ")";
        }
    }
}
