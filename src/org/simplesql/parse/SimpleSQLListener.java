// Generated from SimpleSQL.g4 by ANTLR 4.7.1

    package org.simplesql.parse;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleSQLParser}.
 */
public interface SimpleSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(SimpleSQLParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(SimpleSQLParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#columns}.
	 * @param ctx the parse tree
	 */
	void enterColumns(SimpleSQLParser.ColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#columns}.
	 * @param ctx the parse tree
	 */
	void exitColumns(SimpleSQLParser.ColumnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(SimpleSQLParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(SimpleSQLParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(SimpleSQLParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(SimpleSQLParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SimpleSQLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SimpleSQLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(SimpleSQLParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(SimpleSQLParser.Literal_valueContext ctx);
}