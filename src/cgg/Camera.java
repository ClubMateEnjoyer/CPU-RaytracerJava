package cgg;

import static tools.Functions.*;
import tools.*;

public class Camera {

    double alpha;
    double width;
    double height;
    private Mat44 cameraToWorld;

    public Camera(double alpha, double width, double height) {
        this.alpha = alpha;
        this.width = width;
        this.height = width;
        this.cameraToWorld = identity();
    }

    public Camera(double alpha, double width, double height, Mat44 cameraToWorld) {
        this.alpha = alpha;
        this.width = width;
        this.height = width;
        this.cameraToWorld = cameraToWorld;
    }

    public Ray generateRay(Vec2 pixel){
        // formula to generate a ray
        double d1 = pixel.u() - width/2;
        double d2 = -(pixel.v() - height/2);
        double d3 = -((width / 2) / Math.tan(alpha / 2));

        Vec3 pos = vec3(0, 0, 0);
        Vec3 dir = vec3(d1, d2, d3);

        // ray in local camera coord. system
        Ray r = new Ray(pos, dir);

        Vec3 worldOrigin = Functions.multiplyPoint(cameraToWorld, r.pos());
        Vec3 worldDirection = Functions.multiplyDirection(cameraToWorld, r.dir());

        return new Ray(worldOrigin, worldDirection);

    }

    public void setTransform(Mat44 cameraToWorld) {
        this.cameraToWorld = cameraToWorld;
    }
}
