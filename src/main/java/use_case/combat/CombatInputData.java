package use_case.combat;

import entity.Monster;
import entity.PlayerCharacter;

public class CombatInputData {
    private final PlayerCharacter character;
    private final Monster monster;
    private boolean playerTurn;

    public CombatInputData(PlayerCharacter character, Monster monster, boolean playerTurn) {
        this.character = character;
        this.monster = monster;
        this.playerTurn = playerTurn;
    }

    public PlayerCharacter getCharacter() {return character;}

    public Monster getMonster() {return monster;}

    public boolean isPlayerTurn() {return playerTurn;}
}