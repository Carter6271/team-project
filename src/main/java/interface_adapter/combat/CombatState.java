package interface_adapter.combat;

public class CombatState {
    private String characterName = "";
    private String monsterName = "";

    private int characterHealth;
    private int monsterHealth;

    private String message = "";

    private boolean combatFinished;
    private boolean characterWon;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getCharacterHealth() {
        return characterHealth;
    }

    public void setCharacterHealth(int characterHealth) {
        this.characterHealth = characterHealth;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCombatFinished() {
        return combatFinished;
    }

    public void setCombatFinished(boolean combatFinished) {
        this.combatFinished = combatFinished;
    }

    public boolean isCharacterWon() {
        return characterWon;
    }

    public void setCharacterWon(boolean characterWon) {
        this.characterWon = characterWon;
    }
}