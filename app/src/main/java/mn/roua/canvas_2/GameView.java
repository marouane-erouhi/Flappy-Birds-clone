package mn.roua.canvas_2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    private GameThread gameThread;

    private SurfaceHolder holder;

    public static int gapHeight = 500;
    public static int velocity = 10;

    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    static int currentScreen = 0;

    Paint paint;


    private MainMenu mainMenu;
    private Game game;
    private HighScoreScreen highScoreScreen;

    public GameView(Context context) {
        super(context);
        gameThread = new GameThread(this);

        mainMenu = new MainMenu(this);
        highScoreScreen = new HighScoreScreen(this, context);
        game = new Game(this, context);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);




        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {


                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if(currentScreen == 0) {//main menu
            currentScreen = mainMenu.handleCollisions(event.getX(), event.getY());
        }else if(currentScreen == 1){ //high score
            currentScreen = highScoreScreen.handleCollisions(event.getX(), event.getY());
        }else if(currentScreen == 2){//game screen
            currentScreen = game.handleCollisions();
        }else{//go back to main menu
            currentScreen = 0;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas)
    {
        if(canvas!=null) {
            super.draw(canvas);

            canvas.drawPaint(paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(150);

            canvas.drawRGB(0, 100, 205);



            if(currentScreen == 0) {//main menu
                mainMenu.draw(canvas);
            }else if(currentScreen == 1){ //high score
                highScoreScreen.draw(canvas);
                paint.setTextSize(300);
                canvas.drawText(""+highScoreScreen.score, (screenWidth/2)-70, (screenHeight/2-70), paint);
            }else if(currentScreen == 2){//game screen
                game.draw(canvas);
                paint.setTextSize(150);
                canvas.drawText(""+game.score, (screenWidth/2)-70, 150, paint);
            }else{//go back to main menu
                currentScreen = 0;
            }

        }
    }

}







