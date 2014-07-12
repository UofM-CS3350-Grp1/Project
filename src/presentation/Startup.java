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
		//here as a target for .act scripts
		new SwitchScreen();
	}
}
