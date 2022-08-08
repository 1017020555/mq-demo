package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAMQPTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue(){
        String queueName = "simple.queue";
        String message = "我发生1条message,队列名称:simple.queue";
        rabbitTemplate.convertAndSend(queueName , message);
    }

    @Test
    public void testWorkQueue() throws Exception{

        String queueName = "simple.queue";
        String message = "我发生1条message,队列名称:simple.queue";

        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName , message+i);
            Thread.sleep(20);
        }

    }

    @Test
    public void testFanoutExchange(){

        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, everyone!";
        // 发送消息，参数分别是：交互机名称、RoutingKey（暂时为空）、消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testDirectExchange(){

        String exchangeName = "itcast.direct";
        // 消息
        String message = "hello, red!";

        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testTopicExchange(){

        String exchangeName = "itcast.topic";
        // 消息
        String message = "hello, china.news!";

        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }


    @Test
    public void testSendMap(){

        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);

        // 发送消息
        rabbitTemplate.convertAndSend("object.queue", msg);
    }


}
