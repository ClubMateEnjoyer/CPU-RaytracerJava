package cgg;

import tools.Color;
import static tools.Functions.*;
import tools.Vec3;

public class GlassMaterial implements IMaterial {

    public Color diffuse;
    public GlassMaterial(Color diffuse) {
        this.diffuse = diffuse;
    }

    @Override
    public Color getDiffuse(Hit hit) {
        return Color.white;
    }

    @Override
    public Color getSpecular(Hit hit) {
        return Color.white;
    }

    @Override
    public double getShininess(Hit hit) {
        return 64;
    }

    @Override
    public Color getEmission(Hit hit) {
        return Color.black;
    }


    @Override
    public Ray getSecondaryRay(Hit hit) {
        Vec3 incident = normalize(hit.incident());
        Vec3 normal = normalize(hit.normal());

        double n1 = 1.0;   // air
        double n2 = 1.5;   // glas

        // wether ray comes from inside
        if (dot(incident, normal) > 0) {
            normal = negate(normal);
            double tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        Vec3 refracted = refract(incident, normal, n1, n2);

        if (refracted != null) {
            double r = schlick(incident, normal, n1, n2);
            if (Math.random() < r) {
                // reflect
                return new Ray(hit.pos(), reflect(incident, normal), EPSILON, Double.POSITIVE_INFINITY);
            } else {
                // refract
                return new Ray(hit.pos(), refracted, EPSILON, Double.POSITIVE_INFINITY);
            }
        } else {
            return new Ray(hit.pos(), reflect(incident, normal), EPSILON, Double.POSITIVE_INFINITY);
        }
    }    
}
