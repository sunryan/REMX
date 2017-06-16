package com.intalgent.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intalgent.addressbook.domain.Procedure;
import com.intalgent.addressbook.domain.Table;
import com.intalgent.addressbook.domain.TableField;
import com.intalgent.addressbook.domain.TableIndex;
import com.intalgent.addressbook.domain.TableTitle;
import com.intalgent.addressbook.domain.Task;
import com.intalgent.sql.DatabaseMetaDateApplication;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Hyperlink;

/**
 * Created by lr on 2017/6/13.
 */
public class TableXlsWrite {

    private String dbName;
    private String outPath;
    private DatabaseMetaDateApplication metaData;

    private List<Table> tableList = new ArrayList<Table>();
    private List<Procedure> functionList = new ArrayList<Procedure>();
    private List<Task> taskList = new ArrayList<Task>();


    public TableXlsWrite(String dbName, String outPath, DatabaseMetaDateApplication metaData){
        this.dbName = dbName;
        this.outPath = outPath;
        this.metaData = metaData;

    }

    public boolean start() throws IOException, SQLException {
        File file = new File(outPath);
        if(file.exists()){
            file.delete();//删除文件
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        createSheet1(workbook);
        createSheet2(workbook);
        createSheet3(workbook);
        createSheet4(workbook);
        // 创建文件流
        OutputStream stream = new FileOutputStream(outPath);
        // 写入数据
        workbook.write(stream);
        // 关闭文件流
        stream.close();
        return true;
    }

    private void createSheet1(HSSFWorkbook workbook) throws SQLException {
        HSSFSheet sheet = workbook.createSheet("字典目录");
        HSSFCellStyle titleStyle = PoiExlUtil.getTitleStyle(workbook); //表头样式
        HSSFCellStyle style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        HSSFCellStyle linkStyle = PoiExlUtil.getLinkStyle(workbook); // 取链接样式
        // 所有表
        tableList = metaData.getAllTableList(null);
        TableTitle tableTitle = new TableTitle(titleStyle, "序号,表名(点击链接到相关文档),表解释", "3000,8000,15000");
        int row = 1;
        row = createTableTitle(sheet, tableTitle, row) + 1;
        for (int i = 0; i < tableList.size(); i++) {
            Table table = tableList.get(i);
            int col = 0;
            PoiExlUtil.addIntCell(sheet, style, row, col++, i + 1);
            //记录位置，添加超链接
            table.setCell( PoiExlUtil.addNormalCell(sheet, linkStyle, row, col++, table.getTableName()));
            PoiExlUtil.addNormalCell(sheet, style, row, col++, table.getComment());
            metaData.getAllPrimaryKeys(null, table); //表添加主键信息
            row++;
        }
        row++;
        //所有函数
        functionList = metaData.getProcedures();
        titleStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        //HSSFColor.LIGHT_YELLOW
        tableTitle = new TableTitle(titleStyle, "序号,方法(点击链接到相关文档),表解释", null);
        row = createTableTitle(sheet, tableTitle, row) + 1;
        for (int i = 0; i < functionList.size(); i++) {
            Procedure function = functionList.get(i);
            int col = 0;
            PoiExlUtil.addIntCell(sheet, style, row, col++, i + 1);
            //记录位置，添加超链接
            function.setCell( PoiExlUtil.addNormalCell(sheet, linkStyle, row, col++, function.getName()));
            PoiExlUtil.addNormalCell(sheet, style, row, col++, function.getComment());
            row++;
        }
        row++;
        //所有事件
        taskList = metaData.getTasks(dbName);
        titleStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        tableTitle = new TableTitle(titleStyle, "序号,事件(点击链接到相关文档),表解释", null);
        row = createTableTitle(sheet, tableTitle, row) + 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int col = 0;
            PoiExlUtil.addIntCell(sheet, style, row, col++, i + 1);
            //记录位置，添加超链接
            task.setCell( PoiExlUtil.addNormalCell(sheet, linkStyle, row, col++, task.getName()));
            PoiExlUtil.addNormalCell(sheet, style, row, col++, task.getComment());
            row++;
        }

        
    }

