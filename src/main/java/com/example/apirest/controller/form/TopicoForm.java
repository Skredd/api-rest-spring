package com.example.apirest.controller.form;

import com.example.apirest.modelo.Curso;
import com.example.apirest.modelo.Topico;
import com.example.apirest.repository.CursoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoForm {
    private String titullo;
    private String mensagem;
    private String nomeCurso;


    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titullo, mensagem, curso);
    }
}
