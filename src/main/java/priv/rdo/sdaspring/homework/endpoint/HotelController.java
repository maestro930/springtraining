package priv.rdo.sdaspring.homework.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import priv.rdo.sdaspring.homework.model.Hotel;
import priv.rdo.sdaspring.homework.service.HotelService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Hotel hotel) {
        Hotel save = hotelService.createHotel(hotel);

        return ResponseEntity.created(pathWithId(save.getId())).build();
    }

    @GetMapping
    public List<Hotel> listAllHotels() {
        return hotelService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Hotel> findOne(@PathVariable long id) {
        Hotel found = hotelService.findOne(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("searchByMinPeople")
    public ResponseEntity<List<Hotel>> findWithMinPeople(@RequestParam Integer minPeople) {
        List<Hotel> found = hotelService.findWithMinimumPeopleInRoom(minPeople);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("searchByName")
    public ResponseEntity<List<Hotel>> findWithName(@RequestParam String name) {
        List<Hotel> found = hotelService.findByNameContaining(name);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(found);
    }

    //utility, normally would be in a different class
    //option 1
    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }

    //option 2
    private URI pathWithId2(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.toString())
                .toUri();
    }

    //option 3
    private URI pathWithId3(Object id) {
        return MvcUriComponentsBuilder
                .fromMethodName(HotelController.class, "findOne", id)
                .buildAndExpand(id)
                .toUri();
    }
}
