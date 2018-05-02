package mn.roua.canvas_2;

import android.graphics.Canvas;

class Player {
    int y , x;
    int w, h;
    private float lift = -14;
    private float gravity = 0.6f;

    private float velY = 0.0f;

    private Sprite sprite;

    Player(Sprite sprite){
        x = y = 100;
        w = sprite.w;
        h = sprite.h;
        this.sprite = sprite;
    }

    void draw(Canvas canvas){
        sprite.draw(canvas, this.x, this.y);
    }

    void update() {
        this.velY += this.gravity;
        this.y += this.velY;
    }

    void up(){
        this.velY = this.lift;
    }
}
