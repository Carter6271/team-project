package interface_adapter.combat;

import entity.characters.Monster;
import entity.characters.PlayerCharacter;
import use_case.combat.CombatInputBoundary;
import use_case.combat.CombatInputData;

public class CombatController implements CombatInputBoundary {
    private final CombatInputBoundary interactor;
    private PlayerCharacter character;
    private Monster monster;

    public CombatController(CombatInputBoundary interactor) {
        this.interactor = interactor;
    }

    public CombatController(CombatInputBoundary interactor, PlayerCharacter character,
                            Monster monster) {
        this.interactor = interactor;
        this.character = character;
        this.monster = monster;
    }

    /** Receives the encounter discovered by the Move use case. */
    @Override
    public void startCombat(CombatInputData inputData) {
        this.character = inputData.getCharacter();
        this.monster = inputData.getMonster();
        interactor.startCombat(inputData);
    }

    public void startCombat() {
        CombatInputData inputData = new CombatInputData(character, monster);
        startCombat(inputData);
    }

    public void attack() {
        if (character == null || monster == null) return;
        CombatInputData inputData = new CombatInputData(character, monster);
        interactor.attack(inputData);
    }

    @Override
    public void attack(CombatInputData inputData) {
        interactor.attack(inputData);
    }
}
