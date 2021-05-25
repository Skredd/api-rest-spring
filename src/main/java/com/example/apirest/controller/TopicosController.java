package com.example.apirest.controller;

import com.example.apirest.controller.dto.topicos.AtualizacaoTopicoForm;
import com.example.apirest.controller.dto.topicos.DetalhesTopicoDTO;
import com.example.apirest.controller.dto.topicos.TopicoDTO;
import com.example.apirest.controller.form.TopicoForm;
import com.example.apirest.modelo.Topico;
import com.example.apirest.repository.CursoRepository;
import com.example.apirest.repository.TopicoRepository;
import com.example.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> lista(String nomeCurso) {
        if (nomeCurso == null) {
            return ResponseEntity.ok(TopicoDTO.converter(topicoRepository.findAll()));
        }
        return ResponseEntity.ok(TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso)));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> create(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        var topico = topicoForm.converter(cursoRepository, usuarioRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> findById(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> update(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
