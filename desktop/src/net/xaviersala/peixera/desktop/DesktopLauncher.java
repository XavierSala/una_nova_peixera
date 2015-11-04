package net.xaviersala.peixera.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.xaviersala.peixera.PeixeraGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Peixera";
    config.width = 800;
    config.height = 480;
		new LwjglApplication(new PeixeraGame(), config);
	}
}
