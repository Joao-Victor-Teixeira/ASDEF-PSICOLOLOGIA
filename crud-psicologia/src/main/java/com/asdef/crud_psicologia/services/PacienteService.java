package com.asdef.crud_psicologia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asdef.crud_psicologia.dto.PacienteDTO;
import com.asdef.crud_psicologia.paciente.Paciente;
import com.asdef.crud_psicologia.repositories.PacienteRepository;
import com.asdef.crud_psicologia.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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


    @Transactional
    public PacienteDTO insert(PacienteDTO dto){
        Paciente entity = new Paciente();
        copyToDTO(dto, entity);
        entity = repository.save(entity);
        return new PacienteDTO(entity);
    }

    @Transactional
    public PacienteDTO update(Long id, PacienteDTO dto){
        try {
            Paciente entity = repository.getReferenceById(id);
            copyToDTO(dto, entity);
            entity = repository.save(entity);
            return new PacienteDTO(entity);
        } 
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        repository.deleteById(id);
    }

    public void copyToDTO(PacienteDTO dto, Paciente entity){
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setObservação(dto.getObservacao());
    }
}
