package net.xaviersala.peixera.pantalles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import net.xaviersala.peixera.PeixeraGame;


public class PantallaMenu implements Screen {

  final PeixeraGame joc;
  OrthographicCamera camera;

  public PantallaMenu(PeixeraGame game) {
    joc = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, PeixeraGame.AMPLEPANTALLA, PeixeraGame.ALTPANTALLA);
  }

  @Override
  public void show() {
    // TODO Auto-generated method stub

  }
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    joc.batch.setProjectionMatrix(camera.combined);

    joc.batch.begin();
    joc.font.draw(joc.batch, "El joc de la peixera", 100, 150);
    joc.font.draw(joc.batch, "clica per començar!", 100, 100);
    joc.batch.end();

    if (Gdx.input.isTouched()) {
        joc.setScreen(new PantallaJoc(joc));
        dispose();
    }
  }
  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }
  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }
  @Override
  public void resume() {
    // TODO Auto-generated method stub

  }
  @Override
  public void hide() {
    // TODO Auto-generated method stub

  }
  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }


}
