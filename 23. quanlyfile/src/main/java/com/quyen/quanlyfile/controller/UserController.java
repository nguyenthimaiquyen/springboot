package com.quyen.quanlyfile.controller;

import com.quyen.quanlyfile.model.request.SearchUserRequest;
import com.quyen.quanlyfile.model.response.UserResponse;
import com.quyen.quanlyfile.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUserPage(Model model, SearchUserRequest request) {
        UserResponse userResponse = userService.searchUser(request);
        model.addAttribute("users", userResponse.getUsers());
        model.addAttribute("requestSearch", request);
        model.addAttribute("currentPage", userResponse.getCurrentPage());
        model.addAttribute("totalPage", userResponse.getTotalPage());
        model.addAttribute("totalElement", userResponse.getTotalElement());
        model.addAttribute("pageSize", userResponse.getPageSize());
        return "index";
    }



}
