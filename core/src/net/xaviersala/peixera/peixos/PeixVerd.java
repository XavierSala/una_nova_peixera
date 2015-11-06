package net.xaviersala.peixera.peixos;

import com.badlogic.gdx.graphics.Texture;

public class PeixVerd extends Peix {

  public PeixVerd(Texture image, int sex, float x, float y) {
    super(image, sex, x, y);

  }

  @Override
  public boolean elMates(Peix p) {
    if (p instanceof PeixVerd && p.getSexe() == getSexe()) return true;
    return false;
  }

}
