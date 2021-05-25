package com.example.apirest.controller;

import com.example.apirest.controller.dto.topicos.TopicoDTO;
import com.example.apirest.controller.dto.usuarios.UsuarioDTO;
import com.example.apirest.modelo.Usuario;
import com.example.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDTO> findAll() {
        return UsuarioDTO.converter(usuarioRepository.findAll());
    }


}
