/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author peter
 */
public class AnimalNoDb {
    
    String type;
    String sounds;

    public AnimalNoDb(String type, String sounds) {
        this.type = type;
        this.sounds = sounds;
    }

    public AnimalNoDb() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSounds() {
        return sounds;
    }

    public void setSounds(String sounds) {
        this.sounds = sounds;
    }
    
    
    
}
