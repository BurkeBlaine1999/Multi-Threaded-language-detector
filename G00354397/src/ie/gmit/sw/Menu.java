package ie.gmit.sw;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Blaine Burke
 * @version 1.0
 */
public class Menu {

	private Scanner s = new Scanner(System.in);
	private String dataLocation;
	private String sentence;
	private String queryFile;
	private int kmer;
	private int Sentinel = 999;
	private int choice;

	public void menu() {

	}

	public void start() throws InterruptedException, IOException {

		displayHeader();

	}

	private void displayHeader() throws InterruptedException, IOException {
		while (Sentinel == 999) {
			System.out.println("* =============================== *");
			System.out.println("*            G00354397            *");
			System.out.println("*        Language detector        *");
			System.out.println("*          Blaine Burke           *");
			System.out.println("* =============================== *\n");

			System.out.println("\n=+=+=+=+=+=+=+ MENU =+=+=+=+=+=+=+=");
			System.out.println("Enter 1 for Language detector \n Enter 2 to exit!");
			choice = s.nextInt();
			if (choice == 1) {
				Handler();
			} else if (choice == 2) {
				Sentinel = 0;
				System.exit(0);
			} else {
				System.out.println("Error , Please enter one of the above choices!");
			}
		}

	}

	private void Handler() throws InterruptedException, IOException {

		System.out.print("Enter WiLI Data Location...");
		dataLocation = s.next();

		System.out.print("Enter Query File Location...");
		queryFile = s.next();

		System.out.print("please enter the number of kmers:");
		kmer = s.nextInt();

		Parser p = new Parser(dataLocation, kmer);

		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		t.start();
		t.join();

		db.resize(300);

		p.analyseQuery(queryFile);

	}

}
