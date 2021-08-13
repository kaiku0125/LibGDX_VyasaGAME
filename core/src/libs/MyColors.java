package libs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

public enum MyColors {
    MAYA_BLUE("maya_blue",77f/255f, 195f/255f, 255f/255f, 1),
    TEXT_DARK_BLUE("text_dark_blue", 102/255f, 21/255f, 255/255f, 1),
    TEXT_SHADOW_GREEN("text_shadow_green", 102f/255f, 255f/255f, 102f/255f, 1);

    private float r, g, b, alpha;
    private String name;
    MyColors(String name, float r, float g, float b, float alpha){
        this.name = name;
        this.r = r;
        this.g = g;
        this. b = b;
        this.alpha = alpha;
    }

    public static void glClearColor(MyColors color){
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.alpha);
    }

    private String getName(){
        return name;
    }

    public float r(){
        return r;
    }

    public float g(){
        return g;
    }

    public float b(){
        return b;
    }

    public float a(){
        return alpha;
    }
}
