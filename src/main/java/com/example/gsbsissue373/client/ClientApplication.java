package com.example.gsbsissue373.client;

import com.example.gsbsissue373.client.observer.MyServiceObserver;
import com.example.gsbsissue373.proto.MyServiceGrpc;
import com.example.gsbsissue373.proto.MyServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class ClientApplication {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch client = new CountDownLatch(1);
        MyServiceObserver observer = new MyServiceObserver(client);
        StreamObserver<MyServiceOuterClass.MyRequest> requestSender =
                MyServiceGrpc.newStub(createChannel()).streaming(observer);

        requestSender.onCompleted();
        client.await();
    }

    private static ManagedChannel createChannel() {
        return ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
    }


}
