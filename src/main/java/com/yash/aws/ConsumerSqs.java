package com.yash.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class ConsumerSqs {
    public static void main(String[] args){
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        // receiving messages from queue
        System.out.println("Receiving messages from vidyak\n");
        String myQueue = "https://sqs.us-east-1.amazonaws.com/094853031708/vidyak";
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueue);
        int ans = 0;
        while(true){
            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            if(messages.size() == 0)
                break;
            for(Message message: messages) {
                System.out.println(message.getBody());
                try {
                    ans += Integer.parseInt(message.getBody());
                }
                catch (NumberFormatException e){
                    continue;
                }
            }
        }
        System.out.println(ans);
    }
}
