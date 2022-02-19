package main.metamodel;

import main.Condition;
import main.Operation;

public class Transition {

	private String event;
	private State target;
	private Operation operation;
	private int operationVal;
	private String operationVar;
	private Condition condition;
	private int conditionVal;
	private String conditionVar;

	public Transition(String event,
					  State target,
					  Operation operation,
					  int operationVal,
					  String operationVar,
					  Condition condition,
					  int conditionVal,
					  String conditionVar
					  ){
		this.event = event;
		this.target = target;
		this.operation = operation;
		this.operationVal = operationVal;
		this.operationVar = operationVar;
		this.condition = condition;
		this.conditionVal = conditionVal;
		this.conditionVar = conditionVar;
	}
	public Object getEvent() {
		return event;
	}

	public State getTarget() {
		return target;
	}

	public boolean hasSetOperation() {
		return operation == Operation.SET;
	}

	public Operation getOperation(){
		return operation;
	}

	public boolean hasIncrementOperation() {

		return operation == Operation.INC;
	}

	public boolean hasDecrementOperation() {
		return operation == Operation.DEC;
	}

	public Object getOperationVariableName() {
		return operationVar;
	}
	public Integer getOperationValue(){
		return operationVal;
	}

	public boolean isConditional() {
		return condition != null;
	}

	public Object getConditionVariableName() {
		return conditionVar;
	}

	public Integer getConditionComparedValue() {
		return conditionVal;
	}

	public boolean isConditionEqual() {
		return condition == Condition.EQUAL;
	}

	public boolean isConditionGreaterThan() {
		return condition == Condition.GREATER;
	}

	public boolean isConditionLessThan() {
		return condition == Condition.LESS;
	}

	public boolean hasOperation() {
		return operation != null;
	}

}
