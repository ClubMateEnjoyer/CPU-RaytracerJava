package cgg;

import tools.*;
import static tools.Functions.*;

public record Ray(Vec3 pos, Vec3 dir, double tmin, double tmax) {
    
    public Ray {
        dir = normalize(dir);
        if(tmin < 0)
            tmin = 0;
        if(tmax < tmin)
            tmax = tmin;    
    }

    public Ray(Vec3 pos, Vec3 dir) {
        this(pos, dir, 0, Double.MAX_VALUE);
    }

    public Vec3 pointAt(double t) {
        return add(pos, multiply(t, dir)); // t(x) = x0 + t*d
    }

    public boolean isValid(double t) {
        //t from tmin, tmax
        if(t >= tmin && t <= tmax) { 
            return true;
        } else {
            return false;
        }
    }

    public Ray transform(Mat44 m) {
        Vec3 newPos = multiplyPoint(m, this.pos);
        Vec3 newDir = normalize(multiplyDirection(m, this.dir));
        return new Ray(newPos, newDir, this.tmin, this.tmax);
    }

}