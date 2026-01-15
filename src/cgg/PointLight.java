package cgg;

import static tools.Functions.*;

import tools.Color;
import tools.Vec3;

public record PointLight(Vec3 position, Color intensity) implements ILight {

    @Override
    public LightInfo info(Vec3 positionX) {
        Vec3 direction = subtract(positionX, position);
        double distance = length(direction);
        return new LightInfo(normalize(direction), intensity, distance);
    }    
}
