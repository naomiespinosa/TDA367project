package model;

import java.util.TimerTask;
import model.Timer.Callback;

/** A callable task which is executed on timer events. */
public class CallableTask extends TimerTask {
  private Callback callback;

  /**
   * Constructor.
   *
   * @param callback The callback to execute.
   */
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
