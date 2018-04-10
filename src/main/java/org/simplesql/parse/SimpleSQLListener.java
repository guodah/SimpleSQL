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
	 * Enter a parse tree produced by {@link SimpleSQLParser#data_source}.
	 * @param ctx the parse tree
	 */
	void enterData_source(SimpleSQLParser.Data_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#data_source}.
	 * @param ctx the parse tree
	 */
	void exitData_source(SimpleSQLParser.Data_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#join_condition}.
	 * @param ctx the parse tree
	 */
	void enterJoin_condition(SimpleSQLParser.Join_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#join_condition}.
	 * @param ctx the parse tree
	 */
	void exitJoin_condition(SimpleSQLParser.Join_conditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#table_column}.
	 * @param ctx the parse tree
	 */
	void enterTable_column(SimpleSQLParser.Table_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#table_column}.
	 * @param ctx the parse tree
	 */
	void exitTable_column(SimpleSQLParser.Table_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void enterJoin_operator(SimpleSQLParser.Join_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#join_operator}.
	 * @param ctx the parse tree
	 */
	void exitJoin_operator(SimpleSQLParser.Join_operatorContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleSQLParser#join_type}.
	 * @param ctx the parse tree
	 */
	void enterJoin_type(SimpleSQLParser.Join_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#join_type}.
	 * @param ctx the parse tree
	 */
	void exitJoin_type(SimpleSQLParser.Join_typeContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(SimpleSQLParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(SimpleSQLParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#group_by}.
	 * @param ctx the parse tree
	 */
	void enterGroup_by(SimpleSQLParser.Group_byContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#group_by}.
	 * @param ctx the parse tree
	 */
	void exitGroup_by(SimpleSQLParser.Group_byContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link SimpleSQLParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(SimpleSQLParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleSQLParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(SimpleSQLParser.Function_nameContext ctx);
}