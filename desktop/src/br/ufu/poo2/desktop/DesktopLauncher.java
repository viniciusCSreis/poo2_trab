package br.ufu.poo2.desktop;

import br.ufu.poo2.Poo2Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SpaceJ";
		config.width = getWidth();
		config.height = getHeight();
		new LwjglApplication(new Poo2Game(), config);
	}
	public static int getWidth(){
		return 1366;
	}

	public static int getHeight(){
		return 768;
	}
}
