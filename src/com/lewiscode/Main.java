package com.lewiscode;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        String[][] cinema = new String[rows][seats];
        creatingCinemaRoomSeats(cinema, rows, seats);
        int totalSeats = rows * seats;
        int currentIncome =0;
        int ticketPurchased =0;
        boolean quit = false;
        while (!quit) {
            menu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    printSeatArrangementInCinemaRoom(cinema, rows, seats);
                    break;
                case 2:
                    System.out.println();
                    int selectedRow =0;
                    int seat =0;
                    boolean ticketAvailable = false;
                    do {
                        while (true){
                            System.out.println("Enter a row number:");
                            selectedRow =scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seat = scanner.nextInt();
                            if ((selectedRow>0)&&(selectedRow<=rows)&&(seat>0)&&seat<=seats){
                                break;
                            }else {
                                System.out.println();
                                System.out.println("Wrong input!");
                                System.out.println();
                            }
                        }
                        ticketAvailable = selectingSeat(cinema,selectedRow,seat,ticketAvailable);
                    } while (ticketAvailable);
                    int ticketPrice = printTicketPrice(rows,seats,selectedRow);
                    System.out.println("Ticket price: $"+ticketPrice);
                    System.out.println();
                    currentIncome = currentIncome+ticketPrice;
                    ticketPurchased++;
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Number of purchased tickets: "+ticketPurchased);
                    float percentage = percentageOfPurchasedTicket(ticketPurchased,totalSeats);
                    System.out.print("Percentage: ");
                    System.out.format("%4.2f",percentage);
                    System.out.println("%");
                    System.out.println("Current income: $"+currentIncome);
                    System.out.println("Total income: $"+ totalCost(rows,seats));
                    System.out.println();
                    break;
                default:
                    System.out.println("Please enter the correct option given:");
                    break;
            }
        }
    }
    public  static  void  menu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket ");
        System.out.println("3. Statistics ");
        System.out.println("0. Exit");
    }

    public  static  void creatingCinemaRoomSeats(String[][] cinemaSeats, int row, int seats){
        for (int i=0;i<row;i++){
          for (int j=0;j<seats;j++){
              cinemaSeats[i][j] ="S";
          }
        }
    }
    public static void printSeatArrangementInCinemaRoom(String[][] cinemaSeat ,int rows, int seats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < seats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print("" + (i + 1));
            for (int j = 0; j < seats; j++) {
                System.out.print(" " + cinemaSeat[i][j]);
            }
            System.out.println();
        }

    }
    public static int totalCost(int row, int seats){
        int total ;
        final int price =10;
        final int price1 =8;
        int totalSeats =row*seats;

        if(totalSeats<60){
            total = totalSeats*price;
        }else {
            int firstHalf = price *((row/2)*seats);
            int secondHalf = price1*((row/2)*seats);
            int oddRows =price1*(((row/2)+(row%2))*seats);
            if(row%2 != 0){
                total = firstHalf + oddRows;
            }else{
                total = firstHalf + secondHalf;
            }
        }
        return total;
    }

    public static boolean selectingSeat(String[][] cinemaSeat, int selectedRow,int seatNum, boolean flag){
        String booked = "B";
        for(int i=0; i<cinemaSeat.length;i++){
            for (int j=0; j<cinemaSeat[i].length;j++){
                if(i==selectedRow-1 && j==seatNum-1){
                    if (cinemaSeat[i][j] ==booked){
                        System.out.println("That ticket has already been purchased!");
                        flag = true;
                        break;
                    }else {
                        cinemaSeat[i][j] = booked;
                        flag =false;
                    }
                }
            }
        }
        return flag;
    }

    public static int printTicketPrice(int rows,int seats, int selectedRow) {
        final int price =10;
        final int price1 =8;
        int ticketPrice =0;
        int half = 0;
        int totalSeats = rows*seats;
        if (totalSeats > 60) {
            if (rows%2==0){
                half =rows/2;
                if (selectedRow>half){
                    ticketPrice =price1;
                }else {
                    ticketPrice = price;
                }
            }else {
                half =rows/2;
                if (selectedRow>half){
                    ticketPrice =price1;
                }else {
                    ticketPrice = price;
                }

            }
        }else {
            ticketPrice =price;
        }
       return ticketPrice;
    }
    public static float percentageOfPurchasedTicket(int numberOfPurchasedTicket,int totalSeats){
        return ((float) numberOfPurchasedTicket/(float) totalSeats)*100;
    }


}
