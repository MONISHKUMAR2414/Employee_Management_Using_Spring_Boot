package com.bmt.MyStore.Controller;

import com.bmt.MyStore.models.AppUser;
import com.bmt.MyStore.services.AppUserService;
import com.bmt.MyStore.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final AppUserService appUserService;
    private final PdfService pdfService;

    @Autowired
    public AdminController(AppUserService appUserService, PdfService pdfService) {
        this.appUserService = appUserService;
        this.pdfService = pdfService;
    }

    @GetMapping("/AdminDashboard")
    public String AdminDashboard(Model model) {
        List<AppUser> clients = appUserService.getUsersByRole("Client");
        model.addAttribute("Client", clients);
        return "AdminDashboard"; // Make sure this matches your Thymeleaf template file name
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadAdminDashboardPdf() throws IOException {
        List<AppUser> clients = appUserService.getUsersByRole("Client");
        byte[] pdfBytes = pdfService.generateAdminDashboardPdf(clients);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=admin_dashboard.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}
