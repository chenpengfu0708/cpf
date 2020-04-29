package com.hengtong.led.templateUpload.repository;

import com.hengtong.led.templateUpload.entity.TemplateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDataRepository extends JpaRepository<TemplateData, Long> {

    TemplateData findByType(String entityObject);

}
