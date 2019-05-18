package Mainpack;


import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;




public class GameHero {

    // Engine Atributes
    protected int x;
    protected int y;
    protected boolean[] step = new boolean[17];
    protected int status;
    protected int walkspeed;
    protected int walkend;
    protected int walknow;
    protected int timewalk;
    protected boolean steptime;
    protected int prevposition;
    public int xcell;
    public int ycell;
    public Sprite sprite;
    private Image img;
    protected int LastDirection = 2;
    // Game Atributes
    private int hp;
    private int mp;
    

    public GameHero() {
        
        
        this.status = 9;
        this.steptime=false;
        

        this.x = -304;
        this.y = -32;
        this.walkspeed = 800;

        try {
            img = Image.createImage("/sprite.png");
            }   catch(Exception e){}
        sprite = new Sprite(img,16,24);
        this.sprite.setPosition(224, 296);
    }

    int move(int direction) {

        switch(direction) {
            case 0:
                if(this.status == 9) {
                    this.prevposition=this.x-16;
                    if(this.steptime==false) { this.steptime=true; } else { this.steptime=false; }
                    this.status = 0; for(int q=1; q<17; q++) { step[q]=false; } timewalk=0;
                    this.walkend = (int) System.currentTimeMillis();
                    if (this.walkend<0) { this.walkend = this.walkend * -1; }
                    this.walkend = this.walkend - this.walkspeed;
                }
            break;
            case 1:
                if(this.status == 9) {
                    this.prevposition=this.x+16;
                    this.status = 1; for(int q=1; q<17; q++) { step[q]=false; } timewalk=0;
                    this.walkend = (int) System.currentTimeMillis();
                    if (this.walkend<0) { this.walkend = this.walkend * -1; }
                    this.walkend = this.walkend - this.walkspeed;
                }
            break;
            case 2:
                if(this.status == 9) {
                    this.prevposition=this.y-16;
                    this.status = 2; for(int q=1; q<17; q++) { step[q]=false; } timewalk=0;
                    this.walkend = (int) System.currentTimeMillis();
                    if (this.walkend<0) { this.walkend = this.walkend * -1; }
                    this.walkend = this.walkend - this.walkspeed;
                }
            break;
            case 3:
                if(this.status == 9) {
                    this.prevposition=this.y+16;
                    this.status = 3; for(int q=1; q<17; q++) { step[q]=false; } timewalk=0;
                    this.walkend = (int) System.currentTimeMillis();
                    if (this.walkend<0) { this.walkend = this.walkend * -1; }
                    this.walkend = this.walkend - this.walkspeed;
                }
                
           break;
        } this.sprite.setPosition(this.x, this.y);



        return 0;
    }

    void walksync() {
        int timefunc;
        this.walknow = (int) System.currentTimeMillis();
        if(this.walknow < 0) { this.walknow = this.walknow * -1; }
        timefunc = this.walknow - this.walkend;


        
            if(timefunc < this.walkspeed && timefunc > this.walkspeed - ((this.walkspeed/5)*1) ) {
            
                switch(this.status) {
                    case 2: this.sprite.setFrame(0); break;
                    case 3: this.sprite.setFrame(12); break;
                    case 1: this.sprite.setFrame(4); break;
                    case 0: this.sprite.setFrame(11); break;
                }
            
            }

            if(timefunc < this.walkspeed - ((this.walkspeed/5)*1))   {

                switch(this.status) {
                    case 2: this.sprite.setFrame(1); break;
                    case 3: this.sprite.setFrame(13); break;
                    case 1: this.sprite.setFrame(5); break;
                    case 0: this.sprite.setFrame(10); break;
                }
            }

            if(timefunc < this.walkspeed - ((this.walkspeed/5)*2))   {

                switch(this.status) {
                    case 2: this.sprite.setFrame(2); break;
                    case 3: this.sprite.setFrame(14); break;
                    case 1: this.sprite.setFrame(6); break;
                    case 0: this.sprite.setFrame(9); break;
                }
            }

            if(timefunc < this.walkspeed - ((this.walkspeed/5)*3))   {

                switch(this.status) {
                    case 2: this.sprite.setFrame(3); break;
                    case 3: this.sprite.setFrame(15); break;
                    case 1: this.sprite.setFrame(7); break;
                    case 0: this.sprite.setFrame(8); break;
                }
            }

            if(timefunc < this.walkspeed - ((this.walkspeed/5)*4))   {

                switch(this.status) {
                    case 2: this.sprite.setFrame(0);  break;
                    case 3: this.sprite.setFrame(12); break;
                    case 1: this.sprite.setFrame(4);  break;
                    case 0: this.sprite.setFrame(11); break;
                }
            }

            //if(timefunc < 0) { this.status = 9; timewalk=0; for(int q=1; q<17; q++) { step[q]=false; } }

        //####################################################################
        
        

            if (this.status < 5) {
               
                if(timefunc < (this.walkend/16)*16-(timewalk+1)) { if(step[timewalk]==false) {step[timewalk]=true; if(this.status == 2) { this.y--; } if(this.status == 3) { this.y++; } if(this.status == 1) { this.x++; } if(this.status == 0) { this.x--; } }}
                
                if(timewalk<16){timewalk++;}
                
                
                }

                if(step[16]==true) {
                   
                    switch(this.status){
                        case 2: this.sprite.setFrame(0); this.y = this.prevposition; break;
                        case 3: this.sprite.setFrame(12); this.y = this.prevposition; break;
                        case 1: this.sprite.setFrame(4); this.x = this.prevposition; break;
                        case 0: this.sprite.setFrame(11); this.x = this.prevposition; break;
                    }
                    
                    timefunc= -1;  this.status = 9; timewalk=0; for(int q=1; q<17; q++) { step[q]=false;}
                }


        
                if(this.LastDirection==6) { if(this.status==9) { this.sprite.setFrame(11); }}
                if(this.LastDirection==4) { if(this.status==9) { this.sprite.setFrame(4); }}
                if(this.LastDirection==8) { if(this.status==9) { this.sprite.setFrame(12); }}
                if(this.LastDirection==2) { if(this.status==9) { this.sprite.setFrame(0); }}
    }

}
