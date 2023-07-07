/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.wrk;

import exe.springdata.Util;
import exe.springdata.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class BookingWorker {

    public static String createBooking(long id, long iduser, long idhouse, String datestart, String dateend) {

        String savailable = getBookingAvailable(datestart, dateend, idhouse);
        String shasblock = BlockWorker.hasBlock(datestart, dateend, idhouse);

        if (!shasblock.startsWith("AVAILABLE")) {
            return shasblock;
        } else {
            if (!savailable.startsWith("AVAILABLE")) {
                return savailable;
            } else {
                SimpleDateFormat sdf = Util.simpledateformat;

                String screate = "INSERT INTO booking(id, iduser, idhouse, dateop, datestart, dateend) "
                        + "VALUES ("
                        + id + ","
                        + iduser + ","
                        + idhouse + ","
                        + "'" + sdf.format(new Date()) + "',"
                        + "'" + datestart + "',"
                        + "'" + dateend + "');";

                return Util.executeSQL(screate, "Booking create ");
            }
        }
    }

    public static List<Booking> readBookingsByIduser(long iduser) {
        Connection conn = Util.getConnection();
        List<Booking> bookings = new ArrayList<>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("select * from booking where iduser = ?");
                stmt.setLong(1, iduser);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Booking booking = new Booking(rs);
                    bookings.add(booking);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Util.closeConnection(conn);
        }
        return bookings;
    }

    public static String updateBooking(long id, long iduser, long idhouse, String datestart, String dateend) {

        SimpleDateFormat sdf = Util.simpledateformat;

        String supdate = "Update booking set "
                + "iduser = " + iduser + ","
                + "idhouse = " + idhouse + ","
                + "dateop = " + "'" + sdf.format(new Date()) + "',"
                + "datestart = " + "'" + datestart + "',"
                + "dateend = " + "'" + dateend + "' "
                + " where id = " + id;

        return Util.executeSQL(supdate, "Booking update ");
    }

    public static String deleteBooking(long id) {

        String sdel = "DELETE FROM booking where id = " + id;
        return Util.executeSQL(sdel, "Booking delete ");
    }

    public static List<Booking> readBookingsByIdhouse(long idhouse) {
        Connection conn = Util.getConnection();
        List<Booking> bookings = new ArrayList<>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("select * from booking where idhouse = ?");
                stmt.setLong(1, idhouse);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Booking booking = new Booking(rs);
                    bookings.add(booking);
                }
                System.out.println("Resultset " + bookings.size() + ", with iduser=" + idhouse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Util.closeConnection(conn);
        }
        return bookings;
    }

    public static String getBookingAvailable(String sdatestart, String sdateend, long idhouse) {

        List<Booking> bookings = readBookingsByIdhouse(idhouse);

        SimpleDateFormat sdf = Util.simpledateformat;
        try {
            Date datefrom = sdf.parse(sdatestart);
            Date dateto = sdf.parse(sdateend);
            for (Booking booking : bookings) {
                if (!booking.isAvalilable(datefrom, dateto)) {
                    return "house: " + idhouse + " NOT AVAILABLE from " + booking.getDatestart() + " to: " + booking.getDateend();
                }
            }
            return "AVAILABLE house: " + idhouse + " from " + sdatestart + " to: " + sdateend;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
