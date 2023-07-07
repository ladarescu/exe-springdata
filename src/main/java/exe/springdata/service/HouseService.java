/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.service;

/**
 *
 * @author root
 */
import exe.springdata.model.Booking;
import exe.springdata.model.Block;
import exe.springdata.wrk.BlockWorker;
import exe.springdata.wrk.BookingWorker;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    //required
    public String createBooking(long id, long iduser, long idhouse, String datestart, String dateend) {
        return BookingWorker.createBooking(id, iduser, idhouse, datestart, dateend);
    }

    public List<Booking> readBookingsByIduser(long iduser) {
        return BookingWorker.readBookingsByIduser(iduser);
    }

    public List<Booking> readBookingsByIdhouse(long idhouse) {
        return BookingWorker.readBookingsByIdhouse(idhouse);
    }

    public String updateBooking(long id, long iduser, long idhouse, String datestart, String dateend) {
        return BookingWorker.updateBooking(id, iduser, idhouse, datestart, dateend);
    }

    public String deleteBooking(long id) {
        return BookingWorker.deleteBooking(id);
    }

    // extended
    public String createBlock(long id, long idhouse, String datestart, String dateend) {
        return BlockWorker.createBlock(id, idhouse, datestart, dateend);
    }

    public List<Block> readBlocksByIduser(long iduser) {
        return BlockWorker.readBlocksByIduser(iduser);
    }

    public List<Block> readBlocksByIdhouse(long idhouse) {
        return BlockWorker.readBlocksByIdhouse(idhouse);
    }

    public String updateBlock(long id, long idhouse, String datestart, String dateend) {
        return BlockWorker.updateBlock(id, idhouse, datestart, dateend);
    }

    public String deleteBlock(long id) {
        return BlockWorker.deleteBlock(id);
    }

}
