package model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomTypeEnum;


    public Room(String roomNumber,Double price, RoomType roomTypeEnum)
    {
        this.price = price;
        this.roomNumber = roomNumber;
        this.roomTypeEnum = roomTypeEnum;
    }

    @Override
    public String toString() {
        return "Room{" +" roomNumber='" + roomNumber +
                ",price=" + price +
                 '\'' +
                ", roomTypeEnum=" + roomTypeEnum +
                '}';
    }

    @Override
    public String getRoomNumber()
    {


        return roomNumber;
    }

    @Override
    public Double getRoomPrice()
    {


        return price;
    }

    @Override
    public RoomType getRoomType()
    {


        return roomTypeEnum;
    }

    @Override
    public boolean isFree()
    {
        return price == 0;

    }

    public void setPrice(Double price)
    {

        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomNumber);
    }
}
