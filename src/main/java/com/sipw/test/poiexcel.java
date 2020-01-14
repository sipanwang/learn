package com.sipw.test;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class poiexcel {
    public static void main(String[] args) throws Exception{

        //读取excel：
        InputStream is = new FileInputStream("C:\\Users\\Bangsun\\Desktop\\1.xlsx");
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        //读取sheet，读取sheet里面第一行第一列内容
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        System.out.println(row);
        HSSFCell cell = row.getCell((short) 0);
        String  s = cell.getStringCellValue();

        System.out.println(s);

        //...内容组合自己玩~

        //比如上面组合好了一个String  S2

        //写入一个excel  比如 test3.xls
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Bangsun\\Desktop\\2.xlsx");

        HSSFWorkbook wb2 = new HSSFWorkbook();
        HSSFSheet hsSheet = wb2.createSheet("TestReport");
        HSSFRow hsRow1 = hsSheet.createRow(0);//建行
        hsRow1.setHeight((short) 500);
        HSSFCell hsCell11 = hsRow1.createCell((short) 0);
        //此方法被移除了
        //hsCell11.setEncoding(HSSFCell.ENCODING_UTF_16);
        hsCell11.setCellValue(s+"TESTVALUE");
        wb2.write(fos);
        fos.close();
    }
}
