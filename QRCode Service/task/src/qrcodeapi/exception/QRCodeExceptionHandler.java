package qrcodeapi.exception;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import qrcodeapi.entity.ApiError;

@ControllerAdvice
public class QRCodeExceptionHandler {

    @ExceptionHandler(QRCodeInvalidDataException.class)
    @Nullable
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (ex instanceof QRCodeInvalidDataException qrInvalidExc) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleQRCodeInvalidDataException(qrInvalidExc, httpHeaders, status, request);
        }
        return null;
    }

    protected ResponseEntity<ApiError> handleQRCodeInvalidDataException(QRCodeInvalidDataException ex, HttpHeaders httpHeaders, HttpStatus status, WebRequest request) {
        String error = ex.getMessage();

        return new ResponseEntity<>(new ApiError(error), httpHeaders, status);
    }

}
