package org.xkf.cloudprovidertwo;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.xkf.cloudprovidertwo.domain.PageInfo;
import org.xkf.cloudprovidertwo.mq.User;
import org.xkf.cloudprovidertwo.service.RetryService;
import org.xkf.cloudprovidertwo.utils.excel.read.DemoDataListener;
import org.xkf.cloudprovidertwo.utils.excel.write.DataModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudProviderTwoApplication.class)
public class CloudProviderTwoApplicationTests {

    @Autowired
    private RetryService retryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test2() {



    }





    @Test
    public void simpleRead() throws FileNotFoundException {
        String filePath = "C:\\Users\\13235\\Desktop\\a.xls";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        ExcelReader excelReader = new ExcelReader(fileInputStream, ExcelTypeEnum.XLS, "dd", new DemoDataListener());
        excelReader.read(new Sheet(1, 12));
        IOUtils.closeQuietly(fileInputStream);
        List<Sheet> sheets = excelReader.getSheets();
        for (Sheet sheet : sheets) {
            if (sheet.getSheetNo() == 1) {

            }
        }

        excelReader.finish();

    }

    @Test
    public void poiWrite() throws IOException {
        String filePath = "C:\\Users\\13235\\Desktop\\a33.xls";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheetAt = workbook.getSheetAt(1);
        for (int i = 0; i < 16; i++) {
            int rowNum = sheetAt.getRow(i).getRowNum();
             sheetAt.getRow(rowNum).setHeight((short) 20);
            int physicalNumberOfCells = sheetAt.getRow(rowNum).getPhysicalNumberOfCells();
            for (int i1 = 0; i1 < physicalNumberOfCells; i1++) {

            }
        }
     /*   HSSFRow row = sheetAt.createRow(physicalNumberOfRows);
        HSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("aaa");
		*//*HSSFCell cell1 = row.createCell(1, CellType.STRING);
		cell1.setCellValue("bbbb");*//*
//		CellRangeAddress cellAddresses = new CellRangeAddress(physicalNumberOfRows, physicalNumberOfRows+1, 0, 0);
//		sheetAt.addMergedRegion(cellAddresses);*/
        // workbook.write(new File("C:\\Users\\13235\\Desktop\\a33.xls"));

    }




    @Test
    public void contextLoads() throws Exception {
        int num = 0;
        System.out.println("重试开始:" + num);
        num++;
        retryService.retryMethod();
        System.out.println("重试结束:" + num);

    }


