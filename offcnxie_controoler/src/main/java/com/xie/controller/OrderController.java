package com.xie.controller;


import com.xie.seriver.IMemberService;
import com.xie.seriver.IOrderService;
import com.xie.utils.util.MessageConstant;
import com.xie.utils.util.MyFileUtils;
import com.xie.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private IOrderService iOrderService;
    @Reference
    private IMemberService iMemberService;

    @RequestMapping("/username")
    public Result order(@RequestBody Map<String, Object> map) {
        System.out.println(map + "submit+++++++++++++++++++++++++++++++++++++++++++++++++++");

        try {
            Result submit = iOrderService.submit(map);
            System.out.println(submit + "submit+++++++++++++++++++++++++++++++++++++++++++++++++++");
            return new Result(true, MessageConstant.ORDER_FULL);
//            return  submit;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_FULL);
        }
    }

    @RequestMapping("/getBusiness")
    public Result getBusiness() {
        try {
            Map<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("todayNewMember", iMemberService.todayNewMember());
            stringObjectHashMap.put("totalMember", iMemberService.totalMember());
            stringObjectHashMap.put("thisWeekNewMember", iMemberService.thisWeekNewMember());
            stringObjectHashMap.put("thisMonthNewMember", iMemberService.thisMonthNewMember());

            stringObjectHashMap.put("todayOrderNumber", iOrderService.todayOrderNumber());
            stringObjectHashMap.put("todayVisitsNumber", iOrderService.todayVisitsNumber());
            stringObjectHashMap.put("thisWeekOrderNumber", iOrderService.thisWeekOrderNumber());
            stringObjectHashMap.put("thisWeekVisitsNumber", iOrderService.thisWeekVisitsNumber());
            stringObjectHashMap.put("thisMonthOrderNumber", iOrderService.thisMonthOrderNumber());
            stringObjectHashMap.put("thisMonthVisitsNumber", iOrderService.thisMonthVisitsNumber());
            stringObjectHashMap.put("hotSetmeal", iOrderService.hotSetmeal());


            for (String s : stringObjectHashMap.keySet()) {
                System.out.println(s + "++++++++++++++++++++++++++++++" + stringObjectHashMap.get(s));
            }
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, stringObjectHashMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);

        }

    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() {
        try {
            Map<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("todayNewMember", iMemberService.todayNewMember());
            stringObjectHashMap.put("totalMember", iMemberService.totalMember());
            stringObjectHashMap.put("thisWeekNewMember", iMemberService.thisWeekNewMember());
            stringObjectHashMap.put("thisMonthNewMember", iMemberService.thisMonthNewMember());

            stringObjectHashMap.put("todayOrderNumber", iOrderService.todayOrderNumber());
            stringObjectHashMap.put("todayVisitsNumber", iOrderService.todayVisitsNumber());
            stringObjectHashMap.put("thisWeekOrderNumber", iOrderService.thisWeekOrderNumber());
            stringObjectHashMap.put("thisWeekVisitsNumber", iOrderService.thisWeekVisitsNumber());
            stringObjectHashMap.put("thisMonthOrderNumber", iOrderService.thisMonthOrderNumber());
            stringObjectHashMap.put("thisMonthVisitsNumber", iOrderService.thisMonthVisitsNumber());
            stringObjectHashMap.put("hotSetmeal", iOrderService.hotSetmeal());
            for (String s : stringObjectHashMap.keySet()) {
                System.out.println(s + "++++++++++++++++++++++++++++++" + stringObjectHashMap.get(s));
            }
            File fiel = mapToExcel(stringObjectHashMap);
            return MyFileUtils.download(fiel.getName(), "E:\\LdeaDom1\\xiazai");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File mapToExcel(Map<String, Object> map) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("你好");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));
        sheet.createRow(0).createCell(0).setCellValue("会员统计");
        sheet.createRow(3).createCell(0).setCellValue("谢海卿");
        sheet.createRow(7).createCell(0).setCellValue("王巧林");

        XSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue("新增会员");
        row.createCell(1).setCellValue(Double.parseDouble(map.get("todayNewMember").toString()));
        row.createCell(2).setCellValue("会员人数");
        row.createCell(3).setCellValue(Double.parseDouble(map.get("totalMember").toString()));

        XSSFRow row1 = sheet.createRow(2);
        row1.createCell(0).setCellValue("本周新增会员");
        row1.createCell(1).setCellValue(Double.parseDouble(map.get("thisWeekNewMember").toString()));
        row1.createCell(2).setCellValue("本月新增会员人数");
        row1.createCell(3).setCellValue(Double.parseDouble(map.get("thisMonthNewMember").toString()));

        XSSFRow row2 = sheet.createRow(4);
        row2.createCell(0).setCellValue("今日预约");
        row2.createCell(1).setCellValue(Double.parseDouble(map.get("todayOrderNumber").toString()));
        row2.createCell(2).setCellValue("今日到诊");
        row2.createCell(3).setCellValue(Double.parseDouble(map.get("todayVisitsNumber").toString()));

        XSSFRow row3 = sheet.createRow(5);
        row3.createCell(0).setCellValue("本月预约数");
        row3.createCell(1).setCellValue(Double.parseDouble(map.get("thisMonthOrderNumber").toString()));
        row3.createCell(2).setCellValue("本月到诊");
        row3.createCell(3).setCellValue(Double.parseDouble(map.get("thisMonthVisitsNumber").toString()));

        XSSFRow row4 = sheet.createRow(6);
        row4.createCell(0).setCellValue("本周预约数");
        row4.createCell(1).setCellValue(Double.parseDouble(map.get("thisWeekOrderNumber").toString()));
        row4.createCell(2).setCellValue("本周到诊");
        row4.createCell(3).setCellValue(Double.parseDouble(map.get("thisWeekVisitsNumber").toString()));


        XSSFRow row5 = sheet.createRow(8);
        row5.createCell(0).setCellValue("套餐名称");
        row5.createCell(1).setCellValue("预数数");
        row5.createCell(2).setCellValue("占比");
        row5.createCell(3).setCellValue("备注");
        File file =new File("E:\\LdeaDom1\\xiazai\\dome1.xlsx");
        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;


    }


}

