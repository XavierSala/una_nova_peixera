package net.xaviersala.peixera.pantalles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import net.xaviersala.peixera.PeixeraGame;

public class PantallaSplash implements Screen {

  PeixeraGame joc;
  private boolean timerIsOn;
  private Texture splsh;

  public PantallaSplash(PeixeraGame peixeraGame) {
    joc = peixeraGame;
  }

  @Override
  public void show() {
    splsh = new Texture(Gdx.files.internal("splash.png"));

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    joc.batch.begin();
    joc.batch.draw(splsh, 0, 0);
    joc.batch.end();

       if(!timerIsOn) {
          timerIsOn = true;

          Timer.schedule(new Task() {

             @Override
             public void run() {
               joc.setScreen(new PantallaMenu(joc));
             }

          }, 2);

       } else if(Gdx.input.isTouched()) {
            // Remove the task so we don't call changeScreen twice:
            Timer.instance().clear();
            joc.setScreen(new PantallaMenu(joc));
       }

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    splsh.dispose();

  }

}
