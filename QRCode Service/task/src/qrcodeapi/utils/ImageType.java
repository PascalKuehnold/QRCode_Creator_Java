package qrcodeapi.utils;

import org.springframework.http.MediaType;

public enum ImageType {
    PNG(MediaType.IMAGE_PNG),
    JPEG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF);

    private final MediaType value;

    ImageType(MediaType value) {
        this.value = value;
    }

    public MediaType getValue() {
        return value;
    }
}
