package cgg.a08;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.ArrayList;
import tools.*;
import static tools.Functions.*;

public class Main {
  
  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // texturen
    ISampler spielbrettTex = new ImageTexture("textures/spielbrett.jpg");
    ISampler tischTex = new ImageTexture("textures/tisch.jpg");

    // materialien
    IMaterial mirror = new MirrorMaterial();
    IMaterial glassWhite = new GlassMaterial(Color.white);
    IMaterial glassRed = new GlassMaterial(Color.red);
    IMaterial spielbrettMat = new TexturedPhongMaterial(spielbrettTex, Color.white, 9);
    IMaterial tischMat = new TexturedPhongMaterial(tischTex, Color.white, 9);
    IMaterial black = new PhongMaterial(Color.black, Color.black, 64);


    IShape background = new Background();
    ArrayList<IShape> shapes = new ArrayList<>();
    shapes.add(background); 

    /////////// wuerfel ////////////////////////////////////
    Group dot31 = new Group(move(0.5, 0.5, 0));
    dot31.addShape(new Plane(black, 0.2, 2));
    Group dot32 = new Group(move(-0.5, -0.5, 0));
    dot32.addShape(new Plane(black, 0.2, 2));

    Group dot51 = new Group(move(0.5, 0.5, 0));
    dot51.addShape(new Plane(black, 0.2, 2));
    Group dot52 = new Group(move(-0.5, -0.5, 0));
    dot52.addShape(new Plane(black, 0.2, 2));
    Group dot53 = new Group(move(-0.5, 0.5, 0));
    dot53.addShape(new Plane(black, 0.2, 2));
    Group dot54 = new Group(move(0.5, -0.5, 0));
    dot54.addShape(new Plane(black, 0.2, 2));
    
    

    Group back = new Group(move(0, 0, -2));
    back.addShape(new Plane(mirror, 1, 100));

    Group front = new Group(move(0, 0, 0));
    front.addShape(new Plane(black, 0.2, 2));
    front.addShape(dot51);
    front.addShape(dot52);
    front.addShape(dot53);
    front.addShape(dot54);
    front.addShape(new Plane(mirror, 1, 100));

    Group bottom = new Group(multiply(rotate(1, 0, 0, -90), move(0, 1, -1)));
    
    bottom.addShape(new Plane(mirror, 1, 100));

    Group top = new Group(multiply(rotate(1, 0, 0, -90), move(0, 1, 1)));
    top.addShape(new Plane(black, 0.2, 2));
    top.addShape(dot31);
    top.addShape(dot32);
    top.addShape(new Plane(mirror, 1, 100));

    Group left = new Group(multiply(rotate(0, 1, 0, 90), move(1, 0, -1)));
    left.addShape(new Plane(mirror, 1, 100));

    Group right = new Group(multiply(rotate(0, 1, 0, -90), move(-1, 0, -1)));
    right.addShape(new Plane(black, 0.2, 2));
    right.addShape(new Plane(mirror, 1, 100));

    Group dice = new Group(multiply(move(vec3(-4.5, 0.8, 8.1)), rotate(vec3(0, 1, 0), 35)));
    dice.addShape(back);
    dice.addShape(front);
    dice.addShape(top);
    dice.addShape(bottom);
    dice.addShape(left);
    dice.addShape(right);
    
    //////////////////////////////////////////////////////
    Group figurRot = new Group();
    figurRot.addShape(new Sphere(vec3(0, 0, 0), .6, glassRed));
    figurRot.addShape(new Sphere(vec3(0, 0.8, 0), .4, glassRed));
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    Group figurWhite = new Group();
    figurWhite.addShape(new Sphere(vec3(0, 0, 0), .6, glassWhite));
    figurWhite.addShape(new Sphere(vec3(0, 0.8, 0), .4, glassWhite));
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    Group figur1 = new Group(move(2.45, 0, 11.45));
    figur1.addShape(figurWhite);
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    Group figur2 = new Group(move(-11.7, 0, 2.3));
    figur2.addShape(figurRot);
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    Group figur3 = new Group(move(-2.33, 0, 2.24));
    figur3.addShape(figurRot);
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    Group figur4 = new Group(move(4.6, 0, 2.38));
    figur4.addShape(figurWhite);
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    Group figur5 = new Group(move(2.45, 0, -4.55));
    figur5.addShape(figurWhite);
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    Group figur6 = new Group(move(-9.1, 0, -2.2));
    figur6.addShape(figurWhite);
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    Group figur7 = new Group(move(-2.33, 0, -7.04));
    figur7.addShape(figurRot);
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    Group figur8 = new Group(move(2, 0, 0));
    figur8.addShape(figurRot);
    //////////////////////////////////////////////////////
  
    //////////////////////////////////////////////////////
    Group field = new Group(rotate(vec3(1, 0, 0), 90));
    field.addShape(new Plane(spielbrettMat, 13, 100));
    field.addShape(new Plane(tischMat, 50, 100));
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    Group world = new Group();
    world.addShape(field);
    world.addShape(figur1);
    world.addShape(figur2);
    world.addShape(figur3);
    world.addShape(figur4);
    world.addShape(figur5);
    world.addShape(figur6);
    world.addShape(figur7);
    world.addShape(figur8);
    world.addShape(dice);
    shapes.add(world);
    //////////////////////////////////////////////////////
    
    // keine lichtquellen notwendig, globale beleuchtung durch himmel
    ArrayList<ILight> lights = new ArrayList<>();
    
    Scene scene = new Scene(shapes, lights);

    // camera
    Mat44 camTransform = multiply(rotate(vec3(0, 1, 0), 57), rotate(vec3(1, 0, 0), -50), move(vec3(-4, -8, 12.3)));
    Camera cam = new Camera(Math.PI / 2, width, height, camTransform);


    // raytracer mit pathtracing
    Raytracer raytracer = new Raytracer(cam, scene);
    SuperSampler supersampler = new SuperSampler(raytracer, SAMPLINGTYPE.STRATIFIED, 36);

    Image image = new Image(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, supersampler.getColor(vec2(x, y)));
      }
    }
    image.writePNG("a08-transmission");
  }
}
  
