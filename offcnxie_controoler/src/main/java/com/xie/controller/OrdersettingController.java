package com.xie.controller;


import com.xie.pojo.Caldate;
import com.xie.pojo.Ordersetting;
import com.xie.seriver.IOrdersettingService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.MyFileUtils;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {
    @Value("${fileUploadPath}")
    private String fileUploadPath;
    @Reference
    private IOrdersettingService iOrdersettingService;

    @RequestMapping("/uploadTempleate")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile) {
        try {
            File upload = MyFileUtils.upload(multipartFile, fileUploadPath);
            if (upload != null) {
//               提取文件
                List<Ordersetting> ordersettingsList = excelToList(upload);
                ordersettingsList.forEach(System.out::println);
//                把list集合的数据添加到数据库中
                iOrdersettingService.saveList(ordersettingsList);
                return new Result(true, MessageConstant.UPLOAD_SUCCESS);
            }
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);

        }
    }

    //把excel读取成一个list集合
    private List<Ordersetting> excelToList(File upload) throws IOException, InvalidFormatException {
        System.out.println(upload+"+++++++++++++++upload");
        XSSFWorkbook workbook = new XSSFWorkbook(upload);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        List<Ordersetting> list = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            Date dateCellValue = row.getCell(0).getDateCellValue();
            System.out.println(dateCellValue+"+++++++++++++++++++++++++++datecellvalue");
            int numericCellValue = (int) row.getCell(1).getNumericCellValue();
            System.out.println(numericCellValue+"***********************************numbericcellvalue");
            Ordersetting ordersetting = new Ordersetting(0, dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), numericCellValue, 0);
            System.out.println(ordersetting+"____________________ordersetting___");
            list.add(ordersetting);
        }
        return list;
    }
    @RequestMapping("/listCaldate")
    public Result list(String date){
        System.out.println(date+"-------------");
        try {
            List<Caldate> list = iOrdersettingService.listOrdersetting(date);
            list.forEach(System.out::println);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);

        }

    }
    @RequestMapping("/update")
    public Result list(String date,Integer number){
        System.out.println(date+"+++++++++++++"+number);
        try {
            iOrdersettingService.update1(date,number);
            return new Result(true,MessageConstant.ORDER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);

        }
    }
}

