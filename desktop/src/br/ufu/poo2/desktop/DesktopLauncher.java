package br.ufu.poo2.desktop;

import br.ufu.poo2.SimpleGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SpaceJ";
		config.width = 1366;
		config.height = 768;
		new LwjglApplication(new SimpleGame(), config);
	}
}
