package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType roomTypeEnum) {
        super(roomNumber, 0.0, roomTypeEnum);


    }

    @Override
    public String toString() {
        return "FreeRoom{}";
    }
}
