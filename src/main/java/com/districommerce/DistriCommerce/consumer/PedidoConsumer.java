package com.districommerce.DistriCommerce.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.districommerce.DistriCommerce.config.RabbitMQConfig;

@Component
public class PedidoConsumer {

    @RabbitListener(queues = RabbitMQConfig.PEDIDOS_QUEUE)
    public void receberPedido(String pedido) {
        System.out.println("Pedido recebido: " + pedido);
        //TODO implementar a logica para processar o pedido
    }
} 