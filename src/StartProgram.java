/**
 * @author jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2023
 * Feb 7, 2023
 */
import java.util.List;
import java.util.Scanner;
import controller.SportsCarHelper;
import model.SportsCar;

public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static SportsCarHelper lih = new SportsCarHelper();

	private static void addACar() {
		System.out.print("What is the make of the sports car? ");
		String make = in.nextLine();
		System.out.print("What is the model of the sports car? ");
		String model = in.nextLine();
		System.out.print("What is the year of the sports car? ");
		int year = in.nextInt();
		in.nextLine();
		SportsCar toAdd = new SportsCar(make, model, year);
		lih.insertCar(toAdd);
	}

	private static void deleteACar() {
		System.out.print("Enter the make of the sports car you would like to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the model of the sports car you would like to delete: ");
		String model = in.nextLine();
		System.out.print("Enter the year of the sports car you would like to delete: ");
		int year = in.nextInt();
		in.nextLine();
		SportsCar toDelete = new SportsCar(make, model, year);
		lih.deleteCar(toDelete);
	}

	private static void editACar() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by make");
		System.out.println("2 : Search by model");
		System.out.println("3 : Search by year");
		int searchBy = in.nextInt();
		in.nextLine();
		List<SportsCar> foundSportsCars = null;
		if (searchBy == 1) {
			System.out.print("Enter the make of the sports car you would like to modify: ");
			String make = in.nextLine();
			foundSportsCars = lih.searchForCarByMake(make);
		}
		else if(searchBy == 2) {
			System.out.print("Enter the model of the sports car you would like to modify: ");
			String model = in.nextLine();
			foundSportsCars = lih.searchForCarByModel(model);
		}
		else {
			System.out.print("Enter the year of the sports car you would like to modify: ");
			int year = in.nextInt();
			in.nextLine();
			foundSportsCars = lih.searchForCarByYear(year);
		}
		if (!foundSportsCars.isEmpty()) {
			System.out.println("Found Results.");
			for (SportsCar result : foundSportsCars) {
				System.out.println(result.getId() + " : " + result.returnCarDetails());
			}
			System.out.print("Which ID would you like to edit: ");
			int idToEdit = in.nextInt();
			in.nextLine();
			SportsCar toEdit = lih.searchForCarById(idToEdit);
			System.out.println("Retrieved " + toEdit.getYear() + " " + toEdit.getMake() + " " + toEdit.getModel());
			System.out.println("1 : Update make");
			System.out.println("2 : Update model");
			System.out.println("3 : Update year");
			int update = in.nextInt();
			in.nextLine();
			if (update == 1) {
				System.out.print("New make: ");
				String newMake = in.nextLine();
				toEdit.setMake(newMake);
			}
			else if (update == 2) {
				System.out.print("New model: ");
				String newModel = in.nextLine();
				toEdit.setModel(newModel);
			}
			else {
				System.out.print("New year: ");
				int newYear = in.nextInt();
				in.nextLine();
				toEdit.setYear(newYear);
			}
			lih.updateCar(toEdit);
		}
		else {
			System.out.println("---- No results found");
		}
	}
	
	public static void main(String[] args) {
		runMenu();
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our sports car table! ---");
		while (goAgain) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 -- Add a sports car");
			System.out.println("*  2 -- Edit a sports car");
			System.out.println("*  3 -- Delete a sports car");
			System.out.println("*  4 -- View the list of sports cars");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			if (selection == 1) {
				addACar();
			} else if (selection == 2) {
				editACar();
			} else if (selection == 3) {
				deleteACar();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("	Goodbye!   ");
				goAgain = false;
			}
		}
	}

	private static void viewTheList() {
		List<SportsCar> allCars = lih.showAllCars();
		for(SportsCar singleCar : allCars) {
			System.out.println(singleCar.returnCarDetails());
		}
	}
}