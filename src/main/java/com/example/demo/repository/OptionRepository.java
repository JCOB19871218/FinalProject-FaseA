package com.example.demo.repository;

import com.example.demo.entity.example.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
