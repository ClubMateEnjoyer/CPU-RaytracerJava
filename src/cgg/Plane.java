package cgg;

import static tools.Functions.*;

import tools.*;

public class Plane implements IShape {

    IMaterial material;
    double radius;
    double norm;

    public Plane(IMaterial material, double radius, double norm) {
        this.material = material;
        this.radius = radius;
        this.norm = norm;
    }

    @Override
    public Hit intersect(Ray ray) {

        Vec3 n = Vec3.zAxis; // normal of plane, in x,y-room
        double denom = dot(n, ray.dir());

        if(Math.abs(denom) < EPSILON) {
            return null;
        }

        double nom = -dot(n, ray.pos());
        double t = nom / denom;

        if(!ray.isValid(t)) {
            return null;
        }

        Vec3 hitpoint = ray.pointAt(t);

        // calculate distance with hitpoint
        double distance;
        double dx = Math.abs(hitpoint.x());
        double dy = Math.abs(hitpoint.y());
        distance = Math.pow(Math.pow(dx, norm) + Math.pow(dy, norm), 1.0 / norm);

        if (distance > radius) return null;

        double u = 0.5 + hitpoint.x() / (2 * radius);
        double v = 0.5 + hitpoint.y() / (2 * radius);
        Vec2 texcoords = vec2(u, v);

        return new Hit(t, hitpoint, n, texcoords, material, ray.dir());
    }
    
    @Override
    public BoundingBox getBounds() {
        Vec3 min = vec3(-radius, -radius, -EPSILON);
        Vec3 max = vec3(radius, radius, EPSILON);
        return new BoundingBox(min, max);
    }
}
