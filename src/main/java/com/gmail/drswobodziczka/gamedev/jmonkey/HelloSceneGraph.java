package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import org.jetbrains.annotations.NotNull;

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

        /* create a blue box at coordinates (1,-1,1) */
        Geometry blue = createBox(new Vector3f(1, -1, 1), ColorRGBA.Blue);
        /* create a red box straight above the blue one at (1,3,1) */
        Geometry red = createBox(new Vector3f(1, 3, 1), ColorRGBA.Red);

        /* Create a pivot node at (0,0,0) and attach it to the root node */
        Node pivot = new Node("pivot");


        /* Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        pivot.attachChild(blue);
        pivot.attachChild(red);
        /* Rotate the pivot node: Note that both boxes have rotated! */
        pivot.rotate(.4f, .4f, 0f);
        rootNode.attachChild(pivot); // put this node in the scene
    }

    @NotNull
    private Geometry createBox(Vector3f translation, ColorRGBA color) {
        Box box = new Box(1, 1, 1);
        Material material = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);

        Geometry geometry = new Geometry("Box", box);
        geometry.setLocalTranslation(translation);
        geometry.setMaterial(material);
        return geometry;
    }
}
