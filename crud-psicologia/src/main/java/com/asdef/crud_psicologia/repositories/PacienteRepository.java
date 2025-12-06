package com.asdef.crud_psicologia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdef.crud_psicologia.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

}
