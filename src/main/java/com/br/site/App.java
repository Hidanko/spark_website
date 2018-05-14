package com.br.site;

// Importação estática para abstrair a instanciação de classes
import static spark.Spark.get;
import static spark.Spark.ipAddress;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.swing.JOptionPane;

import spark.Request;
import spark.Spark;

public class App {
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		// É possivel utilizar localhost ou 127.0.0.1 para somente a maquina local se conectar
		// 192.168.xxx.xxx para outras maquinas da rede poderem se conectar
		// ou utilizar o IP "publico" para poder conectar via rede
		String ip = JOptionPane.showInputDialog(null, "Digite o IP de sua máquina", "127.0.0.1");
		
		// Permite que seja feito solicitações de paginas HTML dentro da pasta ./src/main/resources/public
		Spark.staticFiles.location("/public");
		
		// define o IP recebido como porta para requisições
		ipAddress(ip);
		
		// Define os GET/POST com o endereço da requisição e resposta
		get("/", (req, res) -> "/teste, /atributos, /speech.html, /texto.txt");
		get("/teste", (req, res) -> "HELLO");
		get("/atributos", (req, res) -> atributos(req));
		
		// Abre a pagina inicial utilizando o navegador padrão
		Desktop.getDesktop().browse(new URI("http://" + ip + ":4567"));

	}

	
	// Metodo "/atributos" para apresentar a leitura de parametros da url
	private static String atributos(Request req) {

		Set<String> params = req.queryParams();

		if (params.size() == 0) {
			return "Adicione parametros para o url";
		}

		StringBuilder sb = new StringBuilder();

		for (String string : params) {
			sb.append("parametro: " + string + "\t\tatributo: " + req.queryParams(string));
			sb.append("\t\t");
		}

		return sb.toString();
	}

}
