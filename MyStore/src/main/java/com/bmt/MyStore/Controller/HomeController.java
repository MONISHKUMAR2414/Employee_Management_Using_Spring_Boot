package com.bmt.MyStore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"","/"})
    public String home() {
        return "index"; // Ensure you have a file named "index.html" in your templates folder
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // Ensure you have a file named "contact.html" in your templates folder
    }
}
