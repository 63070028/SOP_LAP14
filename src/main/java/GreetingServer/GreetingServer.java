package GreetingServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) {
        System.out.println("Hello gRPC");
        Server server = ServerBuilder.forPort(50055).addService(new GreetServiceImpl()).build();

        try {
            server.start();
            System.out.println("Server Start");
        }catch (IOException e){
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("R S R");
            server.shutdown();
            System.out.println("S S S");
        }));
        try {
            server.awaitTermination();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
