package Mainpack;

// Phantasy Star Mobile

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import java.io.IOException;



public class MainGameCanvas extends GameCanvas implements Runnable {

    private GameHero Alis;

    private int GameStage = 0;

// ##############################################################################
    private volatile boolean gameRunning;
    private long gameFrameDelay;
    private int gameState;
    public TiledLayer MapTile;
    private LayerManager MainLayer;
    private Player CityMusic;
    private Player SegaMusic;
    private Player TitleScreenMusic;
    private Player IntroMusic;
    private Sprite Sega;
    private Image ImgIntro1;
    private Sprite Intro1;
    private Image ImgIntro2;
    private Sprite Intro2;
    private Image ImgIntro3;
    private Sprite Intro3;
    private Image ImgIntro4;
    private Sprite Intro4;
    private Image ImgIntro5;
    private Sprite Intro5;
    private Image ImgIntro6;
    private Sprite Intro6;
    private Image ImgIntro7;
    private Sprite Intro7;
    private Image ImgIntro8;
    private Sprite Intro8;
    private Image ImgIntro9;
    private Sprite Intro9;
    private Image ImgIntro10;
    private Sprite Intro10;
    private Image ImgSega;
    private Image ImgTitleScreen;
    private Sprite TitleScreen;
    private Image ImgMenuArrow;
    private Sprite MenuArrow;
    private byte TileColision[][] = new byte[25][36];
    private int[] FadeScreen;
    private int FadeAlpha;
    private boolean FadeType;
    private boolean MenuSelection;
    private byte MenuLock  = 0;
    private int ArrowFlipFlop = 10;
    private boolean NextScene = false;
    private int TimeCount = 100;
    private int LetterScroll = 82;
    private boolean MenuLife = true;

    
    // ##############################################################################

    public MainGameCanvas () throws IOException {
            
            

            super (true);
            setFullScreenMode(true);
            FadeScreen = new int[getWidth() * getHeight()];
            FadeAlpha = 0;
            FadeType = false;
            gameState = 0;
            gameFrameDelay = 10;

            try {
                 CityMusic = Manager.createPlayer(getClass().getResourceAsStream("/city.mid"),"audio/midi");
                 SegaMusic = Manager.createPlayer(getClass().getResourceAsStream("/sega.mid"),"audio/midi");
                 TitleScreenMusic = Manager.createPlayer(getClass().getResourceAsStream("/titlescreen.mid"),"audio/midi");
                 IntroMusic = Manager.createPlayer(getClass().getResourceAsStream("/intro.mid"),"audio/midi");

            } catch(Exception e) { e.printStackTrace(); }

            try {
            ImgIntro1 = Image.createImage("/intro1.png");
            ImgIntro2 = Image.createImage("/intro2.png");
            ImgIntro3 = Image.createImage("/intro3.png");
            ImgIntro4 = Image.createImage("/intro4.png");
            ImgIntro5 = Image.createImage("/intro5.png");
            ImgIntro6 = Image.createImage("/intro6.png");
            ImgIntro7 = Image.createImage("/intro7.png");
            ImgIntro8 = Image.createImage("/intro8.png");
            ImgIntro9 = Image.createImage("/intro9.png");
            ImgIntro10 = Image.createImage("/intro10.png");
            ImgSega = Image.createImage("/sega.png");
            ImgTitleScreen = Image.createImage("/titlescreen.png");
            for(int f=0; f<FadeScreen.length; f++) { FadeScreen[f] = 0x00000000;  }
            ImgMenuArrow = Image.createImage("/menuarrow.png");
            }   catch(Exception e){}
            Intro1 = new Sprite(ImgIntro1);
            Intro2 = new Sprite(ImgIntro2);
            Intro3 = new Sprite(ImgIntro3);
            Intro4 = new Sprite(ImgIntro4);
            Intro5 = new Sprite(ImgIntro5);
            Intro6 = new Sprite(ImgIntro6);
            Intro7 = new Sprite(ImgIntro7);
            Intro8 = new Sprite(ImgIntro8);
            Intro9 = new Sprite(ImgIntro9);
            Intro10 = new Sprite(ImgIntro10);
            Sega = new Sprite(ImgSega,80,25);
            TitleScreen = new Sprite(ImgTitleScreen);
            MenuArrow = new Sprite(ImgMenuArrow);





            Alis = new GameHero();
            MapTile = createBoard();
            MainLayer = new LayerManager();
            MainLayer.append(Sega);
            MainLayer.append(Alis.sprite); Alis.sprite.setVisible(false);
            MainLayer.append(MapTile);
            MainLayer.append(Intro1); Intro1.setVisible(false); Intro1.setPosition(0, 0);
            MainLayer.append(Intro2); Intro2.setVisible(false); Intro2.setPosition(-8, 64);
            MainLayer.append(Intro3); Intro3.setVisible(false); Intro3.setPosition(-8, 64);
            MainLayer.append(Intro4); Intro4.setVisible(false); Intro4.setPosition(-8, 64);
            MainLayer.append(Intro5); Intro5.setVisible(false); Intro5.setPosition(-8, 64);
            MainLayer.append(Intro6); Intro6.setVisible(false); Intro6.setPosition(-8, 64);
            MainLayer.append(Intro7); Intro7.setVisible(false); Intro7.setPosition(-8, 64);
            MainLayer.append(Intro8); Intro8.setVisible(false); Intro8.setPosition(-8, 64);
            MainLayer.append(Intro9); Intro9.setVisible(false); Intro9.setPosition(-8, 64);
            MainLayer.append(Intro10); Intro10.setVisible(false); Intro10.setPosition(-8, 64);
            MainLayer.append(MenuArrow); MenuArrow.setVisible(false);
            MainLayer.append(TitleScreen); TitleScreen.setVisible(false); TitleScreen.setPosition(-8,24);
            
                    
            Sega.setPosition(80, 143);


            
        }

