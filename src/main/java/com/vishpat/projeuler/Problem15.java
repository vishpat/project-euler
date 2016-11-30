package com.vishpat.projeuler;

import java.util.*;

/**
 * Created by vpati011 on 11/29/16.
 */



public class Problem15 {

    private final int COLS = 7;
    private final int ROWS = 7;

    public Problem15() {

    }


    public void solve() {

        Path path = new Path();
        path.append(new Coordinate(0,0));

        Set<Path> pathSet = new HashSet<>();
        pathSet.add(path);

        while (true) {
            final Set<Path> newPaths = new HashSet<>();
            pathSet.stream().forEach((path1) -> {

                int size = path1.elements.size();
                Coordinate lastElement = path1.elements.get(size - 1);
                List<Coordinate> neigbours = lastElement.getNeigbours();
                neigbours.stream().forEach((coordinate) -> {
                    Path newPath = new Path(path1);
                    newPath.append(coordinate);
                    newPaths.add(newPath);
                });

            }
            );

            if (newPaths.isEmpty()) {
                break;
            }
            pathSet = newPaths;
        }

        System.out.println(pathSet.size());
    }


    class Path {
        private List<Coordinate> elements;

        Path() {
            elements = new LinkedList<>();
        }

        Path(Path path) {
            elements = new LinkedList<>(path.elements);
            Collections.copy(elements, path.elements);
        }

        void append(final Coordinate coordinate) {
            elements.add(coordinate);
        }

        List<Coordinate> getPath() {
            return elements;
        }

        @Override
        public int hashCode() {
            return elements.stream().mapToInt(Object::hashCode).reduce(0, (a,b) -> a*b);
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            elements.stream().forEach(e -> {
                sb.append("->" + e);
            });
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return false;
            }

            if (o instanceof Path == false) {
                return false;
            }

            Path rpath = (Path)o;
            if (rpath.elements.size() != elements.size()) {
                return false;
            }

            final int cnt = rpath.elements.size();
            int i = 0;
            while (i < cnt) {
                Coordinate c1 = elements.get(i);
                Coordinate c2 = rpath.elements.get(i);

                if (c1.equals(c2) == false) {
                    return false;
                }
                i++;
            }

            return false;
        }
    }

    class Coordinate {
        private final int x;
        private final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Coordinate(Coordinate c) {
            this.x = c.x;
            this.y = c.y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }

            if (o instanceof Coordinate == false) {
                return false;
            }

            Coordinate coord = (Coordinate)o;
            return coord.getX() == getX() && coord.getY() == getY();
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result*31 + x;
            result = result*31 + y;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        List<Coordinate> getNeigbours() {
            List<Coordinate> neighbours = new ArrayList<>();

            if ((x + 1) <= ROWS) {
                neighbours.add(new Coordinate(x + 1, y));
            }

            if ((y + 1) <= COLS) {
                neighbours.add(new Coordinate(x, y + 1));
            }

            return neighbours;
        }

    }
}
