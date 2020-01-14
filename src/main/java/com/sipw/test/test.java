package com.sipw.test;


import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    //读取单个单元格
    @Test
    public void testRead() throws Exception {
      /*  //Excel文件
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:test.xls")));

        //Excel工作表
        HSSFSheet sheet = wb.getSheetAt(0);

        //表头那一行
        HSSFRow titleRow = sheet.getRow(0);

        //表头那个单元格
        HSSFCell titleCell = titleRow.getCell(0);

        String title = titleCell.getStringCellValue();

        System.out.println("标题是："+title);*/
//       String a = "6212141400000000080";
//        System.out.println(Integer.parseInt(a.trim()))
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String transTime = "12/26/2019 15:54:04";
        String time = "Wed Dec 25 16:39:04 CST 2019";
        Date d = new Date();
        //String b = "Wed Dec 25 16:39:04 CST 2019";
        //Date d = new Date(b);
       // Date dateformat = HSSFDateUtil.getJavaDate(Double.parseDouble(b));
        String a = sdf.format(d);
        Date e = sdf.parse(transTime);

       // System.out.println(dateformat);
        System.out.println(d);
        System.out.println(a);
        System.out.println(e);

    }

    public static Date StringToDate(String datetime){
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdFormat.parse(datetime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
}
