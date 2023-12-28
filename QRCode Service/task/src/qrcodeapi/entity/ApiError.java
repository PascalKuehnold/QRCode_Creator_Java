package qrcodeapi.entity;

public class ApiError {
    private String error;

    public ApiError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}