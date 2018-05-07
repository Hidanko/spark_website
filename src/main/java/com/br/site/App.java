package com.br.site;

import static spark.Spark.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JOptionPane;

import spark.Spark;
public class App 
{
    public static void main( String[] args )
    {
    	String ip = JOptionPane.showInputDialog(null, "Digite o IP de sua máquina", "127.0.0.1");
    	Spark.staticFiles.location("/public");
       // ipAddress("192.168.25.15");
    	ipAddress(ip);
        get("/teste", (req, res) -> "HELLO");
        
        try {
			Desktop.getDesktop().browse(new URI("http://"+ip+":4567/hello.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
