package ru.barkalova.todolist.controller;



import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkalova.todolist.entity.Record;
import ru.barkalova.todolist.service.RecordService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @GetMapping("/getById/{id}")
    public Record getById(@PathVariable("id") int id, Model model){
        model.addAttribute("recordById",recordService.getById(id));
        return recordService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public List<Record> getByName(@PathVariable("name") String name){
        return recordService.getByName(name);
    }

//    @GetMapping("/getByCalendar/{deadline}")
//    public List<Record> getByCalendar(@PathVariable("deadline") Calendar deadline){
//        return recordService.getByCalendar(deadline);
//    }
    @GetMapping("/getByCalendar/{calendarString}")
    public List<Record> getByCalendar(@PathVariable("calendarString") String calendarString) {
        // Определите формат даты (должен соответствовать формату в URL)
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Пример: "2023-10-27T10:00:00"

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(calendarString, formatter);

            Calendar deadline = GregorianCalendar.from(localDateTime.atZone(java.time.ZoneId.systemDefault()));
            //Преобразование LocalDateTime в Calendar

            return recordService.getByCalendar(deadline);
        } catch (Exception e) {
            // Обработка ошибки преобразования даты
            throw new IllegalArgumentException("Неверный формат даты. Ожидается: " + formatter.toString(), e);
        }
    }


    @GetMapping("/getByIsCompleted/{isCompleted}")
    public List<Record> getByIsCompleted(@PathVariable("isCompleted") boolean isCompleted){
        return recordService.getByIsCompleted(isCompleted);
    }

    @PostMapping("/post/save")
    public String create( @RequestBody @Valid Record record,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "record dont save sorry";

        recordService.save(record);
        return "record save done";
    }

    @PatchMapping("/update/{id}")
    public String update(@RequestBody @Valid Record record,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "record dont update";

        recordService.update(id, record);
        return "record update done";
    }

    @PatchMapping("/post/{id}/{isCompleted}")
    public String updateIsCompleted(@RequestBody @Valid Record record,
                                    BindingResult bindingResult,
                                    @PathVariable("id") int id,
                                    @PathVariable("isCompleted") boolean isCompleted
                                    )
    {
        if (bindingResult.hasErrors())
            return "record dont update isCompleted";

        recordService.updateIsCompleted(id, isCompleted);
        return "record update done! isCompleted="+isCompleted;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){//, BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "record dont update";
        recordService.delete(id);
        return "record delete done!";
    }





}
