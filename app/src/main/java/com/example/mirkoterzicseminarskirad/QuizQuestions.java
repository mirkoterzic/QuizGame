package com.example.mirkoterzicseminarskirad;

import android.app.Application;

public class QuizQuestions extends Application {
    QuizQuestion[] Questions = {
            new QuizQuestion("What is the correct syntax for declaring a variable in Java?", new String[]{"variable = value;", "int variable = value;", "value = variable;", "var = value;"}, "int variable = value;"),
            new QuizQuestion("Which keyword is used to define a class in Java?", new String[]{"class", "define", "java", "new"}, "class"),
            new QuizQuestion("What is the result of 10 % 3 in Java?", new String[]{"3", "1", "0", "10/3"}, "1"),
            new QuizQuestion("What is the default value of a boolean variable in Java?", new String[]{"true", "false", "0", "null"}, "false"),
            new QuizQuestion("Which loop is used to iterate a specific number of times in Java?", new String[]{"for loop", "while loop", "do-while loop", "if-else statement"}, "for loop"),
            new QuizQuestion("What is the purpose of the 'break' statement in Java?", new String[]{"To exit a loop or switch statement", "To continue to the next iteration of a loop", "To define a method", "To declare a variable"}, "To exit a loop or switch statement"),
            new QuizQuestion("Which method is called when an object is created in Java?", new String[]{"start()", "run()", "main()", "constructor()"}, "constructor()"),
            new QuizQuestion("What is the correct way to compare two strings in Java?", new String[]{"string1 = string2;", "string1.equals(string2);", "string1 == string2;", "string1.compare(string2);"}, "string1.equals(string2);"),
            new QuizQuestion("Which data structure uses both FIFO and LIFO principles?", new String[]{"Queue", "Stack", "Linked List", "Heap"}, "Linked List"),
            new QuizQuestion("Which data type is used to store single characters in Java?", new String[]{"char", "string", "int", "float"}, "char"),
            new QuizQuestion("What is the result of adding an element to the top of a stack?", new String[]{"Push", "Pop", "Enqueue", "Dequeue"}, "Push"),
            new QuizQuestion("What is the output of the following code?\nint x = 5;\nSystem.out.println(x++);", new String[]{"5", "6", "4", "0"}, "5")
    };
    public   QuizQuestion[] getQuestions(){
        return Questions;
    }
    public int getQuestionsLength(){
        return Questions.length;
    }
}
