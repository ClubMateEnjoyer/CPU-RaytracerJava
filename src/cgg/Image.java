
package cgg;

import tools.*;

public class Image implements tools.IImage {

    // Attributes
    double[] pixelData;
    int height;
    int width;

    public Image(int width, int height) {
        // Provides storage for the image data. For each pixel in the image
        // three double values are needed to store the pixel components.
        this.width = width;
        this.height = height;
        int size = width * height * 3; // Mc = W * H * C -> C = 3, rgb
        pixelData = new double[size];  // 1D array of pixel   
    }

    public void setPixel(int x, int y, Color color) {
        // Stores the RGB color components for one particular pixel addressed
        // by it's coordinates in the image.
        // Calculate the index in the 1D array
        int index = 3 * (y * width + x); // i(x,y,c) = C(yW + x) + c 
        pixelData[index] = color.r(); // c = 0 -> r
        pixelData[index + 1] = color.g(); // c = 1 -> g
        pixelData[index + 2] = color.b(); // c = 2 -> b
    }

    public Color getPixel(int x, int y) {
        // TODO Retrieves the RGB color components for one particular pixel addressed
        // by it's coordinates in the image.
        return Color.black;
    }

    public void writePNG(String name) {
        // This call also needs to be adjusted once Image() and setPixel()
        // are implemented. Use
        // ImageWriter.writePNG(String basename, double[] data, int width, int height) to
        // write the image data to disk in PNG format.
        //System.out.println("Complete the implementation of class cgg.Image for assignment 1.");
        ImageWriter.writePNG(name, pixelData, width, height);
    }

    public void writeHDR(String name) {
        // This call also needs to be adjusted once Image() and setPixel()
        // are implemented. Use
        // ImageWriter.writeHDR(String basename, double[] data, int width, int height) to
        // write the image data to disk in OpenEXR format.
        ImageWriter.writeHDR(name, pixelData, width, height);
    }

    public int width() {
        // This is just a dummy value to make the compiler happy. This
        // needs to be adjusted such that the actual width of the Image is
        // returned.
        return width;
    }

    public int height() {
        // This is just a dummy value to make the compiler happy. This
        // needs to be adjusted such that the actual height of the Image is
        // returned.
        return height;
    }
}
