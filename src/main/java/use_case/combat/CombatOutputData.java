package use_case.combat;

import entity.Monster;
import entity.PlayerCharacter;

public class CombatOutputData {
    private final PlayerCharacter character;
    private final Monster monster;

    private final int characterHealth;
    private final int monsterHealth;

    private final String combatMessage;

    private final boolean playerTurn;
    private final boolean combatFinished;
    private final boolean characterWon;

    public CombatOutputData(PlayerCharacter character,
                            Monster monster,
                            int characterHealth,
                            int monsterHealth,
                            String combatMessage,
                            boolean playerTurn,
                            boolean combatFinished,
                            boolean characterWon) {

        this.character = character;
        this.monster = monster;
        this.characterHealth = characterHealth;
        this.monsterHealth = monsterHealth;
        this.combatMessage = combatMessage;
        this.playerTurn = playerTurn;
        this.combatFinished = combatFinished;
        this.characterWon = characterWon;
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getCharacterHealth() {
        return characterHealth;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public String getCombatMessage() {
        return combatMessage;
    }

    public boolean isPlayerTurn() {return playerTurn;}

    public boolean isCombatFinished() {
        return combatFinished;
    }

    public boolean isCharacterWon() {
        return characterWon;
    }
}
