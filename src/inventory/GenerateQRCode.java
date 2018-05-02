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
import com.google.gson.*;
import com.google.zxing.Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import java.io.OutputStream;
import java.util.Random;
import model.QRModel;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class GenerateQRCode {
    
    public static String generateQRCode(String itemNo, String itemName, long barcodeIdentifier) throws IOException, WriterException{
        String filePathQr = "C:/DSL QR Codes/" + itemName + "-" + itemNo +".png";
        int size = 250;
        String fileType = "png";
        Gson gson = new Gson();
        
        File myFile = new File(filePathQr);
        
        String barcodeData = Long.toString(barcodeIdentifier);
        Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(barcodeData, BarcodeFormat.QR_CODE, size,
                        size, hintMap);
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
                

        
        return "Successful";
    }
    
    public static String generateBarcode(String itemNo, String itemName, long barcodeIdentifier) throws WriterException, IOException, ConfigurationException, BarcodeException{
        String filePathBarcode = "C:/DSL Barcodes/" + itemName + "-" + itemNo +".png";
        BarcodeUtil util = BarcodeUtil.getInstance();
        BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("code128"));

        OutputStream fout = new FileOutputStream(filePathBarcode);
        int resolution = 200;
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
            fout, "image/png", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        gen.generateBarcode(canvas, Long.toString(barcodeIdentifier));
        canvas.finish();
        
        return "Successful";
    }

    public static Configuration buildCfg(String type) {
        DefaultConfiguration cfg = new DefaultConfiguration("barcode");

        //Bar code type
        DefaultConfiguration child = new DefaultConfiguration(type);
        cfg.addChild(child);

        //Human readable text position
        DefaultConfiguration attr = new DefaultConfiguration("human-readable");
        DefaultConfiguration subAttr = new DefaultConfiguration("placement");
          subAttr.setValue("bottom");
          attr.addChild(subAttr);

          child.addChild(attr);
      return cfg;
    }
//    public static String generateBarcode(String itemNo, long barcodeIdentifier) throws WriterException, IOException{
//        String filePathBarcode = "C:/DSL Barcodes/" + itemNo +".png";
//        int size = 250;
//        String fileType = "png";
//        Gson gson = new Gson();
//        File myFileBarcode = new File(filePathBarcode);
//        String barcodeData = Long.toString(barcodeIdentifier);
//        
//        Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
//        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
//        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//        BitMatrix byteMatrix = new EAN13Writer().encode(barcodeData, BarcodeFormat.EAN_13, size, size, hintMap);
//        int CrunchifyWidth = byteMatrix.getWidth();
//        BufferedImage imageBarcode = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
//                        BufferedImage.TYPE_INT_RGB);
//        
//        imageBarcode.createGraphics();
//        Graphics2D graphicsBarcode = (Graphics2D) imageBarcode.getGraphics();
//        graphicsBarcode.setColor(Color.WHITE);
//        graphicsBarcode.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
//        graphicsBarcode.setColor(Color.BLACK);
//        
//        for (int i = 0; i < CrunchifyWidth; i++) {
//            for (int j = 0; j < CrunchifyWidth; j++) {
//                if (byteMatrix.get(i, j)) {
//                        graphicsBarcode.fillRect(i, j, 1, 1);
//                }
//            }
//        }
//        
//        ImageIO.write(imageBarcode, fileType, myFileBarcode);
//        return "Successful";
//    }

}
