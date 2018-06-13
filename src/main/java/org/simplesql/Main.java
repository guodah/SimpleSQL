package org.simplesql;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.simplesql.iterators.Iterator;
import org.simplesql.iterators.Row;
import org.simplesql.relational_algebra.Expression;

public class Main {
	private static String SCHEMA = "schema/test.json"; 
	private static Scanner scanner;
	private static boolean echo = false;
	public static void main(String args[]) 
			throws MalformedURLException, IOException{
		processArgs(args);
		SimpleSQL.setSchema(new File(SCHEMA).toURI().toURL());
		SimpleSQL.printSchema();
		scanner = new Scanner(System.in);
		StringBuilder sql = new StringBuilder();
//		doMenu();
		do{
			String line = doInput();
			if(line.equals("$")){
				execute(sql.toString());
				sql = new StringBuilder();
				System.out.println();
//				doMenu();
			}else if(line.equals("#")){
				break;
			}else{
				if(echo) System.out.println(line);
				sql.append(line);
				sql.append(' ');
			}
		}while(true);
		scanner.close();
	}

	private static void processArgs(String[] args) {
		for(int i=0;i<args.length;) {
			if(args[i].equals("-e")) {
				echo = true;
				i++;
			}else if(args[i].equals("-s")) {
				SCHEMA = args[i+1];
				i+=2;
			}
		}
	}

	private static void execute(String sql) throws IOException {
		Iterator<Row> iterator = SimpleSQL.executeQuery(sql);
		System.out.println();
		boolean headerPrinted = false;
		String headLine = "";
		int rowCount = 0;
		while(iterator.hasNext()){
			Row row = iterator.next();
			List<Expression<?>> headers = row.getOrderedColumns();
			if(!headerPrinted){
				headLine = findHeadLine(row.getOrderedColumns());
				System.out.println(SimpleSQL.bar(headLine.length(),false));
				System.out.println(headLine);
				headerPrinted = true;
			}
			
			System.out.println(SimpleSQL.bar(headLine.length(),true));
			System.out.print("|");
			for(Expression<?> header:headers){
				System.out.printf("%"+(header.toString().length()+1)+"s", row.get(header.toString())+"|");		
			}
			System.out.println();
			rowCount++;
		}			
		System.out.println(SimpleSQL.bar(headLine.length(),false));
		System.out.printf("%d row(s) returned\n", rowCount);
	}

	private static String findHeadLine(List<Expression<?>> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		for(Expression<?> header:headers){
			sb.append(String.format("%s|", header.toString()));
		}
		return sb.toString();
	}
	
	
	private static String doInput() {
		return scanner.nextLine();
	}

	private static void doMenu() {
		System.out.println("\nPlease enter an SQL statement or enter $ to submit or # to terminate:\n" );
	}
}
