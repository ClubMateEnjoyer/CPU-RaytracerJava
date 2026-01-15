/*
package cgg.a09;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.*;
import java.util.concurrent.*;
import tools.*;
import static tools.Functions.*;
//10253 ms
public class Main32768 {
    public static void main(String[] args) {
        int width = 800;
        int height = 800;

        // materialien
        IMaterial red = new PhongMaterial(color(1, 0, 0), color(1), 50);
        IMaterial green = new PhongMaterial(color(0, 1, 0), color(1), 50);
        IMaterial blue = new PhongMaterial(color(0, 0, 1), color(1), 50);
        IMaterial mirror = new MirrorMaterial();
        IMaterial gray = new PhongMaterial(Color.gray, Color.gray, 4);

        // szene
        Group root = new Group();
        
        int gridSize = 181; //32761 kugeln
        double spacing = 1.3;
        double radius = 0.6;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double x = (i - gridSize / 2) * spacing;
                double z = (j - gridSize / 2) * spacing;

                IMaterial mat = switch ((i + j) % 4) {
                    case 0 -> red;
                    case 1 -> green;
                    case 2 -> blue;
                    default -> gray;
                };

                root.addShape(new Sphere(vec3(x, radius, z), radius, mat));
            }
        }
        

        
        // BVH bauen
        Group optimizedRoot = new BVH().build(root);

        // szene mit hintergrund + BVH
        ArrayList<IShape> sceneShapes = new ArrayList<>();
        sceneShapes.add(new Background());      
        sceneShapes.add(optimizedRoot);

        ArrayList<ILight> lights = new ArrayList<>();
        Scene scene = new Scene(sceneShapes, lights);

        // kamera
        Camera camera = new Camera(Math.PI / 4, width, height);
        camera.setTransform(multiply(
                rotate(Vec3.yAxis, 30),
                rotate(Vec3.xAxis, -20),
                move(vec3(-10, -8, 370))));

        
        Raytracer tracer = new Raytracer(camera, scene);
        SuperSampler sampler = new SuperSampler(tracer, SAMPLINGTYPE.STRATIFIED, 36);

        Image image = new Image(width, height);
        long start = System.currentTimeMillis();

        
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cores);
        List<Future<Color>> pixels = new ArrayList<>(width * height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int fx = x;
                final int fy = y;
                pixels.add(pool.submit(() -> sampler.getColor(vec2(fx, fy))));
            }
        }

        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    image.setPixel(x, y, pixels.get(i++).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        pool.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Rendering-Zeit (parallel): " + (end - start) + " ms");

        image.writePNG("a09-bvh-32761");
    }
}
*/




