package com.example.ticket.repository;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t " +
            "where t.user_id = :id and t.state != 'CANCEL'")
    List<Ticket> findTicketsByUserId(@Param("id") UUID userId);


    @Query("select t from Ticket t " +
            "where t.user_id = :id and t.state = :cancel")
    List<Ticket> findCancelTicketsByUserId(@Param("id") UUID userId, @Param("cancel") State cancel);




    @Query("UPDATE Ticket t SET t.state = :newState WHERE t.user_id = :userId AND t.id = :ticketId")
    @Modifying
    @Transactional
    void ticketStateUpdate(@Param("userId") UUID userId, @Param("ticketId") Long ticketId, @Param("newState") State newState);


    @Query("select t from Ticket t " +
            "where t.bus_id = :busId and t.seat_number = :seatNumber")
    Optional<Ticket> findTicketExist(@Param("busId") Long busId, @Param("seatNumber") Integer seatNumber);
}
