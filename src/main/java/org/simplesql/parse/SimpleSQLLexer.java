// Generated from SimpleSQL.g4 by ANTLR 4.7.1

    package org.simplesql.parse;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUMERIC_LITERAL=6, STRING_LITERAL=7, 
		EQ=8, NEQ=9, LTEQ=10, LT=11, GTEQ=12, GT=13, ADD=14, SUB=15, MUL=16, DIV=17, 
		LEFT=18, RIGHT=19, OUTER=20, ON=21, NATURAL=22, INNER=23, JOIN=24, SUM=25, 
		AVERAGE=26, COUNT=27, IS=28, SELECT=29, FROM=30, WHERE=31, AND=32, OR=33, 
		NULL=34, GROUP=35, BY=36, ANY_NAME=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "NUMERIC_LITERAL", "STRING_LITERAL", 
		"EQ", "NEQ", "LTEQ", "LT", "GTEQ", "GT", "ADD", "SUB", "MUL", "DIV", "LEFT", 
		"RIGHT", "OUTER", "ON", "NATURAL", "INNER", "JOIN", "SUM", "AVERAGE", 
		"COUNT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", "GROUP", 
		"BY", "ANY_NAME", "DIGIT", "A", "B", "C", "D", "E", "F", "G", "H", "I", 
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", 
		"X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'.'", null, null, "'='", "'<>'", "'<='", 
		"'<'", "'>='", "'>'", "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", 
		"EQ", "NEQ", "LTEQ", "LT", "GTEQ", "GT", "ADD", "SUB", "MUL", "DIV", "LEFT", 
		"RIGHT", "OUTER", "ON", "NATURAL", "INNER", "JOIN", "SUM", "AVERAGE", 
		"COUNT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", "GROUP", 
		"BY", "ANY_NAME", "WS"
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


	public SimpleSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0182\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\6\7\u0091\n\7\r\7\16\7\u0092\3\7\3\7\7\7\u0097\n\7\f\7\16\7\u009a"+
		"\13\7\5\7\u009c\n\7\3\7\3\7\5\7\u00a0\n\7\3\7\6\7\u00a3\n\7\r\7\16\7\u00a4"+
		"\5\7\u00a7\n\7\3\7\3\7\6\7\u00ab\n\7\r\7\16\7\u00ac\3\7\3\7\5\7\u00b1"+
		"\n\7\3\7\6\7\u00b4\n\7\r\7\16\7\u00b5\5\7\u00b8\n\7\5\7\u00ba\n\7\3\b"+
		"\3\b\3\b\3\b\7\b\u00c0\n\b\f\b\16\b\u00c3\13\b\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3&\6&\u0142\n&\r&\16&\u0143"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\3"+
		"8\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\6B\u017d\n"+
		"B\rB\16B\u017e\3B\3B\2\2C\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M\2O\2Q\2S\2U\2W\2Y\2[\2]"+
		"\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083"+
		"(\3\2!\4\2--//\3\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff"+
		"\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2O"+
		"Ooo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4"+
		"\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\f\17\17\"\"\2\u0175\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2\u0083\3\2\2\2\3\u0085\3\2\2\2\5\u0087\3\2\2\2"+
		"\7\u0089\3\2\2\2\t\u008b\3\2\2\2\13\u008d\3\2\2\2\r\u00b9\3\2\2\2\17\u00bb"+
		"\3\2\2\2\21\u00c6\3\2\2\2\23\u00c8\3\2\2\2\25\u00cb\3\2\2\2\27\u00ce\3"+
		"\2\2\2\31\u00d0\3\2\2\2\33\u00d3\3\2\2\2\35\u00d5\3\2\2\2\37\u00d7\3\2"+
		"\2\2!\u00d9\3\2\2\2#\u00db\3\2\2\2%\u00dd\3\2\2\2\'\u00e2\3\2\2\2)\u00e8"+
		"\3\2\2\2+\u00ee\3\2\2\2-\u00f1\3\2\2\2/\u00f9\3\2\2\2\61\u00ff\3\2\2\2"+
		"\63\u0104\3\2\2\2\65\u0108\3\2\2\2\67\u0110\3\2\2\29\u0116\3\2\2\2;\u0119"+
		"\3\2\2\2=\u0120\3\2\2\2?\u0125\3\2\2\2A\u012b\3\2\2\2C\u012f\3\2\2\2E"+
		"\u0132\3\2\2\2G\u0137\3\2\2\2I\u013d\3\2\2\2K\u0141\3\2\2\2M\u0145\3\2"+
		"\2\2O\u0147\3\2\2\2Q\u0149\3\2\2\2S\u014b\3\2\2\2U\u014d\3\2\2\2W\u014f"+
		"\3\2\2\2Y\u0151\3\2\2\2[\u0153\3\2\2\2]\u0155\3\2\2\2_\u0157\3\2\2\2a"+
		"\u0159\3\2\2\2c\u015b\3\2\2\2e\u015d\3\2\2\2g\u015f\3\2\2\2i\u0161\3\2"+
		"\2\2k\u0163\3\2\2\2m\u0165\3\2\2\2o\u0167\3\2\2\2q\u0169\3\2\2\2s\u016b"+
		"\3\2\2\2u\u016d\3\2\2\2w\u016f\3\2\2\2y\u0171\3\2\2\2{\u0173\3\2\2\2}"+
		"\u0175\3\2\2\2\177\u0177\3\2\2\2\u0081\u0179\3\2\2\2\u0083\u017c\3\2\2"+
		"\2\u0085\u0086\7=\2\2\u0086\4\3\2\2\2\u0087\u0088\7.\2\2\u0088\6\3\2\2"+
		"\2\u0089\u008a\7*\2\2\u008a\b\3\2\2\2\u008b\u008c\7+\2\2\u008c\n\3\2\2"+
		"\2\u008d\u008e\7\60\2\2\u008e\f\3\2\2\2\u008f\u0091\5M\'\2\u0090\u008f"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u009b\3\2\2\2\u0094\u0098\7\60\2\2\u0095\u0097\5M\'\2\u0096\u0095\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u0094\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u00a6\3\2\2\2\u009d\u009f\5W,\2\u009e\u00a0\t\2\2\2\u009f\u009e"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\5M\'\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a7\3\2\2\2\u00a6\u009d\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00ba\3\2\2\2\u00a8\u00aa\7\60\2\2\u00a9\u00ab\5M\'\2\u00aa\u00a9\3\2"+
		"\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00b7\3\2\2\2\u00ae\u00b0\5W,\2\u00af\u00b1\t\2\2\2\u00b0\u00af\3\2\2"+
		"\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b4\5M\'\2\u00b3\u00b2"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b8\3\2\2\2\u00b7\u00ae\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2"+
		"\2\2\u00b9\u0090\3\2\2\2\u00b9\u00a8\3\2\2\2\u00ba\16\3\2\2\2\u00bb\u00c1"+
		"\7)\2\2\u00bc\u00c0\n\3\2\2\u00bd\u00be\7)\2\2\u00be\u00c0\7)\2\2\u00bf"+
		"\u00bc\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00c5\7)\2\2\u00c5\20\3\2\2\2\u00c6\u00c7\7?\2\2\u00c7\22\3\2\2\2\u00c8"+
		"\u00c9\7>\2\2\u00c9\u00ca\7@\2\2\u00ca\24\3\2\2\2\u00cb\u00cc\7>\2\2\u00cc"+
		"\u00cd\7?\2\2\u00cd\26\3\2\2\2\u00ce\u00cf\7>\2\2\u00cf\30\3\2\2\2\u00d0"+
		"\u00d1\7@\2\2\u00d1\u00d2\7?\2\2\u00d2\32\3\2\2\2\u00d3\u00d4\7@\2\2\u00d4"+
		"\34\3\2\2\2\u00d5\u00d6\7-\2\2\u00d6\36\3\2\2\2\u00d7\u00d8\7/\2\2\u00d8"+
		" \3\2\2\2\u00d9\u00da\7,\2\2\u00da\"\3\2\2\2\u00db\u00dc\7\61\2\2\u00dc"+
		"$\3\2\2\2\u00dd\u00de\5e\63\2\u00de\u00df\5W,\2\u00df\u00e0\5Y-\2\u00e0"+
		"\u00e1\5u;\2\u00e1&\3\2\2\2\u00e2\u00e3\5q9\2\u00e3\u00e4\5_\60\2\u00e4"+
		"\u00e5\5[.\2\u00e5\u00e6\5]/\2\u00e6\u00e7\5u;\2\u00e7(\3\2\2\2\u00e8"+
		"\u00e9\5k\66\2\u00e9\u00ea\5w<\2\u00ea\u00eb\5u;\2\u00eb\u00ec\5W,\2\u00ec"+
		"\u00ed\5q9\2\u00ed*\3\2\2\2\u00ee\u00ef\5k\66\2\u00ef\u00f0\5i\65\2\u00f0"+
		",\3\2\2\2\u00f1\u00f2\5i\65\2\u00f2\u00f3\5O(\2\u00f3\u00f4\5u;\2\u00f4"+
		"\u00f5\5w<\2\u00f5\u00f6\5q9\2\u00f6\u00f7\5O(\2\u00f7\u00f8\5e\63\2\u00f8"+
		".\3\2\2\2\u00f9\u00fa\5_\60\2\u00fa\u00fb\5i\65\2\u00fb\u00fc\5i\65\2"+
		"\u00fc\u00fd\5W,\2\u00fd\u00fe\5q9\2\u00fe\60\3\2\2\2\u00ff\u0100\5a\61"+
		"\2\u0100\u0101\5k\66\2\u0101\u0102\5_\60\2\u0102\u0103\5i\65\2\u0103\62"+
		"\3\2\2\2\u0104\u0105\5s:\2\u0105\u0106\5w<\2\u0106\u0107\5g\64\2\u0107"+
		"\64\3\2\2\2\u0108\u0109\5O(\2\u0109\u010a\5y=\2\u010a\u010b\5W,\2\u010b"+
		"\u010c\5q9\2\u010c\u010d\5O(\2\u010d\u010e\5[.\2\u010e\u010f\5W,\2\u010f"+
		"\66\3\2\2\2\u0110\u0111\5S*\2\u0111\u0112\5k\66\2\u0112\u0113\5w<\2\u0113"+
		"\u0114\5i\65\2\u0114\u0115\5u;\2\u01158\3\2\2\2\u0116\u0117\5_\60\2\u0117"+
		"\u0118\5s:\2\u0118:\3\2\2\2\u0119\u011a\5s:\2\u011a\u011b\5W,\2\u011b"+
		"\u011c\5e\63\2\u011c\u011d\5W,\2\u011d\u011e\5S*\2\u011e\u011f\5u;\2\u011f"+
		"<\3\2\2\2\u0120\u0121\5Y-\2\u0121\u0122\5q9\2\u0122\u0123\5k\66\2\u0123"+
		"\u0124\5g\64\2\u0124>\3\2\2\2\u0125\u0126\5{>\2\u0126\u0127\5]/\2\u0127"+
		"\u0128\5W,\2\u0128\u0129\5q9\2\u0129\u012a\5W,\2\u012a@\3\2\2\2\u012b"+
		"\u012c\5O(\2\u012c\u012d\5i\65\2\u012d\u012e\5U+\2\u012eB\3\2\2\2\u012f"+
		"\u0130\5k\66\2\u0130\u0131\5q9\2\u0131D\3\2\2\2\u0132\u0133\5i\65\2\u0133"+
		"\u0134\5w<\2\u0134\u0135\5e\63\2\u0135\u0136\5e\63\2\u0136F\3\2\2\2\u0137"+
		"\u0138\5[.\2\u0138\u0139\5q9\2\u0139\u013a\5k\66\2\u013a\u013b\5w<\2\u013b"+
		"\u013c\5m\67\2\u013cH\3\2\2\2\u013d\u013e\5Q)\2\u013e\u013f\5\177@\2\u013f"+
		"J\3\2\2\2\u0140\u0142\t\4\2\2\u0141\u0140\3\2\2\2\u0142\u0143\3\2\2\2"+
		"\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144L\3\2\2\2\u0145\u0146\t"+
		"\5\2\2\u0146N\3\2\2\2\u0147\u0148\t\6\2\2\u0148P\3\2\2\2\u0149\u014a\t"+
		"\7\2\2\u014aR\3\2\2\2\u014b\u014c\t\b\2\2\u014cT\3\2\2\2\u014d\u014e\t"+
		"\t\2\2\u014eV\3\2\2\2\u014f\u0150\t\n\2\2\u0150X\3\2\2\2\u0151\u0152\t"+
		"\13\2\2\u0152Z\3\2\2\2\u0153\u0154\t\f\2\2\u0154\\\3\2\2\2\u0155\u0156"+
		"\t\r\2\2\u0156^\3\2\2\2\u0157\u0158\t\16\2\2\u0158`\3\2\2\2\u0159\u015a"+
		"\t\17\2\2\u015ab\3\2\2\2\u015b\u015c\t\20\2\2\u015cd\3\2\2\2\u015d\u015e"+
		"\t\21\2\2\u015ef\3\2\2\2\u015f\u0160\t\22\2\2\u0160h\3\2\2\2\u0161\u0162"+
		"\t\23\2\2\u0162j\3\2\2\2\u0163\u0164\t\24\2\2\u0164l\3\2\2\2\u0165\u0166"+
		"\t\25\2\2\u0166n\3\2\2\2\u0167\u0168\t\26\2\2\u0168p\3\2\2\2\u0169\u016a"+
		"\t\27\2\2\u016ar\3\2\2\2\u016b\u016c\t\30\2\2\u016ct\3\2\2\2\u016d\u016e"+
		"\t\31\2\2\u016ev\3\2\2\2\u016f\u0170\t\32\2\2\u0170x\3\2\2\2\u0171\u0172"+
		"\t\33\2\2\u0172z\3\2\2\2\u0173\u0174\t\34\2\2\u0174|\3\2\2\2\u0175\u0176"+
		"\t\35\2\2\u0176~\3\2\2\2\u0177\u0178\t\36\2\2\u0178\u0080\3\2\2\2\u0179"+
		"\u017a\t\37\2\2\u017a\u0082\3\2\2\2\u017b\u017d\t \2\2\u017c\u017b\3\2"+
		"\2\2\u017d\u017e\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0181\bB\2\2\u0181\u0084\3\2\2\2\22\2\u0092\u0098"+
		"\u009b\u009f\u00a4\u00a6\u00ac\u00b0\u00b5\u00b7\u00b9\u00bf\u00c1\u0143"+
		"\u017e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}