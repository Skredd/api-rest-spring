package com.example.apirest.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDeFormularioDTO {
    private String campo;
    private String message;
}
