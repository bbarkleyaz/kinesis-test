package com.performrt.kinesistest;

import com.performrt.kinesistest.configuration.Kinesis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinesisTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(KinesisTestApplication.class, args);

        /*
        Kinesis kinesis = new Kinesis();
        try {
            kinesis.sendRecords();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

         */
    }


}
