package com.example.chatbox.controllers;

import com.example.chatbox.services.CRUDSample;
import com.example.chatbox.services.CRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CRUDController {
    public CRUDService crudService;
    public CRUDController(CRUDService crudService){
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUDSample crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud);
    }

    @GetMapping("/get")
    public CRUDSample getCRUD(@RequestParam String documentId ) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(documentId);
    }

    @PutMapping("/put")
    public String putCRUD(@RequestBody CRUDSample crud) throws InterruptedException, ExecutionException {
        return crudService.putCRUD(crud);
    }

    @PutMapping("delete")
    public String deleteCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.deleteCRUD(documentId);
    }
}
