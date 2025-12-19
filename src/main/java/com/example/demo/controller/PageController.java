package com.example.demo.controller;

import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final AdminService adminService;

    @GetMapping("/")
    public String home(){
        return "/home";
    }

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }


    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard-admin";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student/dashboard-student";
    }

    @GetMapping("/professor/dashboard")
    public String teacherDashboard() {
        return "professor/dashboard-professor";
    }


    @GetMapping("/admin/update-user/{id}")
    public String updateUserPage(@PathVariable Long id, Model model) {
        UserResponseDto user = adminService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/update-user";
    }


    @GetMapping("/admin/add-course")
    public String addCourse(){
        return "admin/add-course";
    }


    @GetMapping("/admin/detail-course/{id}")
    public String detailCoursePage(@PathVariable Long id, Model model) {
        model.addAttribute("courseId", id);
        return "admin/detail-course";
    }



    @GetMapping("/admin/view-courses")
    public String viewAllCourse(){
        return "admin/view-all-course";
    }
}
