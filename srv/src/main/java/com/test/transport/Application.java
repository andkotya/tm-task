package com.test.transport;

import com.sap.cds.services.runtime.CdsRuntime;
import com.sap.cds.services.runtime.ExtendedServiceLoader;
import com.test.transport.service.TransportationOrderHandler;
import com.sap.cds.services.Service;
import com.sap.cds.services.EventContext;

public class Application {
    public static void main(String[] args) {
        // Загружаем CdsRuntime через ExtendedServiceLoader
        CdsRuntime runtime = ExtendedServiceLoader.loadSingle(CdsRuntime.class);

        // Регистрируем обработчик как сервис через аннотации или конфигурацию
        runtime.requestContext();
                //start(); // Запускаем CAP Java runtime

        System.out.println("Starting CAP Java runtime...");
        try {
            synchronized (runtime) {
                runtime.wait(); // Ожидание событий/остановки
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Application interrupted: " + e.getMessage());
        }
    }
}




//package com.test.transport;
//
//// import com.sap.cds.services.runtime.ServiceBootstrap;
//// import com.sap.cds.feature.cds4j.Cds4jRuntime;
//import com.sap.cds.services.runtime.CdsRuntime;
//import com.test.transport.service.TransportationOrderHandler;
//
////import com.sap.cds.services.runtime.CdsRuntimeFactory;
//import com.sap.cds.services.runtime.ExtendedServiceLoader;
//
//public class Application {
//    public static void main(String[] args) {
//        CdsRuntime runtime = ExtendedServiceLoader.loadSingle(CdsRuntime.class);
//             //   CdsRuntime runtime = CdsRuntimeFactory.createRuntime();
//        runtime.requestContext();
//    //registerHandler(new TransportationOrderHandler());
//       // runtime.start();
//        System.out.println("Starting CAP Java runtime...");
//    }
//}