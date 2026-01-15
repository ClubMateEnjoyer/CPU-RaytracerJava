
package cgg.a01;

import cgg.Image;
import tools.Vec2;

import static tools.Functions.*;

import static tools.Color.*;


public class Main {

  public static void main(String[] args) {
    int width = 400;
    int height = 400;

    /************************
     * Constant Color Image *
     ************************/
    // This class instance defines the contents of the image.
    var constant = new ConstantColor(cyan);

    // Creates an image and iterates over all pixel positions inside the image.
    var image1 = new Image(width, height);
    for (int x = 0; x != width; x++)
      for (int y = 0; y != height; y++)
        // Sets the color for one particular pixel.
        image1.setPixel(x, y, constant.getColor(vec2(x, y)));

    // Write the image to disk.
    image1.writePNG("a01-constant");

    /**************
     * Disc Image *
     **************/
    // attributs for the disk
    Vec2 pos =  new Vec2(width/2, height/2);
    int radius = 170;
    // This class instance defines the contents of the disk-image
    var disc = new ColorDisc(cyan, radius, pos);

    var image2 = new Image(width, height);
    for (int x = 0; x != width; x++)
      for (int y = 0; y != height; y++)
        // Sets the color for one particular pixel.
        image2.setPixel(x, y, disc.getColor(vec2(x, y)));

    // Write the image to disk.
    image2.writePNG("a01-disc");

    /***********************
     * Colored Discs Image *
     ***********************/
    // This class instance defines the contents of the many-disk-image
    var manyDisks = new ColoredDiscs(null, width, height);

    var image3 = new Image(width, height);
    for (int x = 0; x != width; x++)
      for (int y = 0; y != height; y++)
        // Sets the color for one particular pixel.
        image3.setPixel(x, y, manyDisks.getColor(vec2(x, y)));

    // Write the image to disk.
    image3.writePNG("a01-colordiscs");
  }
}
