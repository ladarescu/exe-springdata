/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.wrk;

import exe.springdata.Util;
import exe.springdata.model.Block;
import static exe.springdata.wrk.BookingWorker.getBookingAvailable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO refactor and group sql

public class BlockWorker {

    public static String createBlock(long id, long idhouse, String datestart, String dateend) {

        String savailable = getBookingAvailable(datestart, dateend, idhouse);

        if (!savailable.startsWith("AVAILABLE")) {
            return savailable;
        } else {
            SimpleDateFormat sdf = Util.simpledateformat;
            String screate = "INSERT INTO block(id, idhouse, dateop, datestart, dateend) "
                    + "VALUES ("
                    + id + ","
                    + idhouse + ","
                    + "'" + sdf.format(new Date()) + "',"
                    + "'" + datestart + "',"
                    + "'" + dateend + "');";

            return Util.executeSQL(screate, "Block create ");
        }
    }

    public static List<Block> readBlocksByIduser(long iduser) {
        Connection conn = Util.getConnection();
        List<Block> blocks = new ArrayList<>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("select * from block where iduser = ?");
                stmt.setLong(1, iduser);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Block block = new Block(rs);
                    blocks.add(block);
                }
                System.out.println("Resultset " + blocks.size() + ", with iduser=" + iduser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Util.closeConnection(conn);
        }
        return blocks;
    }

    public static String updateBlock(long id, long idhouse, String datestart, String dateend) {
        SimpleDateFormat sdf = Util.simpledateformat;

        String supdate = "Update block set "
                + "idhouse = " + idhouse + ","
                + "dateop = " + "'" + sdf.format(new Date()) + "',"
                + "datestart = " + "'" + datestart + "',"
                + "dateend = " + "'" + dateend + "' "
                + " where id = " + id;

        return Util.executeSQL(supdate, "Block update ");
    }

    public static String deleteBlock(long id) {

        String sdel = "DELETE FROM block where id = "+id;
        return Util.executeSQL(sdel, "Block delete ");
    }

    public static List<Block> readBlocksByIdhouse(long idhouse) {
        Connection conn = Util.getConnection();
        List<Block> blocks = new ArrayList<>();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("select * from block where idhouse = ?");
                stmt.setLong(1, idhouse);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Block block = new Block(rs);
                    blocks.add(block);
                }
                System.out.println("Resultset " + blocks.size() + ", with idhouse=" + idhouse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Util.closeConnection(conn);
        }
        return blocks;
    }

    public static String hasBlock(String sdatestart, String sdateend, long idhouse) {

        List<Block> blocks = readBlocksByIdhouse(idhouse);

        SimpleDateFormat sdf = Util.simpledateformat;
        try {
            Date datefrom = sdf.parse(sdatestart);
            Date dateto = sdf.parse(sdateend);
            for (Block block : blocks) {
                if (!block.isAvailable(datefrom, dateto)) {
                    return "BLOCK FOUND";
                }
            }
            return "AVAILABLE";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
