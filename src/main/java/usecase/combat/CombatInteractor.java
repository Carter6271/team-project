package usecase.combat;

import entity.CombatEncounter;
import entity.DiceRoller;
import entity.Monster;
import entity.PlayerCharacter;

public class CombatInteractor implements CombatInputBoundary {
    private final CombatDataAccessInterface dataAccess;
    private final CombatOutputBoundary presenter;
    private final DiceRoller diceRoller;
    private int playerInitiative;
    private int monsterInitiative;
    private boolean playerTurn;

    public CombatInteractor(
            CombatDataAccessInterface dataAccess,
            CombatOutputBoundary presenter,
            DiceRoller diceRoller) {

        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.diceRoller = diceRoller;
    }

    @Override
    public void startCombat(CombatInputData inputData) {
        PlayerCharacter character = inputData.getCharacter();
        Monster monster = inputData.getMonster();

        if (!character.isAlive() || !monster.isAlive()) {
            //TODO display error
        }

        CombatEncounter combat = new CombatEncounter(character, monster);

        playerInitiative = diceRoller.roll (20) + (character.getStats().getDexterity() - 10) / 2;
        monsterInitiative = diceRoller.roll (20) + (monster.getStats().getDexterity() - 10) / 2;

        playerTurn = playerInitiative >= monsterInitiative;
        CombatOutputData outputData = new CombatOutputData(character, monster,
                                                            character.getHealth(), monster.getCurrentHealth(),
                                                            "Started combat with " + monster.getName(),
                                                            playerTurn,false, false);
        presenter.prepareSuccessView(outputData);
    }

    @Override
    public void attack(CombatInputData inputData) {
        if (inputData.isPlayerTurn()) {

        }
    }
}
