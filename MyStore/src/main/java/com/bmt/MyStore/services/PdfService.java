package com.bmt.MyStore.services;

import com.bmt.MyStore.models.AppUser;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {
    public byte[] generateAdminDashboardPdf(List<AppUser> clients) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Admin Dashboard"));
        document.add(new Paragraph("This is the admin section where you can manage users and view reports."));
        document.add(new Paragraph("Client Users:"));

        float[] columnWidths = {1, 3, 5, 3};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        table.addHeaderCell("ID");
        table.addHeaderCell("Name");
        table.addHeaderCell("Email");
        table.addHeaderCell("Role");

        for (AppUser client : clients) {
            table.addCell(String.valueOf(client.getId()));
            table.addCell(client.getFirstName());
            table.addCell(client.getEmail());
            table.addCell(client.getRole());
        }

        document.add(table);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
