package com.trio.vmalogo.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author loser
 * @ClassName ExcelUtil
 * @Description
 * @Date 2016/06/02 17:02
 */

public class ExcelUtil {

    static public void createExcel(HSSFWorkbook workbook,List<String> columnNames,  List<List<String>> columnData){
        int row = 0;
        HSSFCellStyle cellStyle = getExcelStyle(workbook);
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow header = sheet.createRow(row++);

        for(int i=0; i<columnNames.size(); i++){
            //自适应宽度
            sheet.autoSizeColumn(i);
            header.createCell(i).setCellValue(columnNames.get(i));
            header.getCell(i).setCellStyle(cellStyle);
        }

        for(int i=0; i<columnData.size(); i++){
            HSSFRow column = sheet.createRow(row++);
            for(int j=0;j<columnData.get(i).size();j++){
                column.createCell(j).setCellValue(columnData.get(i).get(j));
                column.getCell(j).setCellStyle(cellStyle);
            }
        }
    }

    static public HSSFCellStyle getExcelStyle(HSSFWorkbook workbook){
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setWrapText(true);//设置自动换行
        return cellStyle;
    }
}
