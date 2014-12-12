package test;

import static org.junit.Assert.*;

import org.junit.*;

import zelda.engine.*;
import zelda.collision.*;
import zelda.enemy.*;
import zelda.karacter.Direction;
import zelda.scene.*;
import zelda.enemy.armos.*;


public class CollisionTest {
	Game game1;
	HyruleScene hyruleTest;
	BlueSoldier soldierBombTest, soldierArrowTest, soldierSwordTest;
	ArmosKnight knightTest;
	GhostSoldier ghostTest;
	Weapon bomb, arrow, sword;
	long lastHit;
	boolean alive;
	
	@Before
	public void setUp() throws Exception {
		game1 = new Game();
		hyruleTest = new HyruleScene(game1, "HouseScene");
		lastHit = System.currentTimeMillis();
		
		bomb = Weapon.BOMB;
		arrow = Weapon.ARROW;
		sword = Weapon.SWORD;
		
		ghostTest = new GhostSoldier(game1, 240, 98, Direction.DOWN);
		knightTest = new ArmosKnight(game1, 231, 90, Direction.DOWN);
		soldierBombTest = new BlueSoldier(game1, 512, 810, Direction.DOWN, 40);
		soldierArrowTest = new BlueSoldier(game1, 522, 830, Direction.DOWN, 40);
		soldierSwordTest = new BlueSoldier(game1, 532, 820, Direction.DOWN, 40);
	}
	
	
	
	@Test
	public void collisionTest() throws InterruptedException {
		
		Thread.sleep(800);
		
		soldierBombTest.hitBy(bomb);
		soldierArrowTest.hitBy(arrow);
		soldierSwordTest.hitBy(sword);
		
		ghostTest.hitBy(sword);
		
		knightTest.hitBy(bomb);
		//knightTest.hitBy(arrow);
		
		//Thread.sleep(1000);
		//knightTest.hitBy(sword);
		
		assertTrue("Must be 0 if hit with bomb", soldierBombTest.getHealth() == 0);
		assertTrue("Must be 3 if hit with an arrow", soldierArrowTest.getHealth() == 3);
		assertTrue("Must be 3 if hit with a sword", soldierSwordTest.getHealth() == 3);
		
		assertEquals("Must be 17 if hit with sowrd", 17, ghostTest.getHealth());
		
		assertEquals("Must be 11 if hit with all 3 weapon once", 15, knightTest.getHealth());
		
		
		
	}
	
	
	@Test
	public void healthTest() throws InterruptedException{
		
		Thread.sleep(800);
		ghostTest.hitBy(bomb);
		Thread.sleep(1000);
		ghostTest.hitBy(bomb);
		
		alive = ghostTest.isAlive();
		assertFalse("The ghostSoldier must be dead after all its health is gone ", alive);
		
	}

}
