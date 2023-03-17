import javadraw.*;
import java.util.ArrayList;

public class App extends Window {

    //player movement booleans
    boolean up, down, right, left = false;

    //booleans to check if keys are pressed so that there is a return to main menu whilst playing the game
    boolean keyR, keyE, keyT = false;
    
    //location of mouse click
    Location mouseClickLocation = new Location(0, 0);

    public static void main(String[] args) throws Exception {
        Window.open(1000, 800);
    }

    public void start() {
        //the different settings numbers after the screen shown 
        menu(1, 17.5, false, false, false, false);
    }

    public void menu(int screenNumber, double moveSpeed, boolean trailOn, boolean hotColdOn, boolean darkMode, boolean invertedMode) {
        //random location for images used offscreen. the .visible() method was occasionally causing some of the images to flash, so instead I moved all the images offscreen.
        Location offScreen = new Location(screen.width() + 100, screen.height() + 100);
        
        //how much to move the images and buttons by when the screen size is bigger than 1000, 800
        double screenCenterWidth = 0;
        double screenCenterHeight = 0;
        
        //declaring the images used in the menus
        //some of these are images of text, because centering the text properly was easier when using images.
        //I used photoshop files to plan out the exact location of the images, and used those values in my code
        Image settingsBackground;
        Image mainMenuBackground;
        Image levelSelectionBackground;
        
        Image yesActiveHot;
        Image noActiveHot;
        Image yesActiveTrail;
        Image noActiveTrail;
        
        Image schemeText;

        Image speedSlow; 
        Image speedNormal;
        Image speedFast;

        //declaring arrayLists so that moving these offscreen are easier
        ArrayList<Image> speedImages = new ArrayList<Image>();
        ArrayList<Image> menu3Images = new ArrayList<Image>();
        
        Color borderColour;


        if (darkMode) { //dark colour scheme
            //main backgrounds
            mainMenuBackground = new Image(screen, "src/Dark/mainMenu Dark.png", offScreen);
            levelSelectionBackground = new Image(screen, "src/Dark/levelSelection Dark.png", offScreen);
            settingsBackground = new Image(screen, "src/Dark/Settings Dark.png", offScreen);
            
            //settings values images
            yesActiveHot = new Image(screen, "src/Dark/yesOn Dark.png", offScreen); //614, 209
            noActiveHot = new Image(screen, "src/Dark/noOn Dark.png", offScreen); //614, 209
            
            yesActiveTrail = new Image(screen, "src/Dark/yesOn Dark.png", offScreen); //614, 292
            noActiveTrail = new Image(screen, "src/Dark/noOn Dark.png", offScreen); //614, 292

            speedSlow = new Image(screen, "src/Dark/slow Dark.png", offScreen); //632, 447
            speedNormal = new Image(screen, "src/Dark/normal Dark.png", offScreen); //632, 447
            speedFast = new Image(screen, "src/Dark/fast Dark.png", offScreen); //632, 447
            
            schemeText = new Image(screen, "src/Dark/schemeName Dark.png", offScreen);
            
            // colours for the maze
            screen.color(new Color(15, 15, 15));
            borderColour = new Color(98, 98, 98);

        } else if (invertedMode){ //inverted colour scheme
            //main backgrounds
            mainMenuBackground = new Image(screen, "src/Inverted/mainMenu Inverted.png", offScreen);
            levelSelectionBackground = new Image(screen, "src/Inverted/levelSelection Inverted.png", offScreen);
            settingsBackground = new Image(screen, "src/Inverted/Settings Inverted.png", offScreen);
            
            //settings values images
            yesActiveHot = new Image(screen, "src/Inverted/yesOn Inverted.png", offScreen); //614, 209
            noActiveHot = new Image(screen, "src/Inverted/noOn Inverted.png", offScreen); //614, 209
            
            yesActiveTrail = new Image(screen, "src/Inverted/yesOn Inverted.png", offScreen); //614, 292
            noActiveTrail = new Image(screen, "src/Inverted/noOn Inverted.png", offScreen); //614, 292

            speedSlow = new Image(screen, "src/Inverted/slow Inverted.png", offScreen); //632, 447
            speedNormal = new Image(screen, "src/Inverted/normal Inverted.png", offScreen); //632, 447
            speedFast = new Image(screen, "src/Inverted/fast Inverted.png", offScreen); //632, 447
            
            schemeText = new Image(screen, "src/Inverted/schemeName Inverted.png", offScreen);

            // colours for the maze
            screen.color(Color.BLACK);
            borderColour = Color.WHITE;

        } else { // default, aka light mode
            //main backgrounds
            mainMenuBackground = new Image(screen, "src/Light/mainMenu Light.png", offScreen);
            levelSelectionBackground = new Image(screen, "src/Light/levelSelection Light.png", offScreen);
            settingsBackground = new Image(screen, "src/Light/Settings Light.png", offScreen);
            
            //settings values images
            yesActiveHot = new Image(screen, "src/Light/yesOn Light.png", offScreen); //614, 209
            noActiveHot = new Image(screen, "src/Light/noOn Light.png", offScreen); //614, 209
            
            yesActiveTrail = new Image(screen, "src/Light/yesOn Light.png", offScreen); //614, 292
            noActiveTrail = new Image(screen, "src/Light/noOn Light.png", offScreen); //614, 292

            speedSlow = new Image(screen, "src/Light/slow Light.png", offScreen); //632, 447
            speedNormal = new Image(screen, "src/Light/normal Light.png", offScreen); //632, 447
            speedFast = new Image(screen, "src/Light/fast Light.png", offScreen); //632, 447
            
            schemeText = new Image(screen, "src/Light/schemeName Light.png", offScreen);

            // colours for the maze
            screen.color(Color.WHITE);
            borderColour = Color.BLACK;
        }
        // intially i was using numbers which had more imges in speed, which would make this list more useful
        speedImages.add(speedSlow);
        speedImages.add(speedNormal);
        speedImages.add(speedFast);
        
        menu3Images.add(settingsBackground);
        menu3Images.add(yesActiveHot);
        menu3Images.add(noActiveHot);
        menu3Images.add(yesActiveTrail);
        menu3Images.add(noActiveTrail);
        menu3Images.add(schemeText);

        //all of the images for the move speed are also on menu 3
        for (Image image : speedImages) {
            menu3Images.add(image);
        }

        while (true) {
            // ensuring that the images non-visible are ALWAYS off screen
            offScreen = new Location(screen.width() + 100, screen.height() + 100);

            //adjusting the centering values for the images and buttons on the screen
            if (screen.width() > 1000){
                screenCenterWidth = (screen.width() - 1000)/2;
            } else {
                screenCenterWidth = 0;
            }

            if (screen.height() > 800){
                screenCenterHeight = (screen.height() - 800)/4;
            } else {
                screenCenterHeight = 0;
            }

            // creates the mouse click rectangle that is used for checking whether a button has been clicked 
            // i use a small square and the overlaps method to check the button because the contains method was working inconsistently, so i used overlaps as an alternate solution
            Rectangle mouseClickRange = new Rectangle(screen, mouseClickLocation, 10, 10, Color.MAGENTA);
            mouseClickRange.visible(false);

            if (screenNumber == 1) { //main menu
                //moves all the relevant images on and off screen.
                mainMenuBackground.moveTo(0 + screenCenterWidth, 0 + screenCenterHeight);
                
                for (Image image : menu3Images) {
                    image.moveTo(offScreen);
                }
                levelSelectionBackground.moveTo(offScreen);

                //declaring the buttons and makes them invisible, as these rectangles function as hitboxes
                Rectangle playButton = new Rectangle(screen, 350 + screenCenterWidth, 229 + screenCenterHeight, 302, 120);
                Rectangle settingsButton = new Rectangle(screen, 350 + screenCenterWidth, 380 + screenCenterHeight, 302, 120);
                Rectangle quitButton = new Rectangle(screen, 350 + screenCenterWidth, 531 + screenCenterHeight, 302, 120);

                playButton.visible(false);
                settingsButton.visible(false);
                quitButton.visible(false); 
                
                //checking if the button has been clicked and calling the appropriate method
                if(buttonClick(mouseClickRange, playButton)){
                    menu(2, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);;
                }

                if (buttonClick(mouseClickRange, settingsButton)){
                    menu(3, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if (buttonClick(mouseClickRange, quitButton)){
                    menu(4, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }   
            }

            if (screenNumber == 2) { //level selection screen
                //moves all the relevant images on and off screen.
                for (Image image : menu3Images) {
                    image.moveTo(offScreen);;
                }
                mainMenuBackground.moveTo(offScreen);;
                
                levelSelectionBackground.moveTo(0 + screenCenterWidth, 0 + screenCenterHeight);

                //declaring the buttons and makes them invisible, as these rectangles function as hitboxes
                Rectangle easyButton = new Rectangle(screen, 70 + screenCenterWidth, 213 + screenCenterHeight, 860, 89);
                Rectangle mediumButton = new Rectangle(screen, 70 + screenCenterWidth, 338 + screenCenterHeight, 860, 89);
                Rectangle hardButton = new Rectangle(screen, 70 + screenCenterWidth, 463 + screenCenterHeight, 860, 89);
                Rectangle returnButton = new Rectangle(screen, 70 + screenCenterWidth, 588 + screenCenterHeight, 860, 89);

                easyButton.visible(false);
                mediumButton.visible(false);
                hardButton.visible(false);
                returnButton.visible(false); 
                
                
                if(buttonClick(mouseClickRange, easyButton)){
                    maze("easy", borderColour, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if(buttonClick(mouseClickRange, mediumButton)){
                    maze("medium", borderColour, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if(buttonClick(mouseClickRange, hardButton)){
                    maze("hard", borderColour, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if(buttonClick(mouseClickRange, returnButton)){
                    menu(1, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }   
            }

            if (screenNumber == 3) { // settings
                
                mainMenuBackground.moveTo(offScreen);
                levelSelectionBackground.moveTo(offScreen);

                settingsBackground.moveTo(0 + screenCenterWidth, 0 + screenCenterHeight);
                
                schemeText.moveTo(644 + screenCenterWidth, 370 + screenCenterHeight);

                Rectangle returnButton = new Rectangle(screen, 349 + screenCenterWidth, 601 + screenCenterHeight, 302, 82);
                returnButton.visible(false);

                Rectangle hotColdChange = new Rectangle(screen, 610 + screenCenterWidth, 209 + screenCenterHeight, 208, 35);
                hotColdChange.visible(false);
                
                Rectangle trailChange = new Rectangle(screen, 610 + screenCenterWidth, 292 + screenCenterHeight, 208, 35);
                trailChange.visible(false);

                Rectangle colourSchemeL = new Rectangle(screen, 585 + screenCenterWidth, 375 + screenCenterHeight, 59, 33);
                colourSchemeL.visible(false);
                Rectangle colourSchemeR = new Rectangle(screen, 776 + screenCenterWidth, 375 + screenCenterHeight, 59, 33);
                colourSchemeR.visible(false);

                Rectangle speedSetL = new Rectangle(screen, 573 + screenCenterWidth, 452 + screenCenterHeight, 59, 33);
                speedSetL.visible(false);
                Rectangle speedSetR = new Rectangle(screen, 788 + screenCenterWidth, 452 + screenCenterHeight, 59, 33);
                speedSetR.visible(false);

                mouseClickRange.front();
                
                //checking if return was clicked

                if(buttonClick(mouseClickRange, returnButton)){
                    menu(1, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                //moving the yes/no options for hot and cold colouring into the correct place on screen depending on the boolean value
                if (hotColdOn){
                    yesActiveHot.moveTo(614 + screenCenterWidth, 209 + screenCenterHeight);
                    noActiveHot.moveTo(offScreen);
                } else {
                    noActiveHot.moveTo(614 + screenCenterWidth, 209 + screenCenterHeight);
                    yesActiveHot.moveTo(offScreen);
                }

                //moving the yes/no options for hot and cold colouring into the correct place on screen depending on the boolean value
                if (trailOn){
                    yesActiveTrail.moveTo(614 + screenCenterWidth, 292 + screenCenterHeight);
                    noActiveTrail.moveTo(offScreen);
                } else {
                    noActiveTrail.moveTo(614 + screenCenterWidth, 292 + screenCenterHeight);
                    yesActiveTrail.moveTo(offScreen);
                }
                
                
                for (Image speedText : speedImages){
                    speedText.moveTo(offScreen);
                }
                

                if (moveSpeed == 10){
                    speedSlow.moveTo(632 + screenCenterWidth, 447 + screenCenterHeight);
                } else if (moveSpeed == 17.5) {
                    speedNormal.moveTo(632 + screenCenterWidth, 447 + screenCenterHeight);
                } else if (moveSpeed == 25){
                    speedFast.moveTo(632 + screenCenterWidth, 447 + screenCenterHeight);
                }

                if(buttonClick(mouseClickRange, trailChange)){
                    if (trailOn) {
                        trailOn = false;
                    } else {
                        trailOn = true;
                    }
                }

                //changing the value of hotColdOn every time that area is clicked
                if(buttonClick(mouseClickRange, hotColdChange)){
                    if (hotColdOn) {
                        hotColdOn = false;
                    } else {
                        hotColdOn = true;
                    }
                }

                //changing the colour scheme
                //I call menu again to reset the menu to the correct color scheme, instead of having the images determined in the loop. this prevents occasionaly flickering 
                if(buttonClick(mouseClickRange, colourSchemeL)){
                    if (darkMode){
                        darkMode = false;
                        invertedMode = false;
                    } else if (invertedMode){
                        darkMode = true;
                        invertedMode = false;
                    } else {
                        darkMode = false;
                        invertedMode = true;
                    }
                    menu(3, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if(buttonClick(mouseClickRange, colourSchemeR)){
                    if (darkMode){
                        darkMode = false;
                        invertedMode = true;
                    } else if (invertedMode){
                        darkMode = false;
                        invertedMode = false;
                    } else {
                        darkMode = true;
                        invertedMode = false;
                    }
                    menu(3, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
                }

                if (buttonClick(mouseClickRange, speedSetL)){
                    if (moveSpeed > 10) {
                        moveSpeed -= 7.5;
                    } else {
                        moveSpeed = 25;
                    }
                }

                if (buttonClick(mouseClickRange, speedSetR)){
                    if (moveSpeed < 25) {
                        moveSpeed += 7.5;
                    } else {
                        moveSpeed = 10;
                    }
                }
            }
            
            if (screenNumber == 4) { // quit screen
                screen.clear();
                Rectangle screenCover = new Rectangle(screen, 0, 0, screen.width() + 100, screen.height() + 100);
                Text instructions = new Text(screen, "Close the Window", screen.center(), Color.WHITE);
                instructions.size(50);
                instructions.move(-instructions.width()/2, -instructions.height()/2);
                instructions.front();
                break;
            }

            screen.update();
            screen.sleep(1/30.0);
        }   
    }
    
    public void maze(String difficulty, Color borderColour, double moveSpeed, boolean trailOn, boolean hotColdOn, boolean darkMode, boolean invertedMode) {
        screen.clear();

        //ensures that the game does not automatically quit
        keyR = false;
        keyE = false;
        keyT = false;

        double startPoint = 0;
        int distanceRange = 0;

        //the top left corner of the maze is originally at (0, 0), so the start point is the value that the maze has to be moved by for the player to be in the correct place
        //the start point gets larger as the maze gets bigger
        if (difficulty.equals("easy")) {
            startPoint = -1887.5;
            distanceRange = 2500;
        } else if (difficulty.equals("medium")){
            startPoint = -3287.5;
            distanceRange = 4500;
        } else if (difficulty.equals("hard")) {
            startPoint = -4337.5;
            distanceRange = 6000;
        }

        //creating all the rectangles in the game and their corresponding arraylists.
        ArrayList<Rectangle> mazeWalls = createMaze(difficulty, startPoint, borderColour);
        ArrayList<Rectangle> target = new ArrayList<Rectangle>();
        ArrayList<Rectangle> trailCollection = new ArrayList<Rectangle>();

        Rectangle player = new Rectangle(screen, screen.center(), 125, 125, screen.color());
        Rectangle playerBorder = new Rectangle(screen, player.location().x() - 20, player.location().y() - 20, 165, 165);
        
        Rectangle playerCenter = new Rectangle(screen, player.location().x() + player.width()/2, 
        player.location().y() + player.height()/2, 1, 1);
        playerCenter.visible(false);

        //creating the end goal and moving it to the correct location on the screen based on which maze is active 
        Rectangle endLarge = new Rectangle(screen, 137.5, 137.5, 125, 125);
        
        Rectangle endRange = new Rectangle(screen, endLarge.location().x() + endLarge.width()/4, 
        endLarge.location().y() + endLarge.height()/4, endLarge.width()/2, endLarge.height()/2);
        
        Rectangle endSmall = new Rectangle(screen, endLarge.location().x() + endLarge.width()/2.666, 
        endLarge.location().y() + endLarge.height()/2.666, endLarge.width()/4, endLarge.height()/4);

        Rectangle endCenter = new Rectangle(screen, endLarge.location().x() + endLarge.width()/2, 
        endLarge.location().y() + endLarge.height()/2, 1, 1);
        endCenter.visible(false);
        
        target.add(endLarge);
        target.add(endRange);
        target.add(endSmall);
        target.add(endCenter);

        rectListMove(target, startPoint + screen.center().x(), startPoint + screen.center().y());

        // moves the player infront of the target
        playerBorder.front();
        player.front();

        //declaring the colours of the game
        Color trailColor;

        if (darkMode){ //dark mode colours
            for (Rectangle wall : mazeWalls){
                wall.color(new Color(98, 98, 98));
            }

            endLarge.color(new Color(139, 0, 0));
            endRange.color(Color.lightGray);
            endSmall.color(new Color(139, 0, 0));

            playerBorder.color(new Color(98, 98, 98));
            trailColor = new Color(0, 88, 38);

        } else if (invertedMode) { //inverted mode colours
            for (Rectangle wall : mazeWalls){
                wall.color(Color.WHITE);
            }
            
            endLarge.color(Color.CYAN);
            endRange.color(Color.BLACK);
            endSmall.color(Color.CYAN);

            playerBorder.color(Color.WHITE);
            trailColor = new Color(255, 89, 174);

        } else { // light mode colours
            for (Rectangle wall : mazeWalls){
                wall.color(Color.BLACK);
            }
            
            endLarge.color(Color.RED);
            endRange.color(Color.WHITE);
            endSmall.color(Color.RED);

            playerBorder.color(Color.BLACK);
            trailColor = new Color(0, 166, 81);
        }

        //collision check booleans
        boolean wallOverlapUp = false;
        boolean wallOverlapDown= false;
        boolean wallOverlapLeft= false;
        boolean wallOverlapRight = false;

        //defining player hitboxes for where they can and can't move
        Rectangle moveCheckDown = new Rectangle(screen, player.location().x() - 10, player.location().y() + player.height() + 14, player.width() + 20, 7, Color.MAGENTA);
        Rectangle moveCheckRight = new Rectangle(screen, player.location().x() + player.width() + 14, player.location().y() - 10, 7, player.height() + 20, Color.MAGENTA);
        Rectangle moveCheckUp = new Rectangle(screen, player.location().x() - 10, player.location().y() - 20, player.width() + 20, 7, Color.MAGENTA);
        Rectangle moveCheckLeft = new Rectangle(screen, player.location().x() - 20, player.location().y() - 10, 7, player.height() + 20, Color.MAGENTA);
        
        //makes the hitboxes invisible
        moveCheckDown.visible(false);
        moveCheckUp.visible(false);
        moveCheckLeft.visible(false);
        moveCheckRight.visible(false); 
        
        while (true) {
            
            //checks if a collision has occured
            for (Rectangle wall : mazeWalls){
                if (moveCheckDown.overlaps(wall)) {
                    wallOverlapDown = true;
                }
                if (moveCheckUp.overlaps(wall)) {
                    wallOverlapUp = true;
                }
                if (moveCheckLeft.overlaps(wall)) {
                    wallOverlapLeft = true;
                }
                if (moveCheckRight.overlaps(wall)) {
                    wallOverlapRight = true;
                }
            } 

            //prevents the code from trying to move in opposing directions simultaneously, which causes erratic movement
            //although not what they are named for, the wallOverlap booleans disable the ability to move, so they are used here for that purpose
            if (down && up) {
                wallOverlapDown = true;
                wallOverlapUp = true;
            } else if (left && right) {
                wallOverlapLeft = true;
                wallOverlapRight = true;
            } 

            //moving downwards
            if (!wallOverlapDown){
                if (down) {
                    rectListMove(target, 0, -moveSpeed);
                    rectListMove(mazeWalls, 0, -moveSpeed);
                    rectListMove(trailCollection, 0, -moveSpeed);
                }
            }

            //moving upwards
            if (!wallOverlapUp){
                if (up) {
                    rectListMove(target, 0, moveSpeed);
                    rectListMove(mazeWalls, 0, moveSpeed);
                    rectListMove(trailCollection, 0, moveSpeed);
                }
            }

            //moving right
            if (!wallOverlapRight){
                if (right) {
                    rectListMove(target, -moveSpeed, 0);
                    rectListMove(mazeWalls, -moveSpeed, 0);
                    rectListMove(trailCollection, -moveSpeed, 0);
                }
            }

            //moving left
            if (!wallOverlapLeft) {
                if (left) {
                    rectListMove(target, moveSpeed, 0);
                    rectListMove(mazeWalls, moveSpeed, 0);
                    rectListMove(trailCollection, moveSpeed, 0);
                }
            }

            //checks if the player has made it to the end, or if the player has entered the return cheat code
            //all 3 of the keys have to be pressed at the same time for the player to return to the main menu, to prevent accidentally returning to the main menu
            if (playerBorder.contains(endCenter.location()) || (keyR && keyE && keyT)){
                break;
            }

            
            //creates a new rectangle, adds it to a list to be moved when the player is moved, and moves the player infront of the new rectangle to create a trail
            if (trailOn) {
                Rectangle trailAdd = new Rectangle(screen, player.center().x() - 10, player.center().y() - 10, 20, 20, trailColor);
                trailCollection.add(trailAdd);
                playerBorder.front();
                player.front();
            }
            
            //changes the colour of player if the hot cold setting is on
            if (hotColdOn){
                double distanceFromEnd = endCenter.location().distance(playerCenter.location());
                
                //the range of each rgb value used to colour the player
                int redStart = 0;
                int redEnd = 255;

                int greenEnd = 150;
                int greenStart = 175;

                int blueEnd = 30;
                int blueStart = 255;

                if (invertedMode) {
                    redStart = 255;
                    redEnd = 0;

                    greenStart = 80;
                    greenEnd = 105;

                    blueStart = 0;
                    blueEnd = 225;

                } else if(darkMode) {
                    redStart = 0;
                    redEnd = 123;

                    greenStart = 91;
                    greenEnd = 46;

                    blueStart = 127;
                    blueEnd = 0;
                }

                //Calculates the rgb values of the player depending on how far the player is from the end goal using the ranges.   
                int redPlayer = (int) distanceFromEnd * redStart/distanceRange + (distanceRange - (int) distanceFromEnd) * redEnd/distanceRange;
                int greenPlayer = (int) distanceFromEnd * greenStart/distanceRange + (distanceRange - (int) distanceFromEnd) * greenEnd/distanceRange;
                int bluePlayer = (int) distanceFromEnd * blueStart/distanceRange + (distanceRange - (int) distanceFromEnd) * blueEnd/distanceRange;

                //if the player is further from the end than the range of the distance it shoudl be (the bottom right of the maze) it will keep the colour at the "coldest" it can be
                if (distanceFromEnd > distanceRange){
                    player.color(new Color(redStart, greenStart, blueStart));
                } else {
                    player.color(new Color(redPlayer, greenPlayer, bluePlayer));
                }

            } else {
                player.color(screen.color());
            }
            
            wallOverlapDown = false;
            wallOverlapLeft = false;
            wallOverlapRight = false;
            wallOverlapUp = false;

            screen.update();
            screen.sleep(1/30.0);
        }
        
        screen.sleep(1);
        screen.clear();
        menu(1, moveSpeed, trailOn, hotColdOn, darkMode, invertedMode);
    }

    //this method moves rectangles in an arraylist, and removes around 20 lines of repeated code
    public void rectListMove(ArrayList<Rectangle> rectList, double x, double y) {
        for (Rectangle rect : rectList) {
            rect.move(x, y);
        } 
    }

    //this method checks if a button has been clicked, and moves the mouse to the correct location after the click. this method prevents the repetition of moving mouseClickLocation to 0,0 after every correct click, which was repeated around 12 times
    public boolean buttonClick(Rectangle mouseClick, Rectangle button) {
        if (mouseClick.overlaps(button)){
            mouseClickLocation = new Location(0, 0);
            return true;
        } else {
            return false;
        }   
    }

    public void mouseDown(int button, Location location) {
        mouseClickLocation = location;
    }

    public void keyDown(Key key) {
        if (key == Key.UP) {
            up = true;
        }
        if (key == Key.DOWN) {
            down = true;
        }
        if (key == Key.LEFT) {
            left = true;
        }
        if (key == Key.RIGHT) {
            right = true;
        } 
        if (key == Key.R) {
            keyR = true;
        }
        if (key == key.E) {
            keyE = true;
        }
        if (key == key.T) {
            keyT = true;
        }
    }

    public void keyUp(Key key) {
        if (key == Key.UP) {
            up = false;
        }
        if (key == Key.DOWN) {
            down = false;
        }
        if (key == Key.LEFT) {
            left = false;
        }
        if (key == Key.RIGHT) {
            right = false;
        }
        if (key == Key.R) {
            keyR = false;
        }
        if (key == key.E) {
            keyE = false;
        }
        if (key == key.T) {
            keyT = false;
        }
    }

    public ArrayList<Rectangle> createMaze(String difficulty, double startPoint, Color borderColour){
        //every rectangle is created, and the added to the array list and then returned to where the function is called
        ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
        
        //I know that this section of the code could be half the length it is if i just make a new rectangle and add it to the list in the same line
        //but seperating them into two separate lines allows for easier reading and editting because of the labelling system

        //within the easy and medium mazes I left the ability for the player to go outside the maze and access the end goal through that path
        //this was deliberate, because on the hard maze i closed the gap at the end goal so a player would attempt to solve it using that method, only to find that they could not get to the end goal from the outside

        //creates the easy maze. 
        if (difficulty.equals("easy")){
            Rectangle leftWall = new Rectangle(screen, 0, 350, 50, 1800);
            walls.add(leftWall);

            Rectangle bottomWall = new Rectangle(screen, 0, 2100, 1800, 50);
            walls.add(bottomWall);
            
            Rectangle rightWall = new Rectangle(screen, 2100, 0, 50, 2150);
            walls.add(rightWall);

            Rectangle topWall = new Rectangle(screen, 0, 0, 2150, 50);
            walls.add(topWall);

            //the rectangles that make the inside walls do not have random numbers, they are named purposefully
            //the word is which direction they are
            //the first digit is the number of squares in a grid they cross (see photoshop file)
            //the second digit is the row or column the are in (depending on if horizontal or vertical)
            //and the third digit is what number along the rectangle is (only useful the row/column has more than one rectangle of the same length)

            Rectangle horizontal321 = new Rectangle(screen, 700, 350, 1100, 50);
            walls.add(horizontal321);

            Rectangle horizontal261 = new Rectangle(screen, 0, 1750, 750, 50);
            walls.add(horizontal261);
            
            Rectangle horizontal131 = new Rectangle(screen, 350, 700, 400, 50);
            walls.add(horizontal131);

            Rectangle horizontal132 = new Rectangle(screen, 1400, 700, 400, 50);
            walls.add(horizontal132);

            Rectangle horizontal141 = new Rectangle(screen, 700, 1050, 400, 50);
            walls.add(horizontal141);

            Rectangle horizontal142 = new Rectangle(screen, 1750, 1050, 400, 50);
            walls.add(horizontal142);


            Rectangle vertical421 = new Rectangle(screen, 350, 0, 50, 1450);
            walls.add(vertical421);

            Rectangle vertical341 = new Rectangle(screen, 1050, 350, 50, 1100);
            walls.add(vertical341);

            Rectangle vertical351 = new Rectangle(screen, 1400, 700, 50, 1100);
            walls.add(vertical351);

            Rectangle vertical361 = new Rectangle(screen, 1750, 700, 50, 1100);
            walls.add(vertical361);

            Rectangle vertical131 = new Rectangle(screen, 700, 700, 50, 400);
            walls.add(vertical131);

            Rectangle vertical132 = new Rectangle(screen, 700, 1400, 50, 400);
            walls.add(vertical132);

            Rectangle vertical142 = new Rectangle(screen, 1050, 1750, 50, 400);
            walls.add(vertical142);
        } 

        else if (difficulty.equals("medium")) {
            Rectangle leftWall = new Rectangle(screen, 0, 350, 50, 3200);
            walls.add(leftWall);

            Rectangle bottomWall = new Rectangle(screen, 0, 3500, 3200, 50);
            walls.add(bottomWall);
            
            Rectangle rightWall = new Rectangle(screen, 3500, 0, 50, 3550);
            walls.add(rightWall);

            Rectangle topWall = new Rectangle(screen, 0, 0, 3550, 50);
            walls.add(topWall);

            // the rectangles that make the inside walls do not have random numbers, they are named purposefully
            // the word is which direction they are
            // the first digit is the number of squares in a grid they cross (see photoshop file)
            // the second two digits is the row or column the are in (depending on if horizontal or vertical)
            // the fourth digit is what number along the rectangle is (only useful the row/column has more than one rectangle of the same length)

            Rectangle horizontal5101 = new Rectangle(screen, 700, 3150, 1800, 50);
            walls.add(horizontal5101);


            Rectangle horizontal4042 = new Rectangle(screen, 1400, 1050, 1450, 50);
            walls.add(horizontal4042);

            Rectangle horizontal4061 = new Rectangle(screen, 350, 1750, 1450, 50);
            walls.add(horizontal4061);

            Rectangle horizontal4072 = new Rectangle(screen, 1050, 2100, 1450, 50);
            walls.add(horizontal4072);


            Rectangle horizontal3021 = new Rectangle(screen, 0, 350, 1100, 50);
            walls.add(horizontal3021);

            Rectangle horizontal3022 = new Rectangle(screen, 1400, 350, 1100, 50);
            walls.add(horizontal3022);

            Rectangle horizontal3033 = new Rectangle(screen, 2100, 700, 1100, 50);
            walls.add(horizontal3033);

            Rectangle horizontal3051 = new Rectangle(screen, 350, 1400, 1100, 50);
            walls.add(horizontal3051);

            Rectangle horizontal3052 = new Rectangle(screen, 2100, 1400, 1100, 50);
            walls.add(horizontal3052);

            Rectangle horizontal3062 = new Rectangle(screen, 2100, 1750, 1100, 50);
            walls.add(horizontal3062);

            Rectangle horizontal3081 = new Rectangle(screen, 350, 2450, 1100, 50);
            walls.add(horizontal3081);


            Rectangle horizontal2023 = new Rectangle(screen, 2800, 350, 750, 50);
            walls.add(horizontal2023);

            Rectangle horizontal2092 = new Rectangle(screen, 1050, 2800, 750, 50);
            walls.add(horizontal2092);

            Rectangle horizontal2093 = new Rectangle(screen, 2100, 2800, 750, 50);
            walls.add(horizontal2093);

            Rectangle horizontal2102 = new Rectangle(screen, 2800, 3150, 750, 50);
            walls.add(horizontal2102);


            Rectangle horizontal1031 = new Rectangle(screen, 0, 700, 400, 50);
            walls.add(horizontal1031);

            Rectangle horizontal1032 = new Rectangle(screen, 1050, 700, 400, 50);
            walls.add(horizontal1032);

            Rectangle horizontal1041 = new Rectangle(screen, 700, 1050, 400, 50);
            walls.add(horizontal1041);

            Rectangle horizontal1043 = new Rectangle(screen, 3150, 1050, 400, 50);
            walls.add(horizontal1043);

            Rectangle horizontal1071 = new Rectangle(screen, 350, 2100, 400, 50);
            walls.add(horizontal1071);

            Rectangle horizontal1091 = new Rectangle(screen, 350, 2800, 400, 50);
            walls.add(horizontal1091);
 


            Rectangle vertical3022 = new Rectangle(screen, 350, 2100, 50, 1100);
            walls.add(vertical3022);

            Rectangle vertical3101 = new Rectangle(screen, 3150, 1750, 50, 1100);
            walls.add(vertical3101);


            Rectangle vertical2021 = new Rectangle(screen, 350, 700, 50, 750);
            walls.add(vertical2021);

            Rectangle vertical2051 = new Rectangle(screen, 1400, 0, 50, 750);
            walls.add(vertical2051);

            Rectangle vertical2061 = new Rectangle(screen, 1750, 700, 50, 750);
            walls.add(vertical2061);

            Rectangle vertical2062 = new Rectangle(screen, 1750, 2450, 50, 750);
            walls.add(vertical2062);

            Rectangle vertical2071 = new Rectangle(screen, 2100, 1400, 50, 750);
            walls.add(vertical2071);

            Rectangle vertical2093 = new Rectangle(screen, 2800, 1750, 50, 750);
            walls.add(vertical2093);

            
            Rectangle vertical1031 = new Rectangle(screen, 700, 350, 50, 400);
            walls.add(vertical1031);

            Rectangle vertical1032 = new Rectangle(screen, 700, 2800, 50, 400);
            walls.add(vertical1032);

            Rectangle vertical1041 = new Rectangle(screen, 1050, 700, 50, 400);
            walls.add(vertical1041);

            Rectangle vertical1042 = new Rectangle(screen, 1050, 1750, 50, 400);
            walls.add(vertical1042);

            Rectangle vertical1052 = new Rectangle(screen, 1400, 1400, 50, 400);
            walls.add(vertical1052);

            Rectangle vertical1072 = new Rectangle(screen, 2100, 2450, 50, 400);
            walls.add(vertical1072);

            Rectangle vertical1081 = new Rectangle(screen, 2450, 2100, 50, 400);
            walls.add(vertical1081);

            Rectangle vertical1082 = new Rectangle(screen, 2450, 3150, 50, 400);
            walls.add(vertical1082);

            Rectangle vertical1091 = new Rectangle(screen, 2800, 350, 50, 400);
            walls.add(vertical1091);

            Rectangle vertical1092 = new Rectangle(screen, 2800, 1050, 50, 400);
            walls.add(vertical1092);

            Rectangle vertical1093 = new Rectangle(screen, 2800, 2800, 50, 400);
            walls.add(vertical1093);  
        } 

        else if (difficulty.equals("hard")){
            Rectangle leftWall = new Rectangle(screen, 0, 0, 50, 4600);
            walls.add(leftWall);

            Rectangle bottomWall = new Rectangle(screen, 0, 4550, 4250, 50);
            walls.add(bottomWall);
            
            Rectangle rightWall = new Rectangle(screen, 4550, 0, 50, 4600);
            walls.add(rightWall);

            Rectangle topWall = new Rectangle(screen, 0, 0, 4600, 50);
            walls.add(topWall);

            // the rectangles that make the inside walls do not have random numbers, they are named purposefully
            // the word is which direction they are
            // the first digit is the number of squares in a grid they cross (see photoshop file)
            // the second two digits is the row or column the are in (depending on if horizontal or vertical)
            // the fourth digit is what number along the rectangle is (only useful the row/column has more than one rectangle of the same length)

            Rectangle horizontal5112 = new Rectangle(screen, 2450, 3500, 1800, 50);
            walls.add(horizontal5112);


            Rectangle horizontal4033 = new Rectangle(screen, 1750, 700, 1450, 50);
            walls.add(horizontal4033);

            Rectangle horizontal4043 = new Rectangle(screen, 2450, 1050, 1450, 50);
            walls.add(horizontal4043);

            Rectangle horizontal4121 = new Rectangle(screen, 700, 3850, 1450, 50);
            walls.add(horizontal4121);

            
            Rectangle horizontal3021 = new Rectangle(screen, 700, 350, 1100, 50);
            walls.add(horizontal3021);

            
            Rectangle horizontal2022 = new Rectangle(screen, 2100, 350, 750, 50);
            walls.add(horizontal2022);

            Rectangle horizontal2031 = new Rectangle(screen, 0, 700, 750, 50);
            walls.add(horizontal2031);

            Rectangle horizontal2041 = new Rectangle(screen, 350, 1050, 750, 50);
            walls.add(horizontal2041);

            Rectangle horizontal2061 = new Rectangle(screen, 700, 1750, 750, 50);
            walls.add(horizontal2061);

            Rectangle horizontal2071 = new Rectangle(screen, 1050, 2100, 750, 50);
            walls.add(horizontal2071);

            Rectangle horizontal2073 = new Rectangle(screen, 3500, 2100, 750, 50);
            walls.add(horizontal2073);

            Rectangle horizontal2083 = new Rectangle(screen, 1750, 2450, 750, 50);
            walls.add(horizontal2083);

            Rectangle horizontal2084 = new Rectangle(screen, 2800, 2450, 750, 50);
            walls.add(horizontal2084);

            Rectangle horizontal2091 = new Rectangle(screen, 350, 2800, 750, 50);
            walls.add(horizontal2091);

            Rectangle horizontal2093 = new Rectangle(screen, 3150, 2800, 750, 50);
            walls.add(horizontal2093);

            Rectangle horizontal2101 = new Rectangle(screen, 0, 3150, 750, 50);
            walls.add(horizontal2101);

            Rectangle horizontal2122 = new Rectangle(screen, 2800, 3850, 750, 50);
            walls.add(horizontal2122 );

            Rectangle horizontal2132 = new Rectangle(screen, 1050, 4200, 750, 50);
            walls.add(horizontal2132);

            Rectangle horizontal2133 = new Rectangle(screen, 2450, 4200, 750, 50);
            walls.add(horizontal2133 );


            Rectangle horizontal1023 = new Rectangle(screen, 3150, 350, 400, 50);
            walls.add(horizontal1023);

            Rectangle horizontal1024 = new Rectangle(screen, 3850, 350, 400, 50);
            walls.add(horizontal1024);

            Rectangle horizontal1032 = new Rectangle(screen, 1050, 700, 400, 50);
            walls.add(horizontal1032);

            Rectangle horizontal1034 = new Rectangle(screen, 4200, 700, 400, 50);
            walls.add(horizontal1034);

            Rectangle horizontal1042 = new Rectangle(screen, 1750, 1050, 400, 50);
            walls.add(horizontal1042);

            Rectangle horizontal1051 = new Rectangle(screen, 350, 1400, 400, 50);
            walls.add(horizontal1051);

            Rectangle horizontal1052 = new Rectangle(screen, 3150, 1400, 400, 50);
            walls.add(horizontal1052);

            Rectangle horizontal1072 = new Rectangle(screen, 2450, 2100, 400, 50);
            walls.add(horizontal1072);

            Rectangle horizontal1081 = new Rectangle(screen, 0, 2450, 400, 50);
            walls.add(horizontal1081);

            Rectangle horizontal1082 = new Rectangle(screen, 700, 2450, 400, 50);
            walls.add(horizontal1082);

            Rectangle horizontal1085 = new Rectangle(screen, 3850, 2450, 400, 50);
            walls.add(horizontal1085);

            Rectangle horizontal1092 = new Rectangle(screen, 1750, 2800, 400, 50);
            walls.add(horizontal1092);

            Rectangle horizontal1102 = new Rectangle(screen, 4200, 3150, 400, 50);
            walls.add(horizontal1102);

            Rectangle horizontal1111 = new Rectangle(screen, 1400, 3500, 400, 50);
            walls.add(horizontal1111);

            Rectangle horizontal1131 = new Rectangle(screen, 0, 4200, 400, 50);
            walls.add(horizontal1131);

            Rectangle horizontal1134 = new Rectangle(screen, 3850, 4200, 400, 50);
            walls.add(horizontal1134);

           

            Rectangle vertical5082 = new Rectangle(screen, 2450, 2100, 50, 1800);
            walls.add(vertical5082);

            
            Rectangle vertical3022 = new Rectangle(screen, 350, 1400, 50, 1100);
            walls.add(vertical3022);

            Rectangle vertical3042 = new Rectangle(screen, 1050, 2800, 50, 1100);
            walls.add(vertical3042);

            Rectangle vertical3052 = new Rectangle(screen, 1400, 2450, 50, 1100);
            walls.add(vertical3052);

            Rectangle vertical3063 = new Rectangle(screen, 1750, 2800, 50, 1100);
            walls.add(vertical3063);
            
            Rectangle vertical3072 = new Rectangle(screen, 2100, 1050, 50, 1100);
            walls.add(vertical3072);

            Rectangle vertical3081 = new Rectangle(screen, 2450, 700, 50, 1100);
            walls.add(vertical3081);

            Rectangle vertical3091 = new Rectangle(screen, 2800, 1050, 50, 1100);
            walls.add(vertical3091);

            Rectangle vertical3101 = new Rectangle(screen, 3150, 1400, 50, 1100);
            walls.add(vertical3101);

            Rectangle vertical3122 = new Rectangle(screen, 3850, 1050, 50, 1100);
            walls.add(vertical3122);
            
            Rectangle vertical3132 = new Rectangle(screen, 4200, 700, 50, 1100);
            walls.add(vertical3132);
            

            Rectangle vertical2023 = new Rectangle(screen, 350, 3500, 50, 750);
            walls.add(vertical2023);

            Rectangle vertical2032 = new Rectangle(screen, 700, 1750, 50, 750);
            walls.add(vertical2032);

            Rectangle vertical2034 = new Rectangle(screen, 700, 3850, 50, 750);
            walls.add(vertical2034);

            Rectangle vertical2041 = new Rectangle(screen, 1050, 700, 50, 750);
            walls.add(vertical2041);
            
            Rectangle vertical2051 = new Rectangle(screen, 1400, 1050, 50, 750);
            walls.add(vertical2051);

            Rectangle vertical2061 = new Rectangle(screen, 1750, 350, 50, 750);
            walls.add(vertical2061);

            Rectangle vertical2062 = new Rectangle(screen, 1750, 1400, 50, 750);
            walls.add(vertical2062);

            Rectangle vertical2074 = new Rectangle(screen, 2100, 3500, 50, 750);
            walls.add(vertical2074);

            Rectangle vertical2092 = new Rectangle(screen, 2800, 2450, 50, 750);
            walls.add(vertical2092);
            
            Rectangle vertical2113 = new Rectangle(screen, 3500, 1750, 50, 750);
            walls.add(vertical2113);

            Rectangle vertical2124 = new Rectangle(screen, 3850, 3150, 50, 750);
            walls.add(vertical2124);

            Rectangle vertical2133 = new Rectangle(screen, 4200, 2450, 50, 750);
            walls.add(vertical2133);
            
            Rectangle vertical2134 = new Rectangle(screen, 4200, 3500, 50, 750);
            walls.add(vertical2134);


            Rectangle vertical1021 = new Rectangle(screen, 350, 0, 50, 400);
            walls.add(vertical1021);

            Rectangle vertical1031 = new Rectangle(screen, 700, 350, 50, 400);
            walls.add(vertical1031);

            Rectangle vertical1033 = new Rectangle(screen, 700, 3150, 50, 400);
            walls.add(vertical1033);

            Rectangle vertical1064 = new Rectangle(screen, 1750, 4200, 50, 400);
            walls.add(vertical1064);

            Rectangle vertical1071 = new Rectangle(screen, 2100, 0, 50, 400);
            walls.add(vertical1071);

            Rectangle vertical1073 = new Rectangle(screen, 2100, 2800, 50, 400);
            walls.add(vertical1073);

            Rectangle vertical1083 = new Rectangle(screen, 2450, 4200, 50, 400);
            walls.add(vertical1083);

            Rectangle vertical1093 = new Rectangle(screen, 2800, 3850, 50, 400);
            walls.add(vertical1093);

            Rectangle vertical1102 = new Rectangle(screen, 3150, 3150, 50, 400);
            walls.add(vertical1102);

            Rectangle vertical1111 = new Rectangle(screen, 3500, 0, 50, 400);
            walls.add(vertical1111);

            Rectangle vertical1112 = new Rectangle(screen, 3500, 700, 50, 400);
            walls.add(vertical1112);

            Rectangle vertical1114 = new Rectangle(screen, 3500, 2800, 50, 400);
            walls.add(vertical1114);

            Rectangle vertical1115 = new Rectangle(screen, 3500, 3850, 50, 400);
            walls.add(vertical1115);

            Rectangle vertical1121 = new Rectangle(screen, 3850, 350, 50, 400);
            walls.add(vertical1121);

            Rectangle vertical1123 = new Rectangle(screen, 3850, 2450, 50, 400);
            walls.add(vertical1123);

            Rectangle vertical1131 = new Rectangle(screen, 4200, 0, 50, 400);
            walls.add(vertical1131);
        }
        //moves the maze to the correct starting point on the screen relative to the player
        //because this function also changes the colour of the maze, i did not use the arrayList move method
        for (Rectangle wall : walls){
            wall.move(startPoint + screen.center().x(), startPoint + screen.center().y());

            wall.color(borderColour);
            //wall.color(Color.random());
        }
        return walls;
    }
}