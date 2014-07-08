package presentation;

import presentation.SwitchScreen;

public class Startup 
{
	public static void main(String[] args)
	{
		jumpStart();
	}
	
	public static void jumpStart()
	{
		//only here as a target for .act scripts
		new SwitchScreen();
		System.out.println("Done.");
	}
}
