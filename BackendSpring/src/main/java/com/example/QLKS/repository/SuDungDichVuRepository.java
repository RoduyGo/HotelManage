package com.example.QLKS.repository;

import com.example.QLKS.entities.SuDungDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SuDungDichVuRepository  extends JpaRepository<SuDungDichVu, Integer> {

}
