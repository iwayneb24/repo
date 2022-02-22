package notation;

import exceptions.InvalidNotationFormatException;
/**
 * A utility class that converts an infix expression to a postfix expression,
 * a postfix expression to an infix expression, and evaluates a postfix expression.
 * @author Wayne Bonifacio
 *
 */
public class Notation {
	private static MyQueue<String> queueIn;
	private static MyStack<String> stackIn;

	/*
	 * Private Helper methods
	 */
	private static boolean checkInteger(String str) {
		try {
			Integer.parseInt(str);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private static boolean checkOperator(String opr) {
		switch(opr) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	/**
	 * Checks the precedence in operators
	 * @param a
	 * @param b
	 * @return if the operator a >= b is in precedence
	 */
	private static boolean checkPrecedence(String a, String b) {
		int temp1 = a == "*" || a == "/" ? 1:0;
		int temp2 = a == "*" || a == "/" ? 1:0;
		return temp1 >= temp2;
	}

	private static int applyOprtrs(String operators, String oprA, String oprB) {
		int a = Integer.parseInt(oprA);
		int b = Integer.parseInt(oprB);
		switch(operators) {
		case "+":
			return (a + b);
		case "-":
			return (a - b);
		case "*":
			return (a * b);
		case "/":
			return (a / b);
		default:
		}
		return 0;

	
	}


	/**
	 * Converts an infix expression into a postfix expression
	 * @param infix - the infix expression in string format
	 * @return - the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		queueIn = new MyQueue<>(infix.length());
		stackIn = new MyStack<>(infix.length());

		String[] infixExpression = infix.split("");
		try {
			for(String character : infixExpression) {
				if(character.equals(" ")) { // determines if there's a space
					continue;
				}

				if(checkInteger(character)) { // determines if it is an integer
					queueIn.enqueue(character);
				}

				if(character.equals("(")) { // determines if it is a left parenthesis
					stackIn.push(character);	
				}

				if (checkOperator(character)) { // determines if it is an operator
					if(!stackIn.isEmpty() && checkOperator(stackIn.top()) 
							&& checkPrecedence(stackIn.top(), character)) {
						queueIn.enqueue(stackIn.pop());
					}
					stackIn.push(character);
				}

				if(character.equals(")")) { // determines if it is a right parenthesis
					while(!stackIn.isEmpty() && checkOperator(stackIn.top())) {   
						queueIn.enqueue(stackIn.pop());
					}
					if(!stackIn.isEmpty() && stackIn.top().equals("(")) {
						stackIn.pop();
					} else {
						throw new InvalidNotationFormatException();
					}
				}
			}
			// pop remaining operators into queue
			while(!stackIn.isEmpty() && checkOperator(stackIn.top())) {
				queueIn.enqueue(stackIn.pop());
			}
		} catch(Exception e) {
			throw new InvalidNotationFormatException();
		}

		return queueIn.toString();
	}

	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix - the postfix expression in string format
	 * @return - the infix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException  {
		queueIn = new MyQueue<>(postfix.length());
		stackIn = new MyStack<>(postfix.length());

		MyStack<String>nStack =new MyStack<>(2);

		String[] postfixExpression = postfix.split("");
		try {
			for(String character : postfixExpression) {
				if(character.equals(" ")) {   
					continue;
				}

				if(checkInteger(character)) {
					stackIn.push(character);
				}

				if (checkOperator(character)) {
					nStack.push(stackIn.pop());
					nStack.push(stackIn.pop());
					stackIn.push("(" + nStack.pop() + character + nStack.pop() + ")");

				}
			}
			// check if there's one or more values
			if( stackIn.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		} catch(Exception e) {
			throw new InvalidNotationFormatException();
		}
		return stackIn.toString();
	}

	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr - the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		queueIn = new MyQueue<>(postfixExpr.length());
		stackIn = new MyStack<>(postfixExpr.length());

		MyStack<String>nStack =new MyStack<>(2);

		String[] postfixExpression = postfixExpr.split("");
		try {
			for(String character : postfixExpression) {
				if(character.equals(" ")) {    
					continue;
				}

				if(checkInteger(character)) {   
					stackIn.push(character);
				}

				if(checkOperator(character)) {  
					nStack.push(stackIn.pop());
					nStack.push(stackIn.pop());
					stackIn.push(String.valueOf(applyOprtrs(character,nStack.pop() ,nStack.pop())));
				}
			}
			// check if there's one or more values
			if(stackIn.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		} catch(Exception e) {
			throw new InvalidNotationFormatException();
		}
		return Integer.parseInt(stackIn.toString());
	}


	public static double evaluateInfixExpression(String complexInfix) throws InvalidNotationFormatException {
		return evaluatePostfixExpression(convertInfixToPostfix(complexInfix));
	}



}
