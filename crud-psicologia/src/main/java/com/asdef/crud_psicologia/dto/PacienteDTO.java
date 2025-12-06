package com.asdef.crud_psicologia.dto;

import com.asdef.crud_psicologia.paciente.Paciente;

public class PacienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String observacao;

    public PacienteDTO(){
    }

    public PacienteDTO(Paciente entity){
        id = entity.getId();
        nome = entity.getNome();
        telefone = entity.getTelefone();
        observacao = entity.getObservacao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getObservacao() {
        return observacao;
    }
}
