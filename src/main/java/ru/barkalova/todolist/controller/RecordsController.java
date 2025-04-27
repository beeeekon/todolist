package ru.barkalova.todolist.controller;


import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.barkalova.todolist.entity.Record;
import ru.barkalova.todolist.repository.RecordRepository;
import ru.barkalova.todolist.service.RecordService;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordsController{
    private final RecordService recordService;

    @Autowired
    public RecordsController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/hello")
    public String showAll(Model model){
        model.addAttribute("showHello","Hello my dear friend!");
        //model.addAttribute("showHello","Hi bitches");
        //return "templates/helloFinal.html";
        return "/WEB-INF/views/records/get/hello.html";
    }


}
