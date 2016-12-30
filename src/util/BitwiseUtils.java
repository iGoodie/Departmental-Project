package util;

/**
 * Utility class for bit manipulation
 * @author Anılcan Metinyurt
 */
public class BitwiseUtils 
{
	public static int mask(int number, int mask) { return number & mask; }
	public static int setBit(int number, int index){ return number | (1 << index); }
	public static int clearBit(int number, int index) { return number & ~(1 << index); }
	public static int toggleBit(int number, int index){ return number ^ (1 << index); }
}
