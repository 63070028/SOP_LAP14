package GreetingServer;

import com.proto.greet.*;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase{
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse>
            responseObserver) {
        // Block 1: extract the data required
        Greeting greeting = request.getGreeting();
        String num1 = greeting.getNum1();
        String num2 = greeting.getNum2();
        // Block 2: create the response message
        System.out.println("Server Output = " + num1 + "+" + num2 + "=" +String.valueOf(Integer.valueOf(num1)+Integer.valueOf(num2)));
        String result = String.valueOf(Integer.valueOf(num1)+Integer.valueOf(num2));
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();
        // Block 3: send the response
        responseObserver.onNext(response);
    // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }

}