        private TiledLayer createBoard() throws IOException {
            Image ImgCamineet = Image.createImage("/board.png" +"" +""+"");
            TiledLayer tiledLayer = new TiledLayer(35, 24 , ImgCamineet, 16, 16);



            // Caminnet
            int[][] MapData = {
                { 1, 1, 1, 1,9,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,10},
                { 1, 1, 1, 1, 4, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2,17},
                { 1, 1, 1, 1, 4, 1, 2,42,42,42,42, 2, 1, 1, 1, 2, 2,20, 3, 3, 3, 3, 3, 3, 3, 2, 1, 1, 1, 2, 2, 7, 8, 2,17},
                { 1, 1, 1, 1, 4, 1, 1,43,26,27,43, 1, 1, 1, 1, 1, 2,28, 3, 3, 3, 3, 3, 3, 3, 2, 1, 2, 2, 2, 2,15,16, 2,17},
                { 1, 1, 1, 1, 4, 1, 2,43,34,35,43,43, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 1, 2, 5, 6, 1, 3, 3, 2,17},
                { 1, 1, 1, 1, 4, 1, 1, 3, 3, 3, 3, 3,43,42,42,42,42,42,42,42,42,42,42, 1, 3, 1, 1, 1,13,14, 1, 3, 2, 2,17},
                { 1, 1, 1, 1, 4, 1, 2, 3,43,43,43, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1,17},
                { 1, 1, 1, 1, 4, 1, 2, 3, 1, 1, 1,43,43,43,43, 3, 3,43,43,43,43,43,43, 2, 3, 2,42, 2,42, 2,42, 2, 1, 2,17},
                { 1, 1, 1, 1, 4, 1, 2, 3, 2, 2, 1, 1, 1, 2, 1,20, 3, 2, 1, 1, 2, 1, 2, 2, 3,43,43,43, 1, 1, 1,42,42,42,17},
                { 1, 1, 1, 1, 4,19, 2, 3, 2, 2, 2, 1, 1, 1, 2,28, 3, 1, 1, 2, 1, 2, 2, 2, 3, 5, 6,43, 1, 1, 2,33,19,19,17},
                { 1, 1, 1, 1, 4,19,19, 3, 1, 1, 2, 2, 1, 2, 1, 3, 3, 2, 1, 1, 2, 2, 1, 2, 3,13,14,43, 1, 1, 2,41,23,24,17},
                { 1, 1, 1, 1, 4,33,19,19, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3,19,31,32,17},
                {38,38,39,40,19,41,19,19, 1, 1, 2,26,27, 1, 1, 3, 3,21,22, 1,21,22,21,22, 3, 1, 1,43, 3, 2, 2,19,19,33,17},
                {46,46,47,48,19,33,19,19, 2,42,42,34,35, 1, 1, 3, 3,29,30, 1,29,30,29,30, 3, 1, 1,43, 3, 1, 2,19,19,41,17},
                { 1, 1, 1, 1, 4,41,19,19, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1,43, 3, 1, 1, 1, 1, 1,17},
                { 1, 1, 1, 1, 4,19, 3, 3, 3, 3, 3, 3,42,42,42,42,42, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1,43, 3, 1, 1, 1, 2, 2,17},
                { 1, 1, 1, 1, 4,19,43, 3, 3, 3, 3, 3,43,43,43,43, 2,20, 3, 2, 1, 2, 1,43, 3, 1, 1,43, 3, 2, 2, 2, 2, 1,17},
                { 1, 1, 1, 1, 4,19,43,42,42,42,43, 3, 1, 1, 1, 2, 2,28, 3, 1, 2, 1, 1,43, 3, 2, 1,43, 3, 2,36,37, 2, 2,17},
                { 1, 1, 1, 1, 4,19,19,19, 1, 2,43, 3, 1,19,19,19,19, 3, 3, 2, 2, 2,43,43, 3, 1, 2,43, 3, 2,44,45, 2, 1,17},
                { 1, 1, 1, 1, 4,19, 2,19, 2, 1,43, 3, 1,33,23,24,33, 3, 3, 1, 2, 2, 5, 6, 3, 1, 2, 2, 3, 3, 3, 3, 2, 2,17},
                { 1, 1, 1, 1, 4,19, 1, 2, 2, 2,43, 3, 2,41,31,32,41, 3, 3, 2, 2, 1,13,14, 3, 2, 2, 2,43,43,43,43, 2, 2,17},
                { 1, 1, 1, 1, 4,19,19, 2, 2,43,43, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 2, 2, 1,17},
                { 1, 1, 1, 1, 4,19,19,19, 2,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43,43, 2, 2, 2, 2, 2, 1, 1, 1, 2,17},
                { 1, 1, 1, 1,11,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,12}
            };

   
            for (int y = 0; y < 24; y++){
                for (int x = 0; x < 35; x++){
                    if(MapData[y][x] == 3 || MapData[y][x] == 19) { TileColision[y][x] = 0; } else { TileColision[y][x] = 1; }
                    tiledLayer.setCell(x, y, MapData[y][x]);
                }
            }

            MapData = null;
            return tiledLayer;

        }


