package cn.itcast.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Exchanger;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String message) throws InterruptedException {
        System.out.println("我收到1： "+message+"--"+ LocalDateTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage2(String message) throws InterruptedException {
        System.out.println("我收到2： "+message+"--"+ LocalDateTime.now());
        Thread.sleep(20);
    }


    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg){
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg){
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(value = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
            )
    )
    public void listenDirectQueue1(String msg){
        System.out.println("消费者1接收到Direct消息：【"+msg+"】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(value = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
            )
    )
    public void listenDirectQueue2(String msg){
        System.out.println("消费者2接收到Direct消息：【"+msg+"】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(value = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"china.#","#.news"}
    )
    )
    public void listenTopicQueue1(String msg){
        System.out.println("消费者1接收到Direct消息：【"+msg+"】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(value = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    )
    )
    public void listenTopicQueue2(String msg){
        System.out.println("消费者2接收到Direct消息：【"+msg+"】");
    }


    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> msg){
        System.out.println("objectQueue: "+msg);
    }


}
