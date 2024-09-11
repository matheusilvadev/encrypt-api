package com.buskopan.encripty_api.controllers;

import com.buskopan.encripty_api.domain.operation.Operation;
import com.buskopan.encripty_api.dto.OperationDTO;
import com.buskopan.encripty_api.dto.OperationResponseDTO;
import com.buskopan.encripty_api.services.OperationService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/api/operation")
public class OperationController {

    final private OperationService service;

    public OperationController(OperationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Operation> create(@RequestBody OperationDTO operationDTO, UriComponentsBuilder uriBuilder){
        Operation newOperation = service.create(operationDTO);

        var uri = uriBuilder.path("/api/operation/{id}").buildAndExpand(newOperation.getId()).toUri();

        return ResponseEntity.created(uri).body(newOperation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResponseDTO> read(@PathVariable Long id){
        OperationResponseDTO operation = service.read(id);

        return ResponseEntity.ok(operation);
    }
}
