package com.example.exercisevalidation.Controller;


import com.example.exercisevalidation.Api.ApiResponse;
import com.example.exercisevalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("Api/va1/eventC")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity show(){
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody Event ev, Errors errors) {
        if (errors.hasErrors()){
            String mesge=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mesge);
        }
        events.add(ev);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity upevent(@PathVariable int index, @RequestBody Event ev, Errors errors) {
        if (errors.hasErrors()) {
            String mesge = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mesge);
        }
        events.set(index, ev);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Delete"));
    }


    @PutMapping("/change-capacity/{index}/{capacity}")
    public  ResponseEntity changeCap(@PathVariable int index, @PathVariable @Valid int capacity, Errors errors) {
        if (errors.hasErrors()) {
            String mesge = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mesge);
        }
        Event event = events.get(index);
        event.setCapacity(capacity);
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("CapacityChanged"));
    }
    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable @Valid String ID, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ArrayList<Event> event = new ArrayList<>();
        for (Event ev : events) {
            if (ev.getId().equalsIgnoreCase(ID)) {
                event.add(ev);
            }
        }
        return ResponseEntity.status(200).body(event);
    }
}