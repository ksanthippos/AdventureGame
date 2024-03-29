package com.mikaelr.GUIapp;

import com.mikaelr.textgameapp.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.Random;

public class Controller {


    private GameLogic logic;
    private TextArea output;
    private GridPane tileMap;

    private Label infoName;
    private Label infoHP;
    private Label infoAP;
    private Label infoDP;
    private Label infoMP;
    private Label infoXP;
    private Label infoPoisoned;
    private Label infoLevel;

    private ChoiceBox<Item> inventoryList;
    private ChoiceBox weaponList;
    private ChoiceBox armorList;
    private ChoiceBox spellList;

    private Button fight;
    private Button flee;
    private Button use;
    private Button look;
    private Button take;
    private Button help;
    private Button north;
    private Button south;
    private Button east;
    private Button west;

    private Image wall;
    private Image empty;
    private Image bones;

    private Image rat;
    private Image snake;
    private Image goblin;
    private Image orc;
    private Image troll;
    private Image dragon;

    private Image hero;
    private Image hpotion;
    private Image mpotion;
    private Image ssword;
    private Image baxe;
    private Image chainmail;
    private Image karmor;
    private Image hspell;
    private Image lspell;



    // game initialization
    public Controller() {

        // two main components, need to be instantiated here
        this.tileMap = new GridPane();
        this.output = new TextArea();

        // map assets
        this.wall = new Image("com/mikaelr/assets/map/wall.png");
        this.empty = new Image("com/mikaelr/assets/map/empty.png");
        this.bones = new Image("com/mikaelr/assets/map/tile040.png");

        // entity assets
        this.hero = new Image("com/mikaelr/assets/entities/tile002.png");
        this.rat = new Image("com/mikaelr/assets/entities/tile008.png");
        this.snake = new Image("com/mikaelr/assets/entities/tile070.png");
        this.goblin = new Image("com/mikaelr/assets/entities/tile016.png");
        this.orc = new Image("com/mikaelr/assets/entities/tile049.png");
        this.troll = new Image("com/mikaelr/assets/entities/tile020.png");
        this.dragon = new Image("com/mikaelr/assets/entities/tile047.png");

        // item assets
        this.hpotion = new Image("com/mikaelr/assets/items/tile075.png");
        this.mpotion = new Image("com/mikaelr/assets/items/tile073.png");
        this.ssword = new Image("com/mikaelr/assets/items/tile091.png");
        this.baxe = new Image("com/mikaelr/assets/items/tile088.png");
        this.chainmail = new Image("com/mikaelr/assets/items/tile126.png");
        this.karmor = new Image("com/mikaelr/assets/items/tile131.png");
        this.hspell = new Image("com/mikaelr/assets/items/tile164.png");
        this.lspell = new Image("com/mikaelr/assets/items/tile165.png");

        // construct and initialize game world
        this.logic = new GameLogic();
        logic.initGame();

        // ..and the adventure begins!
        println("Welcome to the Grand Cave Adventure, good luck to you, " + logic.getPlayer().getName() + "!");
        println("Maybe you should start looking around you?");

    }

