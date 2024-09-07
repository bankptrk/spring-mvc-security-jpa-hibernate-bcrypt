package com.bankptrk.springboot.demosecurity.controller;

import com.bankptrk.springboot.demosecurity.entity.User;
import com.bankptrk.springboot.demosecurity.user.WebUser;
import com.bankptrk.springboot.demosecurity.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel){
        theModel.addAttribute("webUser", new WebUser());
        return "register/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel){
        String userName = theWebUser.getUserName();
        // validation form
        if(theBindingResult.hasErrors()) return "register/registration-form";
        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if(existing != null){
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError","User name already");
            return "register/registration-form";
        }
        // create user account and store in the database
        userService.save(theWebUser);
        // place user in the web http session for later use
        session.setAttribute("user", theWebUser);
        return "register/registration-confirmation";
    }
}
