package priv.rdo.sdaspring.homework.service;

import org.springframework.stereotype.Service;
import priv.rdo.sdaspring.homework.model.Hotel;
import priv.rdo.sdaspring.homework.model.Room;
import priv.rdo.sdaspring.homework.repo.HotelRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @PostConstruct
    void init() {//do NOT do that in real code ; )
        hotelRepository.save(new Hotel("Azor", "addr", Collections.singletonList(new Room(3L, 2))));
        hotelRepository.save(new Hotel("Bzor", "addr", Collections.singletonList(new Room(3L, 8))));
        hotelRepository.save(new Hotel("Czor", "addr", Collections.singletonList(new Room(3L, 5))));
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findOne(long id) {
        return hotelRepository.findOne(id);
    }

    public List<Hotel> findWithMinimumPeopleInRoom(int minPeople) {
        return hotelRepository.findWithMinPeopleCount(minPeople);
    }

    public List<Hotel> findByNameContaining(String name) {
        return hotelRepository.findByNameContaining(name);
    }
}
