package mn.roua.canvas_2;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

class MainMenu {
    private Button playButton;
    private Button highScoreButton;
    private View view;

    private int buttonWidth = 300;

    private int middleX;
    private int middleY;

    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    MainMenu(View view){
        this.view = view;

        middleX = (screenWidth / 2) - (buttonWidth/2);
        middleY = (screenHeight / 2) - (buttonWidth/2);

        playButton = new Button( new Sprite(BitmapFactory.decodeResource(view.getResources() ,R.drawable.play),
                buttonWidth,buttonWidth ),
                middleX, middleY-buttonWidth );

        highScoreButton = new Button( new Sprite(BitmapFactory.decodeResource(view.getResources() ,R.drawable.highscore),
                buttonWidth,buttonWidth ),
                middleX, middleY+50 );
    }

    void draw(Canvas canvas){
        playButton.draw(canvas);
        highScoreButton.draw(canvas);
    }

    int handleCollisions(float x, float y){
        if(playButton.handelClick(x,y)){
            //change the play icon to repeat
            playButton.changeSprite(new Sprite(BitmapFactory.decodeResource(view.getResources() ,R.drawable.repeat),
                    buttonWidth,buttonWidth ));
            //go to game screen
            return 2;
        }

        if(highScoreButton.handelClick(x,y)){
            //change the play icon to repeat
            playButton.changeSprite(new Sprite(BitmapFactory.decodeResource(view.getResources() ,R.drawable.repeat),
                    buttonWidth,buttonWidth ));
            //go to high score
            return 1;
        }

        return 0;
    }


}
