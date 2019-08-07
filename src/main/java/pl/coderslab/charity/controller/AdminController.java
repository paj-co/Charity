package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/404")
    public String admin404() {
        return "admin/admin404";
    }

    @GetMapping("/charts")
    public String adminCharts() {
        return "admin/adminCharts";
    }

    @GetMapping("/tables")
    public String adminTables() {
        return "admin/adminTables";
    }

    @GetMapping("/blank")
    public String adminBlank() {
        return "admin/adminBlank";
    }






}
