package cgg;

import static tools.Functions.normalize;
import tools.*;

public record DirectionalLight(Vec3 direction, Color intensity) implements ILight {

    public DirectionalLight {
        direction = normalize(direction);

    }
    @Override
    public LightInfo info(Vec3 position) {
        return new LightInfo(direction, intensity, Double.POSITIVE_INFINITY);
    }
}
