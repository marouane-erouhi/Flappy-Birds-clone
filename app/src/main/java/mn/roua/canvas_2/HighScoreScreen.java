package mn.roua.canvas_2;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

class HighScoreScreen {
    private View view;
    private Context context;
    private Button backButton;

    int score = 0;

    private boolean scoreRetrieved = false;

    HighScoreScreen(View view, Context context){
        this.view = view;
        this.context = context;

        backButton = new Button( new Sprite(BitmapFactory.decodeResource(view.getResources() ,R.drawable.back),
                150,150 ),
                50, 100 );

    }


    void draw(Canvas canvas){
        backButton.draw(canvas);

        if(!scoreRetrieved){
            //get score
            SharedPreferences sharedPref = context.getSharedPreferences("roua.flappy.topScore", Context.MODE_PRIVATE);
            int highScore = sharedPref.getInt("roua.flappy.topScore1", -1);

            if(highScore != -1){
                score = highScore;
            }

            scoreRetrieved = true;
        }

    }

    int handleCollisions(float x, float y){
        if(backButton.handelClick(x,y)){
            scoreRetrieved = false;
            return 0;//go back to main
        }
        return 1;
    }
}
