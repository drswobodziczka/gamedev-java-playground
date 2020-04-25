package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.stream.IntStream;

import static com.jme3.math.ColorRGBA.randomColor;

/**
 * Sample 2 - How to use nodes as handles to manipulate objects in the scene.
 * You can rotate, translate, and scale objects by manipulating their parent nodes.
 * The Root Node is special: Only what is attached to the Root Node appears in the scene.
 */
public class HelloSceneGraph extends SimpleApplication {

    public static void main(String[] args) {
        HelloSceneGraph app = new HelloSceneGraph();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Create a pivot node at (0,0,0) and attach it to the root node
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene

        // create geometries amd attach to the *pivot* node. (And transitively to the root node.)
        Random random = new Random();
        IntStream.range(0, 1000)
                .forEach((num) -> {
                    if (random.nextBoolean()) {
                        pivot.attachChild(createBox(randomVector(), randomColor()));
                    } else {
                        pivot.attachChild(createSphere(randomVector(), randomColor()));
                    }
                });

        /* Rotate the pivot node: Note that all geometries have rotated! */
        pivot.rotate(.4f, .4f, 0f);
    }

    @NotNull
    private Vector3f randomVector() {
        Random random = new Random();
        int x = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        int y = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        int z = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        return new Vector3f(x, y, z);
    }

    @NotNull
    private Geometry createBox(Vector3f translation, ColorRGBA color) {
        Box box = new Box(1, 1, 1);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        Geometry geometry = new Geometry("Box", box);
        geometry.setLocalTranslation(translation);
        geometry.setMaterial(material);
        return geometry;
    }

    @NotNull
    private Geometry createSphere(Vector3f translation, ColorRGBA color) {
        Sphere sphere = new Sphere(30, 30, 1);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        Geometry geometry = new Geometry("Sphere", sphere);
        geometry.setLocalTranslation(translation);
        geometry.setMaterial(material);
        return geometry;
    }
}
