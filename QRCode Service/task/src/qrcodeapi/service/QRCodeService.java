package qrcodeapi.service;

import org.springframework.http.ResponseEntity;
import qrcodeapi.exception.QRCodeInvalidDataException;

import java.awt.image.BufferedImage;

public interface QRCodeService {
    ResponseEntity<BufferedImage> getImage(
            Integer size,
            String type,
            String contents,
            String correction) throws QRCodeInvalidDataException;
}
