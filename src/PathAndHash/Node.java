/*
 * Copyright (C) 2017 Pavlos Siaperas.
 */
package PathAndHash;

/**
 *
 * @author Pavlos Siaperas
 */
public class Node {

    int x;
    int y;
    int dist;

    //constructor
    public Node(int x, int y, int dist) {
        super();
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    public boolean equals(Object o) {
        Node c = (Node) o;
        return c.x == x && c.y == y && c.dist==dist;
    }
    
    public boolean exists(int x, int y) {
        return this.x == x && this.y == y ;
    }

    //accessors
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return dist;
    }
    
    public int getDistance(int x, int y){
        return dist;
    }
}
