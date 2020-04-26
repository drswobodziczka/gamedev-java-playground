package com.gmail.drswobodziczka.gamedev.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import org.jetbrains.annotations.NotNull;

/**
 * Ninja and elephant in the town!
 */
public class HelloAssets extends SimpleApplication {

    public static void main(String[] args) {
        HelloAssets app = new HelloAssets();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        // attach teapot
        Spatial teapot = createTeapotSpacialFromModel();
        rootNode.attachChild(teapot);

        // Create a wall with a simple texture from test_data
        Spatial wall = createWallWithTexture();
        rootNode.attachChild(wall);

        // Display a line of text with a default font
        displayHelloMessageOnGuiNode();

        // Load a model from test_data (OgreXML + material + texture)
        Spatial ninja = createNinjaFromMeshModel();
        rootNode.attachChild(ninja);
        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

        Spatial elephant = createElephantFromMeshModel();
        rootNode.attachChild(elephant);

//        Spatial scene = assetManager.loadModel("Scenes/ManyLights/Main.scene");
//        rootNode.attachChild(scene);

        // load scene
        Spatial gameLevel = loadSceneFromAssets();
        rootNode.attachChild(gameLevel);
    }

    @NotNull
    private Spatial loadSceneFromZip() {
        assetManager.registerLocator("town.zip", ZipLocator.class);
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        return gameLevel;
    }

    /*
    * NOTE: this would work out-of he box in SDK, as myproject/assets dir would be on classpath.
    * Here in gradle project we workaround this issue with FileLocator.
    * */
    private Spatial loadSceneFromAssets() {
        assetManager.registerLocator("assets", FileLocator.class);
        Spatial gameLevel = assetManager.loadModel("/Scenes/town/main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        return gameLevel;
    }

    @NotNull
    private Spatial createElephantFromMeshModel() {
        Spatial elephant = assetManager.loadModel("Models/Elephant/Elephant.mesh.xml");
        elephant.scale(.15f);
        elephant.move(new Vector3f(-2, 0, -15));
        elephant.rotate(0, 180 * FastMath.DEG_TO_RAD, 0);
        return elephant;
    }

    @NotNull
    private Spatial createNinjaFromMeshModel() {
        Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
        return ninja;
    }

    private void displayHelloMessageOnGuiNode() {
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("I'm dead;y ninja createTeapotSpacialFromModel!");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);
    }

    @NotNull
    private Spatial createWallWithTexture() {
        Box box = new Box(2.5f, 2.5f, 1.0f);
        Spatial wall = new Geometry("Box", box);
        Material mat_brick = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap",
                assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        wall.setMaterial(mat_brick);
        wall.setLocalTranslation(2.0f, -2.5f, 0.0f);
        return wall;
    }

    @NotNull
    private Spatial createTeapotSpacialFromModel() {
        Spatial teapot = assetManager.loadModel("Models/Teapot/Teapot.obj");
        Material mat_default = new Material(
                assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        teapot.setMaterial(mat_default);
        return teapot;
    }
}