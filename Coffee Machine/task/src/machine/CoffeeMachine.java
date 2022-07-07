package machine;

import java.util.Scanner;


class Machine{
    int water;
    int milk;
    int coffeeBeans;
    int cost;

    Machine(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }
}



public class CoffeeMachine {
    static Scanner scan = new Scanner(System.in);
    //static int [] ingredients = new int[5];
    //Resources
    static int water = 400, milk = 540, coffebeans = 120, cups = 9, money = 550;
    Status status;
    boolean keepRunning = true;


    public enum Status {
        CHOOSING_ACTION,
        CHOOSING_COFFEE,
        ADDING_WATER,
        ADDING_MILK,
        ADDING_COFFEE_BEANS,
        ADDING_DISPOSABLE_CUPS,
        TAKING_MONEY
    }

    //constructor
    public CoffeeMachine(int water, int milk, int coffebeans, int cups, int money){
        this.water = water;
        this.milk = milk;
        this.coffebeans = coffebeans;
        this.cups = cups;
        this.money = money;
    }


    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        coffeeMachine.chooseAction();
    }

    public void chooseAction(){
        this.status = Status.CHOOSING_ACTION;

        while (keepRunning) {
            System.out.println("\n\nWrite action (buy, fill, take, remaining, exit): ");
            String action = scan.next();
            switch (action) {
                case "buy":
                    this.buy();
                    break;
                case "fill":
                    this.fill();
                    break;
                case "take":
                    this.take();
                    break;
                case "remaining":
                    this.remaining();
                    break;
                case "exit":
                    keepRunning = false;
                    break;
            }
        }
    }


    public static void remaining(){
        System.out.printf("\nThe coffee machine has: " +
                        "\n%d ml of water" +
                        "\n%d ml of milk" +
                        "\n%d g of coffee beans" +
                        "\n%d disposable cups" +
                        "\n$%d of money",
                        water, milk, coffebeans, cups, money);
    }

    public static void enoughResources(){
        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void notEnoughResources(String missing){
        System.out.printf("Sorry, not enough %s!", missing);
    }

    public void buy(){
        this.status = Status.CHOOSING_COFFEE;
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffe = scan.next();

        String missing;
        switch(coffe){
            case "1" :
                if (water >= 250 && coffebeans >= 16 && cups >= 1){
                    water -= 250;
                    milk -= 0;
                    coffebeans -= 16;
                    cups -= 1;
                    money += 4;
                    enoughResources();
                } else {
                    if (water < 250) {
                        missing = "water";
                    } else if (coffebeans < 16) {
                        missing = "coffe beans";
                    } else {
                        missing = "cups";
                    }
                    notEnoughResources(missing);
                }
                break;
            case "2" :
                if (water >= 350 && milk >= 75 && coffebeans >= 20 && cups >= 1){
                water -= 350;
                milk -= 75;
                coffebeans -= 20;
                cups -= 1;
                money += 7;
                    enoughResources();
                } else {
                    if (water < 350) {
                        missing = "water";
                    } else if (milk < 75) {
                        missing = "coffe beans";
                    }else if (coffebeans < 20) {
                        missing = "coffe beans";
                    } else {
                        missing = "cups";
                    }
                    notEnoughResources(missing);
                }
                break;
            case "3" :
                if (water >= 200 && milk >= 100 && coffebeans >= 12 && cups >= 1){
                water -= 200;
                milk -= 100;
                coffebeans -= 12;
                cups -= 1;
                money += 6;
                    enoughResources();
                } else {
                    if (water < 200) {
                        missing = "water";
                    } else if (milk < 100) {
                        missing = "coffe beans";
                    }else if (coffebeans < 12) {
                        missing = "coffe beans";
                    } else {
                        missing = "cups";
                    }
                    notEnoughResources(missing);
                }
                break;
            case "back" :
                break;
        }
    }

    public void fill(){

        this.status = Status.ADDING_WATER;
        System.out.println("Write how many ml of water you want to add:");
        this.water += scan.nextInt();

        this.status = Status.ADDING_MILK;
        System.out.println("Write how many ml of milk you want to add:");
        this.milk += scan.nextInt();

        this.status = Status.ADDING_COFFEE_BEANS;
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.coffebeans += scan.nextInt();

        this.status = Status.ADDING_DISPOSABLE_CUPS;
        System.out.println("Write how many disposable cups of coffee you want to add:");
        this.cups += scan.nextInt();
    }

    public void take(){
        this.status = Status.TAKING_MONEY;
        System.out.printf("I gave you $%d%n", this.money);
        this.money = 0;
    }


}

