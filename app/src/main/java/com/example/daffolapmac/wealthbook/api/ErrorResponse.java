package com.example.daffolapmac.wealthbook.api;

/**
 * An error implementation of the Errors returned from the server.
 */
public class ErrorResponse {


    private final int mErrorMessage;
    private String mErrorCode;
    private String error;
    private String error_description;

    /**
     * Creates an instance of the error.
     *
     * @param errorMessage the error message equivalent.
     * @param errorCode Error code
     */
    ErrorResponse(int errorMessage, String errorCode) {
        mErrorMessage = errorMessage;
        mErrorCode = errorCode;
    }
    
    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    public int getErrorMessage() {
        return mErrorMessage;
    }
    
    /** Get error code */
    public String getErrorCode() {
        return mErrorCode;
    }
    
    /** Set error code */
    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
