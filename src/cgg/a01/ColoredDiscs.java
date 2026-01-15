
package cgg.a01;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import tools.*;

public record ColoredDiscs(List<ColorDisc> discs, int height, int width) implements ISampler {

    public ColoredDiscs {
        if (discs == null) {
            discs = new ArrayList<>();
            fillDiscs(discs, height, width);
        }
    }

    // Returns the color for the given position.
    public Color getColor(Vec2 point) {
        for (int i = 0; i < discs.size(); i++) {
            Color color = discs.get(i).getColor(point);
            // sobald der erste kreis-hit gefunden, gib die farbe dieses kreises an der stelle zurueck
            if (!color.equals(Color.black)) {
                return color;
            }
        }
        return Color.black;
    }

    public void fillDiscs(List<ColorDisc> discs, int width, int height) {
        int rows = 6;
        int cols = 6;
        int radius = 0;
        /*
         * spacingX = width/(cols+1), da:
         * cols = 4 heisst, ich will 4 cols, sprich for schleife muss 4x durchlaufen
         * spacingX = 400/4 = 100
         * somit wird die schleife (bei width == 400 wird 0 bis 399 betrachtet!) für x=100, 200, 300 betrachtet --> 3x, nicht 4x
         * spacing = 400/(4+1) = 80 --> 80, 160, 240, 320 == 4x
         * deswegen rows+1 und cols+1
         */
        int spacingX = width/(cols + 1);
        int spacingY = height/(rows + 1);
        Random rand = new Random();
        Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.cyan, Color.magenta, Color.yellow};
    
        // nur relevante punkte betrachten -> spacing
        for (int x = spacingX; x < width; x += spacingX) {
            for (int y = spacingY; y < height; y += spacingY) {
                radius = (y+x)/20;
                // pruefen, ob kreis aus bild ragt
                if(x+radius < width && y+radius < height) {
                    // zufaellige farbe fuer jeden kreis
                    int randInt= rand.nextInt(7);
                    discs.add(new ColorDisc(colors[randInt], radius, new Vec2(x, y)));
                }
                
            }
        }
    }
    
}
