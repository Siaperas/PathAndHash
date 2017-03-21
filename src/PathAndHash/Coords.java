/*
 * Copyright (C) 2017 Pavlos Siaperas.
 *
*/
package PathAndHash;

/**
 *
 * @author Pavlos Siaperas
 */
public class Coords {

    int x;
    int y;
    
    //constructos
    public Coords(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.x == x && c.y == y;
    }
    
    public int hashCode() {
        return new Integer(x + "0" + y);

    }
    
    //accessors
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
