package com.br.site;

import static spark.Spark.get;
import static spark.Spark.ipAddress;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.swing.JOptionPane;

import spark.Request;
import spark.Response;
import spark.Spark;

public class App {
	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog(null, "Digite o IP de sua máquina", "127.0.0.1");
		Spark.staticFiles.location("/public");
		// ipAddress("192.168.25.15");
		ipAddress(ip);
		get("/teste", (req, res) -> "HELLO");
		get("/atributos", (req, res) -> atributos(req));
		try {
			Desktop.getDesktop().browse(new URI("http://" + ip + ":4567/hello.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String atributos(Request req) {

		Set<String> params = req.queryParams();

		StringBuilder sb = new StringBuilder();

		for (String string : params) {
			sb.append("PARAMETRO: " + string + "\t\tATRIBUTO: " + req.queryParams(string));
			sb.append("\t\t");
		}

		return sb.toString();
	}

}
