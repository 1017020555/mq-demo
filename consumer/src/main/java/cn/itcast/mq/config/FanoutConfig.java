package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("itcast.fanout");
    }

    @Bean
    public Queue getQueue1(){
        return new Queue("fanout.queue1");
    }

    @Bean
    public Binding bindingQueue1(Queue getQueue1,FanoutExchange getFanoutExchange){
        return BindingBuilder
                .bind(getQueue1)
                .to(getFanoutExchange);
    }


    @Bean
    public Queue getQueue2(){
        return new Queue("fanout.queue2");
    }

    @Bean
    public Binding bindingQueue2(Queue getQueue2,FanoutExchange getFanoutExchange){
        return BindingBuilder
                .bind(getQueue2)
                .to(getFanoutExchange);
    }

    @Bean
    public Queue objectMessageQueue(){
        return new Queue("object.queue");
    }


}
