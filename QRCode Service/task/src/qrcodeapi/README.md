## QR Code Image Generator
### GET /
This endpoint generates a QR code image from the provided contents.

**Request Parameters:**

| Parameter | Description                                                                             | Required | Default Value |
|---|-----------------------------------------------------------------------------------------|---|---|
| size | The size of the QR code image in pixels.                                                | False | 250 |
| type | The image format of the QR code image. Supported formats are `png`, `jpg` and `gif`.    | False | `png` |
| correction | The error correction level of the QR code. Supported levels are `L`, `M`, `Q`, and `H`. | False | `L` |
| contents | The data to encode into the QR code.                                                    | True | |

**GET Endpoint Full:**

GET /qrcode?size=300&type=jpg&correction=L&contents="Hello, world!"

**GET Endpoint Default:**

GET /qrcode?contents="Hello, world!"

**Response:**

* A `BufferedImage` object containing the generated QR code image
* An HTTP status code of `200` if the QR code was generated successfully
* An HTTP status code of `400` if an invalid request parameter was provided
* An HTTP status code of `500` if an error occurred generating the QR code

