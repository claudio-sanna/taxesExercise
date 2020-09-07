package it.sanna.taxexercise;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {

	/**
	 * Round input to the nearest 0.05
	 * @param input - Numeric input to be rounded
	 * @return The input variable rounded to the nearest 0.05 with RoundingMode.HALF_UP rule
	 */
	public static BigDecimal roundToZeroFive(BigDecimal input) {
		BigDecimal factor = BigDecimal.valueOf(0.05);
		return input.divide(factor).setScale(0, RoundingMode.HALF_UP).multiply(factor);
	}


}
