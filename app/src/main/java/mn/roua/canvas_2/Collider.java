package mn.roua.canvas_2;


class Collider {
    static boolean pipeColl(Player player, Pipe pipe){
        //check collision for pipe1
        if(     player.x < pipe.x + pipe.w &&
                player.x+player.w > pipe.x &&
                player.y < pipe.y + pipe.h &&
                player.y+player.h > pipe.y)
            return true;

        //check collision for pipe2
        if(     player.x < pipe.x + pipe.w &&
                player.x+player.w > pipe.x &&

                player.y < pipe.y + (pipe.h*2) + Game.pipeGap &&
                player.y+player.h > pipe.y + pipe.h + Game.pipeGap)
            return true;

        return false;
    }

}
