package cgg;

import tools.*;
import static tools.Functions.*;

public record Sphere(Vec3 position, double radius, IMaterial material) implements IShape {

    @Override
    public Hit intersect(Ray ray) {
        Vec3 pos = ray.pos();
        Vec3 d = ray.dir();
        Vec3 x0 = subtract(pos, position); // pos = ray pos, position = kugel pos

        double a = dot(d, d);
        double b = 2 * dot(x0, d);
        double c = dot(x0, x0) - radius * radius;
        double disk = b * b - 4 * a * c;
        double t1 = (-b + Math.sqrt(disk)) / (2 * a);
        double t2 = (-b - Math.sqrt(disk)) / (2 * a);

        if (disk < 0) {
            return null;
        } else {

            // look for the first hitpoint
            if (t1 > t2) {
                double tmp = t1;
                t1 = t2;
                t2 = tmp;
            }

            if (ray.isValid(t1)) {
                Vec3 posHit = ray.pointAt(t1);
                Vec3 norHit = divide(subtract(posHit, position), radius);

                // uv koordinate
                double x = norHit.x();
                double y = norHit.y();
                double z = norHit.z();

                double theta = Math.acos(y);
                double phi = Math.atan2(x, z);

                double u = (phi + Math.PI) / (2 * Math.PI);
                double v = (theta / Math.PI);

                Vec2 uv = new Vec2(u, v);

                Vec3 incident = negate(ray.dir());

                return new Hit(t1, posHit, norHit, uv, material, incident);
            }
            return null;
        }
    }

    @Override
    public BoundingBox getBounds() {
        Vec3 min = subtract(position, vec3(radius, radius, radius));
        Vec3 max = add(position, vec3(radius, radius, radius));
        return BoundingBox.around(min, max);
    }
}
