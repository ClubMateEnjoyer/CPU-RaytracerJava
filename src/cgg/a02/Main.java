/*
package cgg.a02;

import cgg.*;
import tools.Color;

import static tools.Functions.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int width = 400;
        int height = 400;

        //var camera = new Camera(Math.PI/2, width, height);

        
        List<Sphere> spheres = new ArrayList<>();
        spheres.add(new Sphere(vec3(0, -150, -25), 150, Color.green));
        spheres.add(new Sphere(vec3(0, 0, -25), 4, Color.white));
        spheres.add(new Sphere(vec3(0, 6, -25), 2.5, Color.white));
        spheres.add(new Sphere(vec3(-0.7, 6.7, -22.5), 0.3, Color.black));
        spheres.add(new Sphere(vec3(0.7, 6.7, -22.5), 0.3, Color.black));
        spheres.add(new Sphere(vec3(-30, 30, -25), 20, Color.yellow));
        spheres.add(new Sphere(vec3(14, 13, -25), 3, Color.white));
        spheres.add(new Sphere(vec3(10, 13, -25), 3, Color.white));
        spheres.add(new Sphere(vec3(6, 13, -25), 3, Color.white));
        spheres.add(new Sphere(vec3(8, 15, -25), 3, Color.white));
        spheres.add(new Sphere(vec3(12, 15, -25), 3, Color.white));
        
    
        //var raytracer = new Raytracer(camera, spheres);

        var image = new Image(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //image.setPixel(x, y, raytracer.getColor(vec2(x, y)));
            }
        }

        image.writePNG("a02-spheres");
    }
}

*/