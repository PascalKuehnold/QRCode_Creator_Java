package qrcodeapi.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import qrcodeapi.exception.QRCodeInvalidDataException;
import qrcodeapi.utils.ImageType;
import qrcodeapi.utils.QRCodeImageGenerator;

import java.awt.image.BufferedImage;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Override
    public ResponseEntity<BufferedImage> getImage(
            Integer size,
            String type,
            String contents,
            String correction) throws QRCodeInvalidDataException {

        if(contents == null || contents.isBlank()){
            throw QRCodeInvalidDataException.createWith("Contents cannot be null or blank");
        }

        if (size == null || (size < 150 || size > 350)) {
            throw QRCodeInvalidDataException.createWith("Image size must be between 150 and 350 pixels");
        }

        if(!correction.matches("^[lLmMqQhH]$")){
            throw QRCodeInvalidDataException.createWith("Permitted error correction levels are L, M, Q, H");
        }

        if(type == null || type.isBlank()){
            throw QRCodeInvalidDataException.createWith("Only png, jpeg and gif image types are supported");
        }

        MediaType mediaType = getImageMediaType(type);

        BufferedImage bufferedImage = QRCodeImageGenerator
                .getGeneratedQRCodeImage(size, contents, correction);

        if(bufferedImage == null){
            throw QRCodeInvalidDataException.createWith("Could not create a QR Code Image");
        }

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(bufferedImage);
    }


    private MediaType getImageMediaType(String type) throws QRCodeInvalidDataException {
        MediaType supportedImageType = switch (type.toLowerCase()) {
            case "png" -> ImageType.PNG.getValue();
            case "jpeg" -> ImageType.JPEG.getValue();
            case "gif" -> ImageType.GIF.getValue();
            default -> null;
        };

        if (supportedImageType == null) {
            throw QRCodeInvalidDataException.createWith("Only png, jpeg and gif image types are supported");
        }

        return supportedImageType;
    }

}
