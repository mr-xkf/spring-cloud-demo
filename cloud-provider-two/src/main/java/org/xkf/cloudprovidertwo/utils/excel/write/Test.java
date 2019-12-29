/**
 * FileName: Test
 * Author:   13235
 * Date:     2019/11/1 1:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.excel.write;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/1
 * @since 1.0.0
 */
public class Test {
    /**
     * @param headers      标题集合 tilte的长度应该与list中的model的属性个数一致
     * @param dataset      内容集合
     * @param mergeColumns 合并单元格的列
     */
    public static void createExcel(String[] headers, List<Map<String, String>> dataset, String[] mergeColumns) throws
            IOException {
        if (headers.length == 0) {
            return;
        }
        /*初始化excel模板*/
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;
        try {
            sheet = workbook.createSheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Row row0 = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell_1 = row0.createCell(i, CellType.STRING);
            cell_1.setCellValue(headers[i]);
        }
        List<PoiModel> poiModels = new ArrayList<PoiModel>();
        Iterator<Map<String, String>> iterator = dataset.iterator();
        int index = 1;    //这里1是从excel的第二行开始，第一行已经塞入标题了
        while (iterator.hasNext()) {
            Row row = sheet.createRow(index);
            // 取得当前这行的map，该map中以key，value的形式存着这一行值
            Map<String, String> map = iterator.next();
            // 循环列数，给当前行塞值
            for (int i = 0; i < headers.length; i++) {
                String old = "";
                // old存的是上一行统一位置的单元的值，第一行是最上一行了，所以从第二行开始记
                if (index > 1) {
                    old = poiModels.get(i) == null ? "" : poiModels.get(i).getContent();
                }
                String value = map.get(headers[i]);
                CellRangeAddress cra = null;
                // 循环需要合并的列
                for (int j = 0; j < mergeColumns.length; j++) {
                    PoiModel poiModel = null;
                    if (index == 1) {
                        poiModel = new PoiModel();
                        poiModel.setOldContent(value);
                        poiModel.setContent(value);
                        poiModel.setRowIndex(1);
                        poiModel.setCellIndex(i);
                        poiModels.add(poiModel);
                        old = value;
                        break;
                    }
                    poiModel = poiModels.get(i);
                    int rowStartIndex = poiModel.getRowIndex();
                    int rowEndIndex = index - 1;
                    int cellIndex = poiModel.getCellIndex();
                    String content = poiModel.getContent();
                    String preOldContent = poiModels.get(0).getOldContent();
                    String preValue = map.get(headers[0]);
                    Boolean isHeaderEquals = mergeColumns[0].equals(headers[i]);
                    if (i == 0 && isHeaderEquals && !content.equals(value)) {
                        if (rowStartIndex != rowEndIndex) {
                            cra = new CellRangeAddress(rowStartIndex, rowEndIndex, cellIndex, cellIndex);
                            sheet.addMergedRegion(cra);
                        }
                        // 重新记录该列的内容为当前内容，行标记改为当前行标记
                        poiModel.setContent(value);
                        poiModel.setRowIndex(index);
                        poiModel.setCellIndex(i);
                    } /*else if (i > 0 && isHeaderEquals) {
                        if (!content.equals(value) || (content.equals(value) &&
                                !preOldContent.equals(preValue))) {
                            if (rowStartIndex != rowEndIndex) {
                                cra = new CellRangeAddress(rowStartIndex, rowEndIndex, cellIndex, cellIndex);
                                sheet.addMergedRegion(cra);
                            }
                            poiModels.get(i).setContent(value);
                            poiModels.get(i).setRowIndex(index);
                            poiModels.get(i).setCellIndex(i);
                        }
                    }*/
                    if (isHeaderEquals && index == dataset.size()) {
                        if (i == 0) {
                            if (content.equals(value)) {
                                cra = new CellRangeAddress(rowStartIndex, index, cellIndex, cellIndex);
                                sheet.addMergedRegion(cra);
                            }
                        } else if (i > 0) {
                            if (content.equals(value) && preOldContent.equals(preValue)) {
                                cra = new CellRangeAddress(rowStartIndex, index, cellIndex, cellIndex);
                                sheet.addMergedRegion(cra);
                            }
                        }
                    }
                }
                Cell cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(value);
                // 在每一个单元格处理完成后，把这个单元格内容设置为old内容
                poiModels.get(i).setOldContent(old);
            }
            index++;
        }

        workbook.write(new FileOutputStream(new File("C:\\Users\\13235\\Desktop\\a99.xlsx")));

    }

    public static void main(String[] args) throws IOException {
        String[] headers = {"单位名称", "IP", "IP分类", "危险程度"};
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("单位名称", "test");
        map.put("IP", "10.130.138.96");
        map.put("IP分类", "主机IP");
        map.put("危险程度", "高危");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("单位名称", "test");
        map.put("IP", "10.130.138.96");
        map.put("IP分类", "主机IP");
        map.put("危险程度", "高危");
        list.add(map);
        list.add(map);
        map = new HashMap<String, String>();
        map.put("单位名称", "test111");
        map.put("IP", "10.130.138.96");
        map.put("IP分类", "主机IP");
        map.put("危险程度", "高危");
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        String[] regions = new String[]{"单位名称", "IP"};
        createExcel(headers, list, regions);
    }

}