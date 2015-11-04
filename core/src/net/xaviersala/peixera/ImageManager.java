package net.xaviersala.peixera;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public class ImageManager {
  ObjectMap<String, Texture> imatges;

  public ImageManager() {
    imatges = new ObjectMap<String, Texture>();
  }

  public void afegirImatge(String descripcio, Texture imatge) {
    imatges.put(descripcio, imatge);
  }

  public Texture obtenirImatge(String nom) {
    return imatges.get(nom);
  }

  public void destrueix() {
    Iterator<String> claus = imatges.keys();
    while(claus.hasNext()) {
      String clau = claus.next();
      imatges.get(clau).dispose();
    }
  }

}