    /**
     * @param title      标题集合 tilte的长度应该与list中的model的属性个数一致
     * @param maps       内容集合
     * @param mergeIndex 合并单元格的列
     */
    public static String createExcel(String[] title, Map<String/*sheet名*/, List<Map<String/*对应title的值*/, String>>>
            maps, int[] mergeIndex) {
        if (title.length == 0) {
            return null;
        }
        /*初始化excel模板*/
        Workbook workbook = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = null;
        int n = 0;
        /*循环sheet页*/
        for (Map.Entry<String, List<Map<String/*对应title的值*/, String>>> entry : maps.entrySet()) {
            /*实例化sheet对象并且设置sheet名称，book对象*/
            try {
                sheet = workbook.createSheet();
                workbook.setSheetName(n, entry.getKey());
                workbook.setSelectedTab(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*初始化head，填值标题行（第一行）*/
            Row row0 = sheet.createRow(0);
            for (int i = 0; i < title.length; i++) {
                /*创建单元格，指定类型*/
                Cell cell_1 = row0.createCell(i, CellType.STRING);
                cell_1.setCellValue(title[i]);
            }
            /*得到当前sheet下的数据集合*/
            List<Map<String/*对应title的值*/, String>> list = entry.getValue();
            /*遍历该数据集合*/
            List<DataModel> poiModels = Lists.newArrayList();
            if (null != workbook) {
                Iterator iterator = list.iterator();
                int index = 1;/*这里1是从excel的第二行开始，第一行已经塞入标题了*/
                while (iterator.hasNext()) {
                    Row row = sheet.createRow(index);
                    /*取得当前这行的map，该map中以key，value的形式存着这一行值*/
                    Map<String, String> map = (Map<String, String>) iterator.next();
                    /*循环列数，给当前行塞值*/
                    for (int i = 0; i < title.length; i++) {
                        String old = "";
                        /*old存的是上一行统一位置的单元的值，第一行是最上一行了，所以从第二行开始记*/
                        if (index > 1) {
                            old = poiModels.get(i) == null ? "" : poiModels.get(i).getContent();
                        }
                        if (index == 1) {
                            /*记录第一行的开始行和开始列*/
                            DataModel poiModel = new DataModel();
                            poiModel.setOldContent(map.get(title[i]));
                            poiModel.setContent(map.get(title[i]));
                            poiModel.setRowIndex(1);
                            poiModel.setColIndex(i);
                            poiModels.add(poiModel);
                            break;
                        }
                        /*处理第一列的情况*/
                        if (0 == i && i == 0 && !poiModels.get(i).getContent().equals(map.get(title[i]))) {
                            /*当前行的当前列与上一行的当前列的内容不一致时，则把当前行以上的合并*/
                            CellRangeAddress cra = new CellRangeAddress(poiModels.get(i).getRowIndex()/*从第二行开始*/,
                                    index - 1/*到第几行*/, poiModels.get(i).getColIndex()/*从某一列开始*/, poiModels.get(i)
                                    .getColIndex()/*到第几列*/);
                            //在sheet里增加合并单元格
                            sheet.addMergedRegion(cra);
                            /*重新记录该列的内容为当前内容，行标记改为当前行标记*/
                            poiModels.get(i).setContent(map.get(title[i]));
                            poiModels.get(i).setRowIndex(index);
                            poiModels.get(i).setColIndex(i);
                        }

                        /*最后一行没有后续的行与之比较，所有当到最后一行时则直接合并对应列的相同内容*/
                        if (0 == i && index == list.size()) {
                            CellRangeAddress cra = new CellRangeAddress(poiModels.get(i).getRowIndex()/*从第二行开始*/,
                                    index/*到第几行*/, poiModels.get(i).getColIndex()/*从某一列开始*/, poiModels.get(i)
                                    .getColIndex()/*到第几列*/);
                            //在sheet里增加合并单元格
                            sheet.addMergedRegion(cra);
                        }
                        Cell cell = row.createCell(i, CellType.STRING);
                        cell.setCellValue(map.get(title[i]));
                        /*在每一个单元格处理完成后，把这个单元格内容设置为old内容*/
                        poiModels.get(i).setOldContent(old);
                    }
                    index++;
                }
            }
            n++;
        }
        /*生成临时文件*/
        FileOutputStream out = null;
        String localPath = null;
        File tempFile = null;
        String fileName = String.valueOf(new Date().getTime() / 1000);
        try {
            tempFile = File.createTempFile(fileName, ".xlsx");
            localPath = tempFile.getAbsolutePath();
            out = new FileOutputStream(localPath);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return localPath;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setUsername("aa");
        User user2 = new User();
        user.setUsername("aa");
        map.put(user.getUsername(), user);
        map.put(user2.getUsername(), user2);
        System.out.println(map.size());

        /*此处标题的数组则对应excel的标题*//*
        String[] title = {"id", "标题", "描述", "负责人", "开始时间"};
        List<Map<String, String>> list = Lists.newArrayList();
        *//*这边是制造一些数据，注意每个list中map的key要和标题数组中的元素一致*//*
        for (int i = 0; i < 1; i++) {
            HashMap<String, String> map = com.google.common.collect.Maps.newHashMap();
            map.put("id", "333");
            map.put("标题", "mmmm");
            map.put("id", "333");
            map.put("标题", "aaaaa");
            map.put("描述", "sssssss");
            map.put("负责人", "vvvvv");
            map.put("开始时间", "2017-02-27 11:20:26");
            list.add(map);
        }
        Map<String*//*此处的key为每个sheet的名称，一个excel中可能有多个sheet页*//*, List<Map<String*//*此处key对应每一列的标题*//*,
                String>>*//*该list为每个sheet页的数据*//*> map = Maps.newHashMap();
        map.put("测试合并数据", list);
        System.out.println(createExcel(title, map, new int[]{0}));*/
    }


    /**
     * 获取单元格的值
     *  
     *
     * @param currentCell
     * @return
     */
    private String getStringCellValue(Cell currentCell) {
        String strCell = "";
        if (currentCell != null) {
            switch (currentCell.getCellType()) {
                case STRING:
                    strCell = currentCell.getStringCellValue();
                    break;
                case NUMERIC:
                    strCell = String.valueOf(currentCell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    strCell = String.valueOf(currentCell.getBooleanCellValue());
                    break;
                case BLANK:
                    strCell = "";
                    break;
                default:
                    strCell = "";
                    break;
            }
            if (strCell.equals("") || strCell == null) {
                return "";
            }
            if (currentCell == null) {
                return "";
            }
        }
        return strCell;
    }




}
