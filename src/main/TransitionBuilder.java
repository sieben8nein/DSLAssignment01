package main;

import main.metamodel.State;
import main.metamodel.Transition;

public class TransitionBuilder {
    private String event;
    private State target;
    private Operation operation;
    private String operationVar;
    private int operationValue;
    private Condition condition;
    private String conditionVariable;
    private int conditionValue;

    public TransitionBuilder(){

    }

    public void reset(){
        this.event = null;
        this.target = null;
        this.operation = null;
        this.operationValue = 0;
        this.operationVar = null;
        this.condition = null;
        this.conditionVariable = null;
        this.conditionValue = 0;
    }

    public TransitionBuilder setEvent(String event){
        this.event = event;
        return this;
    }

    public TransitionBuilder setTarget(State target){
        this.target = target;
        return this;
    }

    public TransitionBuilder setOperation(String operation, String varName){
        this.operation = Operation.valueOf(operation);
        this.operationVar = varName;
        return this;
    }

    public TransitionBuilder setOperation(String operation, String varName, int newValue){
        this.operation = Operation.valueOf(operation);
        this.operationVar = varName;
        this.operationValue = newValue;
        return this;
    }

    public TransitionBuilder setCondition(String condition, String conditionVariable, int conditionValue){
        this.condition = Condition.valueOf(condition);
        this.conditionVariable = conditionVariable;
        this.conditionValue = conditionValue;
        return this;
    }

    public Transition build(){
        return new Transition(event, target, operation, operationValue, operationVar, condition, conditionValue, conditionVariable);
    }
}

