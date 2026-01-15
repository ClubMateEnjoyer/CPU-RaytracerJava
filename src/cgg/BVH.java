package cgg;

import java.util.*;
import tools.*;
import static tools.Functions.multiply;

public class BVH {
    
    List<IShape> flattenedShapes;

    // computes a flat list of all concrete shapes under the given node
    private void collectFlattendShapes(Group group) {
        flattenedShapes = new ArrayList<>(); // reset
        _collectShapes(group, group.transform.localToWorld());
    }

    // internal helper function to collect all shapes
    private void _collectShapes(Group group, Mat44 parent) {
        for(var shape : group.getShapes()) {
            if(shape instanceof Group) {
                // recurse through the subgroup and accumulate its world matrix
                Group subgroup = (Group)shape;
                _collectShapes(
                    subgroup,
                    multiply(parent, subgroup.transform.localToWorld())
                );
            } else {
                // its a shape, we add it with the accumulated world matrix
                Group wrapped = new Group(parent);
                wrapped.addShape(shape);
                flattenedShapes.add(wrapped);
            }
        }
    }

    private Group split(Group root) {

        BoundingBox bbox = root.getBounds();
        BoundingBox leftBbox = bbox.splitLeft();
        BoundingBox rightBbox = bbox.splitRight();

        Group left = new Group();
        Group right = new Group();
        List<IShape> stay = new ArrayList<>();

        // distribute shapes along subgroup
        for(var shape : root.getShapes()) {
            BoundingBox shapeBox = shape.getBounds();
            if(leftBbox.contains(shapeBox)) {
                left.addShape(shape);
            } else if(rightBbox.contains(shapeBox)) {
                right.addShape(shape);
            } else {
                stay.add(shape); 
            }
        }

        // distribute the undecided shapes by a strategy
        int len = stay.size();
        for(int i = 0; i < len; i++) {
            if(i % 2 == 0) {
                left.addShape(stay.get(i));
            } else {
                right.addShape(stay.get(i));
            }
        }

        // wenn eine seite leer -> split sinnlos
        if (left.getShapes().isEmpty() || right.getShapes().isEmpty()) {
            return root;
        }
        
     
        if (root.getShapes().size() <= 4) {
            return root; 
        }

        if (left.getShapes().size() > 4) {
            left = split(left);
        }

        if (right.getShapes().size() > 4) {
            right = split(right);
        }

        Group g = new Group();
        g.addShape(left);
        g.addShape(right);
        return g;
    }

    public Group build(Group root) {
        collectFlattendShapes(root);

        Group flat = new Group();
        for (IShape shape : flattenedShapes) {
            flat.addShape(shape);
        }

        return split(flat);
    }
}