    public Parent gameRoot() {

        // **********
        // gaming view initialization: map, text, buttons...

        // left view (map only)
        BorderPane mapView = new BorderPane();
        mapView.setPadding(new Insets(15));
        tileMap.setStyle("-fx-background-color: black");    // dungeon floor

        // player stats: name, health, XP etc
        GridPane playerStatsView = new GridPane();
        playerStatsView.setPadding(new Insets(15));
        playerStatsView.setVgap(5);
        playerStatsView.setHgap(10);
        Label lblName = new Label("Name :");
        Label lblHP = new Label("HP :");
        Label lblAP = new Label("AP :");
        Label lblDP = new Label("DP :");
        Label lblMP = new Label("MP :");
        Label lblXP = new Label("XP :");
        Label lblPoisoned = new Label("Poison :");
        Label lblLevel = new Label("Level :");
        infoName = new Label(logic.getPlayer().getName());
        infoHP = new Label(String.valueOf(logic.getPlayer().getHP()));
        infoAP = new Label(String.valueOf(logic.getPlayer().getAP()));
        infoDP = new Label(String.valueOf(logic.getPlayer().getDP()));
        infoMP = new Label(String.valueOf(logic.getPlayer().getMP()));
        infoXP = new Label(String.valueOf(logic.getPlayer().getXP()));
        infoPoisoned = new Label(String.valueOf(logic.getPlayer().isPoisoned()));
        infoLevel = new Label(String.valueOf(logic.getPlayer().getLevel()));


        playerStatsView.add(lblName, 0,0,1,1);
        playerStatsView.add(infoName, 1,0,1,1);
        playerStatsView.add(lblHP, 0,1,1,1);
        playerStatsView.add(infoHP, 1,1,1,1);
        playerStatsView.add(lblAP, 0,2,1,1);
        playerStatsView.add(infoAP, 1,2,1,1);
        playerStatsView.add(lblDP, 0,3,1,1);
        playerStatsView.add(infoDP, 1,3,1,1);
        playerStatsView.add(lblMP, 0,4,1,1);
        playerStatsView.add(infoMP, 1,4,1,1);
        playerStatsView.add(lblXP, 0,5,1,1);
        playerStatsView.add(infoXP, 1,5,1,1);
        playerStatsView.add(lblPoisoned, 0,6,1,1);
        playerStatsView.add(infoPoisoned, 1,6,1,1);
        playerStatsView.add(lblLevel, 0,7,1,1);
        playerStatsView.add(infoLevel, 1,7,1,1);


        // inventory and equip choiceboxes
        GridPane listView = new GridPane();
        listView.setPadding(new Insets(15));
        listView.setVgap(10);
        listView.setHgap(10);
        Label lblInv = new Label("Inventory:");
        Label lblEqW = new Label("Equipped weapon:");
        Label lblEqA = new Label("Equipped armor:");
        Label lblEqS = new Label("Armed spell:");
        inventoryList = new ChoiceBox<>();

        weaponList = new ChoiceBox();

        armorList = new ChoiceBox();
        spellList = new ChoiceBox();
        weaponList.getItems().addAll("(none)");
        armorList.getItems().addAll("(none)");
        spellList.getItems().addAll("(none)");
        inventoryList.setPrefWidth(100);
        weaponList.setPrefWidth(100);
        armorList.setPrefWidth(100);
        spellList.setPrefWidth(100);

        listView.add(lblInv, 0, 0, 1,1);
        listView.add(inventoryList, 1, 0, 1,1);
        listView.add(lblEqW, 0, 1, 1,1);
        listView.add(weaponList, 1, 1, 1,1);
        listView.add(lblEqA, 0, 2, 1,1);
        listView.add(armorList, 1, 2, 1,1);
        listView.add(lblEqS, 0, 3, 1,1);
        listView.add(spellList, 1, 3, 1,1);

        // UI top: stats + lists
        HBox UItop = new HBox();
        UItop.getChildren().addAll(playerStatsView, listView);
        UItop.setSpacing(15);
        UItop.setPadding(new Insets(15));


        // UI bottom: one gridpane of move controls, two vboxes of action controls
        HBox UIBottom = new HBox();

        GridPane moveControls = new GridPane();
        north = new Button("North");
        south = new Button("South");
        east = new Button("East");
        west = new Button("West");
        north.setPrefSize(50, 30);
        south.setPrefSize(50, 30);
        east.setPrefSize(50, 30);
        west.setPrefSize(50, 30);
        moveControls.add(west, 0, 1, 1, 1);
        moveControls.add(north, 1, 0, 1, 1);
        moveControls.add(south, 1, 2, 1, 1);
        moveControls.add(east, 2, 1, 1, 1);
        moveControls.setHgap(15);
        moveControls.setVgap(15);
        moveControls.setPadding(new Insets(10));

        VBox actionControls1 = new VBox();
        fight = new Button("Fight");
        flee = new Button("Flee");
        use = new Button("Use");
        fight.setPrefSize(50, 30);
        flee.setPrefSize(50,30);
        use.setPrefSize(50,30);
        actionControls1.getChildren().addAll(fight, flee, use);
        actionControls1.setSpacing(15);
        actionControls1.setPadding(new Insets(10));

        VBox actionControls2 = new VBox();
        look = new Button("Look");
        take = new Button("Take");
        help = new Button("Help");  // smt else here, maybe DROP? help suits better to the upper menu
        look.setPrefSize(50, 30);
        take.setPrefSize(50, 30);
        help.setPrefSize(50, 30);
        actionControls2.getChildren().addAll(look, take, help);
        actionControls2.setPrefSize(100, 30);
        actionControls2.setSpacing(15);
        actionControls2.setPadding(new Insets(10));

        UIBottom.getChildren().addAll(moveControls, actionControls1, actionControls2);
        UIBottom.setSpacing(15);
        UIBottom.setPadding(new Insets(15));

        // whole UI (top + bottom)
        VBox UIwhole = new VBox();
        UIwhole.getChildren().addAll(UItop, UIBottom);
        UIwhole.setSpacing(15);
        UIwhole.setPadding(new Insets(15));

        // bottom view (text output)
        BorderPane bottomView = new BorderPane();
        output.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-font-size: 12; -fx-text-fill: yellow; ");
        output.setPrefSize(500, 300);
        output.setEditable(false);
        output.setFocusTraversable(false);
        //output.setMouseTransparent(true);     // output window not affected by clicking if this is active BUT CANNOT SCROLL...
        bottomView.setCenter(output);
        bottomView.setPadding(new Insets(15));

        // top view (map + UI)
        HBox topView = new HBox();
        topView.getChildren().addAll(mapView, UIwhole);

        // whole view together!
        VBox root = new VBox();
        root.getChildren().addAll(topView, bottomView);
        root.setStyle("-fx-background-color: #CBBFBF");
        root.setPrefSize(900, 600);

        // update everything
        updateMap();
        updateStats();

        // ***************

        // keyboard input(key press fires button event)
        root.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

            // combat NOT active
            if (!logic.isCombatActivated()) {
                if (key.getCode() == KeyCode.W) {
                    north.fire();
                } else if (key.getCode() == KeyCode.A) {
                    west.fire();
                } else if (key.getCode() == KeyCode.S) {
                    south.fire();
                } else if (key.getCode() == KeyCode.D) {
                    east.fire();
                }
                // take items
                else if(key.getCode() == KeyCode.T) {
                    take.fire();
                }
                // **************
                // TEST USE ONLY
                else if(key.getCode() == KeyCode.E) {
                    println("Item equipped!");
                    logic.getPlayer().equipItem(ItemType.SHORT_SWORD);
                }
                // help
                else if(key.getCode() == KeyCode.H) {
                    help.fire();
                }
                // look around
                else if(key.getCode() == KeyCode.L) {
                    look.fire();
                }
                else {
                    println("Unknown command.");
                }
            }

            // combat IS active
            else {
                if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.A || key.getCode() == KeyCode.S || key.getCode() == KeyCode.D) {
                    println("Cannot move normally in a combat! Choose to fight (x) or flee (f).");
                }
                // fight
                else if(key.getCode() == KeyCode.X) {
                    fight.fire();
                }
                // flee
                else if(key.getCode() == KeyCode.F) {
                    flee.fire();
                }
                // help also available in combat
                else if(key.getCode() == KeyCode.H) {
                    help.fire();
                }
            }

