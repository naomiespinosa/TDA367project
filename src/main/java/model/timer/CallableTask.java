package model.timer;

import java.util.TimerTask;
import model.timer.Timer.Callback;

public class CallableTask extends TimerTask {
  private Callback callback;

  public CallableTask(final Callback callback) {
    this.callback = callback;
  }

  @Override
  public void run() {
    if (callback == null) {
      return;
    }

    callback.callback();
  }
}
