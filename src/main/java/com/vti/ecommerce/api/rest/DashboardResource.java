package com.vti.ecommerce.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.services.IDashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardResource {

    @Autowired
    private IDashboardService dashboardService;

    @GetMapping("/count-genders")
    public Object countGenders() {
        return dashboardService.countGenders();
    }

    @GetMapping("/count-by-gender")
    public Object countByGender(@RequestParam String gender) {
        return dashboardService.countByGender(gender);
    }
}
