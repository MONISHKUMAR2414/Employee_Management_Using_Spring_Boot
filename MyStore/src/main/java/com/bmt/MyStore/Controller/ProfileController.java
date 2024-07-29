package com.bmt.MyStore.Controller;

import com.bmt.MyStore.models.AppUser;
import com.bmt.MyStore.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final AppUserService appUserService;

    @Autowired
    public ProfileController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        // Fetch the currently authenticated user
        AppUser user = appUserService.getCurrentUser();
        model.addAttribute("user", user);
        return "profile"; // Make sure this matches your Thymeleaf template file name
    }
}