    public void start () {

        gameRunning = true;

        Thread t = new Thread (this);
        t.start ();
    }

    public void stop() { gameRunning = false; }

    public void run() {
    
        Graphics g = getGraphics();

            while (gameRunning == true) {

                if(GameStage == 1) {
                if(Sega.getFrame() < 16) { Sega.nextFrame(); }
                if(SegaMusic.getState() == 100) { try { SegaMusic.start(); } catch(Exception e) { e.printStackTrace(); } }
                if(SegaMusic.getState() == 300) { GameStage = 2; FadeAlpha = 255; }
                } else { Sega.setVisible(false); }

                if(GameStage == 2) {
                    TitleScreen.setVisible(true);
                    MenuArrow.setVisible(true);
                    
                    if(TitleScreenMusic.getState() == 100 || TitleScreenMusic.getState() == 300) { try { TitleScreenMusic.start(); } catch(Exception e) { e.printStackTrace(); } }

                    if(ArrowFlipFlop > 0) { ArrowFlipFlop--; } else { ArrowFlipFlop = 10; }
                    if(ArrowFlipFlop<8) { MenuArrow.setVisible(true); } else { MenuArrow.setVisible(false); }
                    
                    if(MenuSelection == false) { MenuArrow.setPosition(65, 152); } else { MenuArrow.setPosition(65, 167); }
                
                    if(NextScene == true) {  if(FadeAlpha==255) { try { TitleScreenMusic.stop(); FadeType = false;} catch(Exception e) { e.printStackTrace(); } GameStage = 3; NextScene=false; } }
                } else { TitleScreen.setVisible(false); MenuArrow.setVisible(false); }

                if(GameStage == 3) {
                    Intro1.setVisible(true); 
                    if(Intro1.getY() > -120) { if(FadeAlpha==0) { Intro1.move(0, -1); } } else { if(TimeCount > 0) { TimeCount--; } else { FadeType=true; NextScene=true; }  }
                    if(NextScene == true) {  if(FadeAlpha==255) { FadeType = false; GameStage = 4; Intro2.setVisible(true); TimeCount=100; NextScene=false; } }
                } else { Intro1.setVisible(false); }

                if(GameStage >= 3 && GameStage <= 12) { if(IntroMusic.getState() == 300 || IntroMusic.getState() == 100) { try { IntroMusic.start(); } catch(Exception e) { e.printStackTrace(); } } }

                if(GameStage == 4) {

                    if(FadeAlpha==0) { if(TimeCount > 0) { TimeCount--; } else { FadeType=true; NextScene=true; }  }
                    if(NextScene == true) {  if(FadeAlpha==255) { FadeType = false; Intro3.setVisible(true); GameStage = 5; NextScene = false; } }

                }else { Intro2.setVisible(false); }


        if(GameStage == 5) {
            if(FadeAlpha == 0) { if(LetterScroll > 0) { LetterScroll--; } }
            if(NextScene == true) { Intro4.setVisible(true); GameStage = 6; NextScene = false; } 


        }else { Intro3.setVisible(false); }

        if(GameStage == 6) {
            if(LetterScroll > 0) { LetterScroll--; }
            if(NextScene == true) {  if(FadeAlpha==255) { FadeType = false; Intro5.setVisible(true); GameStage = 7; LetterScroll=82; NextScene = false; } }
        }else { Intro4.setVisible(false); }

        if(GameStage == 7) {
        if(FadeAlpha == 0) { if(LetterScroll > 0) { LetterScroll--; } }
        if(NextScene == true) { Intro6.setVisible(true); GameStage = 8; NextScene = false; }
        }else { Intro5.setVisible(false); }

        if(GameStage == 8) {
            if(NextScene == true) { Intro7.setVisible(true); GameStage = 9; NextScene = false; }
            if(LetterScroll > 0) { LetterScroll--; }
        } else { Intro6.setVisible(false); }

        if(GameStage == 9) {
            if(NextScene == true) { Intro8.setVisible(true); GameStage = 10; NextScene = false; }
            if(LetterScroll > 0) { LetterScroll--; }
        } else { Intro7.setVisible(false); }

        if(GameStage == 10) {
            if(NextScene == true) { if(FadeAlpha==255) { Intro9.setVisible(true);  FadeType = false; GameStage = 11;  LetterScroll=82;  NextScene = false; } }
            if(LetterScroll > 0) { LetterScroll--; }
        } else { Intro8.setVisible(false); }

     if(GameStage == 11) {
            Intro9.setVisible(true);
            if(NextScene == true) { Intro10.setVisible(true); GameStage = 12; NextScene = false; }
            if(FadeAlpha == 0) { if(LetterScroll > 0) { LetterScroll--; } }
        } else { Intro9.setVisible(false); }

     if(GameStage == 12) {
           if(NextScene == true) { if(FadeAlpha==255) {  try { IntroMusic.stop(); } catch(Exception e) { e.printStackTrace(); } FadeType = false; NextScene = false; GameStage = 15; cleanthings(); } }
           if(LetterScroll > 0) { LetterScroll--; }
        } else { Intro10.setVisible(false); }                

     



                if(GameStage == 15) {
                Alis.sprite.setVisible(true);
                MapTile.setVisible(true);
                Alis.walksync();
                MapTile.setPosition(Alis.x+6,Alis.y);
                
                if(CityMusic.getState() == 300 || CityMusic.getState() == 100) { try { CityMusic.start(); } catch(Exception e) { e.printStackTrace(); } }
                Alis.xcell = (Alis.x-96)/16;
                Alis.ycell = (Alis.y-144)/16;
                if (Alis.xcell < 0) { Alis.xcell = Alis.xcell * -1;  }
                if (Alis.ycell < 0) { Alis.ycell = Alis.ycell * -1;  }
                System.out.println("X: "+Alis.xcell+" Y:"+Alis.ycell);
                } else { MapTile.setVisible(false); }

                tick();
                input();
                render(g);

                try { Thread.sleep(gameFrameDelay); }
                catch (InterruptedException ie) { stop(); }
            }
    }

