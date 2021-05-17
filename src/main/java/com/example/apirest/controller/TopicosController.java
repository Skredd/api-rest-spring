package com.example.apirest.controller;

import com.example.apirest.controller.dto.DetalhesTopicoDTO;
import com.example.apirest.controller.dto.TopicoDTO;
import com.example.apirest.controller.form.TopicoForm;
import com.example.apirest.modelo.Topico;
import com.example.apirest.repository.CursoRepository;
import com.example.apirest.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso) {
        if (nomeCurso == null) {
            return TopicoDTO.converter(topicoRepository.findAll());
        }
        return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDTO detalhar(@PathVariable("id") Long id) {
        Topico topico = topicoRepository.getOne(id);
        return new DetalhesTopicoDTO(topico);
    }

}