            // map and stats updated
            updateMap();
            updateStats();

        });

        // ************

        // control button actions (includes code)
        north.setOnAction(e -> {
            if (!logic.isCombatActivated()) {
                println(logic.movePlayer(0, -1));
                updateMap();
                updateStats();
            }
            else {
                println("No moving actions but fleeing are allowed during combat. Try something else!");
            }
        });
        south.setOnAction(e -> {
            if (!logic.isCombatActivated()) {
                println(logic.movePlayer(0, 1));
                updateMap();
                updateStats();
            }
            else {
                println("No moving actions but fleeing are allowed during combat. Try something else!");
            }
        });
        east.setOnAction(e -> {
            if (!logic.isCombatActivated()) {
                println(logic.movePlayer(1, 0));
                updateMap();
                updateStats();
            }
            else {
                println("No moving actions but fleeing are allowed during combat. Try something else!");
            }
        });
        west.setOnAction(e -> {
            if (!logic.isCombatActivated()) {
                println(logic.movePlayer(-1, 0));
                updateMap();
                updateStats();
            }
            else {
                println("No moving actions but fleeing are allowed during combat. Try something else!");
            }
        });
        look.setOnAction(e -> {
            println(logic.describeBlock());
        });
        fight.setOnAction(e -> {
            if (logic.isCombatActivated()) {
                // init combat
                Combat combat = new Combat(logic.getPlayer(), logic.activatedEnemy());
                combat.engageCombat();
                println(combat.combatReport());

                if (!logic.getPlayer().isAlive()) {
                    logic.setGameOver();
                    println(logic.gameOver());
                    // disable all input
                    root.addEventFilter(KeyEvent.ANY, KeyEvent::consume);
                    UIwhole.setDisable(true);
                }

                else if (!logic.activatedEnemy().isAlive()) {
                    logic.activatedEnemy().setActive(false);
                    logic.setCombatActivated(false);
                    println("COMBAT MODE DEACTIVATED");
                    println("*********");
                    logic.describeBlock();
                }
                updateMap();
                updateStats();
            }
            else {
                println("There's no one to fight here..");
            }
        });
        flee.setOnAction(e -> {
            if (logic.isCombatActivated()) {
                // player has 50% chance of getting hit before escaping
                Random random = new Random();
                int hit = logic.activatedEnemy().getAP();
                if (random.nextInt(2) > 0) {
                    logic.getPlayer().takeHit(Math.abs(logic.getPlayer().getDP() - hit));
                    println("Enemy strikes you in the back before you escape! You get " + hit + " damage.");
                    logic.setCombatActivated(false);
                    println("COMBAT MODE DEACTIVATED");
                    println("*********");
                }
                else {
                    println("Enemy fails to hit and you manage to escape unharmed!");
                    logic.setCombatActivated(false);
                    println("COMBAT MODE DEACTIVATED");
                    println("*********");
                }
                updateMap();
                updateStats();
            }
            else {
                println("You don't have to run away from anything.");
            }
        });
        take.setOnAction(e -> {
            if (logic.takeItems()) {
                println("All items collected to your inventory!");
                updateStats();
            }
            else {
                println("Nothing you can take.");
            }
        });

        // THIS MAYBE TO MSG BOX / ALERT?
        help.setOnAction(e -> {
            println("Keyboard commands:");
            println("Movement: WASD (north, west, south, east)");
            println("Look: L");
            println("Help: H");
            println("Inventory: I");
            println("Player stats: P");
            println("Fight: X");
            println("Flee: F");
        });

        // choiceboxes

        use.setOnAction(e -> {

            Item usable = inventoryList.getValue();

            if (usable.toString().equals("nothing")) {
                println("Nothign to use!");
            }
            else {

                if (usable.getItemClass().equals("potion")) {
                    if (usable.getType().equals(ItemType.HEALING_POTION)) {
                        println("*GULP* What a wonderful taste.. the taste of life!");
                        println("You gain " + usable.getPotion() + " HP.");
                        logic.getPlayer().setHP(logic.getPlayer().getHP() + usable.getPotion());
                    }
                    else if (usable.getType().equals(ItemType.MANA_POTION)) {
                        println("*GULP* You feel the magic flowing into your heart and veins!");
                        println("You gain " + usable.getPotion() + " MP.");
                        logic.getPlayer().setMP(logic.getPlayer().getMP() + usable.getPotion());
                    }

                    logic.getPlayer().getInventory().remove(usable);
                    updateStats();
                }

                else if (usable.getItemClass().equals("spell")) {
                    if (logic.getPlayer().getMP() >= usable.getSpell()) {
                        println("You succesfully cast " + usable.toString() + "!");

                        if (usable.getType().equals(ItemType.HEALING_SPELL)) {
                            println("You gain " + usable.getSpell() + " HP.");
                            logic.getPlayer().setHP(usable.getSpell());
                        }
                        else if (usable.getType().equals(ItemType.LIGHTNING_BOLT)) {
                            if (logic.isCombatActivated()) {
                                logic.activatedEnemy().takeHit(usable.getSpell());
                                println("A lightning bolt hits the enemy, ignoring armo and dealing " + usable.getSpell() + " damage!");
                                if (!logic.activatedEnemy().isAlive()) {
                                    logic.setCombatActivated(false);
                                }
                            }
                            else {
                                println("A ball of lightning bounces from the walls totally random and then disappears.\nVery useful. Good job.");
                            }
                        }

                        logic.getPlayer().getInventory().remove(usable);
                        updateStats();
                    }

                    else {
                        println("Not enough mana for spell casting.");
                    }
                }
            }
        });

        // actions for weapon and armor lists

        
        mapView.setCenter(tileMap);
        return root;    // returns whole view to the app class
    }


    // draws the entire map
    private void updateMap() {

        for (int i = 0; i < logic.getMapSizeX(); i++) {
            for (int j = 0; j < logic.getMapSizeY(); j++) {

                char mapID = logic.getMap()[i][j];

                ImageView groundTile = new ImageView();
                groundTile.setFitWidth(32);
                groundTile.setFitHeight(32);

                // walls and corridors
                if (mapID == '#') {
                    groundTile.setImage(wall);
                }
                else if (mapID == 'C' || !logic.getBlocks()[i][j].hasEnemies()) {   // empty also when enemies = dead
                    groundTile.setImage(empty);
                }

                tileMap.add(groundTile, i, j);

                // items
                if (logic.getBlocks()[i][j].hasItems()) {

                    ImageView itemTile = new ImageView();
                    itemTile.setFitWidth(32);
                    itemTile.setFitHeight(32);
                    itemTile.setScaleX(0.5);    // items scaled smaller
                    itemTile.setScaleY(0.5);

                    if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.HEALING_POTION)) {
                        itemTile.setImage(hpotion);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.MANA_POTION)) {
                        itemTile.setImage(mpotion);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.MANA_POTION)) {
                        itemTile.setImage(mpotion);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.SHORT_SWORD)) {
                        itemTile.setImage(ssword);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.BATTLE_AXE)) {
                        itemTile.setImage(baxe);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.CHAINMAIL_ARMOR)) {
                        itemTile.setImage(chainmail);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.KNIGHT_ARMOR)) {
                        itemTile.setImage(karmor);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.HEALING_SPELL)) {
                        itemTile.setImage(hspell);
                    }
                    else if (logic.getBlocks()[i][j].getItems().get(0).getType().equals(ItemType.LIGHTNING_BOLT)) {
                        itemTile.setImage(lspell);
                    }

                    tileMap.add(itemTile, i, j);
                }

                // enemies
                if (logic.getBlocks()[i][j].hasEnemies()) {

                    ImageView enemyTile = new ImageView();
                    enemyTile.setFitWidth(32);
                    enemyTile.setFitHeight(32);


                    if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.RAT)) {
                        enemyTile.setScaleX(0.6);
                        enemyTile.setScaleY(0.6);
                        enemyTile.setImage(rat);
                    }
                    else if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.SNAKE)) {
                        enemyTile.setScaleX(0.6);
                        enemyTile.setScaleY(0.6);
                        enemyTile.setImage(snake);
                    }
                    else if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.GOBLIN)) {
                        enemyTile.setScaleX(0.7);
                        enemyTile.setScaleY(0.7);
                        enemyTile.setImage(goblin);
                    }
                    else if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.ORC)) {
                        enemyTile.setScaleX(0.8);
                        enemyTile.setScaleY(0.8);
                        enemyTile.setImage(orc);
                    }
                    else if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.CAVE_TROLL)) {
                        enemyTile.setImage(troll);
                        enemyTile.setScaleX(0.9);
                        enemyTile.setScaleY(0.9);
                    }
                    else if (logic.getBlocks()[i][j].getEnemies().get(0).getType().equals(EnemyType.DRAGON)) {
                        enemyTile.setImage(dragon);
                    }
                    tileMap.add(enemyTile, i, j);
                }

                // player image last, so it sits on top of items
                if (mapID == 'S' && logic.getPlayer().isAlive()) {
                    ImageView playerTile = new ImageView(hero);
                    playerTile.setFitWidth(32);
                    playerTile.setFitHeight(32);
                    playerTile.setScaleX(0.8);
                    playerTile.setScaleY(0.8);
                    tileMap.add(playerTile, i, j);
                }
                else if (mapID == 'S' && !logic.getPlayer().isAlive()) {
                    ImageView deadTile = new ImageView(bones);
                    deadTile.setFitWidth(32);
                    deadTile.setFitHeight(32);
                    deadTile.setScaleX(0.8);
                    deadTile.setScaleY(0.8);
                    tileMap.add(deadTile, i , j);
                }
            }
        }
    }

    private void updateStats() {

        infoHP.setText(String.valueOf(logic.getPlayer().getHP()));
        // HP counter goes red when reached 2 or below
        if (logic.getPlayer().getHP() <= 2) {
            infoHP.setStyle("-fx-text-fill: red;");
        }
        else {
            infoHP.setStyle("-fx-text-fill: black;");
        }
        infoAP.setText(String.valueOf(logic.getPlayer().getAP()));
        infoDP.setText(String.valueOf(logic.getPlayer().getDP()));
        infoMP.setText(String.valueOf(logic.getPlayer().getMP()));
        infoXP.setText(String.valueOf(logic.getPlayer().getXP()));
        if (logic.getPlayer().isPoisoned()) {
            infoPoisoned.setText("Yes");
        }
        else {
            infoPoisoned.setText("No");
        }
        infoLevel.setText(String.valueOf(logic.getPlayer().getLevel()));

        // update lists (inventory, weapon, armor, spell)
        inventoryList.getItems().clear();
        if (!logic.getPlayer().getInventory().isEmpty()) {
            for (Item e: logic.getPlayer().getInventory()) {
                inventoryList.getItems().add(e);
                inventoryList.setValue(e);
            }
        }
        else {
            Item n = new Item(ItemType.NOTHING);
            inventoryList.getItems().add(n);
            inventoryList.setValue(n);
        }

/*        weaponList.getItems().clear();
        weaponList.getItems().add("(none)");
        for (Item e: logic.getPlayer().getInventory()) {
            if (e.getItemClass().equals("weapon")) {
                weaponList.getItems().add(e.toString());
            }
        }

        armorList.getItems().clear();
        armorList.getItems().add("(none)");
        for (Item e: logic.getPlayer().getInventory()) {
            if (e.getItemClass().equals("armor")) {
                armorList.getItems().add(e.toString());
            }
        }

        spellList.getItems().clear();
        spellList.getItems().add("(none)");
        for (Item e: logic.getPlayer().getInventory()) {
            if (e.getItemClass().equals("spell")) {
                spellList.getItems().add(e.toString());
            }
        }*/



    }


    // text output is handled by this method
    private void println(String text) {

     // combat text style red, but maybe activate combat buttons instead?
        if (logic.isCombatActivated()) {
            output.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-font-size: 12; -fx-text-fill: red");
        }
        else {
            output.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-font-size: 12;-fx-text-fill: yellow");
        }

        output.appendText(text + "\n");
        output.setScrollLeft(0);
        output.setScrollTop(0);
    }

}
