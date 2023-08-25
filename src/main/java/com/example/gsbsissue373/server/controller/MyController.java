package com.example.gsbsissue373.server.controller;

import com.example.gsbsissue373.proto.MyServiceGrpc;
import com.example.gsbsissue373.proto.MyServiceOuterClass;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class MyController extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public StreamObserver<MyServiceOuterClass.MyRequest> streaming(StreamObserver<MyServiceOuterClass.MyResponse> responseObserver) {
        return new StreamObserver<MyServiceOuterClass.MyRequest>() {

            @Override
            public void onCompleted() {
                responseObserver.onError(
                        Status.NOT_FOUND
                                .withCause(new Exception("some exception"))
                                .asRuntimeException());
            }

            @Override
            public void onNext(MyServiceOuterClass.MyRequest myRequest) {}

            @Override
            public void onError(Throwable throwable) {}

        };
    }
}
