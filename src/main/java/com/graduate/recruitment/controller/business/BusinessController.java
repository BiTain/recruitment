package com.graduate.recruitment.controller.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BusinessController {

    @GetMapping("/doanh-nghiep")
    public String home() {
        return "business/home";
    }
}
