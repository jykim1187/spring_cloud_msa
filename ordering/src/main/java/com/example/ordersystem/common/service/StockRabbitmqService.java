package com.example.ordersystem.common.service;

import com.example.ordersystem.common.config.RabbitmqConfig;
import com.example.ordersystem.member.dtos.StockRabbitDto;
import com.example.ordersystem.product.domain.Product;
import com.example.ordersystem.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockRabbitmqService {
    private final RabbitTemplate template;
    private final ProductRepository productRepository;

    public StockRabbitmqService(RabbitTemplate template, ProductRepository productRepository) {
        this.template = template;
        this.productRepository = productRepository;
    }

    //    mq에 rdb동기화 관련 메시지를 발행
    public void publish(StockRabbitDto dto){
        template.convertAndSend(RabbitmqConfig.STOCK_DECREASE_QUEUE,dto);
    }

//    mq에 저장된 메시지를 소비하여 rdb에 동기화
//    listner는 publish와는 독립적으로 동작하기 때문에, 비동기적으로 실행.
//    한 트랜잭션이 완료된 이후에 그 다음 메시지 수신하므로, 동시성 이슈 발생x
    @RabbitListener(queues = RabbitmqConfig.STOCK_DECREASE_QUEUE)
    public void subscribe(Message message) throws JsonProcessingException {
//        {"productId":1, "productCount":3}
        String messageBody = new String(message.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        StockRabbitDto dto = objectMapper.readValue(messageBody, StockRabbitDto.class);
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(()->new EntityNotFoundException("product is not found"));
        product.updateStockQuantity(dto.getProductCount()); //영속성.더티체킹은 Transactional어노테이션 필요
    }

}
