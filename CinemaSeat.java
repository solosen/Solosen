package com.example.projectuno.t;

public class CinemaSeat implements Comparable<CinemaSeat> {

    private int row;
    private int column;
    private int price;
    private boolean available;

    public CinemaSeat(int row, int column) {
        this.row = row;
        this.column = column;
        if (row <= 4) {
            this.price = 10;
        } else this.price = 8;
        this.available = true;
    }

    @Override
    public int compareTo(CinemaSeat o) {
        return this.row == o.getRow() ? this.column - o.getColumn() : this.row - o.getRow();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    boolean isAvailable() {
        return available;
    }
}