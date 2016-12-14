package util;

import java.util.Objects;

public class Pair<F, S> implements Cloneable
{
	private F first;
	private S second;
	public F getFirst() { return first; }
	public S getSecond() { return second; }
	
	public Pair(F first, S second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(!(obj instanceof Pair)) return false;
		Pair<?, ?> obj2 = (Pair<?, ?>)obj;
		return Objects.equals(first, obj2.getFirst()) && Objects.equals(second, obj2.getSecond());
	}
	
	@Override
	public int hashCode()
	{
		return (first==null? 0 : first.hashCode()) ^ (second==null? 0 :second.hashCode());
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		return new Pair<F, S>(first, second);
	}
}
