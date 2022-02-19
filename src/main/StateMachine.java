package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine {

    private List<State> allStates = new ArrayList<State>() {
    };
    private State newState;
    private String initialStateName;
    private Machine machine;

    private Map<String, Integer> integerCollection = new HashMap<>();

    //Transition
    private List<Transition> transitions = new ArrayList<Transition>() {
    };

    private boolean stateStarted = false;
    private String stateName;
    private boolean transitionStarted = false;
    private TransitionBuilder tbuilder = new TransitionBuilder();

    public Machine build() {
		if(stateStarted){
            if(transitionStarted){
                transitions.add(tbuilder.build());
            }
            newState = new State(stateName, transitions);
            allStates.add(newState);
            transitions = new ArrayList<>();
		}
        if(initialStateName == null && allStates.size() > 0){
            initialStateName = (String) allStates.get(0).getName();
        }
        Machine m = new Machine(allStates, initialStateName, integerCollection);
        allStates = new ArrayList<State>() {
        };
        integerCollection = new HashMap<>();
        stateStarted = false;
        transitionStarted = false;
        return m;
    }

    public StateMachine state(String string) {
        if (stateStarted) {
            if (transitionStarted) {
                transitions.add(tbuilder.build());
            }
            newState = new State(stateName, transitions);
            allStates.add(newState);
            transitions = new ArrayList<>();
        }
        stateStarted = true;
        stateName = string;
        transitionStarted = false;
        tbuilder.reset();
        return this;
    }

    public StateMachine initial() {
        if (stateName == null) return this;
        initialStateName = stateName;
        return this;
    }

    public StateMachine when(String string) {
        if (transitionStarted) {
            transitions.add(tbuilder.build());
        }
        transitionStarted = true;
        tbuilder.reset();
        tbuilder.setEvent(string);
        return this;
    }

    public StateMachine to(String string) {
        tbuilder.setTarget(new State(string));
        return this;
    }

    public StateMachine integer(String string) {
        integerCollection.put(string, 0);
        return this;
    }

    public StateMachine set(String string, int i) {
        tbuilder.setOperation(Operation.SET.name(), string, i);
        return this;
    }

    public StateMachine increment(String string) {
        tbuilder.setOperation(Operation.INC.name(), string);
        return this;
    }

    public StateMachine decrement(String string) {
        tbuilder.setOperation(Operation.DEC.name(), string);
        return this;
    }

    public StateMachine ifEquals(String string, int i) {
        tbuilder.setCondition(Condition.EQUAL.name(), string, i);
        return this;
    }

    public StateMachine ifGreaterThan(String string, int i) {
        tbuilder.setCondition(Condition.GREATER.name(), string, i);
        return this;
    }

    public StateMachine ifLessThan(String string, int i) {
        tbuilder.setCondition(Condition.LESS.name(), string, i);
        return this;
    }

}
