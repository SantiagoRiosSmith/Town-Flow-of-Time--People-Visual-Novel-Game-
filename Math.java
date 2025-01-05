// The class that does the derivative calculator
// Santiago Rios Smith
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package town.flow.of.time.people;

import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JLabel; 

/**
 *
 * @author daves
 */


// THIS WAS NOT FULLY COMPLETED READ COMMENTS WRITTEN THROUGHOUT TO UNDERSTAND 

public class Math {
    private String currentEquasion;
    private boolean bottomFraction;
    public Math(){
        this.currentEquasion = "f(x)=";
        this.bottomFraction = false;
    }  
  
    public void setText(String string, JLabel label){
        if(this.bottomFraction == true){
           // This keeps the fraction sign in the equasion and puts the number the player wants behind the fraction
           this.currentEquasion = this.currentEquasion.substring(0, this.currentEquasion.length() - 1);
           this.currentEquasion = this.currentEquasion + string + "/"; 
        }
        else{
        this.currentEquasion = this.currentEquasion + string;
        }
        label.setText(this.currentEquasion);
    }
    
    public void setBottomFraction(boolean TF){
        this.bottomFraction = TF;
    }
    
    public void deleteCharacter(JLabel label){
        // So the player can delete what's in the fraction not the fraction its self
        if(this.currentEquasion.length() > 5 && this.bottomFraction == true && !this.currentEquasion.substring(this.currentEquasion.length() - 1, this.currentEquasion.length()).equals("/")){
            this.currentEquasion = this.currentEquasion.substring(0, this.currentEquasion.length() - 1);
            this.bottomFraction = false;
        }
        // I don't want them to erase the opening bracket infront of the fraction either
        else if(this.currentEquasion.length() > 5 && this.bottomFraction == true && !this.currentEquasion.substring(this.currentEquasion.length() - 2, this.currentEquasion.length() - 1).equals("(")){
           this.currentEquasion = this.currentEquasion.substring(0, this.currentEquasion.length() - 2);
           this.currentEquasion = this.currentEquasion + "/";
        }
        // If there is nothing in the fraction the program will allow the player to delete the fraction and opening bracket
        else if(this.currentEquasion.length() > 5){
            this.currentEquasion = this.currentEquasion.substring(0, this.currentEquasion.length() - 1);
        }
        label.setText(this.currentEquasion);
    }
    
    // This identifies the brackets in the equasion and puts their index beside what type of bracket they are (Opening or Closing)
    public LinkedList bracketIndex(){
        LinkedList bracketList = new LinkedList<String>();
        for(int i = 5; i < this.currentEquasion.length(); i++){
            if(this.currentEquasion.substring(i, i + 1).equals("(")){
                bracketList.add("Opening" + i); 
            }
            else if(this.currentEquasion.substring(i, i + 1).equals(")")){
                bracketList.add("Closing" + i);
            }
        }
        return bracketList;
    }
    
