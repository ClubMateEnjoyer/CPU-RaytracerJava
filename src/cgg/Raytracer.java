package cgg;

import tools.*;
import static tools.Color.*;
import static tools.Functions.add;
import static tools.Functions.multiply;

public class Raytracer implements ISampler {

    private final Camera camera;
    private final Scene scene;

    public Raytracer(Camera camera, Scene scene) {
        this.camera = camera;
        this.scene = scene;
    }

    @Override
    public Color getColor(Vec2 pixel) {
        Ray ray = camera.generateRay(pixel);
        return pathtrace(ray, 7);
    }

    /*
     private Color raytrace(Ray ray) {
        Hit hit = scene.intersect(ray);
        if(hit != null) {
            return scene.shade(hit, ray);
        }else {
            return Color.black;
        }
    }
    */

    public Color pathtrace(Ray ray, int depth) {
        if (ray == null || depth == 0) return black;

        Hit hit = scene.intersect(ray);
        if (hit == null) return white;

        Color emission = hit.material().getEmission(hit);
        Color direct = scene.shade(hit, ray);

        Ray secondary = hit.material().getSecondaryRay(hit);
        Color indirect = black;

        if (secondary != null) {
            Color bounced = pathtrace(secondary, depth - 1);
            Color surface = hit.material().getDiffuse(hit);
            indirect = multiply(surface, bounced);
        }

        return add(add(emission, direct), indirect);
    }

}
