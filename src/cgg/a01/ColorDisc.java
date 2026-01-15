
package cgg.a01;

import static tools.Functions.*;
import tools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public record ColorDisc(Color color, double radius, Vec2 pos) implements ISampler {

    // Returns the color for the given position.
    public Color getColor(Vec2 point) {
        double distance = length(subtract(point, pos));
        if(distance < radius) {
            return color;
        }else {
            return Color.black;
        }
    }
}
