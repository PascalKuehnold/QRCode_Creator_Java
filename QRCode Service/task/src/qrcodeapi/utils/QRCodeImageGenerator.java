package qrcodeapi.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Map;

public class QRCodeImageGenerator {

    public static BufferedImage getGeneratedQRCodeImage(Integer size, String data, String correction) {
        return writeQRCode(size, data, correction);
    }

    private static BufferedImage writeQRCode(Integer size, String data, String correction) {
        QRCodeWriter writer = new QRCodeWriter();

        ErrorCorrectionLevel errorCorrectionLevel = getErrorCorrectionLevel(correction);
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);

        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);

            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ErrorCorrectionLevel getErrorCorrectionLevel(String correction) {
        ErrorCorrectionLevel errorCorrectionLevel;

        switch (correction){
            case "l", "L" -> errorCorrectionLevel = ErrorCorrectionLevel.L;
            case "m", "M" -> errorCorrectionLevel = ErrorCorrectionLevel.M;
            case "q", "Q" -> errorCorrectionLevel = ErrorCorrectionLevel.Q;
            case "h", "H" -> errorCorrectionLevel = ErrorCorrectionLevel.H;
            default -> errorCorrectionLevel = null;
        }

        return errorCorrectionLevel;
    }
}
