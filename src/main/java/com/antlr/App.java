package com.antlr;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.spec.ECFieldF2m;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import com.antlr.demonstrative.A;
import com.antlr.demonstrative.B;
import com.antlr.grammar.parser.HelloGrammarBaseListener;
import com.antlr.grammar.parser.HelloGrammarLexer;
import com.antlr.grammar.parser.HelloGrammarParser;
import com.antlr.reflection.Reflection;
import com.github.therapi.runtimejavadoc.ClassJavadoc;
import com.github.therapi.runtimejavadoc.Comment;
import com.github.therapi.runtimejavadoc.CommentFormatter;
import com.github.therapi.runtimejavadoc.MethodJavadoc;
import com.github.therapi.runtimejavadoc.OtherJavadoc;
import com.github.therapi.runtimejavadoc.ParamJavadoc;
import com.github.therapi.runtimejavadoc.RuntimeJavadoc;
import com.github.therapi.runtimejavadoc.SeeAlsoJavadoc;
import com.github.therapi.runtimejavadoc.ThrowsJavadoc;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final CommentFormatter formatter = new CommentFormatter();

    public static void main( String[] args ) throws IOException {
        B b = new B();
        
        String text = "hello world";
        //#region Grammar
        CharStream input = CharStreams.fromString(text);
        HelloGrammarLexer lexer = new HelloGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloGrammarParser parser = new HelloGrammarParser(tokens);
        ParseTree tree = parser.r();

        ParseTreeWalker walker = new ParseTreeWalker();
        HelloGrammarBaseListener listener = new HelloGrammarBaseListener(); 

        walker.walk(listener, tree);
        //#endregion

        Class obj = HelloGrammarBaseListener.class;

        // printClass(obj);

        Example.printJavadoc(obj.getName());
    }

    public static void printJavaDoc(Class obj) {
        System.out.println("\n");

        ClassJavadoc classDoc = RuntimeJavadoc.getJavadoc(obj);
        if (classDoc.isEmpty()) {
            System.out.println("No documentation for " + obj);
            // return;
        }

        System.out.println(classDoc.getName());
        System.out.println(format(classDoc.getComment()));
        System.out.println();

        

        // @see tags
        for (SeeAlsoJavadoc see : classDoc.getSeeAlso()) {
            System.out.println("See also: " + see.getLink());
        }

        // miscellaneous and custom javadoc tags (@author, etc.)
        for (OtherJavadoc other : classDoc.getOther()) {
            System.out.println(other.getName() + ": " + format(other.getComment()));
        }

        System.out.println();
        System.out.println("Constructors: ");
        for (MethodJavadoc mehtodDoc : classDoc.getConstructors()) {
            printMethodJavadoc(mehtodDoc);
        }

        System.out.println();
        System.out.println("Methods: ");
        System.out.println(classDoc.getMethods().size());
        for (MethodJavadoc mehtodDoc : classDoc.getMethods()) {
            printMethodJavadoc(mehtodDoc);
        }
    }

    public static void printMethodJavadoc(MethodJavadoc methodDoc) {
        System.out.println(methodDoc.getName() + methodDoc.getParamTypes());
        System.out.println(methodDoc.getComment());

        if (!methodDoc.isConstructor()) {
            System.out.println("  returns  " + format(methodDoc.getReturns()));
        }

        for (SeeAlsoJavadoc see : methodDoc.getSeeAlso()) {
            System.out.println("   See also: " + see.getLink());
        }

        for (OtherJavadoc other : methodDoc.getOther()) {
            System.out.println("   " + other.getName() + ": " + format(other.getComment()));
        }

        for (ParamJavadoc paramDoc : methodDoc.getParams()) {
            System.out.println("   param " + paramDoc.getName() + ": " + format(paramDoc.getComment()));
        }

        for (ThrowsJavadoc throwsDoc : methodDoc.getThrows()) {
            System.out.println("   throws " + throwsDoc.getName() + ": " + format(throwsDoc.getComment()));
        }

        System.out.println();
    }

    private static String format(Comment c) {
        return formatter.format(c);
    }

    public static void printClass(Object obj) {
        Reflection r = new Reflection<Object>(obj);

        System.out.println(r.getObjectClass() + "\n");

        System.out.println(String.format("%s attributes: ", obj.getClass().getName()));
        r.printClassAttributes();

        System.out.println(String.format("\n%s methods: ", obj.getClass().getName()));
        r.printClassMethods();
    }

    public static void parse(String inputString) {
        CharStream input = CharStreams.fromString(inputString);
        HelloGrammarLexer lexer = new HelloGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloGrammarParser parser = new HelloGrammarParser(tokens);
        ParseTree tree = parser.r();

        ParseTreeWalker walker = new ParseTreeWalker();
        HelloGrammarBaseListener listener = new HelloGrammarBaseListener() {
            @Override
            public void exitR(HelloGrammarParser.RContext ctx) {
                System.out.println("Greet: " + ctx.ID().getText());
            }
        }; 

        System.out.println(String.format("=== %s ===", parser.getGrammarFileName()));
        walker.walk(listener, tree);
    }
}
