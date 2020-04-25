package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import org.jetbrains.annotations.NotNull;

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
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene
        Geometry box = createBox(new Vector3f(1, -2, 1), randomColor());
        Geometry sphere = createSphere(new Vector3f(1, 2, 1), randomColor());
        pivot.attachChild(box);
        pivot.attachChild(sphere);
        pivot.rotate(.4f, .4f, 0f);

        Box mesh = new Box(Vector3f.ZERO, 1, 1, 1); // a cuboid default mesh
        Geometry thing = new Geometry("thing", mesh);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/ShowNormals.j3md");
        thing.setMaterial(mat);
        rootNode.attachChild(thing);

        thing.rotate(45* FastMath.DEG_TO_RAD, 45* FastMath.DEG_TO_RAD, 45* FastMath.DEG_TO_RAD);
        thing.setLocalTranslation(new Vector3f(1, 1, -10));
        thing.scale( 10.0f, 0.1f, 1.0f );
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
