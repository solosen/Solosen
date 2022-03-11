package com.example.projectuno.t;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SeatsController {

    private final int numberOfRows = 9;
    private final int numberOfColumns = 9;

    MovieTheatre changeableTheatre = new MovieTheatre(numberOfRows, numberOfColumns, markAllSeatsAvailable());

    public List<PurchasedTicket> list = new ArrayList<>();

    public Statistics statistics = new Statistics(0, 81, 0);

    public final String outOfBoundsError = "The number of a row or a column is out of bounds!";
    public final String alreadyPurchasedError = "The ticket has been already purchased!";
    public final String wrongTokenError = "Wrong token!";
    public final String wrongPassword = "The password is wrong!";
    public final String correctPassword = "super_secret";

    @GetMapping("/seats")
    public MovieTheatre getCinemaRoom() {
        return this.changeableTheatre;
    }

    private ArrayList<CinemaSeat> markAllSeatsAvailable() {
        ArrayList<CinemaSeat> availableSeats = new ArrayList<>();
        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= numberOfColumns; j++) {
                availableSeats.add(new CinemaSeat(i, j));
            }
        }
        return availableSeats;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchasedTicket> purchase(@RequestBody Seat seat) {

        int row = seat.getRow();
        int column = seat.getColumn();

        boolean flag = false;

        if (!(row <= numberOfRows && column <= numberOfColumns && row > 0 && column > 0)) {
            return new ResponseEntity(Map.of("error", outOfBoundsError), HttpStatus.BAD_REQUEST);
        } else for (int i = 0; i < changeableTheatre.getAvailable_seats().size(); i++) {
            if (changeableTheatre.getAvailable_seats().get(i).getRow() == row && changeableTheatre.getAvailable_seats().get(i).getColumn() == column) {
                flag = true;
                changeableTheatre.getAvailable_seats().remove(i);
            }
        }

        if (!flag) {
            return new ResponseEntity(Map.of("error", alreadyPurchasedError), HttpStatus.BAD_REQUEST);
        } else {
            PurchasedTicket ticket = new PurchasedTicket(new CinemaSeat(row, column));
            list.add(ticket);
            statistics.setCurrent_income(statistics.getCurrent_income() + ticket.getTicket().getPrice());
            statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() - 1);
            statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() + 1);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }

    @PostMapping("/return")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ToReturn> returnMethod(@RequestBody String token) {
        boolean flag = false;
        CinemaSeat seat = null;
        String pureToken = token.split("\"")[3];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getToken().equals(pureToken)) {
                flag = true;
                seat = new CinemaSeat(list.get(i).getTicket().getRow(), list.get(i).getTicket().getColumn());
                changeableTheatre.getAvailable_seats().add(seat);
                statistics.setCurrent_income(statistics.getCurrent_income() - list.get(i).getTicket().getPrice());
                statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() + 1);
                statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() - 1);
                list.remove(i);
                break;
            }
        }
        if (flag) {
            return new ResponseEntity<>(new ToReturn(seat), HttpStatus.OK);
        } else return new ResponseEntity(Map.of("error", wrongTokenError), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Statistics> statistics(@RequestParam(required = false) String password) {
        if (password == null || !password.equals(correctPassword)) {
            return new ResponseEntity(Map.of("error", wrongPassword), HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}