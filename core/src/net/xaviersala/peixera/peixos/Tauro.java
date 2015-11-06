package net.xaviersala.peixera.peixos;

import com.badlogic.gdx.graphics.Texture;

public class Tauro extends Peix {

  public Tauro(Texture image, int sex, float x, float y) {
    super(image, sex, x, y);

  }

  @Override
  public boolean elMates(Peix p) {
    if (p instanceof Tauro && getSexe() != p.getSexe()) return false;
    return true;
  }



}
