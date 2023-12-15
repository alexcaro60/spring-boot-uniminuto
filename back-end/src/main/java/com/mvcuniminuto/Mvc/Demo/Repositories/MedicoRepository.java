package com.mvcuniminuto.Mvc.Demo.Repositories;

import com.mvcuniminuto.Mvc.Demo.Entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Medico findById(int id);
}
