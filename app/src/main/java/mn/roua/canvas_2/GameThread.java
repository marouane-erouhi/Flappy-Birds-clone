package mn.roua.canvas_2;


import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.widget.ListView;


class GameThread extends Thread{
    private GameView view;
    private boolean running = true;

    GameThread(GameView view){
        this.view = view;
    }

    void setRunning(boolean run){
        running = run;
    }

    @Override
    public void run() {
        while(running){
            Canvas canvas = null;
            try{
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.draw(canvas);
                }
            }finally {
                if(canvas != null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
