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
		NEQ=8, LTEQ=9, LT=10, GTEQ=11, GT=12, WILDCARD=13, SUM=14, AVERAGE=15, 
		COUNT=16, IS=17, SELECT=18, FROM=19, WHERE=20, AND=21, OR=22, NULL=23, 
		GROUP=24, BY=25, ANY_NAME=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", 
		"NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "SUM", "AVERAGE", "COUNT", 
		"IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", "GROUP", "BY", "ANY_NAME", 
		"DIGIT", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", null, null, "'='", "'<>'", "'<='", "'<'", 
		"'>='", "'>'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", 
		"NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "SUM", "AVERAGE", "COUNT", 
		"IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", "GROUP", "BY", "ANY_NAME", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u013d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\6\6y\n\6\r\6\16\6z\3\6\3\6\7\6\177\n\6\f\6\16\6\u0082\13\6\5\6\u0084"+
		"\n\6\3\6\3\6\5\6\u0088\n\6\3\6\6\6\u008b\n\6\r\6\16\6\u008c\5\6\u008f"+
		"\n\6\3\6\3\6\6\6\u0093\n\6\r\6\16\6\u0094\3\6\3\6\5\6\u0099\n\6\3\6\6"+
		"\6\u009c\n\6\r\6\16\6\u009d\5\6\u00a0\n\6\5\6\u00a2\n\6\3\7\3\7\3\7\3"+
		"\7\7\7\u00a8\n\7\f\7\16\7\u00ab\13\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33"+
		"\6\33\u00fd\n\33\r\33\16\33\u00fe\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3"+
		"\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3"+
		"*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63"+
		"\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\6\67\u0138\n\67\r\67\16\67\u0139"+
		"\3\67\3\67\2\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a"+
		"\2c\2e\2g\2i\2k\2m\35\3\2!\4\2--//\3\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2"+
		"DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4"+
		"\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUu"+
		"u\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\f\17"+
		"\17\"\"\2\u0130\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2m\3\2\2\2\3o\3"+
		"\2\2\2\5q\3\2\2\2\7s\3\2\2\2\tu\3\2\2\2\13\u00a1\3\2\2\2\r\u00a3\3\2\2"+
		"\2\17\u00ae\3\2\2\2\21\u00b0\3\2\2\2\23\u00b3\3\2\2\2\25\u00b6\3\2\2\2"+
		"\27\u00b8\3\2\2\2\31\u00bb\3\2\2\2\33\u00bd\3\2\2\2\35\u00bf\3\2\2\2\37"+
		"\u00c3\3\2\2\2!\u00cb\3\2\2\2#\u00d1\3\2\2\2%\u00d4\3\2\2\2\'\u00db\3"+
		"\2\2\2)\u00e0\3\2\2\2+\u00e6\3\2\2\2-\u00ea\3\2\2\2/\u00ed\3\2\2\2\61"+
		"\u00f2\3\2\2\2\63\u00f8\3\2\2\2\65\u00fc\3\2\2\2\67\u0100\3\2\2\29\u0102"+
		"\3\2\2\2;\u0104\3\2\2\2=\u0106\3\2\2\2?\u0108\3\2\2\2A\u010a\3\2\2\2C"+
		"\u010c\3\2\2\2E\u010e\3\2\2\2G\u0110\3\2\2\2I\u0112\3\2\2\2K\u0114\3\2"+
		"\2\2M\u0116\3\2\2\2O\u0118\3\2\2\2Q\u011a\3\2\2\2S\u011c\3\2\2\2U\u011e"+
		"\3\2\2\2W\u0120\3\2\2\2Y\u0122\3\2\2\2[\u0124\3\2\2\2]\u0126\3\2\2\2_"+
		"\u0128\3\2\2\2a\u012a\3\2\2\2c\u012c\3\2\2\2e\u012e\3\2\2\2g\u0130\3\2"+
		"\2\2i\u0132\3\2\2\2k\u0134\3\2\2\2m\u0137\3\2\2\2op\7=\2\2p\4\3\2\2\2"+
		"qr\7.\2\2r\6\3\2\2\2st\7*\2\2t\b\3\2\2\2uv\7+\2\2v\n\3\2\2\2wy\5\67\34"+
		"\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\u0083\3\2\2\2|\u0080\7\60"+
		"\2\2}\177\5\67\34\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083|\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u008e\3\2\2\2\u0085\u0087\5A!\2\u0086\u0088\t\2\2"+
		"\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u008b"+
		"\5\67\34\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2"+
		"\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u0085\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u00a2\3\2\2\2\u0090\u0092\7\60\2\2\u0091\u0093\5\67\34"+
		"\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\u009f\3\2\2\2\u0096\u0098\5A!\2\u0097\u0099\t\2\2\2\u0098"+
		"\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u009c\5\67"+
		"\34\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u0096\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\u00a2\3\2\2\2\u00a1x\3\2\2\2\u00a1\u0090\3\2\2\2\u00a2\f\3"+
		"\2\2\2\u00a3\u00a9\7)\2\2\u00a4\u00a8\n\3\2\2\u00a5\u00a6\7)\2\2\u00a6"+
		"\u00a8\7)\2\2\u00a7\u00a4\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2"+
		"\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ad\7)\2\2\u00ad\16\3\2\2\2\u00ae\u00af\7?\2\2"+
		"\u00af\20\3\2\2\2\u00b0\u00b1\7>\2\2\u00b1\u00b2\7@\2\2\u00b2\22\3\2\2"+
		"\2\u00b3\u00b4\7>\2\2\u00b4\u00b5\7?\2\2\u00b5\24\3\2\2\2\u00b6\u00b7"+
		"\7>\2\2\u00b7\26\3\2\2\2\u00b8\u00b9\7@\2\2\u00b9\u00ba\7?\2\2\u00ba\30"+
		"\3\2\2\2\u00bb\u00bc\7@\2\2\u00bc\32\3\2\2\2\u00bd\u00be\7,\2\2\u00be"+
		"\34\3\2\2\2\u00bf\u00c0\5]/\2\u00c0\u00c1\5a\61\2\u00c1\u00c2\5Q)\2\u00c2"+
		"\36\3\2\2\2\u00c3\u00c4\59\35\2\u00c4\u00c5\5c\62\2\u00c5\u00c6\5A!\2"+
		"\u00c6\u00c7\5[.\2\u00c7\u00c8\59\35\2\u00c8\u00c9\5E#\2\u00c9\u00ca\5"+
		"A!\2\u00ca \3\2\2\2\u00cb\u00cc\5=\37\2\u00cc\u00cd\5U+\2\u00cd\u00ce"+
		"\5a\61\2\u00ce\u00cf\5S*\2\u00cf\u00d0\5_\60\2\u00d0\"\3\2\2\2\u00d1\u00d2"+
		"\5I%\2\u00d2\u00d3\5]/\2\u00d3$\3\2\2\2\u00d4\u00d5\5]/\2\u00d5\u00d6"+
		"\5A!\2\u00d6\u00d7\5O(\2\u00d7\u00d8\5A!\2\u00d8\u00d9\5=\37\2\u00d9\u00da"+
		"\5_\60\2\u00da&\3\2\2\2\u00db\u00dc\5C\"\2\u00dc\u00dd\5[.\2\u00dd\u00de"+
		"\5U+\2\u00de\u00df\5Q)\2\u00df(\3\2\2\2\u00e0\u00e1\5e\63\2\u00e1\u00e2"+
		"\5G$\2\u00e2\u00e3\5A!\2\u00e3\u00e4\5[.\2\u00e4\u00e5\5A!\2\u00e5*\3"+
		"\2\2\2\u00e6\u00e7\59\35\2\u00e7\u00e8\5S*\2\u00e8\u00e9\5? \2\u00e9,"+
		"\3\2\2\2\u00ea\u00eb\5U+\2\u00eb\u00ec\5[.\2\u00ec.\3\2\2\2\u00ed\u00ee"+
		"\5S*\2\u00ee\u00ef\5a\61\2\u00ef\u00f0\5O(\2\u00f0\u00f1\5O(\2\u00f1\60"+
		"\3\2\2\2\u00f2\u00f3\5E#\2\u00f3\u00f4\5[.\2\u00f4\u00f5\5U+\2\u00f5\u00f6"+
		"\5a\61\2\u00f6\u00f7\5W,\2\u00f7\62\3\2\2\2\u00f8\u00f9\5;\36\2\u00f9"+
		"\u00fa\5i\65\2\u00fa\64\3\2\2\2\u00fb\u00fd\t\4\2\2\u00fc\u00fb\3\2\2"+
		"\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\66"+
		"\3\2\2\2\u0100\u0101\t\5\2\2\u01018\3\2\2\2\u0102\u0103\t\6\2\2\u0103"+
		":\3\2\2\2\u0104\u0105\t\7\2\2\u0105<\3\2\2\2\u0106\u0107\t\b\2\2\u0107"+
		">\3\2\2\2\u0108\u0109\t\t\2\2\u0109@\3\2\2\2\u010a\u010b\t\n\2\2\u010b"+
		"B\3\2\2\2\u010c\u010d\t\13\2\2\u010dD\3\2\2\2\u010e\u010f\t\f\2\2\u010f"+
		"F\3\2\2\2\u0110\u0111\t\r\2\2\u0111H\3\2\2\2\u0112\u0113\t\16\2\2\u0113"+
		"J\3\2\2\2\u0114\u0115\t\17\2\2\u0115L\3\2\2\2\u0116\u0117\t\20\2\2\u0117"+
		"N\3\2\2\2\u0118\u0119\t\21\2\2\u0119P\3\2\2\2\u011a\u011b\t\22\2\2\u011b"+
		"R\3\2\2\2\u011c\u011d\t\23\2\2\u011dT\3\2\2\2\u011e\u011f\t\24\2\2\u011f"+
		"V\3\2\2\2\u0120\u0121\t\25\2\2\u0121X\3\2\2\2\u0122\u0123\t\26\2\2\u0123"+
		"Z\3\2\2\2\u0124\u0125\t\27\2\2\u0125\\\3\2\2\2\u0126\u0127\t\30\2\2\u0127"+
		"^\3\2\2\2\u0128\u0129\t\31\2\2\u0129`\3\2\2\2\u012a\u012b\t\32\2\2\u012b"+
		"b\3\2\2\2\u012c\u012d\t\33\2\2\u012dd\3\2\2\2\u012e\u012f\t\34\2\2\u012f"+
		"f\3\2\2\2\u0130\u0131\t\35\2\2\u0131h\3\2\2\2\u0132\u0133\t\36\2\2\u0133"+
		"j\3\2\2\2\u0134\u0135\t\37\2\2\u0135l\3\2\2\2\u0136\u0138\t \2\2\u0137"+
		"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2"+
		"\2\2\u013a\u013b\3\2\2\2\u013b\u013c\b\67\2\2\u013cn\3\2\2\2\22\2z\u0080"+
		"\u0083\u0087\u008c\u008e\u0094\u0098\u009d\u009f\u00a1\u00a7\u00a9\u00fe"+
		"\u0139\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}