    private void createSheet2(HSSFWorkbook workbook){
        // 创建sheet对象
        HSSFSheet sheet = workbook.createSheet("表结构");

        HSSFCellStyle style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        style.setWrapText(true);
        TableTitle tableTitle = new TableTitle(PoiExlUtil.getTitleStyle(workbook),
                "字段名,类型,允许空,约束,默认值,字段说明", "5000,5000,3000,3000,5000,10000");
        HSSFCellStyle indexTitleStyle = PoiExlUtil.getTitleStyle(workbook);
        indexTitleStyle .setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        TableTitle indexTitle = new TableTitle(indexTitleStyle, "索引名,栏位,唯一索引,索引方法,描述", null);

        int row = 1;
        for(Table table : tableList){
            //一张数据库表的大标题
            PoiExlUtil.addNormalCell(sheet, null, row, 0, "表名: " + table.getTableName());
            PoiExlUtil.addMergedRegion(sheet,row, row++,0, 5);
            //超链接地址
            Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);
            hyperlink.setAddress("#表结构!A" + row);
            table.getCell().setHyperlink(hyperlink);
            //数据结构表
            row = createTableTitle(sheet, tableTitle, row) + 1;
            row = createTable(sheet, style, row, table) + 1;
            //索引表
            row = createTableTitle(sheet, indexTitle, row) + 1;
            row = createIndexTable(sheet, style, row, table) + 1;
        }

    }

    private void createSheet3(HSSFWorkbook workbook){
        // 创建sheet对象
        HSSFSheet sheet = workbook.createSheet("函数");
        sheet.setColumnWidth(0, Integer.valueOf(50000));
        HSSFCellStyle style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        style.setWrapText(true);
        int row = 1;
        for(Procedure function : functionList){
            //一张数据库表的大标题
            PoiExlUtil.addNormalCell(sheet, null, row++, 0, "函数: " + function.getName());
            //超链接地址
            Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);
            hyperlink.setAddress("#函数!A" + row);
            function.getCell().setHyperlink(hyperlink);
            //数据结构表
            PoiExlUtil.addNormalCell(sheet, style, row++, 0, function.getContent());
            row++;
        }

    }

    private void createSheet4(HSSFWorkbook workbook){
        // 创建sheet对象
        HSSFSheet sheet = workbook.createSheet("事件");
        sheet.setColumnWidth(0, Integer.valueOf(50000));
        HSSFCellStyle style = PoiExlUtil.getNormalStyle(workbook); // 取一般样式
        style.setWrapText(true);
        int row = 1;
        for(Task task : taskList){
            //一张数据库表的大标题
            PoiExlUtil.addNormalCell(sheet, null, row++, 0, "事件: " + task.getName());
            //超链接地址
            Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);
            hyperlink.setAddress("#事件!A" + row);
            task.getCell().setHyperlink(hyperlink);
            //数据结构表
            PoiExlUtil.addNormalCell(sheet, style, row++, 0, task.getContent());
            row++;
        }
    }


    private int createIndexTable(HSSFSheet sheet, HSSFCellStyle style, int row, Table table) {
        //所有索引信息
        List<TableIndex> tableIndexList = metaData.getIndexInfo(null, table);

        for(TableIndex tableIndex : tableIndexList){
            int col = 0;
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableIndex.getName());
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableIndex.getColumnNames());
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableIndex.getUnique() != null && tableIndex.getUnique() ? "是" : "");
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableIndex.getType());
            PoiExlUtil.addNormalCell(sheet, style, row, col++, tableIndex.getComment());
            row++;
        }
        return row;
    }

    public int createTable(HSSFSheet sheet,HSSFCellStyle style, int row, Table table){
        String tableName = table.getTableName();
        List<TableField> list = metaData.getTableColumns(null , tableName);
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
        return row;
    }


    /**
     * 创建表头
     * @param sheet
     * @param tableTitle
     * @param row
     * @return
     */
    private int createTableTitle(HSSFSheet sheet, TableTitle tableTitle, int row){
        if(tableTitle.getTitles() != null){
            String[] title = tableTitle.getTitles().split(",");
            String[] width = tableTitle.getWidths() == null ? null : tableTitle.getWidths().split(",");
            for (int i = 0; i < title.length; i++) {
                if(width != null){
                    sheet.setColumnWidth(i, Integer.valueOf(width[i]));
                }
                PoiExlUtil.addNormalCell(sheet, tableTitle.getStyle(), row, i, title[i]);
            }
        }
        return row;
    }

}
