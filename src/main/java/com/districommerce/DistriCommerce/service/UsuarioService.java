package com.districommerce.DistriCommerce.service;

import com.districommerce.DistriCommerce.dto.LoginDTO;
import com.districommerce.DistriCommerce.dto.UsuarioDTO;
import com.districommerce.DistriCommerce.entity.Usuario;
import com.districommerce.DistriCommerce.repository.UsuarioRepository;
import com.districommerce.DistriCommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenha(passwordEncoder.encode(dto.getSenha()));
        u.setRole("USER");
        return repository.save(u);
    }

    public String login(LoginDTO dto) {
        Optional<Usuario> user = repository.findByEmail(dto.getEmail());
        if (user.isPresent() && passwordEncoder.matches(dto.getSenha(), user.get().getSenha())) {
            return jwtUtil.gerarToken(user.get().getEmail());
        }
        throw new RuntimeException("Credenciais inválidas");
    }
}
