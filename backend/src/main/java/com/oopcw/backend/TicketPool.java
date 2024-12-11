package com.oopcw.backend;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class TicketPool {

    private final TicketRepository ticketRepository;
    private final Queue<Long> ticketQueue = new LinkedList<>();
    private int maxCapacity;

    public TicketPool(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public synchronized void configureMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(int vendorId) throws InterruptedException {
        while (ticketQueue.size() >= maxCapacity) {
            System.out.println("Ticket pool is full. Vendor-" + vendorId + " is waiting...");
            wait();
        }
        Ticket ticket = ticketRepository.save(new Ticket());
        ticketQueue.add(ticket.getId());
        System.out.println("Vendor-" + vendorId + " added: Ticket-" + ticket.getId());
        notifyAll();
    }

    public synchronized Long removeTicket(int customerId) throws InterruptedException {
        while (ticketQueue.isEmpty()) {
            System.out.println("No tickets available. Customer-" + customerId + " is waiting...");
            wait();
        }
        Long ticketId = ticketQueue.poll();
        System.out.println("Customer-" + customerId + " purchased: Ticket-" + ticketId);
        notifyAll();
        return ticketId;
    }
}
