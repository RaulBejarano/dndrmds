package dandremids.src.threads;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import dandremids.src.views.CreatureView;

public class CreatureViewLoopThread extends Thread {
    private CreatureView view;
    private boolean running = false;
   
    public CreatureViewLoopThread(CreatureView view) {
          this.view = view;
    }

    static final long FPS = 30;
  
    public void setRunning(boolean run) {
          running = run;
    }

    @SuppressLint("WrongCall")
	@Override
    public void run() {
          long ticksPS = 1000 / FPS;
          long startTime;
          long sleepTime;
          while (running) {
                 Canvas c = null;
                 startTime = System.currentTimeMillis();
                 try {
                        c = view.getHolder().lockCanvas();
                        synchronized (view.getHolder()) {
                               view.onDraw(c);
                        }
                 } finally {
                        if (c != null) {
                               view.getHolder().unlockCanvasAndPost(c);
                        }
                 }
                 sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
                 try {
                        if (sleepTime > 0)
                               sleep(sleepTime);
                        else
                               sleep(10);
                 } catch (Exception e) {}
          }
    }
} 
