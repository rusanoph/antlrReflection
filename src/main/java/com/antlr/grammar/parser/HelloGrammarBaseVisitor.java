// Generated from c:/Users/007/Desktop/ANTLR/newhelloworld/src/main/java/com/antlr/grammar/HelloGrammar.g4 by ANTLR 4.13.1
package com.antlr.grammar.parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link HelloGrammarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class HelloGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements HelloGrammarVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitR(HelloGrammarParser.RContext ctx) { return visitChildren(ctx); }
}