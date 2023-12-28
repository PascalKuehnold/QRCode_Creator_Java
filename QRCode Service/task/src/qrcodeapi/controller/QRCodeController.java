package qrcodeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qrcodeapi.exception.QRCodeInvalidDataException;
import qrcodeapi.service.QRCodeService;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/qrcode")
    public ResponseEntity<BufferedImage> getImage(
            @RequestParam(required = false, defaultValue = "250") Integer size,
            @RequestParam(required = false, defaultValue = "png") String type,
            @RequestParam(required = false, defaultValue = "L") String correction,
            @RequestParam String contents) throws QRCodeInvalidDataException {
        return qrCodeService.getImage(size, type, contents, correction);
    }

}
