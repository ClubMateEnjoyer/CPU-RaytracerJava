package cgg;

import cgg.ILight.LightInfo;
import java.util.ArrayList;
import tools.*;
import static tools.Color.*;
import static tools.Functions.*;

public class Scene {

    ArrayList<IShape> shapes = new ArrayList<>();
    ArrayList<ILight> lights = new ArrayList<>();

    public Scene(ArrayList<IShape> shapes, ArrayList<ILight> lights) {
        this.shapes = shapes;
        this.lights = lights;
    }
    
    public Hit intersect(Ray ray) {
        
        Hit closest = null;

        for (IShape shape : shapes) {
            Hit hit = shape.intersect(ray); 
            if (hit != null && (closest == null || hit.t() < closest.t())) {
                closest = hit;
            }
        }
        return closest;
    }


    public Color shade(Hit hit, Ray ray) {
        Color finalColor = black;

        for (var l : lights) {
            // create shadow ray to light
            LightInfo lightInfo = l.info(hit.pos());
            Hit shadow = intersect(new Ray(
                hit.pos(),
                negate(lightInfo.direction()),
                EPSILON,
                lightInfo.distance()
            ));

            var material = hit.material();
            var kd = material.getDiffuse(hit);
            var ks = material.getSpecular(hit);
            var alpha = material.getShininess(hit);

            Color diffuse = black;
            Color specular = black;

            if (shadow == null) {
                Vec3 n = normalize(hit.normal());
                Vec3 s = negate(lightInfo.direction());
                Vec3 v = negate(ray.dir());

                Vec3 r = subtract(multiply(2 * dot(n, s), n), s);
                
                double ndots = Math.max(dot(n, s), 0.0);
                diffuse = multiply(kd, ndots);
                diffuse = multiply(diffuse, lightInfo.intensity());

                double rdotv = Math.max(dot(r, v), 0.0);
                specular = multiply(ks, Math.pow(rdotv, alpha));
                specular = multiply(specular, lightInfo.intensity());
            }

            finalColor = add(finalColor, diffuse, specular);
        }

        return finalColor;
    }

}
