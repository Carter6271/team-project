package usecase.combat;

import entity.CombatEncounter;
import entity.DiceRoller;
import entity.Monster;
import entity.PlayerCharacter;

public class CombatInteractor implements CombatInputBoundary {
    private final CombatDataAccessInterface dataAccess;
    private final CombatOutputBoundary presenter;
    private final DiceRoller diceRoller;

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
        int playerInitiative;
        int monsterInitiative;
        boolean playerTurn;
        StringBuilder message = new StringBuilder();

        if (character == null || monster == null) {
            //TODO display error
            return;
        }
        if (!character.isAlive() || !monster.isAlive()) {
            //TODO display error
            return;
        }

        CombatEncounter combat = new CombatEncounter(character, monster);

        playerInitiative = diceRoller.roll (20) + (character.getStats().getDexterity() - 10) / 2;
        monsterInitiative = diceRoller.roll (20) + (monster.getStats().getDexterity() - 10) / 2;

        playerTurn = playerInitiative >= monsterInitiative;
        if (playerTurn) {
            message.append("Started combat with ")
                    .append(monster.getName())
                    .append(". You got a ")
                    .append(playerInitiative)
                    .append(" and ")
                    .append(monster.getName())
                    .append(" got a ")
                    .append(monsterInitiative)
                    .append(" so you go first.");

        } else {
            message.append("Started combat with ")
                    .append(monster.getName())
                    .append(". You got a ")
                    .append(playerInitiative)
                    .append(" and ")
                    .append(monster.getName())
                    .append(" got a ")
                    .append(monsterInitiative)
                    .append(" so ")
                    .append(monster.getName())
                    .append(" goes first.");
        }
        CombatOutputData outputData = new CombatOutputData(character, monster,
                character.getHealth(), monster.getCurrentHealth(), message.toString(),
                playerTurn,false, false);

        presenter.prepareSuccessView(outputData);
    }

    @Override
    public void attack(CombatInputData inputData) {


        if (inputData.isPlayerTurn()) {

        }
    }
}
