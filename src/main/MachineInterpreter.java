package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.List;

public class MachineInterpreter {
    private Machine m;
    private State currentState;

    public MachineInterpreter() {

    }

    public void run(Machine m) {
        this.m = m;
        currentState = m.getInitialState();
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void processEvent(String string) {
        if (!DoesEventExist(string)) return;
        List<Transition> possibleTransitions = currentState.getTransitions();
        Transition usefulTransition = null;
        for (Transition t: possibleTransitions) {
            if (t.getEvent() == string && CheckCondition(t)) {
                usefulTransition = t;
                break;
            }
        }
        if(usefulTransition == null) return;
        currentState = m.getState((String) usefulTransition.getTarget().getName());
        HandleOperation(usefulTransition);

    }

    private boolean DoesEventExist(String event) {
        return currentState.getTransitions().stream().anyMatch(t -> event.equals(t.getEvent()));
    }

    private boolean CheckCondition(Transition t) {
        if (t.isConditionEqual()) {
            String var = (String) t.getConditionVariableName();
            if (m.hasInteger(var)) {
                return m.getIntegerValue(var) == t.getConditionComparedValue();
            }
        } else if (t.isConditionGreaterThan()) {
            String var = (String) t.getConditionVariableName();
            if (m.hasInteger(var)) {
                return m.getIntegerValue(var) > t.getConditionComparedValue();
            }
        } else if (t.isConditionLessThan()) {
            String var = (String) t.getConditionVariableName();
            if (m.hasInteger(var)) {
                return m.getIntegerValue(var) < t.getConditionComparedValue();
            }
        }
        return true;
    }

    private void HandleOperation(Transition t) {
        if (t.hasOperation()) {
            switch (t.getOperation()) {
                case SET -> {
                    if (m.hasInteger((String) t.getOperationVariableName())) {
                        m.setIntegerValue((String) t.getOperationVariableName(), t.getOperationValue());
                    }
                }
                case DEC -> {
                    if (m.hasInteger((String) t.getOperationVariableName())) {
                        m.setIntegerValue((String) t.getOperationVariableName(), m.getIntegerValue((String) t.getOperationVariableName())-1);
                    }
                }
                case INC -> {
                    if (m.hasInteger((String) t.getOperationVariableName())) {
                        m.setIntegerValue((String) t.getOperationVariableName(), m.getIntegerValue((String) t.getOperationVariableName())+1);
                    }
                }
            }
        }
    }


    public int getInteger(String string) {
        return m.getIntegerValue(string);
    }

}
