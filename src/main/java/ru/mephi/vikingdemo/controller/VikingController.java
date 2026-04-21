package ru.mephi.vikingdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/vikings")
@Tag(name = "Vikings", description = "Операции с викингами")
public class VikingController {

    private final VikingService vikingService;
    private VikingListener vikingListener;

    public VikingController(VikingService vikingService, VikingListener vikingListener) {
        this.vikingService = vikingService;
        this.vikingListener = vikingListener;
    }
    
    @GetMapping
    @Operation(summary = "Получить список созданных викингов", 
            operationId = "getAllVikings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<Viking> getAllVikings() {
        System.out.println("GET /api/vikings called");
        return vikingService.findAll();
    }

    @GetMapping("/test")
    @Operation(summary = "Получить список тестовых викингов", 
            operationId = "getTest")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<String> test() {
        System.out.println("GET /api/vikings/test called");
        return List.of("Ragnar", "Bjorn");
    }
    
    @PostMapping("/post")
    public void addRandomViking(){
        vikingListener.testAdd();
    }
    
    @PostMapping
    @Operation(summary = "Добавить нового викинга с конкретными параметрами")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка")
    })
    public Viking addViking(@RequestBody Viking viking){
        System.out.println("POST /api/vikings called with: " + viking);
        return vikingService.createViking(viking);
    }
    
    @DeleteMapping("/{index}")
    @Operation(summary = "Удалить викинга по индексу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно удален"),
            @ApiResponse(responseCode = "404", description = "Викинг с таким индексом не найден")
    })
    public void deleteViking(@PathVariable int index){
        System.out.println("DELETE /api/vikings/" + index + " called");
        vikingService.deleteViking(index);
    }
    
    @PutMapping("/{index}")
    @Operation(summary = "Обновить параметры викинга по индексу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Викинг с таким индексом не найден")
    })
    public Viking updateViking(@PathVariable int index, @RequestBody Viking viking){
        System.out.println("PUT /api/vikings/" + index + " called with: " + viking);
        return vikingService.updateViking(index, viking);
    }
}
