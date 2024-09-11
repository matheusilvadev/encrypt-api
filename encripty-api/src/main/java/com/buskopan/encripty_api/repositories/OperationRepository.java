package com.buskopan.encripty_api.repositories;

import com.buskopan.encripty_api.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
