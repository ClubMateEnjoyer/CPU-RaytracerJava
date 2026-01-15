/*
import static tools.Functions.*;

import java.util.ArrayList;

import cgg.*;
import tools.Color;

public class Main {
    public static void main(String[] args) {
        int width = 400;
        int height = 400;
    
        
        ArrayList<Sphere> spheres = new ArrayList<>();
        spheres.add(new Sphere(vec3(0, -5005, -30), 5000, multiply(Color.gray, 0.5)));
        spheres.add(new Sphere(vec3(0, 1, -16), 6, multiply(Color.yellow, 0.2)));


        ArrayList<ILight> lights = new ArrayList<>();
        lights.add(new DirectionalLight(vec3(0, -1, 0.5), multiply(Color.white, 0.4)));
        lights.add(new PointLight(vec3(-12,12, 0), multiply(Color.magenta, 3)));
        lights.add(new PointLight(vec3(12,12, 0), multiply(Color.cyan, 3)));
        
        var camera = new Camera(Math.PI/2, width, height);
        var scene = new Scene(spheres, lights);
        var raytracer = new Raytracer(camera, scene);
    
        var image = new Image(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, raytracer.getColor(vec2(x, y)));
            }
        }

        image.writePNG("a03-phong");
    }
}
*/