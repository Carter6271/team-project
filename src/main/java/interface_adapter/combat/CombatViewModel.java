package interface_adapter.combat;

import java.util.ArrayList;
import java.util.List;

public class CombatViewModel {
    public static final String TITLE = "Combat";

    private CombatState state = new CombatState();

    private final List<CombatViewModelListener> listeners = new ArrayList<>();

    public CombatState getState() {return this.state;}

    public void setState(CombatState state) {
        this.state = state;
        notifyStateChanged();
    }

    public void addListener(CombatViewModelListener listener) { listeners.add(listener);}

    private void notifyStateChanged() {
        for (CombatViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }

    public String getViewName() {
        return TITLE;
    }
}
