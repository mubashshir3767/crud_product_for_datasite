package com.example.crud_product_for_datasite.controller;

import com.example.crud_product_for_datasite.ApiResponse;
import com.example.crud_product_for_datasite.dto.request.UserRequestDTO;
import com.example.crud_product_for_datasite.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public ApiResponse addUser(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        return userService.addUser(userRequestDTO);
    }

}
