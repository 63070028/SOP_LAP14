package GreetingClient;

import com.proto.dummy.DummyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.proto.greet.*;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50055)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        // created a greet service client (blocking - synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetClient;
        greetClient = GreetServiceGrpc.newBlockingStub(channel);
// created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setNum1("1")
                .setNum2("2")
                .build();

// created a protocol buffer greetRequest message
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();
// call the RPC and get back a GreetResponse (Protocol Buffers)
        GreetResponse greetResponse = greetClient.greet(greetRequest);
// show the result in GreetResponse message
        System.out.println(greetResponse.getResult());

        System.out.println("S D C");
        channel.shutdown();
    }
}
