/*
 * Copyright (C) 2017 Pavlos Siaperas.
 */
package PathAndHash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
 * @author Pavlos Siaperas
 */
public class Graph {

    public Graph() {

    }

    //Parsing Function
    public HashMap<Coords, String> parsing(String directory) {
        //initialize variables to be used
        File folder = new File(directory + "/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> arrlist = new ArrayList<String>();
        HashMap<Coords, String> map = new HashMap<Coords, String>();

        //read all the files in the specified directory
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    String content = new Scanner(new File("graph/" + file.getName())).useDelimiter("\\Z").next();
                    //add the content in an arraylist
                    ArrayList<String> arrlist2 = new ArrayList<String>(Arrays.asList(content.split(",")));
                    arrlist.addAll(arrlist2);

                } catch (FileNotFoundException e) {

                }
            }
        }
        //initialize max_x and max_y to find the outer limits of the graph
        int max_x = Integer.MIN_VALUE;
        int max_y = Integer.MIN_VALUE;
        for (int i = 0; i < arrlist.size(); i++) {
            //manipulate the string values to find the coordinates
            String value = arrlist.get(i);
            value = value.replace("\n", "");
            value = value.replace("x", "");
            String[] parts = value.split("y");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            if (x > max_x) {
                max_x = x;
            }
            if (y > max_y) {
                max_y = y;
            }
            //insert in the map the walls of the graph according to their coordinates
            map.put(new Coords(x, y), "X");

        }
        //insert the empty parts of the graph int the map
        for (int i = 0; i <= max_y; i++) {

            for (int j = 0; j <= max_x; j++) {
                if (map.get(new Coords(j, i)) == null) {
                    map.put(new Coords(j, i), ".");
                }
            }
        }
        return map;
    }

    //Distance between points
    public String pathfinding(HashMap<Coords, String> map, Coords start, Coords end) {
        //insert in the map the start and end points
        map.put(start, "S");
        map.put(end, "E");
        //find the max limits
        int max_x = Integer.MIN_VALUE;
        int max_y = Integer.MIN_VALUE;

        for (Coords key : map.keySet()) {

            if (key.getX() > max_x) {
                max_x = key.getX();
            }
            if (key.getY() > max_y) {
                max_y = key.getY();
            }

        }
        //create a list of nodes tha contain the coordinates and the distance from start
        ArrayList<Node> arrlist = new ArrayList<Node>();
        arrlist.add(new Node(start.getX(), start.getY(), 0));
        int counter = 0;
        //variable to denote if the variable has been found
        boolean found = false;
        //in case the end point can't be reached
        boolean nonewvalues = false;
        while (!found && !nonewvalues) {
            for (int i = 0; i < arrlist.size(); i++) {
                //find distances until finding the end point
                Node node = arrlist.get(i);
                int distance = node.getDistance();
                int x = node.getX();
                int y = node.getY();
                if (distance == counter) {
                    if (map.get(new Coords(x + 1, y)).equals(".") || map.get(new Coords(x + 1, y)).equals("E")) {
                        //add to arraylist and to map the found info
                        arrlist.add(new Node(x + 1, y, counter + 1));
                        map.put(new Coords(x + 1, y), Integer.toString(counter + 1));

                    }
                    if (map.get(new Coords(x, y + 1)).equals(".") || map.get(new Coords(x, y + 1)).equals("E")) {
                        //add to arraylist and to map the found info
                        arrlist.add(new Node(x, y + 1, counter + 1));
                        map.put(new Coords(x, y + 1), Integer.toString(counter + 1));

                    }
                    if (map.get(new Coords(x - 1, y)).equals(".") || map.get(new Coords(x - 1, y)).equals("E")) {
                        //add to arraylist and to map the found info
                        arrlist.add(new Node(x - 1, y, counter + 1));
                        map.put(new Coords(x - 1, y), Integer.toString(counter + 1));

                    }
                    if (map.get(new Coords(x, y - 1)).equals(".") || map.get(new Coords(x, y - 1)).equals("E")) {
                        //add to arraylist and to map the found info
                        arrlist.add(new Node(x, y - 1, counter + 1));
                        map.put(new Coords(x, y - 1), Integer.toString(counter + 1));

                    }
                }

            }
            nonewvalues = true;
            //if there are no new nodes end the loop
            for (int i = 0; i < arrlist.size(); i++) {
                if (arrlist.get(i).getDistance() == counter + 1) {
                    nonewvalues = false;
                }
            }
            //if end point is found end the loop
            if (arrlist.contains(new Node(end.getX(), end.getY(), counter + 1))) {
                found = true;
            }
            //increase the current distance
            counter++;
        }
        //initialize the input desired for part 4
        String hash_input = "";
        int current_x = end.getX();
        int current_y = end.getY();
        int current_distance = counter;
        //repeat until start is found
        while (current_x != start.getX() || current_y != start.getY()) {
            //add coordinates to the hash_input mentioned in part4       
            hash_input = hash_in(hash_input, current_y);
            hash_input = hash_in(hash_input, current_x);

            int y_to_check = current_y;
            int x_to_check = current_x;
            //variable to stop the array list loop if all four adjacent nodes are checked
            int position_checked = 0;
            //loop that finds the adjacent node with the smallest distance from end node to start
            for (int i = arrlist.size() - 1; i >= 0; i--) {
                if (arrlist.get(i).getX() == x_to_check - 1 && arrlist.get(i).getY() == y_to_check) {
                    if (arrlist.get(i).getDistance() < current_distance) {
                        current_distance = arrlist.get(i).getDistance();
                        current_x = arrlist.get(i).getX();
                        current_y = arrlist.get(i).getY();
                    }
                    arrlist.remove(i);

                    position_checked++;
                }
                if (arrlist.get(i).getX() == x_to_check && arrlist.get(i).getY() == y_to_check - 1) {
                    if (arrlist.get(i).getDistance() < current_distance) {
                        current_distance = arrlist.get(i).getDistance();
                        current_x = arrlist.get(i).getX();
                        current_y = arrlist.get(i).getY();
                    }
                    arrlist.remove(i);

                    position_checked++;
                }
                if (arrlist.get(i).getX() == x_to_check + 1 && arrlist.get(i).getY() == y_to_check) {
                    if (arrlist.get(i).getDistance() < current_distance) {
                        current_distance = arrlist.get(i).getDistance();
                        current_x = arrlist.get(i).getX();
                        current_y = arrlist.get(i).getY();
                    }
                    arrlist.remove(i);
                    position_checked++;
                }

                if (arrlist.get(i).getX() == x_to_check && arrlist.get(i).getY() == y_to_check + 1) {
                    if (arrlist.get(i).getDistance() < current_distance) {
                        current_distance = arrlist.get(i).getDistance();
                        current_x = arrlist.get(i).getX();
                        current_y = arrlist.get(i).getY();
                    }
                    arrlist.remove(i);

                    position_checked++;
                }
                //break loop if all four node have already been checked
                if (position_checked == 4) {
                    break;
                }
            }
            if (!((current_x == start.getX() && current_y == start.getY()))) {

                map.put(new Coords(current_x, current_y), "O");
            } else {
                hash_input = hash_in(hash_input, current_y);
                hash_input = hash_in(hash_input, current_x);
            }
        }
        //output message if there is no path
        if (!found && nonewvalues) {
            System.out.println("The end point could not be reached!");
        }
        //add the end point in the amp
        map.put(new Coords(end.getX(), end.getY()), "E");

        //output the map
        for (int i = 0; i <= max_y; i++) {
            for (int j = 0; j <= max_x; j++) {
                if ("X".equals(map.get(new Coords(j, i))) || "E".equals(map.get(new Coords(j, i))) || "S".equals(map.get(new Coords(j, i))) || "O".equals(map.get(new Coords(j, i))) || ".".equals(map.get(new Coords(j, i)))) {
                    System.out.print(map.get(new Coords(j, i)));
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }

        return hash_input;
    }

    //method to build hash_function
    private String hash_in(String hash_input, int current) {
        if (current < 10) {
            hash_input = "0" + current + hash_input;
        } else {
            hash_input = current + hash_input;

        }
        return hash_input;
    }
}
