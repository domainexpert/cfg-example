package org.domainexpert;

import soot.PackManager;
import soot.Transform;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Syntax: java Main <classfile> [soot options]");
			System.exit(0);
		}
				
		PackManager.v().getPack("jtp").add(new Transform("jtp.propagator", PathExplorer.v()));
		soot.Main.main(args);		
	}

}
