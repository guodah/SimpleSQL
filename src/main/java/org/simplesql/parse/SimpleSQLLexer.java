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
		T__0=1, T__1=2, T__2=3, T__3=4, NUMERIC_LITERAL=5, STRING_LITERAL=6, EQ=7, 
		NEQ=8, LTEQ=9, LT=10, GTEQ=11, GT=12, WILDCARD=13, NATURAL=14, JOIN=15, 
		SUM=16, AVERAGE=17, COUNT=18, IS=19, SELECT=20, FROM=21, WHERE=22, AND=23, 
		OR=24, NULL=25, GROUP=26, BY=27, ANY_NAME=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", 
		"NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "NATURAL", "JOIN", "SUM", 
		"AVERAGE", "COUNT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", 
		"GROUP", "BY", "ANY_NAME", "DIGIT", "A", "B", "C", "D", "E", "F", "G", 
		"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", 
		"V", "W", "X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", null, null, "'='", "'<>'", "'<='", "'<'", 
		"'>='", "'>'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", 
		"NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "NATURAL", "JOIN", "SUM", 
		"AVERAGE", "COUNT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", 
		"GROUP", "BY", "ANY_NAME", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u014e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\6\6}\n\6\r\6\16\6~\3\6\3\6\7\6\u0083\n\6\f\6\16\6\u0086"+
		"\13\6\5\6\u0088\n\6\3\6\3\6\5\6\u008c\n\6\3\6\6\6\u008f\n\6\r\6\16\6\u0090"+
		"\5\6\u0093\n\6\3\6\3\6\6\6\u0097\n\6\r\6\16\6\u0098\3\6\3\6\5\6\u009d"+
		"\n\6\3\6\6\6\u00a0\n\6\r\6\16\6\u00a1\5\6\u00a4\n\6\5\6\u00a6\n\6\3\7"+
		"\3\7\3\7\3\7\7\7\u00ac\n\7\f\7\16\7\u00af\13\7\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\6\35\u010e\n\35\r\35\16\35\u010f\3\36\3\36\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3"+
		",\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\69\u0149\n9\r9\169\u014a\39\3"+
		"9\2\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2"+
		"g\2i\2k\2m\2o\2q\37\3\2!\4\2--//\3\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2DD"+
		"dd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2"+
		"MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4"+
		"\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\f\17\17"+
		"\"\"\2\u0141\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5u\3\2\2\2\7w\3\2\2\2\ty\3\2\2\2\13\u00a5"+
		"\3\2\2\2\r\u00a7\3\2\2\2\17\u00b2\3\2\2\2\21\u00b4\3\2\2\2\23\u00b7\3"+
		"\2\2\2\25\u00ba\3\2\2\2\27\u00bc\3\2\2\2\31\u00bf\3\2\2\2\33\u00c1\3\2"+
		"\2\2\35\u00c3\3\2\2\2\37\u00cb\3\2\2\2!\u00d0\3\2\2\2#\u00d4\3\2\2\2%"+
		"\u00dc\3\2\2\2\'\u00e2\3\2\2\2)\u00e5\3\2\2\2+\u00ec\3\2\2\2-\u00f1\3"+
		"\2\2\2/\u00f7\3\2\2\2\61\u00fb\3\2\2\2\63\u00fe\3\2\2\2\65\u0103\3\2\2"+
		"\2\67\u0109\3\2\2\29\u010d\3\2\2\2;\u0111\3\2\2\2=\u0113\3\2\2\2?\u0115"+
		"\3\2\2\2A\u0117\3\2\2\2C\u0119\3\2\2\2E\u011b\3\2\2\2G\u011d\3\2\2\2I"+
		"\u011f\3\2\2\2K\u0121\3\2\2\2M\u0123\3\2\2\2O\u0125\3\2\2\2Q\u0127\3\2"+
		"\2\2S\u0129\3\2\2\2U\u012b\3\2\2\2W\u012d\3\2\2\2Y\u012f\3\2\2\2[\u0131"+
		"\3\2\2\2]\u0133\3\2\2\2_\u0135\3\2\2\2a\u0137\3\2\2\2c\u0139\3\2\2\2e"+
		"\u013b\3\2\2\2g\u013d\3\2\2\2i\u013f\3\2\2\2k\u0141\3\2\2\2m\u0143\3\2"+
		"\2\2o\u0145\3\2\2\2q\u0148\3\2\2\2st\7=\2\2t\4\3\2\2\2uv\7.\2\2v\6\3\2"+
		"\2\2wx\7*\2\2x\b\3\2\2\2yz\7+\2\2z\n\3\2\2\2{}\5;\36\2|{\3\2\2\2}~\3\2"+
		"\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0087\3\2\2\2\u0080\u0084\7\60\2\2\u0081"+
		"\u0083\5;\36\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\u0080\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0092\3\2\2\2\u0089\u008b\5E"+
		"#\2\u008a\u008c\t\2\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008e\3\2\2\2\u008d\u008f\5;\36\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092"+
		"\u0089\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u00a6\3\2\2\2\u0094\u0096\7\60"+
		"\2\2\u0095\u0097\5;\36\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u00a3\3\2\2\2\u009a\u009c\5E"+
		"#\2\u009b\u009d\t\2\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u00a0\5;\36\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u009a\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5|\3\2\2\2"+
		"\u00a5\u0094\3\2\2\2\u00a6\f\3\2\2\2\u00a7\u00ad\7)\2\2\u00a8\u00ac\n"+
		"\3\2\2\u00a9\u00aa\7)\2\2\u00aa\u00ac\7)\2\2\u00ab\u00a8\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\7)\2\2\u00b1"+
		"\16\3\2\2\2\u00b2\u00b3\7?\2\2\u00b3\20\3\2\2\2\u00b4\u00b5\7>\2\2\u00b5"+
		"\u00b6\7@\2\2\u00b6\22\3\2\2\2\u00b7\u00b8\7>\2\2\u00b8\u00b9\7?\2\2\u00b9"+
		"\24\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb\26\3\2\2\2\u00bc\u00bd\7@\2\2\u00bd"+
		"\u00be\7?\2\2\u00be\30\3\2\2\2\u00bf\u00c0\7@\2\2\u00c0\32\3\2\2\2\u00c1"+
		"\u00c2\7,\2\2\u00c2\34\3\2\2\2\u00c3\u00c4\5W,\2\u00c4\u00c5\5=\37\2\u00c5"+
		"\u00c6\5c\62\2\u00c6\u00c7\5e\63\2\u00c7\u00c8\5_\60\2\u00c8\u00c9\5="+
		"\37\2\u00c9\u00ca\5S*\2\u00ca\36\3\2\2\2\u00cb\u00cc\5O(\2\u00cc\u00cd"+
		"\5Y-\2\u00cd\u00ce\5M\'\2\u00ce\u00cf\5W,\2\u00cf \3\2\2\2\u00d0\u00d1"+
		"\5a\61\2\u00d1\u00d2\5e\63\2\u00d2\u00d3\5U+\2\u00d3\"\3\2\2\2\u00d4\u00d5"+
		"\5=\37\2\u00d5\u00d6\5g\64\2\u00d6\u00d7\5E#\2\u00d7\u00d8\5_\60\2\u00d8"+
		"\u00d9\5=\37\2\u00d9\u00da\5I%\2\u00da\u00db\5E#\2\u00db$\3\2\2\2\u00dc"+
		"\u00dd\5A!\2\u00dd\u00de\5Y-\2\u00de\u00df\5e\63\2\u00df\u00e0\5W,\2\u00e0"+
		"\u00e1\5c\62\2\u00e1&\3\2\2\2\u00e2\u00e3\5M\'\2\u00e3\u00e4\5a\61\2\u00e4"+
		"(\3\2\2\2\u00e5\u00e6\5a\61\2\u00e6\u00e7\5E#\2\u00e7\u00e8\5S*\2\u00e8"+
		"\u00e9\5E#\2\u00e9\u00ea\5A!\2\u00ea\u00eb\5c\62\2\u00eb*\3\2\2\2\u00ec"+
		"\u00ed\5G$\2\u00ed\u00ee\5_\60\2\u00ee\u00ef\5Y-\2\u00ef\u00f0\5U+\2\u00f0"+
		",\3\2\2\2\u00f1\u00f2\5i\65\2\u00f2\u00f3\5K&\2\u00f3\u00f4\5E#\2\u00f4"+
		"\u00f5\5_\60\2\u00f5\u00f6\5E#\2\u00f6.\3\2\2\2\u00f7\u00f8\5=\37\2\u00f8"+
		"\u00f9\5W,\2\u00f9\u00fa\5C\"\2\u00fa\60\3\2\2\2\u00fb\u00fc\5Y-\2\u00fc"+
		"\u00fd\5_\60\2\u00fd\62\3\2\2\2\u00fe\u00ff\5W,\2\u00ff\u0100\5e\63\2"+
		"\u0100\u0101\5S*\2\u0101\u0102\5S*\2\u0102\64\3\2\2\2\u0103\u0104\5I%"+
		"\2\u0104\u0105\5_\60\2\u0105\u0106\5Y-\2\u0106\u0107\5e\63\2\u0107\u0108"+
		"\5[.\2\u0108\66\3\2\2\2\u0109\u010a\5? \2\u010a\u010b\5m\67\2\u010b8\3"+
		"\2\2\2\u010c\u010e\t\4\2\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110:\3\2\2\2\u0111\u0112\t\5\2\2"+
		"\u0112<\3\2\2\2\u0113\u0114\t\6\2\2\u0114>\3\2\2\2\u0115\u0116\t\7\2\2"+
		"\u0116@\3\2\2\2\u0117\u0118\t\b\2\2\u0118B\3\2\2\2\u0119\u011a\t\t\2\2"+
		"\u011aD\3\2\2\2\u011b\u011c\t\n\2\2\u011cF\3\2\2\2\u011d\u011e\t\13\2"+
		"\2\u011eH\3\2\2\2\u011f\u0120\t\f\2\2\u0120J\3\2\2\2\u0121\u0122\t\r\2"+
		"\2\u0122L\3\2\2\2\u0123\u0124\t\16\2\2\u0124N\3\2\2\2\u0125\u0126\t\17"+
		"\2\2\u0126P\3\2\2\2\u0127\u0128\t\20\2\2\u0128R\3\2\2\2\u0129\u012a\t"+
		"\21\2\2\u012aT\3\2\2\2\u012b\u012c\t\22\2\2\u012cV\3\2\2\2\u012d\u012e"+
		"\t\23\2\2\u012eX\3\2\2\2\u012f\u0130\t\24\2\2\u0130Z\3\2\2\2\u0131\u0132"+
		"\t\25\2\2\u0132\\\3\2\2\2\u0133\u0134\t\26\2\2\u0134^\3\2\2\2\u0135\u0136"+
		"\t\27\2\2\u0136`\3\2\2\2\u0137\u0138\t\30\2\2\u0138b\3\2\2\2\u0139\u013a"+
		"\t\31\2\2\u013ad\3\2\2\2\u013b\u013c\t\32\2\2\u013cf\3\2\2\2\u013d\u013e"+
		"\t\33\2\2\u013eh\3\2\2\2\u013f\u0140\t\34\2\2\u0140j\3\2\2\2\u0141\u0142"+
		"\t\35\2\2\u0142l\3\2\2\2\u0143\u0144\t\36\2\2\u0144n\3\2\2\2\u0145\u0146"+
		"\t\37\2\2\u0146p\3\2\2\2\u0147\u0149\t \2\2\u0148\u0147\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014d\b9\2\2\u014dr\3\2\2\2\22\2~\u0084\u0087\u008b\u0090\u0092"+
		"\u0098\u009c\u00a1\u00a3\u00a5\u00ab\u00ad\u010f\u014a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}