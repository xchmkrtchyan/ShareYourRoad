package com.example.finalproject.controllers;

import com.example.finalproject.models.User;
import com.example.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");//resources/templates/login.html
        return modelAndView;
    }

    @RequestMapping(value = {"/home"},method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");//resources/templates/home.html
        return modelAndView;
    }

    @RequestMapping(value = {"/register"},method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("register");//resources/templates/register.html
        return modelAndView;
    }

    @RequestMapping(value = {"/register"},method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()){
            modelAndView.addObject("successMessage","Please correct the errors in form!");
            modelMap.addAttribute("bindingResult",bindingResult);
        }else if (userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage","user already exists!");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage","user is registered successfully");
        }
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
