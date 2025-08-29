package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {
    private final String logFilePath;
    private final Workbook workbook;
    private final Sheet sheet;
    private int currentRow = 1;

    public LogService() {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                Files.createDirectory(logDir.toPath());
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            logFilePath = "logs/session_log_" + timestamp + ".xlsx";

            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Log");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Timestamp");
            header.createCell(1).setCellValue("ID");
            header.createCell(2).setCellValue("Name");
            header.createCell(3).setCellValue("Email");
            header.createCell(4).setCellValue("UserType");

            save();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла лога", e);
        }
    }

    public void logUser(User user) {
        try {
            Row row = sheet.createRow(currentRow++);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            row.createCell(0).setCellValue(timestamp);
            row.createCell(1).setCellValue(user.getId().toString());
            row.createCell(2).setCellValue(user.getName());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(user.getUserType().toString());

            save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(logFilePath)) {
            workbook.write(fos);
        }
    }
}
