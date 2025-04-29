package ru.barkalova.todolist.controller;



import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkalova.todolist.entity.Record;
import ru.barkalova.todolist.service.RecordService;

import java.util.Calendar;
import java.util.List;


//плохо что возвращается сама сущность, нужно использовать паттерн dto
//оборачиваем класс сущности в dto и храним там только важные поля (без id и тп)
//гайд на паттерн https://youtu.be/LnUCr8fY0sk?si=VKNwdsLBTjPw2Sma
@Tag(name="record_controller")
@RestController
@RequestMapping("/records")
public class RecordsController{
    private final RecordService recordService;

    @Autowired
    public RecordsController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value="name",defaultValue = "my dear friend") String name, Model model){
        model.addAttribute("showHello","Hello my dear friend!");
        //model.addAttribute("showHello","Hi bitches");
        //return "templates/helloFinal.html";
        //return "helloFinal";
        return "Hello, "+name+"! How are you?";
    }

    @GetMapping()
    public List<Record> showAll(){
        return recordService.showAll();
    }

    @GetMapping("/{id}")
    public Record getById(@PathVariable("id") int id, Model model){
        model.addAttribute("recordById",recordService.getById(id));
        return recordService.getById(id);
    }

    @GetMapping("/{name}")
    public List<Record> getByName(@PathVariable("name") String name){
        return recordService.getByName(name);
    }

    @GetMapping("/{calendar}")
    public List<Record> getByCalendar(@PathVariable("deadline") Calendar deadline){
        return recordService.getByCalendar(deadline);
    }

    @GetMapping("/{isCompleted}")
    public List<Record> getByIsCompleted(@PathVariable("isCompleted") boolean isCompleted){
        return recordService.getByIsCompleted(isCompleted);
    }

    @PostMapping()
    public String create(@ModelAttribute("record") @Valid Record record,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "record dont save sorry";

        recordService.save(record);
        return "record save done";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("record") @Valid Record record,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "record dont update";

        recordService.update(id, record);
        return "record update done";
    }

    @PatchMapping("/{id}/{isCompleted}")
    public String updateIsCompleted(@ModelAttribute("record") @Valid Record record,
                                    BindingResult bindingResult,
                                    @PathVariable("id") int id,
                                    @PathVariable("isCompleted") boolean isCompleted
                                    )
    {
        if (bindingResult.hasErrors())
            return "record dont update";

        recordService.updateIsCompleted(id, isCompleted);
        return "record update done! isCompleted="+isCompleted;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        recordService.delete(id);
        return "redirect:/people";
    }





}
