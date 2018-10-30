package com.herobone.heron;

import com.herobone.heron.gui.HeronApp;

public class GuiAlgorithm {

	public static double repetitions(double in, int rep) {
		double a = in;
		double b = in / a;
		
		AdvancedStringBuilder.append(HeronApp.strBui,"A = " + in);
		AdvancedStringBuilder.append(HeronApp.strBui,"a = " + a);
		AdvancedStringBuilder.append(HeronApp.strBui,"b = " + b + "\n");
		
		for (int i = 0; i < rep; i++) {
			AdvancedStringBuilder.append(HeronApp.strBui,"Loop " + (i + 1));
			
			a = middle(a,b);
			b = otherStep(in, a);
			
			AdvancedStringBuilder.append(HeronApp.strBui,"a = " + a);
			AdvancedStringBuilder.append(HeronApp.strBui,"b = " + b + "\n");
		}
		
		return middle(a ,b);
	}
	
	private static double middle(double a, double b) {
		return (a+b) / 2;
	}
	
	private static double otherStep(double in, double a) {
		return in / a;
	}
	
	public static double repetitionsNoOut(double in, int rep) {
		double a = in;
		double b = in / a;

		for (int i = 0; i < rep; i++) {
			a = middle(a,b);
			b = otherStep(in, a);
		}
		
		return middle(a ,b);
	}
}
