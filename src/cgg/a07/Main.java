package cgg.a07;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.ArrayList;
import tools.*;
import static tools.Functions.*;

public class Main {
  public static void main(String[] args) {
    int width = 1920;
    int height = 1080;

    // texturen
    ISampler oneBallTex = new ImageTexture("textures/Ball1.jpg");
    ISampler twoBallTex = new ImageTexture("textures/Ball2.jpg");
    ISampler threeBallTex = new ImageTexture("textures/Ball3.jpg");
    ISampler fourBallTex = new ImageTexture("textures/Ball4.jpg");
    ISampler fiveBallTex = new ImageTexture("textures/Ball5.jpg");
    ISampler sixBallTex = new ImageTexture("textures/Ball6.jpg");
    ISampler sevenBallTex = new ImageTexture("textures/Ball7.jpg");
    ISampler eightBallTex = new ImageTexture("textures/Ball8.jpg");
    ISampler nineBallTex = new ImageTexture("textures/Ball9.jpg");
    ISampler tenBallTex = new ImageTexture("textures/Ball10.jpg");
    ISampler elevenBallTex = new ImageTexture("textures/Ball11.jpg");
    ISampler twelveBallTex = new ImageTexture("textures/Ball12.jpg");
    ISampler thirteenBallTex = new ImageTexture("textures/Ball13.jpg");
    ISampler fourteenBallTex = new ImageTexture("textures/Ball14.jpg");
    ISampler fifteenBallTex = new ImageTexture("textures/Ball15.jpg");
    ISampler earthTex = new ImageTexture("textures/earth.jpg");
    ISampler moonTex = new ImageTexture("textures/moon.jpg");

    // materialien
    IMaterial mirror = new MirrorMaterial();
    IMaterial white = new PhongMaterial(Color.gray, Color.black, 0);
    IMaterial red = new PhongMaterial(Color.red, Color.red, 8);
    IMaterial blue = new PhongMaterial(Color.blue, Color.blue, 8);    
    IMaterial green = new PhongMaterial(Color.green, Color.gray, 8);
    IMaterial oneBall = new TexturedPhongMaterial(oneBallTex, Color.white, 128);
    IMaterial twoBall = new TexturedPhongMaterial(twoBallTex, Color.white, 128);
    IMaterial threeBall = new TexturedPhongMaterial(threeBallTex, Color.white, 128);
    IMaterial fourBall = new TexturedPhongMaterial(fourBallTex, Color.white, 128);
    IMaterial fiveBall = new TexturedPhongMaterial(fiveBallTex, Color.white, 128);
    IMaterial sixBall = new TexturedPhongMaterial(sixBallTex, Color.white, 128);
    IMaterial sevenBall = new TexturedPhongMaterial(sevenBallTex, Color.white, 128);
    IMaterial eightBall = new TexturedPhongMaterial(eightBallTex, Color.white, 128);
    IMaterial nineBall = new TexturedPhongMaterial(nineBallTex, Color.white, 128);
    IMaterial tenBall = new TexturedPhongMaterial(tenBallTex, Color.white, 128);
    IMaterial elevenBall = new TexturedPhongMaterial(elevenBallTex, Color.white, 128);
    IMaterial twelveBall = new TexturedPhongMaterial(twelveBallTex, Color.white, 128);
    IMaterial thirteenBall = new TexturedPhongMaterial(thirteenBallTex, Color.white, 128);
    IMaterial fourteenBall = new TexturedPhongMaterial(fourteenBallTex, Color.white, 128);
    IMaterial fifteenBall = new TexturedPhongMaterial(fifteenBallTex, Color.white, 128);
    IMaterial earth = new TexturedPhongMaterial(earthTex, Color.white, 128);
    IMaterial moon = new TexturedPhongMaterial(moonTex, Color.white, 128);


    IShape background = new Background();

    ArrayList<IShape> shapes = new ArrayList<>();
    shapes.add(background); 
    shapes.add(new Sphere(vec3(0, -100004, 0), 100000, white)); // boden

    shapes.add(new Sphere(vec3(9, 4, -17), 8, earth));
    shapes.add(new Sphere(vec3(-10, 4, -31), 8, mirror));
    shapes.add(new Sphere(vec3(-23.3, -1, -40.2), 3, oneBall));
    shapes.add(new Sphere(vec3(-9.7, -1, -8), 3, twoBall));
    shapes.add(new Sphere(vec3(-26.7, -1, -30.8), 3, threeBall));
    shapes.add(new Sphere(vec3(-29.7, -1, -69.9), 3, fourBall));
    shapes.add(new Sphere(vec3(61.3, -1, -61.1), 3, fiveBall));
    shapes.add(new Sphere(vec3(-32.3, -1, -21.5), 3, sixBall));
    shapes.add(new Sphere(vec3(1.0, -1, -65.1), 3, sevenBall));
    shapes.add(new Sphere(vec3(-42.9, -1, -73.9), 3, eightBall));
    shapes.add(new Sphere(vec3(43.6, -1, -71.1), 3, nineBall));
    shapes.add(new Sphere(vec3(35.6, -1, -40.3), 3, tenBall));
    shapes.add(new Sphere(vec3(46.8, -1, -57.6), 3, elevenBall));
    shapes.add(new Sphere(vec3(21.9, -1, -11.8), 3, twelveBall));
    shapes.add(new Sphere(vec3(-23.7, -1, -2.5), 3, thirteenBall));
    shapes.add(new Sphere(vec3(1.5, -1, -122.0), 3, fourteenBall));
    shapes.add(new Sphere(vec3(-2.8, -1, 1.4), 3, fifteenBall));
    shapes.add(new Sphere(vec3(38.5, -1, -23.4), 3, red));
    shapes.add(new Sphere(vec3(-49.8, -1, -34.0), 3, green));
    shapes.add(new Sphere(vec3(-64.1, -1, -65.0), 3, blue));
    shapes.add(new Sphere(vec3(-51.5, -1, -70.4), 3, mirror));
    shapes.add(new Sphere(vec3(-14.8, -1, -2.6), 3, red));
    shapes.add(new Sphere(vec3(23.5, -1, -4.1), 3, green));
    shapes.add(new Sphere(vec3(-39.0, -1, -81.8), 3, blue));
    shapes.add(new Sphere(vec3(-19.8, -1, -10.7), 3, mirror));
    shapes.add(new Sphere(vec3(-57.1, -1, -93.7), 3, red));
    shapes.add(new Sphere(vec3(2.0, -1, -6.4), 3, green));
    shapes.add(new Sphere(vec3(22, -1, -30.5), 3, blue));
    shapes.add(new Sphere(vec3(10.1, -1, 4.5), 3, mirror));
    shapes.add(new Sphere(vec3(79.6, -1, -70.7), 3, red));
    shapes.add(new Sphere(vec3(-39.5, -1, -35.5), 3, green));
    shapes.add(new Sphere(vec3(44.1, -1, -51.5), 3, blue));
    shapes.add(new Sphere(vec3(68.8, -1, -48.4), 3, mirror));
    shapes.add(new Sphere(vec3(20, 14, -16.4), 2, moon));



    // keine lichtquellen notwendig, globale beleuchtung durch himmel
    ArrayList<ILight> lights = new ArrayList<>();

    Scene scene = new Scene(shapes, lights);

    // camera
    Mat44 camTransform = multiply(rotate(vec3(-1, 0, 0), 22), move(vec3(0, -5, 36)));
    Camera cam = new Camera(Math.PI / 2, width, height, camTransform);


    // raytracer mit pathtracing
    Raytracer raytracer = new Raytracer(cam, scene);
    SuperSampler supersampler = new SuperSampler(raytracer, SAMPLINGTYPE.STRATIFIED, 9);

    Image image = new Image(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, supersampler.getColor(vec2(x, y)));
      }
    }
    image.writePNG("a07-pathtracing");
  }
}
