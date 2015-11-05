package net.xaviersala.peixera.pantalles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.xaviersala.peixera.PeixeraGame;


public class PantallaMenu extends Stage implements Screen {

  final PeixeraGame joc;
  private Texture fons;
  private Texture start;


  public PantallaMenu(PeixeraGame game) {
    super(new StretchViewport(PeixeraGame.AMPLEPANTALLA, PeixeraGame.ALTPANTALLA, new OrthographicCamera()));
    joc = game;


    fons = new Texture( Gdx.files.internal("menu.png") );
    start = new Texture(Gdx.files.internal("start.png"));
    // Carregar el joc


    carregaJoc();
  }

  private void carregaJoc() {
    Image bg = new Image(fons);
    addActor(bg);

    // És una mica recargolat però es crea un botó d'aquesta forma
    ImageButton btnStart = new ImageButton(new TextureRegionDrawable(new TextureRegion(start)));

    btnStart.setPosition(3 * getWidth() / 4, 120.f, Align.center);
    addActor(btnStart);

    // Afegir un Listener al botó per canviar de pantalla....
    btnStart.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            joc.setScreen(new PantallaJoc(joc));
            return false;
          }
        }
    );

//    btnExit.addListener(
//        new InputListener() {
//          @Override
//          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//            Gdx.app.exit();
//            return false;
//          }
//        });

  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    super.act(delta);
    super.draw();

  }
  @Override
  public void resize(int width, int height) {
    getViewport().update(width, height);

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
    super.dispose();
    fons.dispose();
    start.dispose();

  }


}
