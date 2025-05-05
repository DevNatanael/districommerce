package com.districommerce.DistriCommerce.service;

import com.districommerce.DistriCommerce.dto.PedidoDTO;
import com.districommerce.DistriCommerce.entity.Pedido;
import com.districommerce.DistriCommerce.entity.Produto;
import com.districommerce.DistriCommerce.entity.Usuario;
import com.districommerce.DistriCommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    public Pedido criarPedido(PedidoDTO dto) {
        // Validar usuário
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(dto.getUsuarioId());

        List<Pedido.ItemPedido> itens = new ArrayList<>();
        double total = 0.0;

        // Validar produtos e calcular total
        for (PedidoDTO.ItemPedidoDTO itemDTO : dto.getItens()) {
            Produto produto = produtoService.buscarPorId(itemDTO.getProdutoId());
            if (produto == null) {
                throw new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId());
            }

            Pedido.ItemPedido item = new Pedido.ItemPedido();
            item.setProdutoId(produto.getId());
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            itens.add(item);
            total += produto.getPreco() * itemDTO.getQuantidade();
        }

        pedido.setItens(itens);
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(String id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
