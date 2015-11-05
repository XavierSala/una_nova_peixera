package net.xaviersala.peixera.pantalles;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import net.xaviersala.peixera.ImageManager;
import net.xaviersala.peixera.PeixeraGame;
import net.xaviersala.peixera.peixos.Peix;

public class PantallaJoc implements Screen {

  private static final int NUMERODEPEIXOSACREAR = 25;
  private static final int QUANTITATSEXES = 2;
  private static final String[] IMATGESDEPEIXOS = { "peix1", "peixa1" };
  /*
   * Referència al objecte principal.
   */
  private PeixeraGame joc;
  OrthographicCamera camera;
  /**
   * Coordenades de la pantalla. Es fa servir bàsicament per comprovar si
   * algun dels objectes se n'ha anat de la pantalla.
   */
  private Rectangle pantalla;
  /**
   * Objecte per mantenir les imatges i evitar haver de tenir-ne moltes.
   */
  ImageManager imatges;
  /**
   * Array que té tots els peixos de la peixera.
   */
  Array<Peix> peixos;
  /**
   * Llista dels nous peixos (per evitar que els matin els seus pares).
   */
  Array<Peix> bebes;


  public PantallaJoc(PeixeraGame joc) {
    this.joc = joc;

    camera = new OrthographicCamera();
    pantalla = new Rectangle(0, 0, PeixeraGame.AMPLEPANTALLA, PeixeraGame.ALTPANTALLA);
    camera.setToOrtho(false, pantalla.getWidth(), pantalla.getHeight());


    // Gestor per reutilitzar les imatges
    imatges = new ImageManager();

    // Carregar les imatges
    for(String imatgeDePeix: IMATGESDEPEIXOS) {
       imatges.afegirImatge(imatgeDePeix, new Texture(imatgeDePeix+".png"));
    }

    peixos = new Array<Peix>();
    bebes = new Array<Peix>();

    // Crear els peixos dels dos sexes
    for(int j=0; j < QUANTITATSEXES; j++) {
      crearPeixos(j, NUMERODEPEIXOSACREAR);
    }


  }
  /**
   * Crea una quantitat de peixos i la posa en l'array de peixos.
   * @param imatgeDelPeix Imatge que també diu de quin sexe són els peixos (parell: mascle, senar: femella)?
   * @param quants Quants peixos es volen crear
   */
  private void crearPeixos(int imatgeDelPeix, int quants) {
    for(int i=0; i < quants; i++) {
       peixos.add(crearPeixEnPosicioAleatoria(imatgeDelPeix));
    }
  }

  /**
   * Crea un peix amb el sexe definit per l'índex de la imatge en una posició
   * aleatòria dins de la pantalla.
   *
   * @param numeroDePeix Índex de la imatge del peix
   * @return Peix creat
   */
  private Peix crearPeixEnPosicioAleatoria(int numeroDePeix) {
    Texture imatgePeix = imatges.obtenirImatge(IMATGESDEPEIXOS[numeroDePeix]);
     return new Peix(imatgePeix, numeroDePeix % 2,
         MathUtils.random(0, pantalla.getWidth() - imatgePeix.getWidth()),
         MathUtils.random(0, pantalla.getHeight()-imatgePeix.getHeight())
     );
  }


  /**
   * Crea un peix en una posició determinada.
   *
   * @param numeroDePeix Quin peix és el que s'ha de crear
   * @param x posició x
   * @param y posicio y
   * @return Peix creat
   */
  private Peix crearPeixA(int numeroDePeix, float x, float y) {
    Texture imatgePeix = imatges.obtenirImatge(IMATGESDEPEIXOS[numeroDePeix]);
    return new Peix(imatgePeix, numeroDePeix %2, x, y);
  }

  @Override
  public void show() {


  }

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0f, 0.73f, 0.83f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    joc.batch.setProjectionMatrix(camera.combined);

    joc.batch.begin();
    // Pinta els peixos
    for (Peix peix: peixos) {
      peix.pinta(joc.batch);
    }

    // Pinta els bebes
    for(Peix bebe: bebes) {
      bebe.pinta(joc.batch);
    }

    joc.batch.end();

    int i = 0;
    Iterator<Peix> iter = peixos.iterator();
    while(iter.hasNext()) {
      Peix peix = iter.next();
      peix.mou();

      if (peix.esMort() ) {
        // Els morts no els mato inmediatament
        // per fer un efecte de desaparició
        // if (peix.isDesaparegut()) {
        //  iter.remove();
        // }
      } else {
      // Encara està dins de la pantalla?
          tornarALaPantalla(peix);
          // Mirar si xoca
          Peix x = comprovaSiXoca(peix, i);
          if (x != null) {
            if (peix.getSexe() == x.getSexe()) {
              peix.mort();
              x.mort();
            } else {
              // S'estimen
              if (!peix.isQuarentena() && !x.isQuarentena()) {
                creaUnNouPeix(peix.getPosicio());
                peix.quarentena();
                x.quarentena();
              }
            }
          }
      }
      i++;
    }
    // Mira si els bebes poden entrar
    Iterator<Peix> iterBebe = bebes.iterator();
    while(iterBebe.hasNext()) {
       Peix bebe = iterBebe.next();
       if (comprovaSiXoca(bebe, -1) == null) {
          // Posar el bebe a l'altra llista
          peixos.add(bebe);
          iterBebe.remove();
       }
    }

  }


  /**
   * Crea un peix aleatòriament.
   * @param rectangle
   */
  private void creaUnNouPeix(Rectangle rectangle) {
     int tria = MathUtils.random(0, IMATGESDEPEIXOS.length - 1);
     bebes.add(crearPeixA(tria, rectangle.x, rectangle.y));

  }

  private Peix comprovaSiXoca(Peix peix, int i) {
    for (int j = 0; j < peixos.size; j++) {

      if (i != j) {
        Peix peixot = peixos.get(j);
        if (!peixot.esMort()) {
          if (peix.getPosicio().overlaps(peixot.getPosicio())) {
            return peixot;
          }
        }
      }
    }
    return null;
  }
  /**
   * @param peix
   * @param posicioPeix
   */
  private void tornarALaPantalla(Peix peix) {
    Rectangle posicioPeix = peix.getPosicio();
    if (!pantalla.overlaps(posicioPeix)) {
      if (posicioPeix.getX() < 0) {
        peix.setPosicioX(pantalla.getWidth());
      } else if (posicioPeix.getY() < 0) {
        peix.setPosicioY(pantalla.getHeight());
      } else {
        peix.setPosicioX(posicioPeix.getX() % pantalla.getWidth());
        peix.setPosicioY(posicioPeix.getY() % pantalla.getHeight());
      }
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
    imatges.destrueix();
  }

}
