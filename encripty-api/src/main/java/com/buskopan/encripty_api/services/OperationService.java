package com.buskopan.encripty_api.services;

import com.buskopan.encripty_api.domain.operation.Operation;
import com.buskopan.encripty_api.domain.operation.exceptions.OperationNotFoundException;
import com.buskopan.encripty_api.dto.OperationDTO;
import com.buskopan.encripty_api.dto.OperationResponseDTO;
import com.buskopan.encripty_api.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OperationService {
    private OperationRepository repository;
    private EncryptService encryptService;

    public OperationService(OperationRepository repository, EncryptService encryptService){
        this.repository = repository;
        this.encryptService = encryptService;
    }


    public Operation create(OperationDTO operationDTO){
        Operation operation = new Operation();
        String userDocumentHashed = this.encryptService.encryptData(operationDTO.userDocument());
        String creditCardHashed = this.encryptService.encryptData(operationDTO.creditCardToken());

        operation.setCredCardToken(creditCardHashed);
        operation.setUserDocument(userDocumentHashed);
        operation.setValue(operationDTO.operationValue());

        this.repository.save(operation);

        return operation;

    }

    public OperationResponseDTO read(Long id) throws OperationNotFoundException{
       Operation operation = this.repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));

        String userDocumentHashed = this.encryptService.decryptData(operation.getUserDocument());
        String creditCardHashed = this.encryptService.decryptData(operation.getCredCardToken());

        OperationResponseDTO dto = new OperationResponseDTO(operation.getId(), userDocumentHashed, creditCardHashed, operation.getValue());


        return dto;
    }
}
