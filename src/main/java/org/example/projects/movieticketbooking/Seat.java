package org.example.projects.movieticketbooking;

enum SeatType{
    REGULAR, PREMIUM
}

enum SeatStatus{
    AVAILABLE, BOOKED
}
public class Seat {
    private String id;
    private int row;
    private int col;
    private SeatStatus status;
    private SeatType type;
    private double price;

    public Seat(int row, int col, SeatType type,
                double price) {
        this.id = row + "-" + col;
        this.row = row;
        this.col = col;
        this.status = SeatStatus.AVAILABLE;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
