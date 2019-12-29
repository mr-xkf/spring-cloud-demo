/**
 * FileName: ExcelUtils
 * Author:   13235
 * Date:     2019/10/2 3:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/2
 * @since 1.0.0
 */
public class ExcelUtils {

    public static final int DEFAULT_WINDOW_SIZE = 100;
    public static final int SHEET_MAX_ROW = 100000;

    private List list;

    private List<ExcelHeaderInfo> headerInfoList;

    private Map<String, ExcelFormat> formatMap;
    private Workbook cellStyle;


    public ExcelUtils(List list, List<ExcelHeaderInfo> headerInfoList) {
        this.list = list;
        this.headerInfoList = headerInfoList;
    }

    public ExcelUtils(List list, List<ExcelHeaderInfo> headerInfoList, Map<String, ExcelFormat> formatMap) {
        this.list = list;
        this.headerInfoList = headerInfoList;
        this.formatMap = formatMap;
    }

    public Workbook getWorkbook() {
        Workbook workbook = new SXSSFWorkbook(DEFAULT_WINDOW_SIZE);
        String[][] datas = transformData();
        Field[] fields = this.list.get(0).getClass().getDeclaredFields();
        int length = datas.length;
        CellStyle style = setCellStyle(workbook);
        int pageRowNum = 0;
        Sheet sheet = null;
        for (int i = 0; i < length; i++) {
             //如果是每个sheet的首行
            if (i % SHEET_MAX_ROW == 0) {
                 //创建新的sheet
                sheet = createSheet(workbook, i);
                pageRowNum=0;//行号重置为0
                for(int j=0;j<getHeaderRowNum(headerInfoList); j++){
                    sheet.createRow(pageRowNum++);
                }
                createHeader(sheet, style);
            }
            //创建内容
            Row row = sheet.createRow(pageRowNum++);
            createContent(row, style, datas, i, fields);
        }
        return workbook;

    }

    private void createContent(Row row, CellStyle style, String[][] datas, int i, Field[] fields) {


    }

    private void createHeader(Sheet sheet, CellStyle style) {


    }

    private int getHeaderRowNum(List<ExcelHeaderInfo> excelHeaderInfos) {
        int rowNum=0;
        for (ExcelHeaderInfo excelHeaderInfo : excelHeaderInfos) {
            int lastRow = excelHeaderInfo.getLastRow();
            if (lastRow > rowNum) {
                rowNum = lastRow;
            }
        }
        return rowNum + 1;
    }


    private CellStyle setCellStyle(Workbook workbook) {

        return null;
    }

    private Sheet createSheet(Workbook workbook, int i) {
        return null;
    }

    private String[][] transformData() {


        return new String[0][];
    }


}
