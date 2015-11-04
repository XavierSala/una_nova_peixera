package net.xaviersala.peixera.peixos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Peix {
  private static final int VELOCITAT = 1;
  // He canviat la textura per un Sprite perquè són
  // més fàcils de girar, etc..
  Sprite imatge;
  int angle;
  int velocitat;
  boolean quarentena;
  private int sexe;
  private long lastSexTime;


  public Peix(Texture image, int sex, float x, float y) {
    imatge = new Sprite(image);
    sexe = sex;
    velocitat = VELOCITAT;

    imatge.setPosition(x, y);
    // imatge.setOrigin(image.getWidth()/2, image.getHeight()/2);
    imatge.setOriginCenter();

    int rotacio = MathUtils.random(-1, 2);
    angle = 90 * rotacio;

    if (rotacio == 2) {
      imatge.flip(true, false);
      rotacio = 0;
    }

    imatge.setRotation(rotacio * 90);
  }

  public int getSexe() {
    return sexe;
  }

  public void pinta(SpriteBatch batch) {
    // batch.draw(imatge, posicio.x, posicio.y);
    if (velocitat != 0) {
      imatge.draw(batch);
    }
  }

  public void mou() {
//    imatge.setPosition(imatge.getX() - MathUtils.cosDeg(angle),
//                       imatge.getY() - MathUtils.sinDeg(angle));
    imatge.translate(-MathUtils.cosDeg(angle) * velocitat, -MathUtils.sinDeg(angle) * velocitat);
    if (quarentena) {
      if(TimeUtils.timeSinceNanos(lastSexTime)/3 > 1000000000) {
        quarentena = false;
      }
    }
  }

  public Rectangle getPosicio() {
    return imatge.getBoundingRectangle();
  }

  public void setPosicioX(float x) {
    imatge.setX(x);
  }

  public void setPosicioY(float y) {
    imatge.setY(y);
  }

  public void mort() {
    velocitat = 0;
  }

  public boolean esMort() {
    return (velocitat == 0);
  }

  public void quarentena() {
    quarentena = true;
    lastSexTime = TimeUtils.nanoTime();

  }

  public boolean isQuarentena() {
    return quarentena;
  }

}
