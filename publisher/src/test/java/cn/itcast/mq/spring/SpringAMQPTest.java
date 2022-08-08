package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


}
