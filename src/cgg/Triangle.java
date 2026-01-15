package cgg;

import tools.*;
import static tools.Functions.*;

public class Triangle implements IShape {

    Vertex a, b, c;
    IMaterial material;

    public Triangle(Vertex a, Vertex b, Vertex c, IMaterial material) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.material = material;
    }
    
    @Override
    public Hit intersect(Ray ray) {
        Vec3 ab = subtract(b.position(), a.position());
        Vec3 ac = subtract(c.position(), a.position());
        Vec3 abcNormal = (normalize(cross(ab, ac)));
        
        // prüfe Parallelität des Strahls mit Dreieck n*d = 0
        if (almostEqual(dot(ray.dir(), abcNormal), 0)) return null;
        double abc = length(cross(ab, ac)) / 2;

        double nom = dot(subtract(a.position(), ray.pos()), abcNormal);
        double denom = dot(ray.dir(), abcNormal);
        double t = nom / denom;
        if(!ray.isValid(t)) {return null;}
        Vec3 p = ray.pointAt(t);


        // berechne u und prüfe u aus [0, 1]
        Vec3 bp = subtract(p, b.position());
        Vec3 cp = subtract(p, c.position());
        Vec3 bcpNormal = normalize(cross(bp, cp));
        double bcp = length(cross(bp, cp)) / 2;
        double su = dot(abcNormal, bcpNormal);
        double u = su * (bcp / abc);

        if(u < -EPSILON || u > 1 + EPSILON) {return null;}

        // berechne v und prüfe v aus [0, 1]
        Vec3 ap = subtract(p, a.position());
        Vec3 capNormal = normalize(cross(cp, ap));
        double cap = length(cross(ap, cp)) / 2;
        double sv = dot(abcNormal, capNormal);
        double v = sv * (cap / abc);

        if(v < -EPSILON || v > 1 + EPSILON) {return null;}

        double w = 1 - u - v;

        if(w < -EPSILON || w > 1 + EPSILON) {return null;}

        Vec3 normal = abcNormal; 
        // Vec3 normal = normalize(interplolate(a.normal(), b.normal(), c.normal(), vec3(u, v, w)));
        // Color color = interpolate(a.color(), b.color(), c.color(), vec3(u, v, w));
        Vec2 uv = interplolate(a.uv(), b.uv(), c.uv(), vec3(u, v, w));
        uv = vec2(uv.x(), 1.0 - uv.y()); // y-achse spiegeln für texture mapping obj


        return new Hit(t, p, normal, uv, material, negate(ray.dir()));
    }

    @Override
    public BoundingBox getBounds() {
        Vec3 min = min(min(a.position(), b.position()), c.position());
        Vec3 max = max(max(a.position(), b.position()), c.position());
        return new BoundingBox(min, max);
    }

    public void setMaterial(IMaterial newMaterial) {
        this.material = newMaterial;
    }

}
