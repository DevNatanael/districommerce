package com.districommerce.DistriCommerce.service;

import com.districommerce.DistriCommerce.dto.LoginDTO;
import com.districommerce.DistriCommerce.dto.UsuarioDTO;
import com.districommerce.DistriCommerce.entity.Usuario;
import com.districommerce.DistriCommerce.repository.UsuarioRepository;
import com.districommerce.DistriCommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail.");
        }
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

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario atualizar(Long id, UsuarioDTO dto) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            Usuario u = optional.get();
            u.setNome(dto.getNome());
            u.setEmail(dto.getEmail());
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                u.setSenha(passwordEncoder.encode(dto.getSenha()));
            }
            return repository.save(u);
        }
        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
