package org.domainexpert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.Unit;
import soot.jimple.JimpleBody;
import soot.toolkits.graph.CompleteUnitGraph;

/**
 * This is a simple CFG path explorer
 *
 */
public class PathExplorer extends BodyTransformer {

	public class Pair {
		private Unit unit;
		private int level;
		
		public Pair(Unit u, int i) {
			unit = u;
			level = i;
		}

		public Unit getUnit() {
			return unit;
		}
		
		public int getLevel() {
			return level;
		}
		
		@Override
		public String toString() {
			return "LEVEL " + Integer.valueOf(level) + ": " + unit.toString();
		}
	}
	
	private static PathExplorer instance = new PathExplorer();
	private PathExplorer() {}

	public static PathExplorer v() {
		return instance;
	}

	@Override
	protected void internalTransform(Body b, String phaseName,
			Map<String, String> options) {
		JimpleBody body = (JimpleBody) b;
		
		G.v().out.println("METHOD: " + body.getMethod().getDeclaration());
		
		CompleteUnitGraph stmtGraph = new CompleteUnitGraph(body);

		Stack<Unit> entryPoints = new Stack<Unit>();
		entryPoints.addAll(stmtGraph.getHeads());

		while (!entryPoints.empty()) {
			Stack<Pair> workList = new Stack<Pair>();
			
			G.v().out.println("NEW ENTRY POINT");
			Unit entry = entryPoints.pop();
			
			workList.add(new Pair(entry, 1));
			Set<Unit> traversed = new HashSet<Unit>();
			
			while (!workList.isEmpty()) {
				Pair pair = workList.pop();
				Unit stmt = pair.getUnit();
				int level = pair.getLevel();
				
				if (traversed.contains(stmt)) {
					G.v().out.println("BACKEDGE\n");
					continue;
				}
				
				traversed.add(stmt);
				G.v().out.println(pair.toString());

				List<Unit> successors = stmtGraph.getSuccsOf(stmt);
				
				if (successors.isEmpty()) {
					G.v().out.println("LAST STATEMENT\n");
				} else {
					List<Pair> successorPairs = new ArrayList<Pair>();
					for (Iterator<Unit> i = successors.iterator(); i.hasNext();) {
						successorPairs.add(new Pair(i.next(), level + 1));
					}
					workList.addAll(successorPairs);
				}
			}
		}
				
	}

}
