package com.example.demo23.server;



import com.example.demo23.service.BookServiceImpl;

import com.example.demo23.thrift.BookService;
import lombok.AllArgsConstructor;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class Server {


    private  BookServiceImpl bookService;

    public void startServer() {

        TProcessor processor = new BookService.Processor<BookService.Iface>(bookService);

        TServerSocket serverSocket = null;
        try {
            serverSocket = new TServerSocket(8282);
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        TServer.Args simpleArgs = new TServer.Args(serverSocket).processor(processor).protocolFactory(protocolFactory);
        TServer server = new TSimpleServer(simpleArgs);
        System.out.println("Open port");
        server.serve();

    }
}
