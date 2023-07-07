/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.model;

import exe.springdata.Util;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root
 */
public class Block {
     private long id;
    private long idhouse;
    private Date dateop = null;
    private Date datestart = null;
    private Date dateend = null;

    public Block(ResultSet rs) {
        try {
            SimpleDateFormat sdf = Util.simpledateformat;
            this.idhouse = rs.getLong("idhouse");
            this.id = rs.getLong("id");
            this.dateop = sdf.parse(rs.getString("dateop"));
            this.datestart = sdf.parse(rs.getString("datestart"));
            this.dateend = sdf.parse(rs.getString("dateend"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        };
    }

    public long getHouse() {
        return this.idhouse;
    }

    public void setHouse(long idhouse) {
        this.idhouse = idhouse;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateop(Date date) {
        this.dateop = date;
    }

    public Date getDateop() {
        return this.dateop;
    }

    public void setDatestart(Date date) {
        this.datestart = date;
    }

    public Date getDatestart() {
        return this.datestart;
    }

    public void setDateend(Date date) {
        this.dateend = date;
    }

    public Date getDateend() {
        return this.dateend;
    }

    public boolean isAvailable(Date datefrom, Date dateto) {
        
        if (datefrom.compareTo(dateend) >= 0) {
            return true;
        }
        if (dateto.compareTo(datestart) <= 0) {
            return true;
        }

        return false;
    }
}