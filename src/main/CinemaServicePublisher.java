package main;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

import model.Database;
import service.CinemaService;
import service.ICinemaService;

public class CinemaServicePublisher {
	private static final String URL = "http://192.168.0.105:8080/cinema";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ICinemaService service = new CinemaService();
		Scanner input = new Scanner(System.in);
		Endpoint.publish(URL, service);
		System.out.println("Serwis gotowy.");
		while (true) {
			String command = input.nextLine();
			Database.getInstance().processCommand(command);
		}
	}
}
