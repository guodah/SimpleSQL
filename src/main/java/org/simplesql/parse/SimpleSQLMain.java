package org.simplesql.parse;
import java.io.IOException;


import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class SimpleSQLMain {
	public static void main(String args[]) throws IOException{
//		CharStream input = CharStreams.fromFileName("test1.sql");
		CharStream input = CharStreams.fromFileName("sql/test_agg_filter_join.sql");
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);
		
		
		
		ParseTree tree = parser.parse();
		
		traverse(tree);
		
		System.out.println(tree.toStringTree(parser));
		
		 JFrame frame = new JFrame("Antlr AST");
	        JPanel panel = new JPanel();
	        TreeViewer viewr = new TreeViewer(Arrays.asList(
	                parser.getRuleNames()),tree);
	        viewr.setScale(1.5);//scale a little
	        panel.add(viewr);
	        frame.add(panel);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(200,200);
	        frame.setVisible(true);
	        
	}

	private static void traverse(ParseTree tree) {
		if(tree==null){
			return;
		}else if(tree.getChildCount()==0){
			System.out.println(tree.getText());
		}
		
		for(int i=0;i<tree.getChildCount();i++){
			traverse(tree.getChild(i));
		}
	}
}
