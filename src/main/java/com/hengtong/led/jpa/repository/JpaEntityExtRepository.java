package com.hengtong.led.jpa.repository;

import com.hengtong.led.jpa.dto.JpaDto;
import com.hengtong.led.jpa.dto.JpaRequestDto;

import java.util.List;

public interface JpaEntityExtRepository {

    List<JpaDto> findByCondition(JpaRequestDto request);

    Long countByCondition(JpaRequestDto request);
}
