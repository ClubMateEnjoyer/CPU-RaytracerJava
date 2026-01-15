/*
package cgg.a05;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.ArrayList;
import tools.*;
import static tools.Functions.*;

public class Main {
  public static void main(String[] args) {
    int width = 1000;
    int height = 1000;

    ArrayList<Sphere> spheres = new ArrayList<>();

    ISampler earthTex = new ImageTexture("textures/earth.jpg");
    ISampler moonTex = new ImageTexture("textures/moon.jpg");
    ISampler sunTex = new ImageTexture("textures/sun.jpg");

    spheres.add(new Sphere(vec3(5, 50, -200), 30, new TexturedPhongMaterial(moonTex, Color.white, 128)));
    spheres.add(new Sphere(vec3(6, 6, -5), 3, new TexturedPhongMaterial(sunTex, Color.white, 128)));
    spheres.add(new Sphere(vec3(-190, -50, -450), 160, new TexturedPhongMaterial(earthTex, Color.white, 256)));

    ArrayList<ILight> lights = new ArrayList<>();
    lights.add(new DirectionalLight(vec3(-10, -7, -10), multiply(Color.white, 0.7)));
    lights.add(new DirectionalLight(vec3(10, 10, 10), multiply(Color.white, 0.7)));


    var scene = new Scene(spheres, lights);
    var cam = new Camera(Math.PI / 2, width, height);
    var raytracer = new Raytracer(cam, scene);
    var supersampler = new SuperSampler(raytracer, SAMPLINGTYPE.STRATIFIED, 36);

    var image = new Image(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, supersampler.getColor(vec2(x, y)));
      }
    }

    image.writePNG("a05-corrected");
  }
}
*/