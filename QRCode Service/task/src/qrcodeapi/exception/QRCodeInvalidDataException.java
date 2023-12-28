package qrcodeapi.exception;

public class QRCodeInvalidDataException extends Exception {
    private final String message;

    public static QRCodeInvalidDataException createWith(String message) {
        return new QRCodeInvalidDataException(message);
    }

    private QRCodeInvalidDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
