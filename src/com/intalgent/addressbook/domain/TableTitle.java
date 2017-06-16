package com.intalgent.addressbook.domain;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * 表头
 * Created by lr on 2017/6/15.
 */
public class TableTitle {

    private HSSFCellStyle style;

    private String titles;

    private String widths;

    public TableTitle(HSSFCellStyle style, String titles, String widths) {
        this.style = style;
        this.titles = titles;
        this.widths = widths;
    }

    public HSSFCellStyle getStyle() {
        return style;
    }

    public void setStyle(HSSFCellStyle style) {
        this.style = style;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getWidths() {
        return widths;
    }

    public void setWidths(String widths) {
        this.widths = widths;
    }
}
