package frontend.console;

import backend.DBManager;
import backend.services.CarService;
import backend.services.ClientService;
import frontend.console.views.CarView;
import frontend.console.views.ClientView;

import java.util.Scanner;

public class App {
    public static final Scanner SCANNER = new Scanner(System.in);

    private static CarService carService;
    private static ClientService clientService;

    private static CarView carView;
    private static ClientView clientView;

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        carService = new CarService(dbManager.getConnection());
        clientService = new ClientService(dbManager.getConnection());

        carView = new CarView(carService, clientService);
        clientView = new ClientView(clientService);
        showMainMenu();
    }

    private static void showMainMenu() {
        int cmd;
        do {
            System.out.println();
            System.out.print("1. Show cars\n2. Show clients\n3. Add car\n4. Drop car\n5. Buy car\n0. Exit\n- ");
            cmd = Integer.valueOf(SCANNER.nextLine());
            switch (cmd) {
                case 1: {
                    System.out.println();
                    carView.showCars();
                    break;
                }
                case 2: {
                    System.out.println();
                    clientView.showClients();
                    break;
                }
                case 3: {
                    System.out.println();
                    carView.addCar();
                    break;
                }
                case 4: {
                    System.out.println();
                    carView.dropCar();
                    break;
                }
                case 5: {
                    System.out.println();
                    carView.buyCar();
                    break;
                }
                case 0:
                    System.out.println();
                    System.out.println("THE END.");
                    return;
                default:
                    System.out.println();
                    System.out.println("Error");
            }
        }
        while (true);
    }
}
