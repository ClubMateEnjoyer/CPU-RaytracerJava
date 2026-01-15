package cgg.a10;

import cgg.*;
import cgg.SuperSampler.SAMPLINGTYPE;
import java.util.*;
import tools.*;
import static tools.Functions.*;

public class MainComputer {
    public static void main(String[] args) {
        int width = 1280;
        int height = 720;

        // materialien
        ISampler codeTex = new ImageTexture("textures/vscode.png");
        IMaterial codeMat = new TexturedPhongMaterial(codeTex, Color.white, 9);
        IMaterial red = new PhongMaterial(color(1, 0, 0), color(1), 50);
        IMaterial blue = new PhongMaterial(color(0, 0, 1), color(1), 50);
        IMaterial green = new PhongMaterial(color(0, 1, 0), color(1), 50);
        IMaterial greenFloor = new PhongMaterial(color(0.2, 0.8, 0.2), color(1), 50);
        IMaterial glass = new GlassMaterial(Color.white);
        IMaterial mirror = new MirrorMaterial();

        // hauptszene
        Group root = new Group();        

        // fliegende kugeln
        root.addShape(new Sphere(vec3(-35, 52, -25), 2, red));
        root.addShape(new Sphere(vec3(-30, 55, -20), 2.5, blue));
        root.addShape(new Sphere(vec3(-40, 62, -25), 3, mirror));
        root.addShape(new Sphere(vec3(-30, 67, -30), 3.5, green));
        root.addShape(new Sphere(vec3(-25, 75, -35), 4, glass));

        // computer obj + mtl
        Group pc = new Group(multiply(rotate(0, 1, 0, 45), move(15, 0 , -30)));
        Group computer = ModelLoader.loadOBJModel("models/78023/78023.obj");
        if (computer != null) {
            computer.setTransform(
                        rotate(1, 0, 0, -90)      
            );
            pc.addShape(computer);
        }

        // vscode screenshot auf plane als computer display
        Group display = new Group(multiply(move(-22.8, 29, -9.1), rotate(vec3(0, 1, 0), 11), rotate(vec3(1, 0, 0), -6), scale(vec3(2, 1.7, 2))));
        display.addShape(new Plane(codeMat, 10, 100));
        pc.addShape(display);
        
        
        root.addShape(pc);
        root.addShape(new Sphere(vec3(0, -100000, 0), 100000, greenFloor));
        
        // bvh bauen
        IShape sceneRoot = new BVH().build(root);

        ArrayList<IShape> shapes = new ArrayList<>();
        shapes.add(sceneRoot);
        shapes.add(new Background());

        // keine lichter wegen hintergrund
        ArrayList<ILight> lights = new ArrayList<>();

        Scene scene = new Scene(shapes, lights);

        // kamera
        Camera camera = new Camera(Math.PI / 4, width, height);
        camera.setTransform(multiply(rotate(1, 0, 0, -9), move(vec3(10, 0, 200))));

        Raytracer tracer = new Raytracer(camera, scene);
        SuperSampler sampler = new SuperSampler(tracer, SAMPLINGTYPE.STRATIFIED, 25);

        Image image = new Image(width, height);
        long start = System.currentTimeMillis();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setPixel(x, y, sampler.getColor(vec2(x, y)));
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Renderzeit: " + (end - start) + " ms");

        image.writePNG("a10-triangles");
    }
}
