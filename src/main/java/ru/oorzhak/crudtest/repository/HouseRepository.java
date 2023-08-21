package ru.oorzhak.crudtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oorzhak.crudtest.model.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
