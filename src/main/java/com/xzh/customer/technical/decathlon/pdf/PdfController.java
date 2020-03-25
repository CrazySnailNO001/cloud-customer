package com.xzh.customer.technical.decathlon.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author ：xzh
 * @date ：Created in 2020-02-13 10:24
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/pdf")
public class PdfController {
    private final static int ROW_SIZE = 6;


    @PostMapping("/test001")
    public void test001() throws IOException, DocumentException {
        String tmpPath = "src/main/resources/";
        String directory = tmpPath + "shoppinglist_pattern.pdf";


        List<ShoppingListDto.SkuDataFormDto> skuDataFormDtoList = new ArrayList<>();

        ShoppingListDto.SkuDataFormDto skuDataFormDto01 = new ShoppingListDto.SkuDataFormDto();
        skuDataFormDto01.setSkuDiscout("12");
        skuDataFormDto01.setSkuId("5995");
        skuDataFormDto01.setSkuName("小型火箭");
        skuDataFormDto01.setSkuNum("4");
        skuDataFormDto01.setSkuPrice("4.00");
        skuDataFormDto01.setSkuSalePrice("50.00");

        ShoppingListDto.SkuDataFormDto skuDataFormDto02 = new ShoppingListDto.SkuDataFormDto();
        skuDataFormDto02.setSkuDiscout("12");
        skuDataFormDto02.setSkuId("12434");
        skuDataFormDto02.setSkuName("小型直升机");
        skuDataFormDto02.setSkuNum("4");
        skuDataFormDto02.setSkuPrice("12.00");
        skuDataFormDto02.setSkuSalePrice("34.00");

        skuDataFormDtoList.add(skuDataFormDto01);
        skuDataFormDtoList.add(skuDataFormDto02);


        int rows = skuDataFormDtoList.size();
        int pages = rows % ROW_SIZE == 0 ?
                rows / ROW_SIZE : (rows / ROW_SIZE + 1);

        ByteArrayOutputStream[] baos = new ByteArrayOutputStream[pages];
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

        List<PdfReader> readerList = new LinkedList<>();

        for (int i = 0; i < pages; i++) {
            PdfReader pdfReader = new PdfReader(directory);
            baos[i] = new ByteArrayOutputStream();
            PdfStamper pdfStamper = new PdfStamper(pdfReader, baos[i]);
            AcroFields acroFields = pdfStamper.getAcroFields();
            acroFields.addSubstitutionFont(baseFont);

            acroFields.setField("delivery_fee", "12");
            acroFields.setField("shipping_name", "曹阳运输");
            acroFields.setField("shipping_country", "CN");
            acroFields.setField("shipping_mobile", "175897378984");
            acroFields.setField("total_price", "4355.00");
            acroFields.setField("pay_method", "支付宝");
            acroFields.setField("total_discount", "3");
            acroFields.setField("other_discount", "4");
            acroFields.setField("order_platform", "Tmall");
            acroFields.setField("order_product_num", "2");
            acroFields.setField("order_total_price", "40.00");
            acroFields.setField("b2c_order_id", "2000134423432");
            acroFields.setField("ship_group_id", "2020328498");
            acroFields.setField("page_num", i + 1 + "");
            acroFields.setField("shipping_time", "2020-02-13");

            System.out.println(skuDataFormDtoList);
            Iterator<ShoppingListDto.SkuDataFormDto> iterator = skuDataFormDtoList.iterator();
            for (int rowNum = 0; rowNum < ROW_SIZE; ) {
                if (!iterator.hasNext()) break;
                ShoppingListDto.SkuDataFormDto skuDataFormDto = iterator.next();
                rowNum++;
                acroFields.setField("sku_id_" + rowNum, skuDataFormDto.getSkuId());
                acroFields.setField("sku_name_" + rowNum, skuDataFormDto.getSkuName());
                acroFields.setField("sku_price_" + rowNum, skuDataFormDto.getSkuPrice());
                acroFields.setField("sku_num_" + rowNum, skuDataFormDto.getSkuNum());
                acroFields.setField("sku_discount_" + rowNum, skuDataFormDto.getSkuDiscout());
                acroFields.setField("sku_sale_price_" + rowNum, skuDataFormDto.getSkuSalePrice());
                iterator.remove();
            }
            pdfStamper.setFormFlattening(true);
            pdfStamper.close();
            readerList.add(new PdfReader(baos[i].toByteArray()));

        }
        String saveLabelURL = "src/main/resources" + "/" + "shoppingList_" + "1234567890" + "_" + new Date().getTime() +
                ".pdf";
        FileOutputStream fos = new FileOutputStream(saveLabelURL);
        Document doc = new Document(PageSize.A5);
        PdfCopy pdfCopy = new PdfCopy(doc, fos);
        pdfCopy.setPdfVersion(PdfWriter.VERSION_1_5);
        doc.open();

        for (PdfReader pdfReader : readerList) {
            doc.newPage();
            pdfCopy.addDocument(pdfReader);
        }

        pdfCopy.close();
        doc.close();
        fos.close();

    }
}
