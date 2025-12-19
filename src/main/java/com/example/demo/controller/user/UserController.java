package com.example.demo.controller.user;

import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;



    @PutMapping("/{id}/role")
    public String changeRole(@PathVariable Long id,
                             @RequestParam String role) {
        adminService.changeRole(id, role);
        return "change role of user";
    }
}