    private void tick() {

        gameState = (gameState + 1) % 20;

    }

    private void input () {

        int keyStates = getKeyStates();
        
        if(GameStage == 2) {
            if(MenuLife == true) {
            if(MenuLock > 0) { MenuLock--; }
            if ((keyStates & UP_PRESSED) != 0) { if(MenuLock == 0) { MenuLock = 9; if(MenuSelection == true) { MenuSelection = false; } else { MenuSelection = true; }  }  }
            if ((keyStates & DOWN_PRESSED) != 0) { if(MenuLock == 0) { MenuLock = 9; if(MenuSelection == true) { MenuSelection = false; } else { MenuSelection = true; }  }  }
            if ((keyStates & FIRE_PRESSED) != 0) { if(MenuSelection == false) { MenuLife=false; FadeType=true; FadeAlpha = 1; NextScene = true; }
            }}
        }

        if(GameStage == 3) {
            if ((keyStates & FIRE_PRESSED) != 0) {  if(Intro1.getY() < -20) { TimeCount=0; FadeType=true; NextScene=true; }  }
        }



        if(GameStage == 4) {
            if ((keyStates & FIRE_PRESSED) != 0) {  if(TimeCount<80) { TimeCount=0; }  }
        }

        if(GameStage == 5) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { NextScene=true; LetterScroll = 82; } }
        }

        if(GameStage == 6) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll < 40) { FadeType=true; NextScene=true; } }
        }

