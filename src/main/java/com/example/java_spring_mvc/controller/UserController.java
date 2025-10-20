package com.example.java_spring_mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.java_spring_mvc.domain.User;
import com.example.java_spring_mvc.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> users = this.userService.getAllUseres();
        System.out.println(users);
        model.addAttribute("users1", users);
        return "/admin/user/table-user";
    }

    @RequestMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User huukhanh) {
        this.userService.handleSaveUser(huukhanh);
        return "redirect:/";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/update-user";
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String updateUserPage(Model model, @ModelAttribute("newUser") User huukhanh) {
        User currentUser = this.userService.getUserById(huukhanh.getId());
        if (currentUser != null) {
            currentUser.setId(huukhanh.getId());
            currentUser.setEmail(huukhanh.getEmail());
            currentUser.setFullName(huukhanh.getFullName());
            currentUser.setAddress(huukhanh.getAddress());
            currentUser.setPhone(huukhanh.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        // this.userService.handleSaveUser(huukhanh);
        return "redirect:/";
    }

    @RequestMapping("/admin/user/delete/{id}")
    public String getDeleteUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUserPage(Model modelm, @ModelAttribute("user") User user) {
        this.userService.deleteUserById(user.getId());
        return "redirect:/";
    }

}

// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getMethodName() {
// return this.userService.handldHello();
// }

// }
