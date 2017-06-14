package com.intalgent.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Date;

public class PoiExlUtil {

	/**
	 * 获得普通样式
	 * @author kjl
	 * @date 2015-11-16
	 * 
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getNormalStyle(HSSFWorkbook wb) {
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeight((short) 200);
		font.setColor(HSSFColor.BLACK.index);
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		/*style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);*/
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFont(font);// 设置字体
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置为垂直居中
		return style;
	}

	public static HSSFCellStyle getLinkStyle(HSSFWorkbook wb){
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeight((short) 200);
		font.setUnderline((byte) 1);
		font.setColor(HSSFColor.BLUE.index);
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFont(font);// 设置字体
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置为垂直居中
		return style;
	}
	
	/**
	 * 获得标题样式
	 * @author zkh
	 * @date 2016-05-24
	 * 
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeight((short) 200);
		font.setColor(HSSFColor.BLACK.index);
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFont(font);// 设置字体
		return style;
	}

	/**
	 * 普通日期格式样式
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getDateStyle(HSSFWorkbook wb, String formatStr) {
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeight((short) 200);
		font.setColor(HSSFColor.BLACK.index);
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFont(font);// 设置字体
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置为垂直居中
		//日期格式
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat(formatStr));
		return style;
	}
	
	/**
	 * 通用样式
	 * @author lqq
	 * @date 2015-12-03
	 * 
	 * @param ...
	 * @return
	 */
	public static HSSFCellStyle getPurStyle(HSSFWorkbook wb,
											String fontName,
											Short boldWeight,
											Integer fontHeight,
											Short fontColor,
											Short alignment,
											Short verticalAlignment,
											Short fillPattern,
											Short fillForegroundColor,
											Short borderBottom,
											Short borderLeft,
											Short borderRight,
											Short borderTop,
											String dataFormat) {
		// 创建字体样式
		HSSFFont font = wb.createFont();
		if (fontName != null) {
			font.setFontName(fontName);
		}
		if (boldWeight != null) {
			font.setBoldweight(boldWeight);
		}
		if (fontHeight != null) {
			font.setFontHeight((short) fontHeight.intValue());
		}
		if (fontColor != null) {
			font.setColor(fontColor);
		}
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		if (alignment != null) {
			style.setAlignment(alignment);
		}
		if (verticalAlignment != null) {
			style.setVerticalAlignment(verticalAlignment);
		}
		if (fillPattern != null) {
			style.setFillPattern(fillPattern);
		}
		if (fillForegroundColor != null) {
			style.setFillForegroundColor(fillForegroundColor);
		}
		// 设置边框
		if (borderBottom != null) {
			style.setBorderBottom(borderBottom);
		}
		if (borderLeft != null) {
			style.setBorderLeft(borderLeft);
		}
		if (borderRight != null) {
			style.setBorderRight(borderRight);
		}
		if (borderTop != null) {
			style.setBorderTop(borderTop);
		}
		if (dataFormat != null) {
			style.setDataFormat(wb.createDataFormat().getFormat(dataFormat));
		}
		style.setFont(font);// 设置字体
		style.setWrapText(true);
		return style;
	}

	/**
	 * 增加文本单元格
	 * @author kjl
	 * @date 2015-11-16
	 * 
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param content
	 */
	public static HSSFCell addNormalCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, String content) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		if(row == null){
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		if (content != null) {
			cell.setCellValue(content);
		}
		return cell;
	}

	/**
	 * 增加日期单元格
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param content
	 */
	public static void addDateCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, Date content) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		if (content != null) {
			cell.setCellValue(content);
		}

	}


	/**
	 * 增加文本单元格
	 * @author kjl
	 * @date 2015-11-16
	 *
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param content
	 */
	public static void addWrapNormalCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, String content) {
		style.setWrapText(true); //自动换行
		// 创建Excel的sheet的一行
		addNormalCell(sheet, style, rowNum, cellNum, content);
	}
	
	/**
	 * 增加double数据
	 * @author lqq
	 * @date 2015-12-10
	 * 
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param content
	 */
	public static void addDoubleCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, Double content) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		if(row == null){
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		if (content != null) {
			cell.setCellValue(content);
		}
	}
	
	/**
	 * 增加int数据
	 * @author lqq
	 * @date 2015-12-10
	 * 
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param content
	 */
	public static void addIntCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, Integer content) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		if(row == null){
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		if (content != null) {
			cell.setCellValue(content);
		}
	}
	
	/**
	 * 增加公式单元格
	 * @author lqq
	 * @date 2015-12-10
	 * 
	 * @param sheet
	 * @param style
	 * @param rowNum
	 * @param cellNum
	 * @param rule
	 */
	public static void addFormulaCell(HSSFSheet sheet, HSSFCellStyle style, int rowNum, int cellNum, String rule) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		if(row == null){
			row = sheet.createRow(rowNum);
		}
		HSSFCell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		// 创建一个Excel的单元格
		if (style != null) {
			cell.setCellStyle(style);
		}
		if (rule != null) {
			cell.setCellFormula(rule);
		}
	}
	

	/**
	 * 增加行合区域
	 * @author lqq
	 * @date 2015-12-10
	 * 
	 * @param sheet
	 * @param startRow
	 * @param endRow
	 * @param startCol
	 * @param endCol
	 */
	public static void addMergedRegion(HSSFSheet sheet, int startRow, int endRow, int startCol, int endCol) {
		CellRangeAddress region = new CellRangeAddress((short)startRow, (short)endRow, (short)startCol, (short)endCol);
		sheet.addMergedRegion(region);// 到rowTo行columnTo的区域
	}
	
	/**
	 * 设置高度
	 * @author lqq
	 * @date 2015-12-15
	 * 
	 * @param sheet
	 * @param rowNum
	 * @param height
	 */
	public static void setHeight(HSSFSheet sheet, int rowNum, int height) {
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.getRow(rowNum);
		row.setHeight((short) height);// 设定行的高度
	}

	/**
	 * 删除sheet
	 * @author lqq
	 * @date 2015-12-16
	 * 
	 * @param sheetArr
	 */
	public static void removeSheet(HSSFWorkbook workbook, Integer[] sheetArr) {
		// 创建Excel的sheet的一行
		for (int i=0; i<sheetArr.length; i++) {
			workbook.removeSheetAt(sheetArr[i]);
		}
	}
	
	/**
	 * 删除sheet
	 * @author lqq
	 * @date 2015-12-16
	 * 
	 * @param sheetArr
	 */
	public static void moveRow(HSSFSheet sheet, int startRow, int endRow, int rowNum) {
		if (rowNum != 0) {
			sheet.shiftRows(startRow, endRow, rowNum);
		}
	}
	
	/**
	 * 新建自定义颜色-废弃
	 * @author lqq
	 * @date 2015-12-24
	 * 报错：RuntimeExcetion:Could not find free color index 未知原因
	 * @param RGB
	 */
	public static short setNewColor(HSSFWorkbook workbook, int r, int g, int b) {
	    HSSFPalette customPalette = workbook.getCustomPalette();
	    HSSFColor newColor = customPalette.addColor((byte) 22, (byte) 11, (byte) 62);
	    return newColor.getIndex();
	}
	
	/**
	 * 自定义颜色
	 * @author lqq
	 * @date 2015-12-24
	 * 
	 * @param RGB
	 */
	public static HSSFColor createColor(HSSFWorkbook workbook, byte r, byte g, byte b){
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		hssfColor= palette.findColor(r, g, b);
		if (hssfColor == null ){
		    palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
		    hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
		}
		return hssfColor;
	}
	
	/**
	 * 设置边框颜色
	 * @author lqq
	 * @date 2015-12-24
	 * 
	 * @param style
	 * @param color
	 */
	public static void setBorderColor(HSSFCellStyle style, HSSFColor color){
		style.setTopBorderColor(color.getIndex());
		style.setBottomBorderColor(color.getIndex());
		style.setLeftBorderColor(color.getIndex());
		style.setRightBorderColor(color.getIndex());
	}

}
