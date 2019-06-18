package by.dorozhuk.rwmvc.service;

import by.dorozhuk.rwmvc.entity.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketService {

    void add(Ticket ticket);

    void delete(Ticket ticket);

    void update(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Long id);

}
