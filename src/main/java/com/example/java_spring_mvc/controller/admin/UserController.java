package com.example.java_spring_mvc.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.java_spring_mvc.domain.User;
import com.example.java_spring_mvc.service.UploadService;
import com.example.java_spring_mvc.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getHomePage(Model model) {
        List<User> users = this.userService.getAllUseres();
        // System.out.println(users);
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User huukhanh,
            BindingResult newUserBindingResult,
            @RequestParam("gicungdcFile") MultipartFile file) {

        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError e : errors) {
        // System.out.println(">>>>>>>>> " + e.getField() + " - " +
        // e.getDefaultMessage());
        // }

        String hashPassword = this.passwordEncoder.encode(huukhanh.getPassword());
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");

        huukhanh.setAvatar(avatar);
        huukhanh.setPassword(hashPassword);
        huukhanh.setRole(this.userService.getRoleByName(huukhanh.getRole().getName()));

        if (newUserBindingResult.hasErrors()) {
            return "/admin/user/create";
        }
        this.userService.handleSaveUser(huukhanh);

        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/update-user";
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String updateUserPage(Model model,
            @ModelAttribute("user") User huukhanh,
            @RequestParam("gicungdcFile") MultipartFile file) {
        User currentUser = this.userService.getUserById(huukhanh.getId());

        if (currentUser != null) {
            String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
            huukhanh.setRole(this.userService.getRoleByName(huukhanh.getRole().getName()));
            currentUser.setId(huukhanh.getId());
            currentUser.setEmail(huukhanh.getEmail());
            currentUser.setFullName(huukhanh.getFullName());
            currentUser.setAddress(huukhanh.getAddress());
            currentUser.setPhone(huukhanh.getPhone());
            currentUser.setAvatar(avatar);
            currentUser.setRole(huukhanh.getRole());
            this.userService.handleSaveUser(currentUser);
        }
        // this.userService.handleSaveUser(huukhanh);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/delete/{id}")
    public String getDeleteUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUserPage(Model model, @ModelAttribute("user") User user) {
        this.userService.deleteUserById(user.getId());
        return "redirect:/admin/user";
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
