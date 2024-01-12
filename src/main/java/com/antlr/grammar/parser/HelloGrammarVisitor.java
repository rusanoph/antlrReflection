// Generated from c:/Users/007/Desktop/ANTLR/newhelloworld/src/main/java/com/antlr/grammar/HelloGrammar.g4 by ANTLR 4.13.1
package com.antlr.grammar.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloGrammarParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(HelloGrammarParser.RContext ctx);
}