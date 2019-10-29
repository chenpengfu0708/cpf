package com.hengtong.led.jpa.repository;

import com.hengtong.led.jpa.entity.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEntityRepository extends JpaRepository<JpaEntity, Long>, JpaEntityExtRepository {

    JpaEntity findAllByName(String name);
}
