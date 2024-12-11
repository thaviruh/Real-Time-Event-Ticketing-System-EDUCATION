package com.oopcw.backend;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TicketingService {

    private TicketingConfig config;
    private final TicketPool ticketPool;
    private ExecutorService vendorExecutor;
    private ExecutorService customerExecutor;

    public TicketingService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public synchronized void configureSystem(TicketingConfig config) {
        this.config = config;
        ticketPool.configureMaxCapacity(config.getMaxTicketCapacity());
    }

    public void startSystem() {
        if (config == null) {
            throw new IllegalStateException("System not configured!");
        }

        vendorExecutor = Executors.newFixedThreadPool(5);
        customerExecutor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            vendorExecutor.submit(new Vendor(i, ticketPool, config.getTicketReleaseRate(), config.getTotalTickets() / 5));
            customerExecutor.submit(new Customer(i, ticketPool, config.getCustomerRetrievalRate()));
        }
    }

    public void stopSystem() {
        if (vendorExecutor != null) vendorExecutor.shutdownNow();
        if (customerExecutor != null) customerExecutor.shutdownNow();
        System.out.println("Simulation stopped.");
    }
}

