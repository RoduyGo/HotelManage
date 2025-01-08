package com.example.QLKS.repository;

import com.example.QLKS.entities.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
DichVuRepository extends JpaRepository<DichVu, Integer> {
}
