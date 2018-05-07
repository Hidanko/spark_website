package com.br.site;

import static spark.Spark.*;
public class App 
{
    public static void main( String[] args )
    {
        ipAddress("192.168.25.15");
        
        get("/teste", (req, res) -> "HELLO");
    }
}
