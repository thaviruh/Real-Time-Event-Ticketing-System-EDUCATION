package com.oopcw.backend;

public class Customer implements Runnable {

    private final int customerId; // Customer identifier (1-5)
    private final TicketPool ticketPool;
    private final int retrievalRate; // Tickets per second

    public Customer(int customerId, TicketPool ticketPool, int retrievalRate) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketPool.removeTicket(customerId); // Purchase a ticket from the pool
                Thread.sleep((long) (Math.random() * (1000 / retrievalRate))); // Randomized delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer-" + customerId + " was interrupted.");
        }
    }
}
