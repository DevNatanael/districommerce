package com.districommerce.DistriCommerce.controller;

import com.districommerce.DistriCommerce.dto.LoginDTO;
import com.districommerce.DistriCommerce.dto.UsuarioDTO;
import com.districommerce.DistriCommerce.entity.Usuario;
import com.districommerce.DistriCommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public Usuario cadastrar(@RequestBody UsuarioDTO dto) {
        return service.cadastrar(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return service.login(dto);
    }
}
