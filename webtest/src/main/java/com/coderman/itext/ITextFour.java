package com.coderman.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by ${fanchunshuai} on 2016-12-22.
 *
 * @version: V1.0
 * @Desc:
 * @Copyright (c) 2016 58到家-版权所有
 */
public class ITextFour {
    public static void main(String[] args) {
        try {
            Document document = new Document(PageSize.A4.rotate());
            File file = new File("E:/test/pdftest.pdf");
            if (file.exists()) {
                file.deleteOnExit();
                file = new File("E:/test/pdftest.pdf");
                if (!file.exists()) {
                    file.createNewFile();
                }
            } else {
                file.createNewFile();
            }

            PdfWriter.getInstance(document, new FileOutputStream(file));

            //设置字体  微软雅黑字体
            BaseFont bfChinese = BaseFont.createFont("C:/Windows/fonts/msyhbd.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);

            //字体加粗
            com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);

            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);

            //字体正常
            com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);

            //字体颜色 绿色
            com.itextpdf.text.Font FontChinese11Color = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL, BaseColor.GREEN);


            document.open();
            //table1
            PdfPTable table1 = new PdfPTable(3);
            PdfPCell cell11 = new PdfPCell(new Paragraph("费用报销", FontChinese24));
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setBorder(0);

            String imagePath = "E:/test/logo_new_bg.png";
            Image image1 = Image.getInstance(imagePath);

            Image image2 = Image.getInstance(imagePath);
            //设置每列宽度比例
            int width11[] = {35, 40, 25};
            table1.setWidths(width11);

            table1.getDefaultCell().setBorder(0);
            table1.addCell(image1);
            table1.addCell(image2);
            table1.addCell(cell11);

            document.add(table1);


            Chunk chunk = new Chunk("下划线测试.", FontChinese11Normal);
            chunk.setUnderline(0.2f, -2f);               //设置下划线
            chunk.setBackground(BaseColor.BLUE);        //设置字体背景颜色


            Paragraph p1 = new Paragraph(new Chunk("这是中文2.", FontChinese11Normal));
            Paragraph p2 = new Paragraph(new Chunk("这是中国文2.", FontChinese11Bold));
            Paragraph p3 = new Paragraph("这是中文三.", FontChinese11Normal);

            Paragraph p4 = new Paragraph(chunk);

            Paragraph p5 = new Paragraph(new Chunk("这是带有字体颜色的设置", FontChinese11Color));


            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.add(p4);
            document.add(p5);
            document.close();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
