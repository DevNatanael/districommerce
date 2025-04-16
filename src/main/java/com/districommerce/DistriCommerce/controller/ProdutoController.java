package com.districommerce.DistriCommerce.controller;

import com.districommerce.DistriCommerce.dto.ProdutoDTO;
import com.districommerce.DistriCommerce.entity.Produto;
import com.districommerce.DistriCommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public Produto criar(@RequestBody ProdutoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
