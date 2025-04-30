package com.districommerce.DistriCommerce.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.districommerce.DistriCommerce.config.RabbitMQConfig;

@Service
public class PedidoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarPedido(String pedido) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PEDIDOS_QUEUE, pedido);
    }
} 