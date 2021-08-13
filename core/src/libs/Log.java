package libs;

import com.badlogic.gdx.Gdx;

public class Log {
    public final static boolean DEBUG = false;

    public static void e (String tag, String msg){
        Gdx.app.error(tag, msg);
    }

}
