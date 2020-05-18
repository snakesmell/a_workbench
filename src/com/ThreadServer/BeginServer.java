package com.ThreadServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BeginServer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Thread thread=new Thread(new DemonServer(arg0));
		thread.setDaemon(true);
		thread.start();
	}

}
