/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata.model;

/**
 *
 * @author root
 */
public class House {
    private long id;
    private long idowner;

    public long getOwner() {
        return this.idowner;
    }

    public void setOwner(long idowner) {
        this.idowner = idowner;
    }

    public long getId() {
        return this.id;
    }
}
