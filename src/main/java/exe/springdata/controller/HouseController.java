/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.controller;

/**
 *
 * @author root
 */
import exe.springdata.model.Block;
import exe.springdata.model.Booking;
import exe.springdata.service.HouseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseController {

    @Autowired
    HouseService service;

    //bookings
    @GetMapping("/api/booking/create")
    public String createBooking(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "iduser", required = true) long iduser,
            @RequestParam(value = "idhouse", required = true) long idhouse,
            @RequestParam(value = "datestart", required = true) String datestart,
            @RequestParam(value = "dateend", required = true) String dateend
    ) {
        return service.createBooking(id, iduser, idhouse, datestart, dateend);
    }

    @GetMapping("/api/user/bookings")
    public List<Booking> readBookingsByIduser(@RequestParam(value = "iduser", required = true) long iduser) {
        return service.readBookingsByIduser(iduser);
    }
    
    @GetMapping("/api/house/bookings")
    public List<Booking> readBookingsByIdhouse(@RequestParam(value = "idhouse", required = true) long idhouse) {
        return service.readBookingsByIdhouse(idhouse);
    }

    @GetMapping("/api/booking/update")
    public String updateBooking(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "iduser", required = true) long iduser,
            @RequestParam(value = "idhouse", required = true) long idhouse,
            @RequestParam(value = "datestart", required = true) String datestart,
            @RequestParam(value = "dateend", required = true) String dateend
    ) {
        return service.updateBooking(id, iduser, idhouse, datestart, dateend);
    }

    @GetMapping("/api/booking/delete")
    public String deleteBooking(
            @RequestParam(value = "id", required = true) long id
    ) {
        return service.deleteBooking(id);
    }

    //blocks
    @GetMapping("/api/block/create")
    public String createBlock(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "idhouse", required = true) long idhouse,
            @RequestParam(value = "datestart", required = true) String datestart,
            @RequestParam(value = "dateend", required = true) String dateend
    ) {
        return service.createBlock(id, idhouse, datestart, dateend);
    }

    @GetMapping("/api/user/blocks")
    public List<Block> readBlocksByIduser(@RequestParam(value = "iduser", required = true) long iduser) {
        return service.readBlocksByIduser(iduser);
    }

    @GetMapping("/api/house/blocks")
    public List<Block> readBlocksByIdhouse(@RequestParam(value = "idhouse", required = true) long idhouse) {
        return service.readBlocksByIdhouse(idhouse);
    }

    @GetMapping("/api/block/update")
    public String updateBlock(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "idhouse", required = true) long idhouse,
            @RequestParam(value = "datestart", required = true) String datestart,
            @RequestParam(value = "dateend", required = true) String dateend
    ) {
        return service.updateBlock(id, idhouse, datestart, dateend);
    }

    @GetMapping("/api/block/delete")
    public String deleteBlock(
            @RequestParam(value = "id", required = true) long id
    ) {
        return service.deleteBlock(id);
    }
}
