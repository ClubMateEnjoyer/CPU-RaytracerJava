package cgg;

import tools.*;
import static tools.Functions.*;

public class Background implements IShape {

    IMaterial material = new SkyMaterial(new ImageTexture("textures/dawn.jpg"));


    @Override
    public Hit intersect(Ray ray) {
        if(ray.tmax() >= Double.MAX_VALUE) {
            Vec3 dir = normalize(ray.dir());

            double u = 0.5 + Math.atan2(dir.z(), dir.x()) / (2 * Math.PI);
            double v = 0.5 - Math.asin(dir.y()) / Math.PI;

            return new Hit(
                Double.MAX_VALUE,
                ray.pointAt(Double.MAX_VALUE),
                negate(ray.dir()), vec2(u, v), material, ray.dir()
            );
        }else {
            return null;
        }
    }


    @Override
    public BoundingBox getBounds() {
        return BoundingBox.everything;
    }
}

