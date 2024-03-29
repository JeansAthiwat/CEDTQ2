package utils;

import logic.app.AppController;
import logic.members.BasicMember;
import logic.members.FundamentalMintMember;
import logic.members.PhaiThongCasanovaMember;

import logic.store.Store;

import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    private static final Scanner sc = new Scanner(System.in);
    private static IO instance = null;
    private static AppController ac;


    public static IO getInstance() {
        if (instance == null) {
            instance = new IO();
        }
        return instance;
    }

    public IO() {
        ac = new AppController();
    }

    public void home() {
        System.out.println("============================HOME PAGE============================");
        System.out.println("                 ---*Store Balance : "+Store.getInstance().getStoreMoney() + " Baht*---");
        System.out.println("<0> Add Items To Stock");
        System.out.println("<1> Check Stock");
        System.out.println("<2> Select Member To Buy Items");
        System.out.println("<3> Sign Up New Member");
        System.out.println("<4> Manage Member");
        int choice_home = choiceCheck(0, 4);
        System.out.println("=================================================================");
        switch (choice_home) { // Do the flow for selected option
            case 0 -> { // Add Items To Stock
                System.out.println("                  ---===Creating New Item===---");
                System.out.println("*Enter Item Name : ");
                String name = sc.next();
                System.out.println("*Enter Item Price (use old price if existed) :");
                int price = Integer.parseInt(sc.next());
                System.out.println("*Enter Amount To Add :");
                int amount = Integer.parseInt(sc.next());
                System.out.println(ac.addNewItemToStockFlow(name, price, amount));
            }
            case 1 -> { // Check Stock
                System.out.println(ac.showStockFlow());
            }
            case 2 -> { // Select Member To Buy Items
                System.out.println("                  ---===Member To Buy Items===---");
                System.out.println("*Input Buyer's MemberID :");
                int memberID = Integer.parseInt(sc.next());
                BasicMember member = Store.getInstance().getMemberByID(memberID);
                if (member == null) {
                    System.out.println("This MemberID Doesn't Exist!");
                    break;
                }
                System.out.println("Current Customer: " + member);
                System.out.println("In Shopping Cart: \n" + ac.showItemsInCart(member));
                int maxChoice = (member instanceof FundamentalMintMember) ? 3 : 2;
                while (true) {
                    System.out.println("-------------------------Shopping Option-------------------------");
                    System.out.println("<0> Add Items To Cart");
                    System.out.println("<1> Remove Items From Cart");
                    System.out.println("<2> CheckOut With Cash");
                    if (maxChoice == 3)
                        System.out.println("<3> CheckOut With Digital Money"); // FundamentalMintMember can pay with digitalMoney
                    int choice_member = choiceCheck(0, maxChoice);

                    if (choice_member == 0) { //let member pick item from Stock to add to the member's ShoppingCart
                        System.out.println(ac.showStockFlow());
                        System.out.println("*Pick Item To Add To Cart : ");
                        int choice_stockItem = choiceCheck(0, Store.getInstance().getStock().size() - 1);
                        System.out.println("*Amount To Add :");
                        int amountToAdd = sc.nextInt();
                        System.out.println(ac.addItemToShoppingCartFlow(member, choice_stockItem, amountToAdd));
                    } else if (choice_member == 1) { // remove Items from cart
                        System.out.println("                  ---===Select Item To Remove===--- \n" + ac.showItemsInCart(member));
                        if (member.getShoppingCart().isEmpty()) {
                            System.out.println("No Item In The Shopping Cart!");
                            break;
                        }
                        int choice_cartItem = choiceCheck(0, member.getShoppingCart().size() - 1);
                        System.out.println(ac.removeItemFromShoppingCartFlow(member, choice_cartItem));
                    }
                    if (choice_member == 2) { // CheckOut With Cash
                        System.out.println("*Input Customer Money : ");
                        int givenMoney = sc.nextInt();
                        System.out.println(ac.checkOutWithCashFlow(member, givenMoney));
                        break;
                    }
                    if (choice_member == 3) { // CheckOut With Digital Money
                        System.out.println(ac.checkoutWithDigitalMoneyFlow((FundamentalMintMember) member));
                        break;
                    }
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("In Shopping Cart: \n" + ac.showItemsInCart(member));
                }

            }
            case 3 -> { //Sign Up New Member
                System.out.println("                  ---===Sign Up New Member===---");
                System.out.println("*Select New Member's Tier: ");
                System.out.println("<0> Basic");
                System.out.println("<1> FundamentalMint");
                System.out.println("<2> PhaiThongCasanova");
               //System.out.println("<3> StarvingStudent");
                int choice_memberType = choiceCheck(0, 3);
                System.out.println("*Enter Member's Name: ");
                String memberName = sc.next();
                System.out.println("*Enter Member's ID: ");
                int memberID = sc.nextInt();
                int memberDigitalMoney = 0;
                if (choice_memberType > 0) { // if member is not BasicMember
                    System.out.println("*Enter Member's Starting DigitalMoney Amount: ");
                    memberDigitalMoney = sc.nextInt();
                }
                System.out.println(ac.signUpMemberFlow(choice_memberType, memberName, memberID, memberDigitalMoney));
            }
            case 4 -> { //Manage Member
                System.out.println("                  ---===Manage Member===---");
                System.out.println(ac.showMemberList());
                System.out.println("*Select Member: ");
                ArrayList<BasicMember> members = Store.getInstance().getMembers();
                int choice_member = choiceCheck(0, members.size() - 1);
                BasicMember member = members.get(choice_member);
                int memberHighestChoice = 2;
                System.out.println("-------------------------Member's Option-------------------------");
                System.out.println("<0> Go Back");
                System.out.println("<1> Delete Member");
                System.out.println("<2> Purchase History");


                if (member instanceof FundamentalMintMember) {
                    System.out.println("<3> Top-Up DigitalMoney");
                    System.out.println("<4> Convert Point To DigitalMoney");
                    memberHighestChoice = 4;
                }

                if (member.getTierName().equals("PhaiThongCasanova")) {
                    System.out.println("<5> Points Gacha! (exchange 1000 Points to get 1 random Item)");
                    memberHighestChoice = 5;
                } else {
                    //if (member.getTierName().equals("StarvingStudent")) {
                    //    System.out.println("<5> Get Loan");
                    //    System.out.println("<6> Payback Loan");
                    //    memberHighestChoice = 6;
                    //}
                }
                int choice_memberOption = choiceCheck(0, memberHighestChoice);
                switch (choice_memberOption) {
                    case 0 -> {
                        //continue with the loop
                    }
                    case 1 -> {
                        System.out.println(ac.deleteMemberFlow(member));
                    }
                    case 2 -> {
                        System.out.println(ac.showPurchaseHistory(member));
                    }
                    case 3 -> {
                        System.out.println("*Enter Amount:");
                        int amount = sc.nextInt();
                        System.out.println(ac.topUpDigitalMoneyFlow((FundamentalMintMember) member, amount));
                    }
                    case 4 -> {
                        System.out.println(ac.convertPointFlow((FundamentalMintMember) member));
                    }
                    case 5 -> {
                        if (memberHighestChoice == 5) { // member is PhaiThongCasanova
                            System.out.println(ac.gachaFlow((PhaiThongCasanovaMember) member));
                        } else { // member is StarvingStudent
                            //System.out.println("Current Total Loan: " + ((StarvingStudentMember) member).getLoan());
                            //System.out.println("*Enter Loan Amount:");
                           // int amount = sc.nextInt();
                           // System.out.println(ac.getLoanFlow((StarvingStudentMember) member, amount));
                        }
                    }
                    //case 6 -> {
                       // System.out.println("*Enter Amount To Return: ");
                        //int amount = sc.nextInt();
                       // System.out.println(ac.returnLoanFlow((StarvingStudentMember) member, amount));
                    //}
                }
            }

        }
        System.out.println("=================================================================");
    }

    private static int choiceCheck(int lowestChoice, int HighestChoice) { // make sure input stays in range of these numbers
        int choice = sc.nextInt();
        while (choice < lowestChoice || choice > HighestChoice) {
            System.out.println("Invalid Input! (try again) :");
            choice = sc.nextInt();
        }
        return choice;
    }

}
