package com.sky.rabbitmq.demo2;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sky.rabbitmq.ConnectionUtil;

/**
 * @Title: Producer 测试结果：
1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
2、消费者1和消费者2获取到的消息的数量是相同的，一个是奇数一个是偶数。

其实，这样是不合理的，应该是消费者1要比消费者2获取到的消息多才对。
Work的能者多劳模式
需要将上面两个消费者的channel.basicQos(1);这行代码的注释打开,再次执行会发现,休眠时间短的消费者执行的任务多
消息的确认
在以上的代码中,已经给出了注释,如何使用自动确认和手动确认,消费者从队列中获取消息，服务端如何知道消息已经被消费呢？
模式1：自动确认
只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费。
模式2：手动确认
消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
如果选用自动确认,在消费者拿走消息执行过程中出现宕机时,消息可能就会丢失！！

 * @Package: com.sky.rabbitmq.demo2
 * @Description: ${TODO}
 * @author: sunzhg
 * @date: 2018/5/27 10:12
 */
public class Producer {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 50; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [生产者] Sent '" + message + "'");
            //发送的消息间隔越来越长
            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}