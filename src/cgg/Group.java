
package cgg;
 
import java.util.*;
import tools.*;
import static tools.Functions.*;

public class Group implements IShape {
   
Transform transform;
List<IShape> shapes = new ArrayList<>();
BoundingBox bbox = BoundingBox.empty;
 
    public Group() {
        this.transform = new Transform(identity());
    }
    
    public Group(Mat44 localToWorld) {
        this.transform = new Transform(localToWorld);
    }
    
    public Group(Transform transform) {
        this.transform = transform;
    }
    
    public void addShape(IShape shape) {
        shapes.add(shape);
    }
    
    public List<IShape> getShapes() {
        return this.shapes;
    }
    
    
    @Override
    public Hit intersect(Ray ray) {
        
    if (!getBounds().intersect(ray.pos(), ray.dir(), ray.tmin(), ray.tmax())) {
        return null;
    }
    
    // transform ray into local coord. system
    Vec3 localOrigin = tools.Functions.multiplyPoint(transform.worldToLocal(), ray.pos());
    Vec3 localDirection = tools.Functions.multiplyDirection(transform.worldToLocal(), ray.dir());
    Ray localRay = new Ray(localOrigin, localDirection, EPSILON, Double.POSITIVE_INFINITY);
    
    Hit closest = null;
    for (IShape shape : shapes) {
        Hit hit = shape.intersect(localRay);
        if (hit != null && (closest == null || hit.t() < closest.t())) {
            closest = hit;
        }
    }
    
    if (closest != null) {
        Vec3 worldPos = multiplyPoint(transform.localToWorld(), closest.pos());
        Vec3 worldNormal = normalize(multiplyDirection(transform.localToWorldNormal(), closest.normal()));
        Vec3 localIncident = negate(localRay.dir());
        Vec3 incident = normalize(multiplyDirection(transform.localToWorldNormal(), localIncident));
        
        return new Hit(
            closest.t(),
            worldPos,
            worldNormal,
            closest.uv(),
            closest.material(),
            incident
            );
        }
        return null;
    }
    
    @Override
    public BoundingBox getBounds() {
        if (!bbox.equals(BoundingBox.empty)) {
            return bbox;
        }
        
        bbox = BoundingBox.empty;
        for (IShape shape : shapes) {
            bbox = bbox.extend(shape.getBounds().transform(transform.localToWorld()));
        }
        return bbox;
    }

    public void setTransform(Mat44 m) {
        this.transform = new Transform(m);
    }
}


