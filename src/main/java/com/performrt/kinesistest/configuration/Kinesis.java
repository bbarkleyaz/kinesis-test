package com.performrt.kinesistest.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Component
public class Kinesis {

    @Value("test")
    private String accessKey;
    @Value("${aws.secret.key}")
    private String secretKey;

    public KinesisProducerConfiguration createProducerConfig() {
        System.out.println(accessKey);
        System.out.println(secretKey);
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        KinesisProducerConfiguration producerConfig = new KinesisProducerConfiguration()
                .setCredentialsProvider(new AWSStaticCredentialsProvider(awsCredentials))
                .setVerifyCertificate(false)
                .setRegion(Regions.US_WEST_2.getName());

        return producerConfig;
    }

    public void sendRecords() throws InterruptedException {
        KinesisProducer kinesisProducer = new KinesisProducer(createProducerConfig());
        while (true) {
            IntStream.range(1, 200).mapToObj(ipSuffix -> ByteBuffer.wrap(("192.168.0." + ipSuffix).getBytes()))
                    .forEach(entry -> kinesisProducer.addUserRecord("Test", "test", entry));
            Thread.sleep(10000);
        }
    }

}
