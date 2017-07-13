/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
 
import javax.imageio.ImageIO;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
public class GenerateQRCode {
    
    public static String generateQRCode(int itemID) throws IOException{
     String myCodeText = "https://crunchify.com/";
        String filePath = "C:/DSL Barcodes/" + itemID +".png";
        //String filePath = "/Users/<userName>/Documents/CrunchifyQR.png";
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);
        try {  
            /* QR Code */
                Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
                hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix byteMatrix = qrCodeWriter.encode(Integer.toString(itemID), BarcodeFormat.QR_CODE, size,
                                size, hintMap);
//                BitMatrix bitMatrix = new UPCAWriter().encode(text, BarcodeFormat.UPC_A, width, height);
                int CrunchifyWidth = byteMatrix.getWidth();
                BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                                BufferedImage.TYPE_INT_RGB);
                image.createGraphics();

                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
                graphics.setColor(Color.BLACK);

                for (int i = 0; i < CrunchifyWidth; i++) {
                    for (int j = 0; j < CrunchifyWidth; j++) {
                        if (byteMatrix.get(i, j)) {
                                graphics.fillRect(i, j, 1, 1);
                        }
                    }
                }
                ImageIO.write(image, fileType, myFile);
                
                /* CODE 128 Barcode */
//                BitMatrix bitMatrix = new Code128Writer().encode("11111", BarcodeFormat.CODE_128, 150, 80, null);
//                MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("C:/Barcodes/code128.png")));
//                System.out.println("Code128 Barcode Generated.");
//                
//                BitMatrix bitMatrix1 = new UPCAWriter().encode("69277198116", BarcodeFormat.UPC_A, 150, 80, null);
//                MatrixToImageWriter.writeToStream(bitMatrix1, "png", new FileOutputStream(new File("C:/Barcodes/upca.png")));
//                System.out.println("UPC A Barcode Generated.");
//                
//                InputStream barCodeInputStream = new FileInputStream("file.jpg");
//                BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);W
//
//                LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                Reader reader = new MultiFormatReader();
//                Result result = reader.decode(bitmap);
        return "Successful";
        } catch (WriterException e) {
                e.printStackTrace();
                return "Failed";
        }
    }
}
