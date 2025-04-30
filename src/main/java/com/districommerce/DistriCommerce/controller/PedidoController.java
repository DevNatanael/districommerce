package com.districommerce.DistriCommerce.controller;

import com.districommerce.DistriCommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public String enviarPedido(@RequestBody String pedido) {
        pedidoService.enviarPedido(pedido);
        return "Pedido enviado com sucesso!";
    }
} 