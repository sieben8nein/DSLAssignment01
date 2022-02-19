package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Transition> transitions;
	private String name;
	public State(String name, List<Transition> transitions){
		this.name = name;
		this.transitions = transitions;
	}

	public State(String name){
		this(name, new ArrayList<Transition>());
	}
	public Object getName(){
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		return transitions.stream()
				.filter(t -> string.equals(t.getEvent()))
				.findFirst()
				.orElse(null);
	}

}
