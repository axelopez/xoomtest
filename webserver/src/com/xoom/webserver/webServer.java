package com.xoom.webserver;

/*Java SEBSERVER
 *
 * Autor: Axelopez
 * axelopez@gmail.com
 *
 **/
 
import java.io.*;
import java.net.*;
import java.util.*;
public class webServer {
    
    /*
    Proyect Constants
    */ 
   
	
    final int ERROR = 0;
    final int WARNING = 1;
    final int DEBUG = 2;
	
    private int serverPort = 8080; /*sever PORT*/
    
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    //Debug messages method
    void debug(String mensaje)
	{
		debug(mensaje,DEBUG);
	}	

    void debug(String mensaje, int gravedad)
	{
		System.out.println("Message: " + mensaje);
	}
    
    /*
    Method MAIN
    */
    public static void main(String[] args) {
        // TODO code application logic here 
       
        webServer server = new webServer(args);	
	server.start();
        
    }
    
    // server Constructor with param
	webServer(String[] param)
	{
		proccessParams();	
	}
	
	//params method, example port: 80
	boolean proccessParams()
	{
		return true;	
	}
        
        boolean start()
	{
		debug("Start Server XOOM",DEBUG);
		
		try
		{
		
			
			ServerSocket socket = new ServerSocket(getServerPort());

			debug("waiting for incoming connections..");
			
			while(true)  // Loop for waiting 
			{
				Socket income = socket.accept();                                
				webRequest pClient = new webRequest(income);
				pClient.start();
			}
			
		}
		catch(Exception e)
		{
			debug("Server Error\n" + e.toString());
		}
		
		return true;
	}

    
}