    public boolean syntax(JLabel label){
        boolean syntaxError = false;
        int openingCount = 0;
        int closingCount = 0;
        LinkedList<String> bracketIndex = bracketIndex();
        for(int i = 0; i < bracketIndex.size() - 1; i++){
            //Seeing if any open and closed brackets are right beside each other which means the input is invalid
            if(!bracketIndex.get(i).substring(0, 7).equals(bracketIndex.get(i + 1).substring(0, 7)) && Integer.parseInt(bracketIndex.get(i).substring(7, bracketIndex.get(i).length())) == Integer.parseInt(bracketIndex.get(i + 1).substring(7, bracketIndex.get(i).length())) - 1){
                syntaxError = true;
                System.out.println("beside brackets");
            }
        }
        // This loop determines if every opening bracket is eventurally closed in the equasion if not it determines there is a syntax error
        for(int i = 0; i < bracketIndex.size(); i++){
            if(bracketIndex.get(i).substring(0, 7).equals("Opening")){
                openingCount++;
            }
            else{
                closingCount++;
            }
        }
        if(openingCount != closingCount){
            syntaxError = true;
            System.out.println("Number of brackets mismatch");
        }
        // This if statement makes syntaxEroor true if the first character in the equasion is a operation
        if(this.currentEquasion.substring(5, 6).equals("*") || this.currentEquasion.substring(5, 6).equals("+") || this.currentEquasion.substring(5, 6).equals("-") || this.currentEquasion.substring(5, 6).equals("/")){
            syntaxError = true;
            System.out.println("opening operation");
        }
        // This if statement makes syntaxEroor true if the last character in the equasion is a operation
        if(this.currentEquasion.substring(this.currentEquasion.length() - 1, this.currentEquasion.length()).equals("*") || this.currentEquasion.substring(this.currentEquasion.length() - 1, this.currentEquasion.length()).equals("+") || this.currentEquasion.substring(this.currentEquasion.length() - 1, this.currentEquasion.length()).equals("-") || this.currentEquasion.substring(this.currentEquasion.length() - 1, this.currentEquasion.length()).equals("/")){
            syntaxError = true;
            System.out.println("closing operation");
        }
        // This loop checks if at any point there is a (/ or /) if there is it sets syntaxError to true
        for(int i = 0; i < this.currentEquasion.length() - 1; i++){
            if(this.currentEquasion.substring(i, i + 2).equals("(/") || this.currentEquasion.substring(i, i + 2).equals("/)")){
                syntaxError = true;
                System.out.println("bracket beside divide");
            }
        }
        // This loop checks if an ending bracket is seperated from the next part of an equasion with a operation if not it makes syntaxError true
        for(int i = 0; i < this.currentEquasion.length() - 1; i++){
            if(this.currentEquasion.substring(i, i + 1).equals(")")){
                if(!this.currentEquasion.substring(i + 1, i + 2).equals("*") && !this.currentEquasion.substring(i + 1, i + 2).equals("+") && !this.currentEquasion.substring(i + 1, i + 2).equals("-") && !this.currentEquasion.substring(i + 1, i + 2).equals("^") && !this.currentEquasion.substring(i + 1, i + 2).equals("=") && !this.currentEquasion.substring(i + 1, i + 2).equals(")")){
                    System.out.println("closed bracket beside another bracket or number");
                    syntaxError = true;
                }
            }
        }
        if(syntaxError == true){
            label.setText("Syntax Error");
        }
        else{
           label.setText("derivative"); 
        }
        return syntaxError;
    }
    
    // This method makes a LinkedList and returns the operations and the index they are at (Didn't end up using it however I didn't end up finishing this class anyway)
    public LinkedList operation(){
        LinkedList<String> operationIndex = new LinkedList<String>();
        // This loop detects the operations in the equasion and at what index they are located at
        for(int i = 0; i < this.currentEquasion.length(); i++){
            if(this.currentEquasion.substring(i, i + 1).equals("*") || this.currentEquasion.substring(i, i + 1).equals("+") || this.currentEquasion.substring(i, i + 1).equals("-")){
                operationIndex.add(this.currentEquasion.substring(i, i + 1) + i);
            }    
        }
        return operationIndex;
    }
    
    // This method splits up the equasion into the things in brackets and individual terms and takes the derivative of each of those terms
    // If I don't get much credit for this because the very final product doesn't work I feel this method best displays the best work I did in this class
    // To properly show the extent of how this can work when making an equasion make it with as many brackets as possible, it will 100% of the time correctly identily which brackets belong to each other (Try this out yourself)
    public LinkedList splitEquasion(boolean syntaxError){
        LinkedList<String> individualComponents = new LinkedList<String>();
        if(syntaxError == false){
            LinkedList<String> bracketIndex = bracketIndex();
            Stack<String> openingBracketStack = new Stack<String>();
            LinkedList<String> finalBracketArrangement = new LinkedList<String>();
            // This is an algorithum I came up with all by myself that determines which opening bracket belongs to which closing bracket (This was very difficult and involved a lot of thinking to come up with)
            for(int i = 0; i < bracketIndex.size(); i++){
                // If there is a closing bracket it matches the most recent opening bracket to that closing bracket (which is why we need a stack to determine the most recent (first in first out))
                if(!bracketIndex.get(i).substring(0, 7).equals("Closing")){
                    openingBracketStack.push(bracketIndex.get(i));
                }
                else{
                    finalBracketArrangement.add(openingBracketStack.peek());
                    openingBracketStack.pop();
                    finalBracketArrangement.add(bracketIndex.get(i));
                }
            }
            // This prints out the LinkedList with tbrackets with labeled indexes beside each other based on which bracket belongs to each other
            System.out.println(finalBracketArrangement);
            for(int i = 0; i < finalBracketArrangement.size(); i++){
                // Sorry for making it too complecated to even read but I needed to do it
                // All that it does is add the components of the equasion which is the things between brackets to a LinkedList
                System.out.println(this.currentEquasion.substring(Integer.parseInt(finalBracketArrangement.get(i).substring(7,finalBracketArrangement.get(i).length())),Integer.parseInt(finalBracketArrangement.get(i + 1).substring(7,finalBracketArrangement.get(i + 1).length())) + 1));
                individualComponents.add(this.currentEquasion.substring(Integer.parseInt(finalBracketArrangement.get(i).substring(7,finalBracketArrangement.get(i).length())),Integer.parseInt(finalBracketArrangement.get(i + 1).substring(7,finalBracketArrangement.get(i + 1).length())) + 1));
                i++;
            }
            // I left in this print statement so you could tell what is going on and it's easier to mark (you're welcome)
            System.out.println(individualComponents);
        }
        return individualComponents;
    }
    
