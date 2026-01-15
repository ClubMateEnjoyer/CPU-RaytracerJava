package cgg;

import tools.*;

public class SkyMaterial implements IMaterial {

    ISampler emission;

    public SkyMaterial(ISampler emission) {
        this.emission = emission;
    }

    @Override
    public Color getEmission(Hit hit) {
        return emission.getColor(hit.uv());  // uses the uv-coords
    }

    @Override
    public Ray getSecondaryRay(Hit hit) {
        return null;
    }

    @Override
    public Color getDiffuse(Hit hit) {
        return Color.black;
    }

    @Override
    public Color getSpecular(Hit hit) {
        return Color.black;
    }

    @Override
    public double getShininess(Hit hit) {
        return 0;
    }
}
