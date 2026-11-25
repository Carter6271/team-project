package app;

import data_access.InMemoryExploreDataAccessObject;
import data_access.DungeonFactory;
import entity.Inventory;
import entity.characters.CharacterClass;
import entity.characters.PlayerCharacter;
import entity.characters.Race;
import entity.characters.Stats;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Runs the Explore screen on its own for testing and demos.
 * This is the class to run in IntelliJ (it has the main method).
 */
public class ExploreMain {
    /** Small on purpose, so the "inventory full" case is easy to try. */
    private static final int INVENTORY_CAPACITY = 3;

    private static Stats testStats = new Stats(10, 10, 10, 10, 10, 10);
    private static Race testRace = new Race("test race", testStats);
    private static CharacterClass testClass = new CharacterClass("test class", testStats);

    /**
     * Starts the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Swing rule: create and change UI components only on the Swing event thread.
        SwingUtilities.invokeLater(() -> {
            // Game state: the sample floor plus an empty inventory.
            InMemoryExploreDataAccessObject dataAccess = new InMemoryExploreDataAccessObject(
                    DungeonFactory.createFloorOne(), new Inventory(INVENTORY_CAPACITY),
                    new PlayerCharacter(testRace, testClass));

            // Build the window step by step, then show it.
            JFrame frame = new ExploreAppBuilder(dataAccess)
                    .addExploreView()
                    .addCombatView()
                    .addExploreUseCases()
                    .build();
            frame.setVisible(true);
        });
    }
}
