package com.example.projectuno.t;

import java.util.ArrayList;

public class MovieTheatre {

    private int total_rows;
    private int total_columns;
    private ArrayList<CinemaSeat> available_seats;

    public MovieTheatre(int total_rows, int total_columns, ArrayList<CinemaSeat> available_seats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public ArrayList<CinemaSeat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(ArrayList<CinemaSeat> available_seats) {
        this.available_seats = available_seats;
    }
}