package entity;

import combat.action.*;
import entity.item.equiment.Armor;
import entity.item.equiment.Weapon;
import event.Event;
import main.SimpleRPG;
import world.Tile;
import world.World;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Character {
    public static final int SPRITE_HEIGHT = 48;
    public static final int SPRITE_WIDTH = 48;
    public static final int MOVEMENT_SPEED = 3;
    public static final int SPRINT_SPEED = 6;
    public static final int X = 1280/2-SPRITE_WIDTH/2;
    public static final int Y = 720/2-SPRITE_HEIGHT/2;
    public static final int MAX_STAMINA = 200;

    private Weapon weapon;
    private Armor armor;
    private int stamina;
    private boolean isSprinting;

    @Override
    public double getRelativeX() {
        return this.getX();
    }
    @Override
    public double getRelativeY() {
        return this.getY();
    }
    public int getStamina() {
        return this.stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public void sprint() {
        this.isSprinting = true;
        this.setDx((int)(this.getDx() / this.getMovementSpeed() * Player.SPRINT_SPEED));
        this.setDy((int)(this.getDy() / this.getMovementSpeed() * Player.SPRINT_SPEED));
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        this.setMovementSpeed(Player.SPRINT_SPEED);
    }
    public void unSprint() {
        this.isSprinting = false;
        this.setDx((int)(this.getDx() * Player.MOVEMENT_SPEED / this.getMovementSpeed()));
        this.setDy((int)(this.getDy() * Player.MOVEMENT_SPEED / this.getMovementSpeed()));
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        this.setMovementSpeed(Player.MOVEMENT_SPEED);
    }
    public boolean isSprintable() {
        return (this.getStamina() > 0);
    }
    public boolean isSprinting() {
        return this.isSprinting;
    }
    public void sprintRender() {
        if (this.isSprinting && this.isSprintable()) {
            this.setStamina(this.getStamina() - 1);
        } else if (this.getStamina() < MAX_STAMINA) {
            if (this.getStamina() <= 2 && !this.isSprinting()) {
                this.setStamina(this.getStamina() + 2);
            } else if (this.getStamina() > 2) {
                this.setStamina(this.getStamina() + 2);
            }
        }
        if (this.getStamina() <= 0 && this.isSprinting()) {
            this.unSprint();
        }
    }

    public Player(SimpleRPG master, int x, int y, String name, String imagePath, int level,
                  int attackSpeed, int attackPoint, int defensePoint, int healthPoint, int manaPoint, int maxHealthPoint, int maxManaPoint, Weapon weapon, Armor armor) {
        super(master, x, y, name, imagePath, 32, 80, level, attackSpeed, attackPoint, defensePoint, healthPoint, manaPoint, maxHealthPoint, maxManaPoint, weapon, armor,"left");
        this.weapon = weapon;
        this.armor = armor;
        this.stamina = MAX_STAMINA;
        this.setMovementSpeed(Player.MOVEMENT_SPEED);
        this.defaultFrame(Character.DOWN);
        this.setActionList(new Action[] {new Cyclone(), new Heal(), new DoubleSlash(), new Spark()});
    }

    public void equip(Weapon weapon) {
        super.setAttackSpeed(super.getAttackSpeed() - this.weapon.getAttackSpeed());
        super.setAttackPoint(super.getAttackPoint() - this.weapon.getAttackPoint());
        super.setDefensePoint(super.getDefensePoint() - this.weapon.getDefensePoint());
        super.setMaxHealthPoint(super.getMaxHealthPoint() - this.weapon.getMaxHealthPoint());
        super.setMaxManaPoint(super.getMaxManaPoint() - this.weapon.getMaxManaPoint());
        this.weapon.setActivated(false);

        super.setAttackSpeed(super.getAttackSpeed() + weapon.getAttackSpeed());
        super.setAttackPoint(super.getAttackPoint() + weapon.getAttackPoint());
        super.setDefensePoint(super.getDefensePoint() + weapon.getDefensePoint());
        super.setMaxHealthPoint(super.getMaxHealthPoint() + weapon.getMaxHealthPoint());
        super.setMaxManaPoint(super.getMaxManaPoint() + weapon.getMaxManaPoint());
        this.weapon = weapon;
        this.weapon.setActivated(true);
    }
    public void equip(Armor armor) {
        super.setAttackSpeed(super.getAttackSpeed() - this.armor.getAttackSpeed());
        super.setAttackPoint(super.getAttackPoint() - this.armor.getAttackPoint());
        super.setDefensePoint(super.getDefensePoint() - this.armor.getDefensePoint());
        super.setMaxHealthPoint(super.getMaxHealthPoint() - this.armor.getMaxHealthPoint());
        super.setMaxManaPoint(super.getMaxManaPoint() - this.armor.getMaxManaPoint());
        this.armor.setActivated(false);

        super.setAttackSpeed(super.getAttackSpeed() + armor.getAttackSpeed());
        super.setAttackPoint(super.getAttackPoint() + armor.getAttackPoint());
        super.setDefensePoint(super.getDefensePoint() + armor.getDefensePoint());
        super.setMaxHealthPoint(super.getMaxHealthPoint() + armor.getMaxHealthPoint());
        super.setMaxManaPoint(super.getMaxManaPoint() + armor.getMaxManaPoint());
        this.armor = armor;
        this.armor.setActivated(true);
    }


    @Override
    public void tick() {
        super.tick();
        sprintRender();
//        int xPos = Player.X;
//        int yPos = Player.Y;
        // Now check if the map scrolls or not
        World world = ((World) this.getMaster().getWorld());
        // Check x-axis:
        if (this.getRelativeX() <= (double)SimpleRPG.SCREEN_WIDTH/2 - (double)SPRITE_WIDTH/2) {
//            xPos = (int) this.getRelativeX();
            this.setXDisplay(this.getRelativeX());
        } else if (this.getRelativeX() >= world.getBg().getWidth() - (double)SimpleRPG.SCREEN_WIDTH/2 - (double)SPRITE_WIDTH/2) {
//            xPos = (int) this.getRelativeX() - (int) (world.getBg().getWidth() - SimpleRPG.SCREEN_WIDTH);
            this.setXDisplay(this.getRelativeX() - world.getBg().getWidth() + SimpleRPG.SCREEN_WIDTH);
        } else {
            this.setXDisplay(Player.X);
        }
        // Check y-axis:
        if (this.getRelativeY() <= (double)SimpleRPG.SCREEN_HEIGHT/2 - (double)SPRITE_HEIGHT/2) {
//            yPos = (int) this.getRelativeY();
            this.setYDisplay(this.getRelativeY());
        } else if (this.getRelativeY() >= world.getBg().getHeight() - (double)SimpleRPG.SCREEN_HEIGHT/2 - (double)SPRITE_HEIGHT/2) {
//            yPos = (int) this.getRelativeY() - (int) (world.getBg().getHeight() - SimpleRPG.SCREEN_HEIGHT);
            this.setYDisplay(this.getRelativeY() - world.getBg().getHeight() + SimpleRPG.SCREEN_HEIGHT);
        } else {
            this.setYDisplay(Player.Y);
        }
//        this.getGraphicContext().drawImage(this.getImage(), xPos, yPos);
//        System.out.println("X = " + this.getX() + "; Y = " + this.getY());
    }

    @Override
    public void move(String direction) {
        super.move(direction);
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
    }
    @Override
    public void stopMoving(String direction) {
        super.stopMoving(direction);
        ((World) this.getMaster().getWorld()).setDx(-this.getDx());
        ((World) this.getMaster().getWorld()).setDy(-this.getDy());
    }


    public void interact() {
        World world = (World) this.getMaster().getWorld();
        for (Event event: world.getEventList()) {
            // Make sure that we don't interact with ourselves
//            if (!(this.getTile() == tile)) {
//            }
            if (event.getRect() != null && event.getTriggerType().equals(Event.TRIGGER_TYPE_INTERACT)) {
                switch (this.getLastDirection()) {
                    case Character.UP: {
                        if (event.getRect().intersects(this.getX() - Player.MOVEMENT_SPEED,
                                this.getY() + this.getImage().getHeight() - Tile.TILE_SIZE,
                                this.getRect().getWidth(),
                                this.getRect().getHeight())) {
                            event.trigger();
                        }
                    }
                    case Character.DOWN: {
                        if (event.getRect().intersects(this.getX() + Player.MOVEMENT_SPEED,
                                this.getY() + this.getImage().getHeight() - Tile.TILE_SIZE,
                                this.getRect().getWidth(),
                                this.getRect().getHeight())) {
                            event.trigger();
                        }
                    }
                    case Character.LEFT: {
                        if (event.getRect().intersects(this.getX(),
                                this.getY() + this.getImage().getHeight() - Tile.TILE_SIZE - Player.MOVEMENT_SPEED,
                                this.getRect().getWidth(),
                                this.getRect().getHeight())) {
                            event.trigger();
                        }
                    }
                    case Character.RIGHT: {
                        if (event.getRect().intersects(this.getX(),
                                this.getY() + this.getImage().getHeight() - Tile.TILE_SIZE + Player.MOVEMENT_SPEED,
                                this.getRect().getWidth(),
                                this.getRect().getHeight())) {
                            event.trigger();
                        }
                    }
                }
            }
        }
    }

    // This class ends here
}
