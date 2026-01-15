package cgg.a10;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.*;
import tools.*;
import static tools.Functions.*;

public class MainTriangleTest {
    public static void main(String[] args) {
        int width = 800;
        int height = 800;

        // materialien
        IMaterial red = new PhongMaterial(color(1, 0, 0), color(1), 50);
        IMaterial green = new PhongMaterial(color(0, 1, 0), color(1), 50);
        IMaterial glass = new GlassMaterial(Color.white);

        // 3 eckpunkte
        double h = Math.sqrt(3); 
        Vertex a = new Vertex(vec3(-1, -0.5 * h, 0), Vec3.zAxis, vec2(0, 0));
        Vertex b = new Vertex(vec3(1, -0.5 * h, 0), Vec3.zAxis, vec2(1, 0));
        Vertex c = new Vertex(vec3(0, 0.5 * h, 0), Vec3.zAxis, vec2(0.5, 1));

        Group root = new Group();
        root.addShape(new Triangle(a, b, c, glass));

        IShape sceneRoot = new BVH().build(root);

        ArrayList<IShape> shapes = new ArrayList<>();
        shapes.add(sceneRoot);
        shapes.add(new Sphere(vec3(0, 0, -50), 1, red));
        shapes.add(new Sphere(vec3(0, -10004, 0), 10000, green));
        shapes.add(new Background());

        ArrayList<ILight> lights = new ArrayList<>();
        Scene scene = new Scene(shapes, lights);

        // kamera
        Camera camera = new Camera(Math.PI / 4, width, height);
        camera.setTransform(move(vec3(0, 0, 5)));

        Raytracer tracer = new Raytracer(camera, scene);
        SuperSampler sampler = new SuperSampler(tracer, SAMPLINGTYPE.STRATIFIED, 36);

        Image image = new Image(width, height);
        long start = System.currentTimeMillis();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setPixel(x, y, sampler.getColor(vec2(x, y)));
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Renderzeit: " + (end - start) + " ms");

        image.writePNG("a10-triangles-test");
    }
}
