package com.apirest2.backends2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends ApiBaseController{
    public IndexController() {
    }
 
    @GetMapping("/")

    
    public String home() {
       return "index";
    }
 }
