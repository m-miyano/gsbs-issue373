package com.example.gsbsissue373.client.observer;

import com.example.gsbsissue373.proto.MyServiceOuterClass;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CountDownLatch;

@RequiredArgsConstructor
public class MyServiceObserver implements StreamObserver<MyServiceOuterClass.MyResponse> {

    private final CountDownLatch client;

    @Override
    public void onNext(MyServiceOuterClass.MyResponse value) {}

    @Override
    public void onError(Throwable t) {
        System.out.println("onError");
        if (t instanceof StatusRuntimeException) {
            System.out.println(((StatusRuntimeException) t).getStatus());
        }
        t.printStackTrace();
        client.countDown();
    }

    @Override
    public void onCompleted() {}
}
