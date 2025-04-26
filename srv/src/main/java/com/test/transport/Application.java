package com.test.transport;

import com.sap.cds.CdsCore;
import com.sap.cds.services.runtime.CdsRuntime;
import com.sap.cds.services.runtime.ServiceBootstrap;
import com.test.transport.service.TransportationOrderHandler;

public class Application {
    public static void main(String[] args) {
        CdsRuntime runtime = ServiceBootstrap.createRuntime();
        runtime.registerHandler(new TransportationOrderHandler());
        runtime.start();
        System.out.println("Starting CAP Java runtime...");
    }
}