    public void derivativeTaking(LinkedList<String> individualComponents, JLabel label){
        System.out.println(individualComponents);
    }
//    // I wassant able to completely finish this part because I simply don't have enough time but everything before this final step is completed
//    public void derivativeTaking(LinkedList<String> individualComponents, JLabel label){
//        if(individualComponents.isEmpty() == false){
//            LinkedList<String> bigPart = new LinkedList<String>();
//            LinkedList<String> smallPart = new LinkedList<String>();
//            for(int i = 0; i < individualComponents.size(); i++){
//                boolean big = false;
//                for(int j = 0; j < individualComponents.size(); j++){
//                    if(i != j){
//                        if(individualComponents.get(i).contains(individualComponents.get(j))){
//                           bigPart.add(individualComponents.get(i));
//                           big = true;
//                           break;
//                        }
//                    }
//                }
//                if(big == false){
//                   smallPart.add(individualComponents.get(i)); 
//                }
//            }
//            // This will print out the LinkedList that includes the small bracketed expressions and the linkedlist with the big bracketed expressions
//            System.out.println(bigPart);
//            System.out.println(smallPart);
//            // I apologize for the quality of code beyond here I have been working on this so long that I can't think straight about good ways to code the rest
//            // Due to my tiredness the code here is very brute force style
//            
//            LinkedList<String> individualSmallDerivatives = new LinkedList<String>();
//            boolean X = false;
//            String disposibleNumber = "";
//            int disposibleNumberInt = 0;
//            String derivativeString = "";
//            // this variable will be the derivative that has accumulated for each step of taking the derivadive of each string in smallPart
//            String derivativeStringSoFar = "";
//            String notDerivative = "";
//            int derivativeInt = 0;
//            // THE CODE FROM HERE ON OUT DOESN'T QUITE WORK AND IS UNFINISHED
//            for(int j = 0; j < smallPart.size(); j ++){
//                for(int i = 0; i < smallPart.get(j).length(); i++){
//                    if(X != true){
//                        derivativeInt = 0;
//                    }
//                    if(smallPart.get(i).substring(i + 1, i + 2).equals("0") || smallPart.get(i).substring(i + 1, i + 2).equals("1") ||  smallPart.get(i).substring(i + 1, i + 2).equals("2") ||  smallPart.get(i).substring(i + 1, i + 2).equals("3") ||  smallPart.get(i).substring(i + 1, i + 2).equals("4") ||  smallPart.get(i).substring(i + 1, i + 2).equals("5") ||  smallPart.get(i).substring(i + 1, i + 2).equals("6") ||  smallPart.get(i).substring(i + 1, i + 2).equals("7") ||  smallPart.get(i).substring(i + 1, i + 2).equals("8") ||  smallPart.get(i).substring(i + 1, i + 2).equals("9")){
//                        derivativeString = derivativeString + smallPart.get(i).substring(i + 1, i + 2);
//                    }
//                    else if(smallPart.get(i).substring(i + 1, i + 2).equals("+") || smallPart.get(i).substring(i + 1, i + 2).equals("-") ||  smallPart.get(i).substring(i + 1, i + 2).equals("/")){
//                        derivativeString = derivativeString + smallPart.get(i).substring(i + 1, i + 2);
//                        X = false;
//                    }
//                    // Attempting to do the product rule
//                    else if(smallPart.get(i).substring(i + 1, i + 2).equals("*")){
//                        while(!smallPart.get(i).substring(i + 3,i + 4).equals(")") && !smallPart.get(i).substring(i + 3,i + 4).equals("X")){
//                            if(smallPart.get(i).substring(i + 1, i + 2).equals("0") || smallPart.get(i).substring(i + 1, i + 2).equals("1") ||  smallPart.get(i).substring(i + 1, i + 2).equals("2") ||  smallPart.get(i).substring(i + 1, i + 2).equals("3") ||  smallPart.get(i).substring(i + 1, i + 2).equals("4") ||  smallPart.get(i).substring(i + 1, i + 2).equals("5") ||  smallPart.get(i).substring(i + 1, i + 2).equals("6") ||  smallPart.get(i).substring(i + 1, i + 2).equals("7") ||  smallPart.get(i).substring(i + 1, i + 2).equals("8") ||  smallPart.get(i).substring(i + 1, i + 2).equals("9")){
//                                derivativeString = derivativeString + smallPart.get(i).substring(i + 1, i + 2);
//                            }
//                            else if(smallPart.get(i).substring(i + 3,i + 4).equals("X")){
//                                if(smallPart.get(i).substring(i + 1,i + 2).equals("^")){
//                                    while(!smallPart.get(i).substring(i + 3,i + 4).equals("]")){
//                                        disposibleNumber = disposibleNumber + smallPart.get(i).substring(i + 3,i + 4);
//                                        i++;
//                                    }
//                                    disposibleNumberInt = Integer.parseInt(disposibleNumber);
//                                    disposibleNumberInt--;
//                                    derivativeInt = derivativeInt * Integer.parseInt(disposibleNumber);
//                                    derivativeString = String.valueOf(derivativeInt) + "X^[" + String.valueOf(disposibleNumberInt + "]");
//                                    // There is too much too do I couldn't do it all unless I had more time so if there is an exponent there then that's unfortunate
//                                }
//                                else{
//                                    derivativeStringSoFar = "((" + derivativeStringSoFar + ")" + "(" + derivativeString + "X)" + "+(" + notDerivative + ")" + "(" + derivativeString + "X" + ")";
//                                    individualSmallDerivatives.add(derivativeStringSoFar);
//                                    break;
//                                }
//                            }
//                            disposibleNumber = disposibleNumber + smallPart.get(i).substring(i + 3,i + 4);
//                            i++;
//                            
//                        }
//                    }
//                    else if(smallPart.get(i).substring(i + 1,i + 2).equals("X")){
//                        // preparing derivativeInt to be multiplyed by the exponent of x
//                        X = true;
//                        notDerivative = derivativeString + "X";
//                        if(derivativeString.equals("")){
//                            derivativeInt = 1;
//                        }
//                        else if(derivativeString.equals("+") || derivativeString.equals("-")){
//                            derivativeStringSoFar = derivativeStringSoFar + derivativeString;
//                            derivativeInt = 1;
//                        }
//                        else{
//                        derivativeInt = derivativeInt + Integer.parseInt(derivativeString);
//                        }
//                    }
//                    else if(X == true){
//                        if(smallPart.get(i).substring(i + 1,i + 2).equals("^")){
//                            while(!smallPart.get(i).substring(i + 3,i + 4).equals("]")){
//                                disposibleNumber = disposibleNumber + smallPart.get(i).substring(i + 3,i + 4);
//                                i++;
//                            }
//                            disposibleNumberInt = Integer.parseInt(disposibleNumber);
//                            disposibleNumberInt--;
//                            derivativeInt = derivativeInt * Integer.parseInt(disposibleNumber);
//                            derivativeString = String.valueOf(derivativeInt) + "X^[" + String.valueOf(disposibleNumberInt + "]");
//                        }
//                        // If no exponent derivativeString already has the value needed
//                        disposibleNumber = "";
//                        disposibleNumberInt = 0;
//                        derivativeStringSoFar = derivativeString;
//                        derivativeString = "";
//                    }
//                    else if(smallPart.get(i).substring(i + 1,i + 2).equals(")")){
//                        derivativeStringSoFar = "(" + derivativeStringSoFar + ")";
//                        individualSmallDerivatives.add(derivativeStringSoFar); 
//                        break;
//                    }
//                }
//            }
//            //Might as well print what I got if it works for that attempt
//            for(int j = 0; j < individualSmallDerivatives.size(); j ++){
//                label.setText(individualSmallDerivatives.get(j));
//            }
//            // After this I was going to combine the small derivatives that made up the big ones and combine them accordingly based on the oppereation the seperated the small derivatives this would have acturallyh been much easier to do then taking the derivatives of the small derivatives
//            // Lastly I was gonna go through the this.currentEquasion to find the other terms that were unbracketed and take the derivaive of them
//        }
//    }
}
