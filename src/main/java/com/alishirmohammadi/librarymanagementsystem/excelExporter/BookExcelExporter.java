package com.alishirmohammadi.librarymanagementsystem.excelExporter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import com.alishirmohammadi.librarymanagementsystem.entity.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 public class BookExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Book> listBooks;

    public BookExcelExporter(List<Book> listBooks) {
        this.listBooks = listBooks;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Books");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "نام کتاب", style);
        createCell(row, 2, "شابک", style);
        createCell(row, 3, "شماره سریال", style);
        createCell(row, 4, "توضحات", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Book book : listBooks) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, book.getId(), style);
            createCell(row, columnCount++, book.getName(), style);
            createCell(row, columnCount++, book.getIsbn(), style);
            createCell(row, columnCount++, book.getSerialName(), style);
            createCell(row, columnCount++, book.getDescription(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }
}