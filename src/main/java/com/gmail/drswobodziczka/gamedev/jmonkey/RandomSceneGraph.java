package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;

/**
 * Sample 2 - How to use nodes as handles to manipulate objects in the scene.
 * You can rotate, translate, and scale objects by manipulating their parent nodes.
 * The Root Node is special: Only what is attached to the Root Node appears in the scene.
 */
public class RandomSceneGraph extends SimpleApplication {

    public static void main(String[] args) {
        RandomSceneGraph app = new RandomSceneGraph();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Node pivot = new Models(assetManager).createWorld();
        rootNode.attachChild(pivot); // put this node in the scene
        pivot.rotate(.4f, .4f, .4f);
    }
}
