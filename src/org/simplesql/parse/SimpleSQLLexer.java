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
		T__0=1, T__1=2, NUMERIC_LITERAL=3, STRING_LITERAL=4, EQ=5, NEQ=6, LTEQ=7, 
		LT=8, GTEQ=9, GT=10, IS=11, SELECT=12, FROM=13, WHERE=14, AND=15, OR=16, 
		NULL=17, ANY_NAME=18, WS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", "NEQ", "LTEQ", 
		"LT", "GTEQ", "GT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", 
		"ANY_NAME", "DIGIT", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
		"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", 
		"Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", null, null, "'='", "'<>'", "'<='", "'<'", "'>='", 
		"'>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "NUMERIC_LITERAL", "STRING_LITERAL", "EQ", "NEQ", "LTEQ", 
		"LT", "GTEQ", "GT", "IS", "SELECT", "FROM", "WHERE", "AND", "OR", "NULL", 
		"ANY_NAME", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u010c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\3\3\3\3\4\6\4e\n\4\r\4\16\4f\3\4\3"+
		"\4\7\4k\n\4\f\4\16\4n\13\4\5\4p\n\4\3\4\3\4\5\4t\n\4\3\4\6\4w\n\4\r\4"+
		"\16\4x\5\4{\n\4\3\4\3\4\6\4\177\n\4\r\4\16\4\u0080\3\4\3\4\5\4\u0085\n"+
		"\4\3\4\6\4\u0088\n\4\r\4\16\4\u0089\5\4\u008c\n\4\5\4\u008e\n\4\3\5\3"+
		"\5\3\5\3\5\7\5\u0094\n\5\f\5\16\5\u0097\13\5\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\6\23"+
		"\u00cc\n\23\r\23\16\23\u00cd\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)"+
		"\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\6/\u0107\n/\r/\16/\u0108\3/\3/\2"+
		"\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\2)\2+\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2"+
		"A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\25\3\2!\4\2--//\3\2))\4\2"+
		"C\\c|\3\2\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii"+
		"\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2R"+
		"Rrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4"+
		"\2[[{{\4\2\\\\||\5\2\13\f\17\17\"\"\2\u00ff\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2]\3\2\2\2"+
		"\3_\3\2\2\2\5a\3\2\2\2\7\u008d\3\2\2\2\t\u008f\3\2\2\2\13\u009a\3\2\2"+
		"\2\r\u009c\3\2\2\2\17\u009f\3\2\2\2\21\u00a2\3\2\2\2\23\u00a4\3\2\2\2"+
		"\25\u00a7\3\2\2\2\27\u00a9\3\2\2\2\31\u00ac\3\2\2\2\33\u00b3\3\2\2\2\35"+
		"\u00b8\3\2\2\2\37\u00be\3\2\2\2!\u00c2\3\2\2\2#\u00c5\3\2\2\2%\u00cb\3"+
		"\2\2\2\'\u00cf\3\2\2\2)\u00d1\3\2\2\2+\u00d3\3\2\2\2-\u00d5\3\2\2\2/\u00d7"+
		"\3\2\2\2\61\u00d9\3\2\2\2\63\u00db\3\2\2\2\65\u00dd\3\2\2\2\67\u00df\3"+
		"\2\2\29\u00e1\3\2\2\2;\u00e3\3\2\2\2=\u00e5\3\2\2\2?\u00e7\3\2\2\2A\u00e9"+
		"\3\2\2\2C\u00eb\3\2\2\2E\u00ed\3\2\2\2G\u00ef\3\2\2\2I\u00f1\3\2\2\2K"+
		"\u00f3\3\2\2\2M\u00f5\3\2\2\2O\u00f7\3\2\2\2Q\u00f9\3\2\2\2S\u00fb\3\2"+
		"\2\2U\u00fd\3\2\2\2W\u00ff\3\2\2\2Y\u0101\3\2\2\2[\u0103\3\2\2\2]\u0106"+
		"\3\2\2\2_`\7=\2\2`\4\3\2\2\2ab\7.\2\2b\6\3\2\2\2ce\5\'\24\2dc\3\2\2\2"+
		"ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2go\3\2\2\2hl\7\60\2\2ik\5\'\24\2ji\3\2\2"+
		"\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2oh\3\2\2\2op\3\2\2"+
		"\2pz\3\2\2\2qs\5\61\31\2rt\t\2\2\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2uw\5\'"+
		"\24\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zq\3\2\2\2z{\3"+
		"\2\2\2{\u008e\3\2\2\2|~\7\60\2\2}\177\5\'\24\2~}\3\2\2\2\177\u0080\3\2"+
		"\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u008b\3\2\2\2\u0082\u0084"+
		"\5\61\31\2\u0083\u0085\t\2\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2"+
		"\u0085\u0087\3\2\2\2\u0086\u0088\5\'\24\2\u0087\u0086\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0082\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008dd\3\2\2\2"+
		"\u008d|\3\2\2\2\u008e\b\3\2\2\2\u008f\u0095\7)\2\2\u0090\u0094\n\3\2\2"+
		"\u0091\u0092\7)\2\2\u0092\u0094\7)\2\2\u0093\u0090\3\2\2\2\u0093\u0091"+
		"\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\7)\2\2\u0099\n\3\2\2\2"+
		"\u009a\u009b\7?\2\2\u009b\f\3\2\2\2\u009c\u009d\7>\2\2\u009d\u009e\7@"+
		"\2\2\u009e\16\3\2\2\2\u009f\u00a0\7>\2\2\u00a0\u00a1\7?\2\2\u00a1\20\3"+
		"\2\2\2\u00a2\u00a3\7>\2\2\u00a3\22\3\2\2\2\u00a4\u00a5\7@\2\2\u00a5\u00a6"+
		"\7?\2\2\u00a6\24\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8\26\3\2\2\2\u00a9\u00aa"+
		"\59\35\2\u00aa\u00ab\5M\'\2\u00ab\30\3\2\2\2\u00ac\u00ad\5M\'\2\u00ad"+
		"\u00ae\5\61\31\2\u00ae\u00af\5? \2\u00af\u00b0\5\61\31\2\u00b0\u00b1\5"+
		"-\27\2\u00b1\u00b2\5O(\2\u00b2\32\3\2\2\2\u00b3\u00b4\5\63\32\2\u00b4"+
		"\u00b5\5K&\2\u00b5\u00b6\5E#\2\u00b6\u00b7\5A!\2\u00b7\34\3\2\2\2\u00b8"+
		"\u00b9\5U+\2\u00b9\u00ba\5\67\34\2\u00ba\u00bb\5\61\31\2\u00bb\u00bc\5"+
		"K&\2\u00bc\u00bd\5\61\31\2\u00bd\36\3\2\2\2\u00be\u00bf\5)\25\2\u00bf"+
		"\u00c0\5C\"\2\u00c0\u00c1\5/\30\2\u00c1 \3\2\2\2\u00c2\u00c3\5E#\2\u00c3"+
		"\u00c4\5K&\2\u00c4\"\3\2\2\2\u00c5\u00c6\5C\"\2\u00c6\u00c7\5Q)\2\u00c7"+
		"\u00c8\5? \2\u00c8\u00c9\5? \2\u00c9$\3\2\2\2\u00ca\u00cc\t\4\2\2\u00cb"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce&\3\2\2\2\u00cf\u00d0\t\5\2\2\u00d0(\3\2\2\2\u00d1\u00d2\t\6"+
		"\2\2\u00d2*\3\2\2\2\u00d3\u00d4\t\7\2\2\u00d4,\3\2\2\2\u00d5\u00d6\t\b"+
		"\2\2\u00d6.\3\2\2\2\u00d7\u00d8\t\t\2\2\u00d8\60\3\2\2\2\u00d9\u00da\t"+
		"\n\2\2\u00da\62\3\2\2\2\u00db\u00dc\t\13\2\2\u00dc\64\3\2\2\2\u00dd\u00de"+
		"\t\f\2\2\u00de\66\3\2\2\2\u00df\u00e0\t\r\2\2\u00e08\3\2\2\2\u00e1\u00e2"+
		"\t\16\2\2\u00e2:\3\2\2\2\u00e3\u00e4\t\17\2\2\u00e4<\3\2\2\2\u00e5\u00e6"+
		"\t\20\2\2\u00e6>\3\2\2\2\u00e7\u00e8\t\21\2\2\u00e8@\3\2\2\2\u00e9\u00ea"+
		"\t\22\2\2\u00eaB\3\2\2\2\u00eb\u00ec\t\23\2\2\u00ecD\3\2\2\2\u00ed\u00ee"+
		"\t\24\2\2\u00eeF\3\2\2\2\u00ef\u00f0\t\25\2\2\u00f0H\3\2\2\2\u00f1\u00f2"+
		"\t\26\2\2\u00f2J\3\2\2\2\u00f3\u00f4\t\27\2\2\u00f4L\3\2\2\2\u00f5\u00f6"+
		"\t\30\2\2\u00f6N\3\2\2\2\u00f7\u00f8\t\31\2\2\u00f8P\3\2\2\2\u00f9\u00fa"+
		"\t\32\2\2\u00faR\3\2\2\2\u00fb\u00fc\t\33\2\2\u00fcT\3\2\2\2\u00fd\u00fe"+
		"\t\34\2\2\u00feV\3\2\2\2\u00ff\u0100\t\35\2\2\u0100X\3\2\2\2\u0101\u0102"+
		"\t\36\2\2\u0102Z\3\2\2\2\u0103\u0104\t\37\2\2\u0104\\\3\2\2\2\u0105\u0107"+
		"\t \2\2\u0106\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\b/\2\2\u010b^\3\2\2\2\22"+
		"\2flosxz\u0080\u0084\u0089\u008b\u008d\u0093\u0095\u00cd\u0108\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}