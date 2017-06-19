package PathAndHash;

import java.util.HashMap;


/*
 * Copyright (C) 2017 Pavlos Siaperas.
 *
 */

/**
 *
 * @author Pavlos Siaperas
 */
public class Integration {

    public Integration() {

    }
    
    //Integrate all the code of the previous challenges. Test code has been created for this class
    public static void integration(int number_zeros, int start_x, int start_y, int end_x, int end_y, String directory) {
        Graph p2 = new Graph();
        HashMap<Coords, String> map = p2.parsing(directory);
        String hash_input = p2.pathfinding(map, new Coords(start_x, start_y), new Coords(end_x, end_y));
        System.out.println();
        HashCollision hc = new HashCollision();
        hc.collision(hash_input, number_zeros);
    }
}
