package util;

public abstract class MathUtils 
{
	public static float map(float value, float curMin, float curMax, float targetMin, float targetMax)
	{
		if(curMax-curMin == 0) return value;
		if(targetMax-targetMin == 0) return curMin;
		return targetMin + (value-curMin) * (targetMax-targetMin) / (curMax-curMin);
	}
	
	public static float clamp(float value, float min, float max)
	{
		return Math.max(min, Math.max(value, max));
	}
}
