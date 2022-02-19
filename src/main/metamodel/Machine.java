package main.metamodel;

import java.util.List;
import java.util.Map;

public class Machine {
	private List<State> states;
	private State initialState;
	private Map<String, Integer> integerCollection;

	public Machine(List<State> states, String initialState, Map<String, Integer> intColl){
		this.states = states;
		this.initialState = initialState != null ? states.stream().filter(s -> s.getName() == initialState).findAny().get(): null;
		this.integerCollection = intColl;
	}
	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String stateName) {
		return states.stream().filter(s -> stateName.equals(s.getName())).findAny().orElse(null);
	}

	public int numberOfIntegers() {
		return integerCollection.size();
	}

	public boolean hasInteger(String string) {
		return integerCollection.containsKey(string);
	}

	// added method
	public int getIntegerValue(String string) {
		return integerCollection.get(string);
	}

	public void setIntegerValue(String key, int value){
		integerCollection.put(key, value);
	}

}
