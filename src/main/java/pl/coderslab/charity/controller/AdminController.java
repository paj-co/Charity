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

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/adminLogin";
    }

    @GetMapping("/register")
    public String adminRegister() {
        return "admin/adminRegister";
    }

    @GetMapping("/forgot-password")
    public String adminForgotPassword() {
        return "admin/adminForgotPassword";
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




}
