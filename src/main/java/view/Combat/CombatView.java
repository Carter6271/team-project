package view.Combat;

import interface_adapter.combat.CombatState;
import interface_adapter.combat.CombatViewModel;
import interface_adapter.combat.CombatViewModelListener;
import interface_adapter.combat.CombatController;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;


/**
 * The Combat screen (Use Case 3): displays the player's character,
 * the monster, their health, and the combat log.
 *
 * The view calls the combat controller when the player attacks.
 * It does not calculate attack rolls or damage.
 */
public class CombatView extends JPanel implements CombatViewModelListener{

    /** Where this view gets its data from. */
    private final CombatViewModel viewModel;

    /** Called when the player chooses to attack. */
    private CombatController combatController;

    // ----- Swing components -----

    private final JLabel titleLabel = new JLabel("COMBAT", SwingConstants.CENTER);

    private final JLabel characterNameLabel = new JLabel();
    private final JLabel characterHealthLabel = new JLabel();

    private final JLabel monsterNameLabel = new JLabel();
    private final JLabel monsterHealthLabel = new JLabel();

    private final JTextArea combatLogArea = new JTextArea(8, 40);

    private final JButton attackButton = new JButton("Attack");

    /**
     * Builds the screen and starts listening to the view model.
     *
     * @param viewModel the Combnat view model
     */
    public CombatView(CombatViewModel viewModel) {
        this.viewModel = viewModel;

        viewModel.addListener(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        add(titleLabel, BorderLayout.NORTH);
        add(buildCombatantsPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        wireActions();

        // Display the initial state.
        setFields(viewModel.getState());
    }

    /**
     * Gives this view its Combat controller.
     *
     * @param combatController the controller
     */
    public void setCombatController(CombatController combatController) {this.combatController = combatController;}

    /**
     * @return this screen's name in the CardLayout
     */
    public String getViewName() {return viewModel.getViewName();}

    // =========================================================================
    // Layout: building the panels
    // =========================================================================

    /**
     * Builds the two side-by-side panels showing the player and monster.
     *
     * @return the combatants panel
     */
    private JPanel buildCombatantsPanel() {
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 22f));

        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 0));

        panel.add(buildCharacterPanel());
        panel.add(buildMonsterPanel());

        return panel;
    }

    /**
     * Builds the player's character information.
     *
     * @return the character panel
     */
    private JPanel buildCharacterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createTitledBorder("Your Character"));

        characterNameLabel.setFont(
                characterNameLabel.getFont().deriveFont(Font.BOLD, 18f)
        );

        panel.add(characterNameLabel);
        panel.add(characterHealthLabel);

        return panel;
    }

    /**
     * Builds the monster's information.
     *
     * @return the monster panel
     */
    private JPanel buildMonsterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createTitledBorder("Monster"));

        monsterNameLabel.setFont(
                monsterNameLabel.getFont().deriveFont(Font.BOLD, 18f)
        );

        panel.add(monsterNameLabel);
        panel.add(monsterHealthLabel);

        return panel;
    }

    /**
     * Builds the combat log and the player's action buttons.
     *
     * @return the bottom panel
     */
    private JPanel buildBottomPanel() {
        combatLogArea.setEditable(false);
        combatLogArea.setLineWrap(true);
        combatLogArea.setWrapStyleWord(true);
        combatLogArea.setFont(combatLogArea.getFont().deriveFont(14f));

        JScrollPane logScroll = new JScrollPane(combatLogArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Combat Log"));
        logScroll.setPreferredSize(new Dimension(400, 150));

        JPanel actionsPanel = new JPanel();
        actionsPanel.add(attackButton);

        JPanel panel = new JPanel(new BorderLayout(5, 10));
        panel.add(logScroll, BorderLayout.CENTER);
        panel.add(actionsPanel, BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================================
    // User actions
    // =========================================================================

    /**
     * Connects the attack button to the controller.
     */
    private void wireActions() {
        attackButton.addActionListener(e -> {
            if (combatController != null) {
                combatController.attack();
            }
        });
    }

    // =========================================================================
    // Updates from the view model
    // =========================================================================

    /**
     * Called automatically when the presenter updates the view model.
     * Redraws the screen using the latest combat state.
     */
    @Override
    public void stateChanged() {
        setFields(viewModel.getState());
    }

    /**
     * Copies the state into the Swing components.
     *
     * @param state the current combat state
     */
    private void setFields(CombatState state) {
        characterNameLabel.setText(state.getCharacterName());
        characterHealthLabel.setText("Health: " + state.getCharacterHealth());

        monsterNameLabel.setText(state.getMonsterName());
        monsterHealthLabel.setText("Health: " + state.getMonsterHealth());

        combatLogArea.setText(state.getMessage());
        combatLogArea.setCaretPosition(
                combatLogArea.getDocument().getLength()
        );

        attackButton.setEnabled(!state.isCombatFinished());

        if (state.isCombatFinished()) {
            if (state.isCharacterWon()) {
                titleLabel.setText("VICTORY!");
            } else {
                titleLabel.setText("DEFEAT");
            }
        } else {
            titleLabel.setText("COMBAT");
        }
    }
}
