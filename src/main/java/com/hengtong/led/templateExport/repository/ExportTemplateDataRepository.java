package com.hengtong.led.templateExport.repository;

import com.hengtong.led.templateExport.entity.ExportTemplateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportTemplateDataRepository extends JpaRepository<ExportTemplateData, Long> {

    ExportTemplateData findByEntityObject(String entityObject);
}
