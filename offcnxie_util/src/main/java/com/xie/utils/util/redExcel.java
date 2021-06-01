package com.xie.utils.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class redExcel {

    @Test
    public void redExcel() throws IOException {
        //    工作表
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:/中共课讲/123.xlsx"));
//通过索引获取工作表中的第一个sheet页索引是0
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
//    通过sheet页获取获取页面的行
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
//        获取sheet页上的制定的索引的行
            XSSFRow row = sheet.getRow(i + 1);
//        获取行中指定的下标的单元格，然后获取单元格中的数据
            System.out.println(row.getCell(0).getNumericCellValue());
        }

    }

//读取xlsx中的数据 数据为getNumericCellValue 字符串为getStringCellValue
    @Test
    public void usernaem() throws Exception {
        //工作表
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\中共课讲\\123.xlsx"));
        //通过索引获取工作表中的第一个sheet页  索引是0
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
        //通过sheet页获取页中的行
        int lastRowNum = sheetAt.getLastRowNum();
        System.out.println(lastRowNum+"------");
//        String cellValue = sheetAt.getRow(0).getCell(6).getStringCellValue();
//        int i1 = Integer.parseInt(cellValue);
        for (int i = 0; i < 1; i++) {
            //获取sheet页上的制定索引的行
            XSSFRow row = sheetAt.getRow(i );
            System.out.print(row.getCell(0).getStringCellValue()+"    ");
            System.out.print(row.getCell(1).getStringCellValue()+"    ");
            System.out.print(row.getCell(2).getStringCellValue()+"    ");
            System.out.print(row.getCell(3).getStringCellValue()+"    ");
        }
        for (int i = 0; i < lastRowNum; i++) {
            //获取sheet页上的制定索引的行
            XSSFRow row = sheetAt.getRow(i+1 );
//            防止无法从数字单元格中获取文本值
            System.out.println("    ");
            System.out.print(row.getCell(0).getNumericCellValue()+"    ");
            System.out.print(row.getCell(1).getNumericCellValue()+"    ");
            System.out.print(row.getCell(2).getNumericCellValue()+"    ");
            System.out.print(row.getCell(3).getNumericCellValue()+"    ");


            //获取行中指定下标的单元格，然后获取单元格中的数据
//            System.out.println(row.getCell(0).getStringCellValue());
        }
        xssfWorkbook.close();
    }

    //读取数据库中的代码到xlsx中
    public static void main(String[] args) throws IOException {
        //新建工作簿     这个工作簿是在内存中创建的
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workbook.createSheet("学生信息");
        //创建一个行
        XSSFRow row = sheet.createRow(0);
        //在这个行中创建两个单元格
        row.createCell(0).setCellValue("name");
        row.createCell(1).setCellValue("age");
        //循环创建多行
        for(int i=1;i<=10;i++){
            XSSFRow row2 = sheet.createRow(i);
            //在这个行中创建两个单元格
            row2.createCell(0).setCellValue("name"+1);
            row2.createCell(1).setCellValue(i);
        }
        //创建一个文件  名字可以自定义，但是一定要注意后缀不能写错
        File file=new File("D:\\中共课讲\\123.xlsx");
        //把在内存中创建的工作簿的内容写入到file文件
        workbook.write(new FileOutputStream(file));
        workbook.close();
    }


}
