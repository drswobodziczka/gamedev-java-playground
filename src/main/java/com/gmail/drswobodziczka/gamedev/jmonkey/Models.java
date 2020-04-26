package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

import java.util.Random;
import java.util.stream.IntStream;

import static com.jme3.math.ColorRGBA.randomColor;

class Models {

    private AssetManager assetManager;

    Models(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    Node createWorld() {
        Node gameWorld = new Node("World");
        Random random = new Random();
        IntStream.range(0, 1000)
                .forEach((num) -> {
                    if (random.nextBoolean()) {
                        gameWorld.attachChild(createBox(randomVector(), randomColor()));
                    } else {
                        gameWorld.attachChild(createSphere(randomVector(), randomColor()));
                    }
                });
        return gameWorld;
    }


    Vector3f randomVector() {
        Random random = new Random();
        int x = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        int y = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        int z = random.nextInt(100) * (random.nextBoolean() ? 1 : -1);
        return new Vector3f(x, y, z);
    }

    private Geometry createBox(Vector3f translation, ColorRGBA color) {
        Box box = new Box(1, 1, 1);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        Geometry geometry = new Geometry("Box", box);
        geometry.setLocalTranslation(translation);
        geometry.setMaterial(material);
        return geometry;
    }

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
