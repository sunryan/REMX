package com.intalgent.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intalgent.addressbook.domain.Table;
import com.intalgent.addressbook.domain.TableField;
import com.intalgent.sql.DatabaseMetaDateApplication;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Hyperlink;

/**
 * Created by lr on 2017/6/13.
 */
public class TableXlsWrite {

    private String outPath;
    private DatabaseMetaDateApplication metaData;

    private List<Table> list = new ArrayList<Table>();


    public TableXlsWrite(String outPath, DatabaseMetaDateApplication metaData){
        this.outPath = outPath;
        this.metaData = metaData;

    }

    public boolean start() throws IOException {
        File file = new File(outPath);
        if(file.exists()){
            file.delete();//删除文件
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        createSheet1(workbook);
        createSheet2(workbook);
        // 创建文件流
        OutputStream stream = new FileOutputStream(outPath);
        // 写入数据
        workbook.write(stream);
        // 关闭文件流
        stream.close();
        return true;
    }

    private void createSheet1(HSSFWorkbook workbook){
        // 获取所有表
        list = metaData.getAllTableList(null);
        HSSFSheet sheet = workbook.createSheet("字典目录");
        //标题
        HSSFCellStyle style = PoiExlUtil.getTitleStyle(workbook);
        StringBuffer titles = new StringBuffer("序号,表名(点击链接到相关文档),表解释");
        StringBuffer widths = new StringBuffer("3000,8000,15000");
        String[] title = titles.toString().split(",");
        String[] width = widths.toString().split(",");
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, Integer.valueOf(width[i]));
            PoiExlUtil.addNormalCell(sheet, style, 0, i, title[i]);
        }

        // 内容
        style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        HSSFCellStyle linkStyle = PoiExlUtil.getLinkStyle(workbook); // 取链接样式
        int row = 1;
        for(Table table : list){
            int col = 0;
            PoiExlUtil.addIntCell(sheet, style, row, col++, row);
            //记录位置，添加超链接
            table.setCell( PoiExlUtil.addNormalCell(sheet, linkStyle, row, col++, table.getTableName()));
            PoiExlUtil.addNormalCell(sheet, style, row, col++, table.getComment());
            metaData.getAllPrimaryKeys(null, table); //表添加主键信息
            row++;
        }
    }

    private void createSheet2(HSSFWorkbook workbook){
        // 创建sheet对象
        HSSFSheet sheet = workbook.createSheet("表结构");

        HSSFCellStyle titleStyle = PoiExlUtil.getTitleStyle(workbook);
        HSSFCellStyle style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        StringBuffer titles = new StringBuffer("字段名,类型,允许空,约束,默认值,字段说明");
        StringBuffer widths = new StringBuffer("5000,5000,3000,3000,5000,10000");
        String[] title = titles.toString().split(",");
        String[] width = widths.toString().split(",");
        int row = 1;
        for(Table table : list){
            PoiExlUtil.addNormalCell(sheet, null, row, 0, "表名: " + table.getTableName());
            PoiExlUtil.addMergedRegion(sheet,row, row++,0, 5);
            //超链接地址
            Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);
            hyperlink.setAddress("#表结构!A" + row);
            table.getCell().setHyperlink(hyperlink);
            createTableTitle(sheet, titleStyle, title, width, row);
            row = createTable(sheet, style, row, table) + 1;
        }

    }

    public int createTable(HSSFSheet sheet,HSSFCellStyle style, int row, Table table){
        String tableName = table.getTableName();
        List<TableField> list = metaData.getTableColumns(null , tableName);
        row++;
        for(TableField tableField : list){
            int col = 0;
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableField.getColumnName());
            String type = tableField.getDataTypeName() + "(" + tableField.getColumnSize();
            if(tableField.getDecimalDigits() > 0){
                type += ", " + tableField.getDecimalDigits();
            }
            type += ")";
            PoiExlUtil.addNormalCell(sheet, style, row, col++, type);
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableField.getNullAble() == 1 ? "允许" : "");
            PoiExlUtil.addNormalCell(sheet, style, row, col++, table.getPrimaryList() != null && table.getPrimaryList().contains(tableField.getColumnName()) ? "主键" : "");
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableField.getColumnDef());
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableField.getRemarks());
            row++;
        }

        //所有索引信息
        metaData.getIndexInfo(null, table);
        Map<String, List<String>> keyMap = table.getKeyMap();
        if(keyMap != null){

            for(Map.Entry<String, List<String>> entry : keyMap.entrySet()){
                String keyName = entry.getKey();
                List<String> columuNameList = entry.getValue();
                StringBuilder sb = new StringBuilder("(");
                for(int i = 0; i < columuNameList.size(); i++){
                    sb.append(columuNameList.get(i));
                    if(i < columuNameList.size() - 1){
                        sb.append(",");
                    }else{
                        sb.append(")");
                    }
                }
                PoiExlUtil.addNormalCell(sheet, null, row, 0, "KEY " + keyName +  sb.toString());
                PoiExlUtil.addMergedRegion(sheet,row, row++,0, 5);
            }
        }

        return row;
    }


    private void createTableTitle(HSSFSheet sheet, HSSFCellStyle style, String[] title, String[] width, int row){
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, Integer.valueOf(width[i]));
            PoiExlUtil.addNormalCell(sheet, style, row, i, title[i]);
        }
        row++;
    }

}
