package com.carpool.util;

public class MathUtil {
	
  public int calculateDriverToken(int costFactor,int numOfParticipants){
		
		return (costFactor*(numOfParticipants-1))/numOfParticipants;
	}
	
	public int calculatePassengerToken(int costFactor,int numOfParticipants){
		return -(costFactor/numOfParticipants);
	}
	
	
	public int[] memberCost(int numOfParticipants){
		int[] memberCost=new int[numOfParticipants];
		for(int x=0;x<numOfParticipants;x++){
			
			memberCost[x]=x+1;
		}
		
		return memberCost;
	}
	
	public int getCostFactor(int numOfParticipants){
		int[] memberCost=memberCost(numOfParticipants);
		return lcm(memberCost);
	}
	
	
	private static int gcd(int a, int b)
	{
	    while (b > 0)
	    {
	        int temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static int gcd(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
	    return result;
	}

	
	private static int lcm(int a, int b)
	{
	    return a * (b / gcd(a, b));
	}

	private static int lcm(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}

	

}
