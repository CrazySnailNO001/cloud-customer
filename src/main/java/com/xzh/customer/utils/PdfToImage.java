package com.xzh.customer.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-12 10:29
 * @description：
 * @modified By：
 * @version:
 */
public class PdfToImage {
    /*
     * *
     * 实现pdf文件转换为png
     * 参数是第一个是要转的转换的是pdffile
     * 第二个参数是是要存储的png图片的路径
     */
    public static void pdfFileToImage(File pdfFile, String targetPath) {
        try {
            FileInputStream instream = new FileInputStream(pdfFile);
            InputStream byteInputStream = null;
            PDDocument doc = null;
            ImageOutputStream imOut = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                doc = PDDocument.load(instream);
                PDFRenderer renderer = new PDFRenderer(doc);
                int pageCount = doc.getNumberOfPages();
                if (pageCount > 0) {
                    BufferedImage image = renderer.renderImage(0, 2.0f);
                    image.flush();
                    imOut = ImageIO.createImageOutputStream(bos);
                    ImageIO.write(image, "png", imOut);
                    byteInputStream = new ByteArrayInputStream(bos.toByteArray());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != byteInputStream)
                    byteInputStream.close();
                if (null != doc)
                    doc.close();
                if (null != imOut)
                    imOut.close();
                if (bos.size() > 0)
                    bos.close();
            }

            File uploadFile = new File(targetPath);
            FileOutputStream fops;
            fops = new FileOutputStream(uploadFile);
            try {
                fops.write(readInputStream(byteInputStream));
                fops.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fops.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static void main(String[] args) {
        File file = new File("/Users/ZHONGHUI/Code/MyCode/old/cloud-customer/test.pdf");
        //上传的是png格式的图片结尾
        String targetfile = "/Users/ZHONGHUI/Code/MyCode/old/cloud-customer/test.png";
        pdfFileToImage(file, targetfile);
    }
}
