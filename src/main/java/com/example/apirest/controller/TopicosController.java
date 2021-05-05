package com.example.apirest.controller;

import com.example.apirest.controller.dto.TopicoDTO;
import com.example.apirest.controller.form.TopicoForm;
import com.example.apirest.repository.CursoRepository;
import com.example.apirest.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository

    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso) {
        if (nomeCurso == null) {
            return TopicoDTO.converter(topicoRepository.findAll());
        }
        return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody TopicoForm topico) {
        topicoRepository.save(topico.converter(cursoRepository));
    }

}
