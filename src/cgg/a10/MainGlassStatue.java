package cgg.a10;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.*;
import tools.*;
import static tools.Functions.*;

// hintergrund zu dem wasser ding ändern! (in Backround.java), "sundown.jpg"
// pathtrace tiefe erhöhen
// pathtrace teife 254, ca 23min render time
public class MainGlassStatue {
    public static void main(String[] args) {
        int height = 1920;
        int width = 1080;

        // materialien
        IMaterial glass = new GlassMaterial(Color.white);

        // Szenegruppe
        Group root = new Group();
        Group statue = new Group(multiply(rotate(0, 1, 0, 45), move(15, 0 , -30)));

        Group lucy = ModelLoader.loadOBJModel("models/lucy.obj", glass);
        if (lucy != null) {
            lucy.setTransform(multiply(
                        rotate(0, 1, 0, 90),
                        rotate(1, 0, 0, -90)
            ));
            statue.addShape(lucy);
        }

        root.addShape(statue);
    
        IShape sceneRoot = new BVH().build(root);

        // scene
        ArrayList<IShape> shapes = new ArrayList<>();
        shapes.add(sceneRoot);
        shapes.add(new Background());

        // keine lichter wegen backround
        ArrayList<ILight> lights = new ArrayList<>();

        Scene scene = new Scene(shapes, lights);

        // kamera
        Camera camera = new Camera(Math.PI / 4, width, height);
        camera.setTransform(multiply(
                    rotate(1, 0, 0, -9),
                    move(vec3(-340, 700, 700))                    
        ));

        Raytracer tracer = new Raytracer(camera, scene);
        SuperSampler sampler = new SuperSampler(tracer, SAMPLINGTYPE.STRATIFIED, 64);

        Image image = new Image(width, height);
        long start = System.currentTimeMillis();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setPixel(x, y, sampler.getColor(vec2(x, y)));
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Renderzeit: " + (end - start) + " ms");

        image.writePNG("a10-triangles2");
    }
}
