package cgg;

import static tools.Functions.*;
import static tools.Color.*;

import tools.Color;
import tools.Vec3;

public record MirrorMaterial(Color diffuse, Color specular, double shininess, Color emission) implements IMaterial {


    public MirrorMaterial() {
        this(multiply(0.5, gray), black, 0, black);
    }

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
        return emission;
    }

    @Override
    public Ray getSecondaryRay(Hit hit) {
        Vec3 d = negate(hit.incident());           
        Vec3 n = normalize(hit.normal());  
        Vec3 r = subtract(d, multiply(2 * dot(d, n), n)); 

        return new Ray(hit.pos(), r, EPSILON, Double.POSITIVE_INFINITY);
    } 
}
