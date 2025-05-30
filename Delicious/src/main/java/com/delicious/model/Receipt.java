package com.delicious.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private static final String RECEIPT_FOLDER = "receipts.txt";
    private static final DateTimeFormatter FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static void saveReceipt(Order order) {
        File folder = new File(RECEIPT_FOLDER);
        if (!folder.exists()) {
            System.err.println("Cannot save receipt.");
            return;
        }

        String fileName = order.getOrderDateTime().format(FILE_NAME_FORMATTER) + ".txt";
        File receiptFile = new File(folder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write(order.getDetails());
            writer.newLine();
            System.out.println("Receipt saved to: " + receiptFile.getPath());
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}
