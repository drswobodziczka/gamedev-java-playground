package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class HelloJMonkey extends SimpleApplication {

    public static void main(String[] args) {
        HelloJMonkey app = new HelloJMonkey();

        AppSettings settings = new AppSettings(true);
        settings.setTitle("Hello Monkey Game!");
        app.setSettings(settings);

        app.start();

    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 5);
        Geometry geom = new Geometry("Boxsdasad", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
}
