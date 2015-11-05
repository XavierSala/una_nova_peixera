package net.xaviersala.peixera.pantalles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.xaviersala.peixera.PeixeraGame;

public class PantallaSplash extends Stage implements Screen {

  PeixeraGame joc;
  private boolean timerIsOn;
  private Texture splsh;

  public PantallaSplash(PeixeraGame peixeraGame) {

    super(new StretchViewport(PeixeraGame.AMPLEPANTALLA, PeixeraGame.ALTPANTALLA, new OrthographicCamera()));
    joc = peixeraGame;
    splsh = new Texture(Gdx.files.internal("splash.png"));
    Image bg = new Image(splsh);
    addActor(bg);
  }

  @Override
  public void show() {
    // splsh = new Texture(Gdx.files.internal("splash.png"));
    Gdx.input.setInputProcessor(this);

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    super.act(delta);
    super.draw();

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
    getViewport().update(width, height);
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
