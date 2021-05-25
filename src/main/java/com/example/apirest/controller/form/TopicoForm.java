package com.example.apirest.controller.form;

import com.example.apirest.modelo.Curso;
import com.example.apirest.modelo.Topico;
import com.example.apirest.modelo.Usuario;
import com.example.apirest.repository.CursoRepository;
import com.example.apirest.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoForm {
    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;


    public Topico converter(CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        Usuario usuario = usuarioRepository.findByNome("Aluno");
        return new Topico(titulo, mensagem, curso, usuario);
    }
}
