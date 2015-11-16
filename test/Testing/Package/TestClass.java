/**
 * Nov 16, 2015
 *
 * @author H3247
 */
package Testing.Package;

import game.GameEngine;
import game.character.*;
import game.movement.Location;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClass {

    private Boat boat;
    private GameEngine engine;

    @Before
    public void runBeforeEachTesT() {
        this.engine = GameEngine.getInstance();
        this.engine.initialize();
        Factory tehdas = new Factory();
        this.boat = (Boat) tehdas.createCharacter("BOAT");
    }

    @Test
    public void testEnergy() {
        this.testEnergyHelper(-50);
        this.testEnergyHelper(0);
        this.testEnergyHelper(50);
        this.testEnergyHelper(100);
        this.testEnergyHelper(150);
    }

    private void testEnergyHelper(int energy) {
        this.boat.setEnergy(energy);
        assertTrue(this.boat.getEnergy() == energy);
    }

    @Test
    public void testReduceEnergy() {
        for (int energy = 100; energy > 1; energy--) {
            this.boat.setEnergy(energy);
            this.boat.collision(this.boat);
            assertTrue(this.boat.getEnergy() < energy);
        }
    }

    @Test
    public void testMouseLocation() {

        this.boat.setLocation(new Location(10, 10));

        MouseEvent evt = new MouseEvent(new Canvas(), 0, 0, 0, 50, 50, 0, false);
        this.boat.getController().handleMouseClick(evt);
        this.boat.update();

        // testataan onko sijainti vaihtunut alkuperäisestä
        assertTrue(this.boat.getX() != 10);
        assertTrue(this.boat.getY() != 10);

    }

    @Test
    public void testGoal() {

        Factory tehdas = new Factory();
        Goal maali = (Goal) tehdas.createCharacter("GOAL");
        Boat vene = (Boat) tehdas.createCharacter("BOAT");

        //maali alueelle
        vene.setLocation(new Location(600, 550));
        vene.update();

        assertTrue(maali.collides(vene));

    }

}
