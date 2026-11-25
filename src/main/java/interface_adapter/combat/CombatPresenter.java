package interface_adapter.combat;

import use_case.combat.CombatOutputBoundary;
import use_case.combat.CombatOutputData;

public class CombatPresenter implements CombatOutputBoundary {
    private final CombatViewModel viewModel;

    public CombatPresenter(CombatViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(CombatOutputData outputData) {
        CombatState state = new CombatState();

        state.setCharacterName(outputData.getCharacterName());
        state.setMonsterName(outputData.getMonsterName());

        state.setCharacterHealth(outputData.getCharacterHealth());
        state.setMonsterHealth(outputData.getMonsterHealth());

        state.setMessage(outputData.getCombatMessage());

        state.setCombatFinished(outputData.isCombatFinished());
        state.setCharacterWon(outputData.isCharacterWon());

        viewModel.setState(state);
    }
}
