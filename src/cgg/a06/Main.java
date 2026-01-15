package cgg.a06;

import static tools.Functions.*;

import java.util.ArrayList;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import tools.*;

public class Main {
  public static void main(String[] args) {
    int width = 700;
    int height = 700;

    // materialien
    IMaterial white = new PhongMaterial(Color.white, Color.white, 32);
    IMaterial black = new PhongMaterial(Color.black, Color.black, 32);
    IMaterial green = new PhongMaterial(Color.green, Color.black, 8);

    // lichter
    ArrayList<ILight> lights = new ArrayList<>();
    lights.add(new DirectionalLight(vec3(-10, -7, -10), multiply(Color.white, 0.7)));

    // schnemmaneinzelteile
    IShape bodySphere = new Sphere(vec3(0), 1, white);           
    IShape headSphere = new Sphere(vec3(0), 0.5, white);         
    IShape eyeL = new Sphere(vec3(0), 0.1, black);               
    IShape eyeR = new Sphere(vec3(0), 0.1, black);

    Group eyeLGroup = new Group(move(vec3(-0.15, 0.1, 0.45)));
    eyeLGroup.addShape(eyeL);

    Group eyeRGroup = new Group(move(vec3(0.15, 0.1, 0.45)));
    eyeRGroup.addShape(eyeR);

    Group eyes = new Group();
    eyes.addShape(eyeLGroup);
    eyes.addShape(eyeRGroup);

    Group head = new Group(move(vec3(0, 1.25, 0)));
    head.addShape(headSphere);
    head.addShape(eyes);

    Group root = new Group(); 
    root.addShape(bodySphere);
    root.addShape(head);

    ArrayList<IShape> shapes = new ArrayList<>();

    // boeden
    shapes.add(new Sphere(vec3(0, -1001, 0), 1000, green));

    int gridSize = 5;
    double spacing = 2.5;

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        double x = (i - (gridSize - 1) / 2.0) * spacing;
        double z = (j - (gridSize - 1) / 2.0) * spacing;

        Group instance = new Group(move(vec3(x, 0, z)));
        instance.addShape(root);
        shapes.add(instance);
      }
    }


    Scene scene = new Scene(shapes, lights);

  
    Mat44 camTransform = multiply(rotate(vec3(0, 32, -180), 15), move(vec3(0, 5, 13)));
    Camera cam = new Camera(Math.PI / 2, width, height, camTransform);

   
    Raytracer raytracer = new Raytracer(cam, scene);
    SuperSampler supersampler = new SuperSampler(raytracer, SAMPLINGTYPE.STRATIFIED, 4);

    Image image = new Image(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, supersampler.getColor(vec2(x, y)));
      }
    }

    image.writePNG("a07-transforms");
  }
}
