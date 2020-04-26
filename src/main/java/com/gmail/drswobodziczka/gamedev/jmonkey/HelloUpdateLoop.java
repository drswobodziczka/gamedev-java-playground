package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class HelloUpdateLoop extends SimpleApplication {

    private Spatial elephant;
    private Spatial ninja;

    public static void main(String[] args) {
        HelloUpdateLoop app = new HelloUpdateLoop();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initElephant();
        initNinja();
        setDirectionalLightOn();
    }

    private void setDirectionalLightOn() {
        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
    }

    private void initElephant() {
        elephant = assetManager.loadModel("Models/Elephant/Elephant.mesh.xml");
        elephant.scale(.15f);
        elephant.move(new Vector3f(-2, 0, -18));
        elephant.rotate(0, 180 * FastMath.DEG_TO_RAD, 0);
        rootNode.attachChild(elephant);
    }
    
    private void initNinja() {
        ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
        rootNode.attachChild(ninja);
    }

    /* Use the main event loop to trigger repeating actions. */
    @Override
    public void simpleUpdate(float tpf) {
        // make the players rotate
        ninja.rotate(0, 40 * tpf, 0);
        elephant.rotate(0, 2 * tpf, 0);
    }
}
