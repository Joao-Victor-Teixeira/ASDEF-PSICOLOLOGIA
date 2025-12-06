package com.asdef.crud_psicologia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asdef.crud_psicologia.dto.PacienteDTO;
import com.asdef.crud_psicologia.paciente.Paciente;
import com.asdef.crud_psicologia.repositories.PacienteRepository;
import com.asdef.crud_psicologia.services.exceptions.ResourceNotFoundException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional(readOnly = true)
    public PacienteDTO findById(Long id){
        
        Paciente paciente = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
            return new PacienteDTO(paciente);
    }

    public Page<PacienteDTO> findAll(Pageable pageable){
        Page<Paciente> result = repository.findAll(pageable);
        return result.map(x -> new PacienteDTO(x));
    }
}
