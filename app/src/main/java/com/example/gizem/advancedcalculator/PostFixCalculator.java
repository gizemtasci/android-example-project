package com.example.gizem.advancedcalculator;

import java.util.*;
import java.util.Stack;

/**
 * Created by gizem on 20.03.2018.
 */

public class PostFixCalculator
{
        int operand1, operand2, result = 0;
        private static final char add = '+', sub = '-',mul = '*', div = '/';
        Stack op_Stack = new Stack();
        String in;

        private boolean isSpace(char c)
        {
            return (c == ' ');
        }

        public String ConvertToPostfix(String infix)
        {
            char c;
            StringTokenizer parser = new StringTokenizer(infix, "+-*/^() ", true); //arithm. expr.
            StringBuffer postfix = new StringBuffer(infix.length());

            while (parser.hasMoreTokens())
            {
                String token = parser.nextToken();
                c = token.charAt(0);
                if ((token.length() == 1) && isOperator(c))
                {
                    while(!op_Stack.empty() &&!lowPrec(((String) op_Stack.peek()).charAt(0), c))
                        postfix.append(" ").append((String) op_Stack.pop());

                    if (c == ')')
                    {
                        String operator = (String) op_Stack.pop();
                        while (operator.charAt(0) != '(')
                        {
                            postfix.append(" ").append(operator);
                            operator = (String) op_Stack.pop();
                        }
                    }
                    else
                        op_Stack.push(token);
                }
                else
                {
                    postfix.append(" ").append(token);
                }
            }
            while (!op_Stack.empty())
                postfix.append(" ").append((String) op_Stack.pop());

            return postfix.toString();
        }

        public int Evaluate(String edt_inp)
        {

            Stack stack = new Stack();
            StringTokenizer tokenizer = new StringTokenizer(edt_inp);

            while (tokenizer.hasMoreTokens())
            {
                in = tokenizer.nextToken();
                char c = in.charAt(0);

                if (isOperator(c))
                {
                    operand2 = ((Integer) stack.pop()).intValue();
                    operand1 = ((Integer) stack.pop()).intValue();
                    result = EvalSingleOp(in.charAt(0), operand1, operand2);
                    stack.push(new Integer(result));
                }
                else
                    stack.push(new Integer(Integer.parseInt(in)));
            }

            result = ((Integer) stack.pop()).intValue();
            return result;
        }

         private boolean lowPrec(char op1, char op2)
         {
             if(op1=='+' || op1=='-')
                 return !(op2 == '+' || op2 == '-');

             else if(op1=='*' || op1=='/')
                 return op2 == '^' || op2 == '(';

             else if(op1=='^')
                 return op2 == '(';

             else if(op1=='(')
                 return true;

             else
                 return false;

         }
        private boolean isOperator(char c)
        {
            return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'|| c == '(' || c == ')';
        }

        private int EvalSingleOp(char operation, int op1, int op2)
        {
            int result = 0;

            if(operation==add)
                result = op1 + op2;
            else if(operation==sub)
                result = op1 - op2;
            else if(operation==mul)
                result = op1 * op2;
            else if(operation==div)
                result = op1 / op2;

            return result;
        }

}
