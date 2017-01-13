package com.coderman.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by ${fanchunshuai} on 2016-12-22.
 *
 * @version: V1.0
 * @Desc:
 * @Copyright (c) 2016 58到家-版权所有
 */
public class ITextOne {

    public static final String DEST = "E:/test/html_table.pdf" ;
    public static final String HTML = "E:/test/news.html";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File( DEST);
        file.getParentFile().mkdirs();
        new ITextOne ().createPdf(DEST );
    }

    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter. getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4  支持中文
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), Charset.forName("UTF-8"));
        // step 5
        document.close();
    }

}
