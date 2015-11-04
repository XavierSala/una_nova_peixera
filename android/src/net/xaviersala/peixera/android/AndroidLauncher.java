package net.xaviersala.peixera.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.xaviersala.peixera.PeixeraGame;

public class AndroidLauncher extends AndroidApplication {
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useAccelerometer = false;
    config.useCompass = false;
    initialize(new PeixeraGame(), config);
  }
}
