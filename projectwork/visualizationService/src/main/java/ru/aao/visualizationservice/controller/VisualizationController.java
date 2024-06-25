package ru.aao.visualizationservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visualization")
public class VisualizationController {

    @GetMapping()
    public String getMain() {
        return "main";
    }
}