package org.example.zhprogkorny.repository;

import org.example.zhprogkorny.model.WateringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WateringLogRepository extends JpaRepository<WateringLog, Long> {}