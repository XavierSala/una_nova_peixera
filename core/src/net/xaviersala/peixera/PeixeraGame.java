package net.xaviersala.peixera;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.xaviersala.peixera.pantalles.PantallaSplash;


public class PeixeraGame extends Game {

    public static final int ALTPANTALLA = 480;
    public static final int AMPLEPANTALLA = 800;

    public SpriteBatch batch;
    public BitmapFont font;
    public Music mar;

    @Override
    public void create() {
      batch = new SpriteBatch();
      //Use LibGDX's default Arial font.
      font = new BitmapFont();
      mar = Gdx.audio.newMusic(Gdx.files.internal("mar.mp3"));
      mar.setLooping(true);
      mar.play();
      this.setScreen(new PantallaSplash(this));

    }

    public void render() {
      super.render();
    }

    public void dispose() {
      batch.dispose();
      font.dispose();
  }

}
