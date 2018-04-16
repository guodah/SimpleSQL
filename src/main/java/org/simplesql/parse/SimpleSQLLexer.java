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
		EQ=8, NEQ=9, LTEQ=10, LT=11, GTEQ=12, GT=13, WILDCARD=14, ON=15, NATURAL=16, 
		INNER=17, JOIN=18, SUM=19, AVERAGE=20, COUNT=21, IS=22, SELECT=23, FROM=24, 
		WHERE=25, AND=26, OR=27, NULL=28, GROUP=29, BY=30, ANY_NAME=31, WS=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "NUMERIC_LITERAL", "STRING_LITERAL", 
		"EQ", "NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "ON", "NATURAL", 
		"INNER", "JOIN", "SUM", "AVERAGE", "COUNT", "IS", "SELECT", "FROM", "WHERE", 
		"AND", "OR", "NULL", "GROUP", "BY", "ANY_NAME", "DIGIT", "A", "B", "C", 
		"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
		"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'.'", null, null, "'='", "'<>'", "'<='", 
		"'<'", "'>='", "'>'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", 
		"EQ", "NEQ", "LTEQ", "LT", "GTEQ", "GT", "WILDCARD", "ON", "NATURAL", 
		"INNER", "JOIN", "SUM", "AVERAGE", "COUNT", "IS", "SELECT", "FROM", "WHERE", 
		"AND", "OR", "NULL", "GROUP", "BY", "ANY_NAME", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u015f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\6\7\u0085\n\7\r\7\16\7\u0086\3"+
		"\7\3\7\7\7\u008b\n\7\f\7\16\7\u008e\13\7\5\7\u0090\n\7\3\7\3\7\5\7\u0094"+
		"\n\7\3\7\6\7\u0097\n\7\r\7\16\7\u0098\5\7\u009b\n\7\3\7\3\7\6\7\u009f"+
		"\n\7\r\7\16\7\u00a0\3\7\3\7\5\7\u00a5\n\7\3\7\6\7\u00a8\n\7\r\7\16\7\u00a9"+
		"\5\7\u00ac\n\7\5\7\u00ae\n\7\3\b\3\b\3\b\3\b\7\b\u00b4\n\b\f\b\16\b\u00b7"+
		"\13\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3 \6 \u011f\n \r \16 \u0120\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60"+
		"\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67"+
		"\38\38\39\39\3:\3:\3;\3;\3<\6<\u015a\n<\r<\16<\u015b\3<\3<\2\2=\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2"+
		"o\2q\2s\2u\2w\"\3\2!\4\2--//\3\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2DDdd\4"+
		"\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMm"+
		"m\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2"+
		"VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\f\17\17"+
		"\"\"\2\u0152\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2w\3\2\2\2\3y\3\2\2\2\5{\3\2\2"+
		"\2\7}\3\2\2\2\t\177\3\2\2\2\13\u0081\3\2\2\2\r\u00ad\3\2\2\2\17\u00af"+
		"\3\2\2\2\21\u00ba\3\2\2\2\23\u00bc\3\2\2\2\25\u00bf\3\2\2\2\27\u00c2\3"+
		"\2\2\2\31\u00c4\3\2\2\2\33\u00c7\3\2\2\2\35\u00c9\3\2\2\2\37\u00cb\3\2"+
		"\2\2!\u00ce\3\2\2\2#\u00d6\3\2\2\2%\u00dc\3\2\2\2\'\u00e1\3\2\2\2)\u00e5"+
		"\3\2\2\2+\u00ed\3\2\2\2-\u00f3\3\2\2\2/\u00f6\3\2\2\2\61\u00fd\3\2\2\2"+
		"\63\u0102\3\2\2\2\65\u0108\3\2\2\2\67\u010c\3\2\2\29\u010f\3\2\2\2;\u0114"+
		"\3\2\2\2=\u011a\3\2\2\2?\u011e\3\2\2\2A\u0122\3\2\2\2C\u0124\3\2\2\2E"+
		"\u0126\3\2\2\2G\u0128\3\2\2\2I\u012a\3\2\2\2K\u012c\3\2\2\2M\u012e\3\2"+
		"\2\2O\u0130\3\2\2\2Q\u0132\3\2\2\2S\u0134\3\2\2\2U\u0136\3\2\2\2W\u0138"+
		"\3\2\2\2Y\u013a\3\2\2\2[\u013c\3\2\2\2]\u013e\3\2\2\2_\u0140\3\2\2\2a"+
		"\u0142\3\2\2\2c\u0144\3\2\2\2e\u0146\3\2\2\2g\u0148\3\2\2\2i\u014a\3\2"+
		"\2\2k\u014c\3\2\2\2m\u014e\3\2\2\2o\u0150\3\2\2\2q\u0152\3\2\2\2s\u0154"+
		"\3\2\2\2u\u0156\3\2\2\2w\u0159\3\2\2\2yz\7=\2\2z\4\3\2\2\2{|\7.\2\2|\6"+
		"\3\2\2\2}~\7*\2\2~\b\3\2\2\2\177\u0080\7+\2\2\u0080\n\3\2\2\2\u0081\u0082"+
		"\7\60\2\2\u0082\f\3\2\2\2\u0083\u0085\5A!\2\u0084\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008f\3\2"+
		"\2\2\u0088\u008c\7\60\2\2\u0089\u008b\5A!\2\u008a\u0089\3\2\2\2\u008b"+
		"\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0090\3\2"+
		"\2\2\u008e\u008c\3\2\2\2\u008f\u0088\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u009a\3\2\2\2\u0091\u0093\5K&\2\u0092\u0094\t\2\2\2\u0093\u0092\3\2\2"+
		"\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0097\5A!\2\u0096\u0095"+
		"\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009b\3\2\2\2\u009a\u0091\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00ae\3\2"+
		"\2\2\u009c\u009e\7\60\2\2\u009d\u009f\5A!\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00ab\3\2"+
		"\2\2\u00a2\u00a4\5K&\2\u00a3\u00a5\t\2\2\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5"+
		"\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a8\5A!\2\u00a7\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2"+
		"\2\2\u00ab\u00a2\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u0084\3\2\2\2\u00ad\u009c\3\2\2\2\u00ae\16\3\2\2\2\u00af\u00b5\7)\2\2"+
		"\u00b0\u00b4\n\3\2\2\u00b1\u00b2\7)\2\2\u00b2\u00b4\7)\2\2\u00b3\u00b0"+
		"\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7)"+
		"\2\2\u00b9\20\3\2\2\2\u00ba\u00bb\7?\2\2\u00bb\22\3\2\2\2\u00bc\u00bd"+
		"\7>\2\2\u00bd\u00be\7@\2\2\u00be\24\3\2\2\2\u00bf\u00c0\7>\2\2\u00c0\u00c1"+
		"\7?\2\2\u00c1\26\3\2\2\2\u00c2\u00c3\7>\2\2\u00c3\30\3\2\2\2\u00c4\u00c5"+
		"\7@\2\2\u00c5\u00c6\7?\2\2\u00c6\32\3\2\2\2\u00c7\u00c8\7@\2\2\u00c8\34"+
		"\3\2\2\2\u00c9\u00ca\7,\2\2\u00ca\36\3\2\2\2\u00cb\u00cc\5_\60\2\u00cc"+
		"\u00cd\5]/\2\u00cd \3\2\2\2\u00ce\u00cf\5]/\2\u00cf\u00d0\5C\"\2\u00d0"+
		"\u00d1\5i\65\2\u00d1\u00d2\5k\66\2\u00d2\u00d3\5e\63\2\u00d3\u00d4\5C"+
		"\"\2\u00d4\u00d5\5Y-\2\u00d5\"\3\2\2\2\u00d6\u00d7\5S*\2\u00d7\u00d8\5"+
		"]/\2\u00d8\u00d9\5]/\2\u00d9\u00da\5K&\2\u00da\u00db\5e\63\2\u00db$\3"+
		"\2\2\2\u00dc\u00dd\5U+\2\u00dd\u00de\5_\60\2\u00de\u00df\5S*\2\u00df\u00e0"+
		"\5]/\2\u00e0&\3\2\2\2\u00e1\u00e2\5g\64\2\u00e2\u00e3\5k\66\2\u00e3\u00e4"+
		"\5[.\2\u00e4(\3\2\2\2\u00e5\u00e6\5C\"\2\u00e6\u00e7\5m\67\2\u00e7\u00e8"+
		"\5K&\2\u00e8\u00e9\5e\63\2\u00e9\u00ea\5C\"\2\u00ea\u00eb\5O(\2\u00eb"+
		"\u00ec\5K&\2\u00ec*\3\2\2\2\u00ed\u00ee\5G$\2\u00ee\u00ef\5_\60\2\u00ef"+
		"\u00f0\5k\66\2\u00f0\u00f1\5]/\2\u00f1\u00f2\5i\65\2\u00f2,\3\2\2\2\u00f3"+
		"\u00f4\5S*\2\u00f4\u00f5\5g\64\2\u00f5.\3\2\2\2\u00f6\u00f7\5g\64\2\u00f7"+
		"\u00f8\5K&\2\u00f8\u00f9\5Y-\2\u00f9\u00fa\5K&\2\u00fa\u00fb\5G$\2\u00fb"+
		"\u00fc\5i\65\2\u00fc\60\3\2\2\2\u00fd\u00fe\5M\'\2\u00fe\u00ff\5e\63\2"+
		"\u00ff\u0100\5_\60\2\u0100\u0101\5[.\2\u0101\62\3\2\2\2\u0102\u0103\5"+
		"o8\2\u0103\u0104\5Q)\2\u0104\u0105\5K&\2\u0105\u0106\5e\63\2\u0106\u0107"+
		"\5K&\2\u0107\64\3\2\2\2\u0108\u0109\5C\"\2\u0109\u010a\5]/\2\u010a\u010b"+
		"\5I%\2\u010b\66\3\2\2\2\u010c\u010d\5_\60\2\u010d\u010e\5e\63\2\u010e"+
		"8\3\2\2\2\u010f\u0110\5]/\2\u0110\u0111\5k\66\2\u0111\u0112\5Y-\2\u0112"+
		"\u0113\5Y-\2\u0113:\3\2\2\2\u0114\u0115\5O(\2\u0115\u0116\5e\63\2\u0116"+
		"\u0117\5_\60\2\u0117\u0118\5k\66\2\u0118\u0119\5a\61\2\u0119<\3\2\2\2"+
		"\u011a\u011b\5E#\2\u011b\u011c\5s:\2\u011c>\3\2\2\2\u011d\u011f\t\4\2"+
		"\2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121@\3\2\2\2\u0122\u0123\t\5\2\2\u0123B\3\2\2\2\u0124\u0125"+
		"\t\6\2\2\u0125D\3\2\2\2\u0126\u0127\t\7\2\2\u0127F\3\2\2\2\u0128\u0129"+
		"\t\b\2\2\u0129H\3\2\2\2\u012a\u012b\t\t\2\2\u012bJ\3\2\2\2\u012c\u012d"+
		"\t\n\2\2\u012dL\3\2\2\2\u012e\u012f\t\13\2\2\u012fN\3\2\2\2\u0130\u0131"+
		"\t\f\2\2\u0131P\3\2\2\2\u0132\u0133\t\r\2\2\u0133R\3\2\2\2\u0134\u0135"+
		"\t\16\2\2\u0135T\3\2\2\2\u0136\u0137\t\17\2\2\u0137V\3\2\2\2\u0138\u0139"+
		"\t\20\2\2\u0139X\3\2\2\2\u013a\u013b\t\21\2\2\u013bZ\3\2\2\2\u013c\u013d"+
		"\t\22\2\2\u013d\\\3\2\2\2\u013e\u013f\t\23\2\2\u013f^\3\2\2\2\u0140\u0141"+
		"\t\24\2\2\u0141`\3\2\2\2\u0142\u0143\t\25\2\2\u0143b\3\2\2\2\u0144\u0145"+
		"\t\26\2\2\u0145d\3\2\2\2\u0146\u0147\t\27\2\2\u0147f\3\2\2\2\u0148\u0149"+
		"\t\30\2\2\u0149h\3\2\2\2\u014a\u014b\t\31\2\2\u014bj\3\2\2\2\u014c\u014d"+
		"\t\32\2\2\u014dl\3\2\2\2\u014e\u014f\t\33\2\2\u014fn\3\2\2\2\u0150\u0151"+
		"\t\34\2\2\u0151p\3\2\2\2\u0152\u0153\t\35\2\2\u0153r\3\2\2\2\u0154\u0155"+
		"\t\36\2\2\u0155t\3\2\2\2\u0156\u0157\t\37\2\2\u0157v\3\2\2\2\u0158\u015a"+
		"\t \2\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u0159\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\b<\2\2\u015ex\3\2\2\2\22"+
		"\2\u0086\u008c\u008f\u0093\u0098\u009a\u00a0\u00a4\u00a9\u00ab\u00ad\u00b3"+
		"\u00b5\u0120\u015b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}