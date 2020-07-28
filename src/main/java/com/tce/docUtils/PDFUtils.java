package com.tce.docUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PDFUtils {
    /**
     * 截取pdfFile的第from页至第end页，组成一个新的文件名
     * @param respdfFile  需要分割的PDF
     * @param savepath  新PDF
     * @param from  起始页
     * @param end  结束页
     */
    public static void splitPDFFile(String respdfFile,
                                    String savepath, int from, int end) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(respdfFile);
            int n = reader.getNumberOfPages();
            if(end==0){
                end = n;
            }
            ArrayList<String> savepaths = new ArrayList<String>();
            String staticpath = respdfFile.substring(0, respdfFile.lastIndexOf("\\")+1);
            //String savepath = staticpath+ newFile;
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for(int j=from; j<=end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
            //return copy;

        } catch (IOException e) {
            e.printStackTrace();
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        splitPDFFile("C:\\Users\\10352\\IdeaProjects\\hw\\src\\main\\resources\\5eDnD配图怪物图鉴MMv1.3.pdf",
                "C:\\Users\\10352\\IdeaProjects\\hw\\src\\main\\resources\\5eDnD配图怪物图鉴截取.pdf",1,2);
    }
}
