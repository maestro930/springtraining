package priv.rdo.sdaspring.homework.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomNumber;

    private Integer maxGuests;



    public Room() {
    }

    public Room(Long roomNumber, Integer maxGuests) {
        this.roomNumber = roomNumber;
        this.maxGuests = maxGuests;
    }

    public Long getId() {
        return id;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", maxGuests=" + maxGuests +
                '}';
    }
}
