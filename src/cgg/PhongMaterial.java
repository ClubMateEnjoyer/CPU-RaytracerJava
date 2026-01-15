package cgg;

import tools.Color;
import tools.Vec3;

import static tools.Color.*;
import static tools.Functions.*;

public record PhongMaterial(Color diffuse, Color specular, double shininess) implements IMaterial {

    @Override
    public Color getDiffuse(Hit hit) {
        return diffuse;
    }

    @Override
    public Color getSpecular(Hit hit) {
        return specular;
    }

    @Override
    public double getShininess(Hit hit) {
        return shininess;
    }

    @Override
    public Color getEmission(Hit hit) {
        return black;
    }

    @Override
    public Ray getSecondaryRay(Hit hit) {
        Vec3 n = normalize(hit.normal());

        Vec3 dir;
        do {
            dir = normalize(vec3(
                2 * Math.random() - 1,
                2 * Math.random() - 1,
                2 * Math.random() - 1
            ));
        } while (dot(dir, n) <= 0);

        return new Ray(hit.pos(), dir, EPSILON, Double.POSITIVE_INFINITY);
    }


}
