package com.elevatelabs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Text_Based_Notes {
	private static final String FILE_NAME = "notes.txt";

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n-----Notes Manager-----");
			System.out.println("1. View Notes.");
			System.out.println("2. Add Notes.");
			System.out.println("3. Delete All Notes");
			System.out.println("4. Exit");
			System.out.print("Choose an option: ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {

			case 1:
				viewNotes();
				break;

			case 2:
				addNotes(scanner);
				break;

			case 3:
				deleteAllNotes();
				break;

			case 4:
				System.out.println("Exiting Notes Manager. Goodbye!");
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 4);

		scanner.close();
	}

	private static void viewNotes() {
		try (FileReader fr = new FileReader(FILE_NAME); BufferedReader br = new BufferedReader(fr)) {
			String line;
			System.out.println("\n--- Your Notes ---");
			while ((line = br.readLine()) != null) {
				System.out.println("- " + line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No notes found.");
		} catch (IOException e) {
			System.out.println("Error reading notes: " + e.getMessage());
		}
	}

	private static void addNotes(Scanner scanner) {
		System.out.print("Enter your note: ");
		String note = scanner.nextLine();

		try (FileWriter fw = new FileWriter(FILE_NAME, true); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(note);
			bw.newLine();
			System.out.println("Note added successfully.");
		} catch (IOException e) {
			System.out.println("Error writing note: " + e.getMessage());
		}
	}

	private static void deleteAllNotes() {
		try (FileWriter fw = new FileWriter(FILE_NAME)) {

			System.out.println("All notes deleted.");
		} catch (IOException e) {
			System.out.println("Error clearing notes: " + e.getMessage());
		}
	}
}
