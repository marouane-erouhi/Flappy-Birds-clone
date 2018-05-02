package mn.roua.canvas_2;

import android.graphics.Canvas;

class Button {
    private Sprite sprite;
    private int w,h;
    private int x,y;

    Button(Sprite sp, int x, int y){
        this.x = x; this.y = y;
        sprite = sp;
        w = sprite.w;
        h = sprite.h;
    }

    void draw(Canvas canvas){
        sprite.draw(canvas, x,y);
    }
    boolean handelClick(float x, float y){
        if(x > this.x && x < this.x+this.w){
            if(y > this.y && y < this.y+this.h){
                return true;
            }
        }
        return false;
    }

    void changeSprite(Sprite sprite){
        this.sprite = sprite;
        w = sprite.w;
        h = sprite.h;
    }

}
