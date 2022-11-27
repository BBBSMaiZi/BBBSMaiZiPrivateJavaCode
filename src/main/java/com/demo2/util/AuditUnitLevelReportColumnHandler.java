package com.demo2.util;

        import com.alibaba.excel.metadata.Head;
        import com.alibaba.excel.metadata.data.WriteCellData;
        import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
        import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
        import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.Sheet;

        import java.util.List;

public class AuditUnitLevelReportColumnHandler extends AbstractColumnWidthStyleStrategy {
    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        boolean needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList);
        if (needSetWidth) {
            Sheet sheet = writeSheetHolder.getSheet();
            int columnIndex = cell.getColumnIndex();
            sheet.setColumnWidth(columnIndex, 20 * 256);
         /*   if (columnIndex == 1){
                //要求将当前列的列宽设置为33英寸，理论值应该是33×256=8448，
                //但是这里的设值是8648，具体原因请看下面的计算逻辑
                sheet.setColumnWidth(1,130);
            }else if (columnIndex == 2){
                sheet.setColumnWidth(2,130);
            }else {
                sheet.setColumnWidth(columnIndex,3016);
            }*/
        }
    }

}