if(GameStage == 7) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { NextScene=true; LetterScroll=82; } }
        }

 if(GameStage == 8) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { NextScene=true; LetterScroll=82; } }
        }

 if(GameStage == 9) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { NextScene=true; LetterScroll=82; } }
        }

if(GameStage == 10) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { FadeType=true; NextScene=true; } }
        }

if(GameStage == 11) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll == 0) { LetterScroll = 82; NextScene=true; } }
        }

if(GameStage == 12) {
            if ((keyStates & FIRE_PRESSED) != 0) { if(LetterScroll < 60) { FadeType=true; NextScene=true; } }
        }


        if(GameStage == 15) {
            if ((keyStates & LEFT_PRESSED) != 0) { if(TileColision[Alis.ycell][Alis.xcell-1] ==0) { Alis.LastDirection =4;  Alis.move(1); } else { if(Alis.status == 9 ) { Alis.sprite.setFrame(4);} } }
            if ((keyStates & RIGHT_PRESSED) != 0) { if(TileColision[Alis.ycell][Alis.xcell+1] ==0) { Alis.LastDirection =6; Alis.move(0); } else {  if(Alis.status == 9 ) {  Alis.sprite.setFrame(11);} } }
            if ((keyStates & UP_PRESSED) != 0) { if(TileColision[Alis.ycell-1][Alis.xcell] ==0) { Alis.LastDirection =8; Alis.move(3); }    else { if(Alis.status == 9 ) {  Alis.sprite.setFrame(12);} } }
            if ((keyStates & DOWN_PRESSED) != 0) { if(TileColision[Alis.ycell+1][Alis.xcell] ==0) { Alis.LastDirection =2; Alis.move(2);}   else { if(Alis.status == 9 ) {  Alis.sprite.setFrame(0);} } }
        }

        if(GameStage == 0) {
            if ((keyStates & LEFT_PRESSED) != 0 || (keyStates & RIGHT_PRESSED) != 0 || (keyStates & UP_PRESSED) != 0 || (keyStates & DOWN_PRESSED) != 0 || (keyStates & FIRE_PRESSED) != 0) { Sega.setVisible(true); GameStage=1; }
        }


    }

    private void render (Graphics g) {


        if(FadeType == false) {
            if(FadeAlpha > 0) { if((FadeAlpha-10)<=0) { FadeAlpha=0; } else { FadeAlpha-=10; blend(FadeScreen,FadeAlpha); } }
        }
        else {
    
            if(FadeAlpha < 255) { if(FadeAlpha+10>255) { FadeAlpha = 255; } else { FadeAlpha+=10; blend(FadeScreen,FadeAlpha); }

            }
        }


    
    

        
        if(GameStage == 15) { g.setColor(0x00CE00); } else { g.setColor(0x000000); }

        g.fillRect(0, 0, getWidth(), getHeight());



        
        
        
        if(GameStage == 15) { Alis.sprite.setPosition(104, 136); Alis.sprite.paint(g); }
        MainLayer.paint(g, 0, 0);

        if(GameStage > 5 && GameStage < 12 ) {
            if(FadeAlpha > 0) { g.drawRGB(FadeScreen, 0, 160, 40, 88, 160, 96, true); g.drawRGB(FadeScreen, 0, 160, 40, 194, 160, 96, true); }

        }
        else {
            if(FadeAlpha > 0) { g.drawRGB(FadeScreen, 0, getWidth(), 0, 0, getWidth(), getHeight(), true);}
        }






        if(GameStage >= 5 && GameStage < 13) {
        if(LetterScroll > 79 ) { g.fillRect(40, 200, 7, 8); }
        
        
        for(int f=0; f<20; f++ ) {
            if(LetterScroll > 80-f ) { g.fillRect(40+(8*f), 196, 8, 12); }
        }

        for(int f=0; f<20; f++ ) {
            if(LetterScroll > 60-f ) { g.fillRect(40+(8*f), 212, 8, 12); }
        }

        for(int f=0; f<20; f++ ) {
            if(LetterScroll > 40-f ) { g.fillRect(40+(8*f), 228, 8, 12); }
        }

        for(int f=0; f<20; f++ ) {
           if(LetterScroll > 20-f ) { g.fillRect(40+(8*f), 244, 8, 12); }
        }
        

        }

        if(GameStage == 0) {
            Sega.setVisible(false);
            g.setColor(0xffffff);
            g.drawString("Este software foi desenvolvido", 0, 0, 0);
            g.drawString("por: Jonathan da Silva Santos", 0, 15, 0);
            g.drawString("Com o intuito de apresentar", 0, 45, 0);
            g.drawString("técnicas de programação e", 0, 60, 0);
            g.drawString("desenvolvimento de jogos.", 0, 75, 0);

            g.drawString("Software de circulação", 28, 100, 0);
            g.drawString("restrita.", 65, 115, 0);
            g.drawString("Contato:", 0, 140, 0);
            g.drawString("silva.santos.jonathan", 30, 155, 0);
            g.drawString("@gmail.com", 30, 170, 0);

            g.drawString("Julho de 2009", 10, 300, 0);

            if(g.getColor() == 0xffffff) { g.setColor(0x9999ff); } else { g.setColor(0xffffff); }
            g.drawString("Precione qualquer tecla para", 10, 220, 0);
            g.drawString("continuar.", 65, 235, 0);


        }

        flushGraphics ();
    }




 public static void blend(int[] raw, int alphaValue){
    int len = raw.length;
    // Start loop through all the pixels in the image.
    for(int i=0; i<len; i++){
        int a = 0;
        int color = (raw[i] & 0x00FFFFFF); // get the color of the pixel.
        a = alphaValue;     // set the alpha value we want to use 0-255.

        a = (a<<24);    // left shift the alpha value 24 bits.
        // if color = 00000000 11111111 11111111 00000000 (0xFFFF00 = Yellow)
        // and alpha= 01111111 00000000 00000000 00000000
        // then c+a = 01111111 11111111 11111111 00000000
        // and the pixel will be blended.
        color += a;
        raw[i] = color;
    }
}



 public  void cleanthings() {

    MainLayer.remove(Sega);
    MainLayer.remove(Intro1);
    MainLayer.remove(Intro2);
    MainLayer.remove(Intro3);
    MainLayer.remove(Intro4);
    MainLayer.remove(Intro5);
    MainLayer.remove(Intro6);
    MainLayer.remove(Intro7);
    MainLayer.remove(Intro8);
    MainLayer.remove(Intro9);
    MainLayer.remove(Intro10);
    MainLayer.remove(TitleScreen);
    MainLayer.remove(MenuArrow);
    ImgSega = null;
    ImgIntro1 = null;
    ImgIntro2 = null;
    ImgIntro3 = null;
    ImgIntro4 = null;
    ImgIntro5 = null;
    ImgIntro6 = null;
    ImgIntro7 = null;
    ImgIntro8 = null;
    ImgIntro9 = null;
    ImgIntro10 = null;
    ImgTitleScreen = null;
    ImgMenuArrow = null;
    

    System.out.println("passoyu por aqui");




}



}


