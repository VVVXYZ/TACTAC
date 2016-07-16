package com.trio.breakFast.sys.views;

import com.google.common.base.Strings;
import com.trio.breakFast.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author loser
 * @ClassName ExcelView
 * @Description
 * @Date 2016/06/02 16:59
 */

public class ExcelView extends AbstractExcelView {
    static public final String FILENAME_KEY = "filename";
    static public final String COLUMN_NAME_KEY = "columnName";
    static public final String COLUMN_DATA_KEY= "columnData";
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = (String)model.get(FILENAME_KEY);
        filename = Strings.nullToEmpty(filename);
        filename += Calendar.getInstance().getTimeInMillis()+".xls";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename, "UTF-8"));

        ExcelUtil.createExcel(workbook, (List<String>)model.get(COLUMN_NAME_KEY), (List<List<String>>)model.get(COLUMN_DATA_KEY));
    }
}
