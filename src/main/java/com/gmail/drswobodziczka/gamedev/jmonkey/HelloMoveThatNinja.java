package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class HelloMoveThatNinja extends SimpleApplication {

    private Spatial ninja;
    private Node world;
    private InputListener analogListener;

    public static void main(String[] args) {
        HelloMoveThatNinja app = new HelloMoveThatNinja();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initWorld();
        initNinja();
        initAnalogListener();
        initKeys();
        speed = 10f;
    }

    private void initAnalogListener() {
        analogListener = (AnalogListener) (name, value, tpf) -> {
            if (name.equals("Right")) {
                Vector3f v = ninja.getLocalTranslation();
                ninja.setLocalTranslation(v.x + value * speed, v.y, v.z);
            }
            if (name.equals("Left")) {
                Vector3f v = ninja.getLocalTranslation();
                ninja.setLocalTranslation(v.x - value * speed, v.y, v.z);
            }
            if (name.equals("Up")) {
                Vector3f v = ninja.getLocalTranslation();
                ninja.setLocalTranslation(v.x, v.y, v.z - value * speed);
            }
            if (name.equals("Down")) {
                Vector3f v = ninja.getLocalTranslation();
                ninja.setLocalTranslation(v.x, v.y, v.z + value * speed);
            }
        };
    }

    private void initWorld() {
        world = new Models(assetManager).createWorld();
        rootNode.attachChild(world);
    }

    private void initNinja() {
        ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.02f);
        ninja.rotate(0 * FastMath.DEG_TO_RAD, 0 * FastMath.DEG_TO_RAD, 0 * FastMath.DEG_TO_RAD);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
        rootNode.attachChild(ninja);
        setDirectionalLightOn();
    }

    private void setDirectionalLightOn() {
        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up",  new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        // Add the names to the action listener.
        inputManager.addListener(analogListener, "Left", "Right", "Up", "Down");

    }

    /* Use the main event loop to trigger repeating actions. */
    @Override
    public void simpleUpdate(float tpf) {
//        world.getChildren().forEach(g -> g.move(new Models(assetManager).randomVector()));
        world.getChildren().stream()
                .peek(g -> {
                    Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    material.setColor("Color", ColorRGBA.randomColor());
                    g.setMaterial(material);
                })
                .forEach(g -> g.move(new Vector3f(0.01f, 0.01f,0.01f)));
    }
}
