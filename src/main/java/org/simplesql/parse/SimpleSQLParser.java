// Generated from SimpleSQL.g4 by ANTLR 4.7.1

    package org.simplesql.parse;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMERIC_LITERAL=6, STRING_LITERAL=7, 
		EQ=8, NEQ=9, LTEQ=10, LT=11, GTEQ=12, GT=13, ADD=14, SUB=15, MUL=16, DIV=17, 
		ON=18, NATURAL=19, INNER=20, JOIN=21, SUM=22, AVERAGE=23, COUNT=24, IS=25, 
		SELECT=26, FROM=27, WHERE=28, AND=29, OR=30, NULL=31, GROUP=32, BY=33, 
		ANY_NAME=34, WS=35;
	public static final int
		RULE_parse = 0, RULE_columns = 1, RULE_relation = 2, RULE_join_condition = 3, 
		RULE_table_column = 4, RULE_join_operator = 5, RULE_table_name = 6, RULE_join_type = 7, 
		RULE_expr = 8, RULE_add_sub = 9, RULE_mul_div = 10, RULE_compare_operator = 11, 
		RULE_column = 12, RULE_function = 13, RULE_group_by = 14, RULE_literal_value = 15, 
		RULE_function_name = 16;
	public static final String[] ruleNames = {
		"parse", "columns", "relation", "join_condition", "table_column", "join_operator", 
		"table_name", "join_type", "expr", "add_sub", "mul_div", "compare_operator", 
		"column", "function", "group_by", "literal_value", "function_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'.'", null, null, "'='", "'<>'", "'<='", 
		"'<'", "'>='", "'>'", "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", 
		"EQ", "NEQ", "LTEQ", "LT", "GTEQ", "GT", "ADD", "SUB", "MUL", "DIV", "ON", 
		"NATURAL", "INNER", "JOIN", "SUM", "AVERAGE", "COUNT", "IS", "SELECT", 
		"FROM", "WHERE", "AND", "OR", "NULL", "GROUP", "BY", "ANY_NAME", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimpleSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SimpleSQLParser.SELECT, 0); }
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public TerminalNode FROM() { return getToken(SimpleSQLParser.FROM, 0); }
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SimpleSQLParser.WHERE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Group_byContext group_by() {
			return getRuleContext(Group_byContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(SELECT);
			setState(35);
			columns();
			setState(36);
			match(FROM);
			setState(37);
			relation(0);
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(38);
				match(WHERE);
				setState(39);
				expr(0);
				}
			}

			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(42);
				group_by();
				}
			}

			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(45);
				match(T__0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterColumns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitColumns(this);
		}
	}

	public final ColumnsContext columns() throws RecognitionException {
		ColumnsContext _localctx = new ColumnsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_columns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			expr(0);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(49);
				match(T__1);
				setState(50);
				expr(0);
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ParseContext parse() {
			return getRuleContext(ParseContext.class,0);
		}
		public List<RelationContext> relation() {
			return getRuleContexts(RelationContext.class);
		}
		public RelationContext relation(int i) {
			return getRuleContext(RelationContext.class,i);
		}
		public Join_operatorContext join_operator() {
			return getRuleContext(Join_operatorContext.class,0);
		}
		public Join_conditionContext join_condition() {
			return getRuleContext(Join_conditionContext.class,0);
		}
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		return relation(0);
	}

	private RelationContext relation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationContext _localctx = new RelationContext(_ctx, _parentState);
		RelationContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_relation, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANY_NAME:
				{
				setState(57);
				table_name();
				}
				break;
			case T__2:
				{
				setState(58);
				match(T__2);
				setState(59);
				parse();
				setState(60);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new RelationContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_relation);
					setState(64);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(65);
					join_operator();
					setState(66);
					relation(0);
					setState(68);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(67);
						join_condition();
						}
						break;
					}
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Join_conditionContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(SimpleSQLParser.ON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Join_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterJoin_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitJoin_condition(this);
		}
	}

	public final Join_conditionContext join_condition() throws RecognitionException {
		Join_conditionContext _localctx = new Join_conditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_join_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(ON);
			setState(76);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_columnContext extends ParserRuleContext {
		public List<TerminalNode> ANY_NAME() { return getTokens(SimpleSQLParser.ANY_NAME); }
		public TerminalNode ANY_NAME(int i) {
			return getToken(SimpleSQLParser.ANY_NAME, i);
		}
		public Table_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterTable_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitTable_column(this);
		}
	}

	public final Table_columnContext table_column() throws RecognitionException {
		Table_columnContext _localctx = new Table_columnContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_table_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(ANY_NAME);
			setState(79);
			match(T__4);
			setState(80);
			match(ANY_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_operatorContext extends ParserRuleContext {
		public Join_typeContext join_type() {
			return getRuleContext(Join_typeContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(SimpleSQLParser.JOIN, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterJoin_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitJoin_operator(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_join_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			join_type();
			setState(83);
			match(JOIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode ANY_NAME() { return getToken(SimpleSQLParser.ANY_NAME, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(ANY_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_typeContext extends ParserRuleContext {
		public TerminalNode NATURAL() { return getToken(SimpleSQLParser.NATURAL, 0); }
		public TerminalNode INNER() { return getToken(SimpleSQLParser.INNER, 0); }
		public Join_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterJoin_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitJoin_type(this);
		}
	}

	public final Join_typeContext join_type() throws RecognitionException {
		Join_typeContext _localctx = new Join_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_join_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !(_la==NATURAL || _la==INNER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Mul_divContext mul_div() {
			return getRuleContext(Mul_divContext.class,0);
		}
		public Add_subContext add_sub() {
			return getRuleContext(Add_subContext.class,0);
		}
		public Compare_operatorContext compare_operator() {
			return getRuleContext(Compare_operatorContext.class,0);
		}
		public TerminalNode AND() { return getToken(SimpleSQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SimpleSQLParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
			case NULL:
				{
				setState(90);
				literal_value();
				}
				break;
			case ANY_NAME:
				{
				setState(91);
				column();
				}
				break;
			case T__2:
			case SUM:
			case AVERAGE:
			case COUNT:
				{
				setState(92);
				function();
				}
				break;
			case MUL:
				{
				setState(93);
				match(MUL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(114);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(96);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(97);
						mul_div();
						setState(98);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(100);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(101);
						add_sub();
						setState(102);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(104);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(105);
						compare_operator();
						setState(106);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(108);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(109);
						match(AND);
						setState(110);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(111);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(112);
						match(OR);
						setState(113);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Add_subContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(SimpleSQLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(SimpleSQLParser.SUB, 0); }
		public Add_subContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterAdd_sub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitAdd_sub(this);
		}
	}

	public final Add_subContext add_sub() throws RecognitionException {
		Add_subContext _localctx = new Add_subContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_add_sub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mul_divContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(SimpleSQLParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(SimpleSQLParser.DIV, 0); }
		public Mul_divContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul_div; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterMul_div(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitMul_div(this);
		}
	}

	public final Mul_divContext mul_div() throws RecognitionException {
		Mul_divContext _localctx = new Mul_divContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mul_div);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_la = _input.LA(1);
			if ( !(_la==MUL || _la==DIV) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compare_operatorContext extends ParserRuleContext {
		public TerminalNode GTEQ() { return getToken(SimpleSQLParser.GTEQ, 0); }
		public TerminalNode NEQ() { return getToken(SimpleSQLParser.NEQ, 0); }
		public TerminalNode EQ() { return getToken(SimpleSQLParser.EQ, 0); }
		public TerminalNode GT() { return getToken(SimpleSQLParser.GT, 0); }
		public TerminalNode LTEQ() { return getToken(SimpleSQLParser.LTEQ, 0); }
		public TerminalNode LT() { return getToken(SimpleSQLParser.LT, 0); }
		public TerminalNode IS() { return getToken(SimpleSQLParser.IS, 0); }
		public Compare_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterCompare_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitCompare_operator(this);
		}
	}

	public final Compare_operatorContext compare_operator() throws RecognitionException {
		Compare_operatorContext _localctx = new Compare_operatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_compare_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << LT) | (1L << GTEQ) | (1L << GT) | (1L << IS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public Table_columnContext table_column() {
			return getRuleContext(Table_columnContext.class,0);
		}
		public TerminalNode ANY_NAME() { return getToken(SimpleSQLParser.ANY_NAME, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitColumn(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_column);
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				table_column();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(ANY_NAME);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_function);
		int _la;
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUM:
			case AVERAGE:
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				function_name();
				{
				setState(130);
				match(T__2);
				setState(131);
				expr(0);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(132);
					match(T__1);
					setState(133);
					expr(0);
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139);
				match(T__3);
				}
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(141);
				match(T__2);
				setState(142);
				match(T__3);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Group_byContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(SimpleSQLParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(SimpleSQLParser.BY, 0); }
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public Group_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterGroup_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitGroup_by(this);
		}
	}

	public final Group_byContext group_by() throws RecognitionException {
		Group_byContext _localctx = new Group_byContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_group_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(GROUP);
			setState(146);
			match(BY);
			setState(147);
			columns();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SimpleSQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SimpleSQLParser.STRING_LITERAL, 0); }
		public TerminalNode NULL() { return getToken(SimpleSQLParser.NULL, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitLiteral_value(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERIC_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public TerminalNode SUM() { return getToken(SimpleSQLParser.SUM, 0); }
		public TerminalNode AVERAGE() { return getToken(SimpleSQLParser.AVERAGE, 0); }
		public TerminalNode COUNT() { return getToken(SimpleSQLParser.COUNT, 0); }
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleSQLListener ) ((SimpleSQLListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_function_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUM) | (1L << AVERAGE) | (1L << COUNT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return relation_sempred((RelationContext)_localctx, predIndex);
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean relation_sempred(RelationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%\u009c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\5\2+\n\2\3\2\5\2.\n\2\3\2\5\2\61\n\2\3\3\3\3"+
		"\3\3\7\3\66\n\3\f\3\16\39\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\4\3"+
		"\4\3\4\3\4\5\4G\n\4\7\4I\n\4\f\4\16\4L\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\5\na\n\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\nu\n"+
		"\n\f\n\16\nx\13\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\5\16\u0082\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u0089\n\17\f\17\16\17\u008c\13\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0092\n\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\22\2\4\6\22\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\b\3\2\25"+
		"\26\3\2\20\21\3\2\22\23\4\2\n\17\33\33\4\2\b\t!!\3\2\30\32\2\u009c\2$"+
		"\3\2\2\2\4\62\3\2\2\2\6@\3\2\2\2\bM\3\2\2\2\nP\3\2\2\2\fT\3\2\2\2\16W"+
		"\3\2\2\2\20Y\3\2\2\2\22`\3\2\2\2\24y\3\2\2\2\26{\3\2\2\2\30}\3\2\2\2\32"+
		"\u0081\3\2\2\2\34\u0091\3\2\2\2\36\u0093\3\2\2\2 \u0097\3\2\2\2\"\u0099"+
		"\3\2\2\2$%\7\34\2\2%&\5\4\3\2&\'\7\35\2\2\'*\5\6\4\2()\7\36\2\2)+\5\22"+
		"\n\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\5\36\20\2-,\3\2\2\2-.\3\2\2\2.\60"+
		"\3\2\2\2/\61\7\3\2\2\60/\3\2\2\2\60\61\3\2\2\2\61\3\3\2\2\2\62\67\5\22"+
		"\n\2\63\64\7\4\2\2\64\66\5\22\n\2\65\63\3\2\2\2\669\3\2\2\2\67\65\3\2"+
		"\2\2\678\3\2\2\28\5\3\2\2\29\67\3\2\2\2:;\b\4\1\2;A\5\16\b\2<=\7\5\2\2"+
		"=>\5\2\2\2>?\7\6\2\2?A\3\2\2\2@:\3\2\2\2@<\3\2\2\2AJ\3\2\2\2BC\f\3\2\2"+
		"CD\5\f\7\2DF\5\6\4\2EG\5\b\5\2FE\3\2\2\2FG\3\2\2\2GI\3\2\2\2HB\3\2\2\2"+
		"IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\7\3\2\2\2LJ\3\2\2\2MN\7\24\2\2NO\5\22"+
		"\n\2O\t\3\2\2\2PQ\7$\2\2QR\7\7\2\2RS\7$\2\2S\13\3\2\2\2TU\5\20\t\2UV\7"+
		"\27\2\2V\r\3\2\2\2WX\7$\2\2X\17\3\2\2\2YZ\t\2\2\2Z\21\3\2\2\2[\\\b\n\1"+
		"\2\\a\5 \21\2]a\5\32\16\2^a\5\34\17\2_a\7\22\2\2`[\3\2\2\2`]\3\2\2\2`"+
		"^\3\2\2\2`_\3\2\2\2av\3\2\2\2bc\f\t\2\2cd\5\26\f\2de\5\22\n\neu\3\2\2"+
		"\2fg\f\b\2\2gh\5\24\13\2hi\5\22\n\tiu\3\2\2\2jk\f\7\2\2kl\5\30\r\2lm\5"+
		"\22\n\bmu\3\2\2\2no\f\6\2\2op\7\37\2\2pu\5\22\n\7qr\f\5\2\2rs\7 \2\2s"+
		"u\5\22\n\6tb\3\2\2\2tf\3\2\2\2tj\3\2\2\2tn\3\2\2\2tq\3\2\2\2ux\3\2\2\2"+
		"vt\3\2\2\2vw\3\2\2\2w\23\3\2\2\2xv\3\2\2\2yz\t\3\2\2z\25\3\2\2\2{|\t\4"+
		"\2\2|\27\3\2\2\2}~\t\5\2\2~\31\3\2\2\2\177\u0082\5\n\6\2\u0080\u0082\7"+
		"$\2\2\u0081\177\3\2\2\2\u0081\u0080\3\2\2\2\u0082\33\3\2\2\2\u0083\u0084"+
		"\5\"\22\2\u0084\u0085\7\5\2\2\u0085\u008a\5\22\n\2\u0086\u0087\7\4\2\2"+
		"\u0087\u0089\5\22\n\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u008e\7\6\2\2\u008e\u0092\3\2\2\2\u008f\u0090\7\5\2\2\u0090\u0092\7\6"+
		"\2\2\u0091\u0083\3\2\2\2\u0091\u008f\3\2\2\2\u0092\35\3\2\2\2\u0093\u0094"+
		"\7\"\2\2\u0094\u0095\7#\2\2\u0095\u0096\5\4\3\2\u0096\37\3\2\2\2\u0097"+
		"\u0098\t\6\2\2\u0098!\3\2\2\2\u0099\u009a\t\7\2\2\u009a#\3\2\2\2\17*-"+
		"\60\67@FJ`tv\u0081\u008a\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}