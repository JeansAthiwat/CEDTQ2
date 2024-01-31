package logic.members;

import logic.store.Item;
import utils.ItemUtils;


import java.util.ArrayList;

public class BasicMember {
    private final ArrayList<Item> purchaseHistory;
    private final ArrayList<Item> shoppingCart;
    private String name;
    private int memberID;

    public BasicMember(String name, int memberID){
        //TODO: implement this method
        setName(name);
        setMemberID(memberID);
        purchaseHistory = new ArrayList<Item>();
        shoppingCart = new ArrayList<Item>();
    }

    public int totalCartPrice(){
        //TODO: implement this method
        return ItemUtils.calculateTotalPrice(shoppingCart);
    }
    public void addToPurchaseHistory(Item item) {
        //TODO: implement this method

        for (Item i : purchaseHistory) {
            if (item.equals(i)) {
                i.setAmount(i.getAmount() + 1);
                return;
            }
        }
        purchaseHistory.add(item);
    }


    public void checkout() {
        purchaseHistory.addAll(shoppingCart);
        shoppingCart.clear();
    }


    @Override
    public String toString() {
        return "(" + getTierName() + ")" + " " + getMemberID() + "-" + getName();
    }

    @Override
    public boolean equals(Object o) {
        //TODO: implement this method
        if (o instanceof BasicMember && ((BasicMember) o).getMemberID() == getMemberID()) {
            return true;
        }
        return false;
    }

    public ArrayList<Item> getPurchaseHistory() {
        return purchaseHistory;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public String getTierName() {
        return "Basic";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isBlank()) this.name = name;
        else this.name = "UnknownMember";
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        if (memberID >= 0) this.memberID = memberID;
        else this.memberID = 0;
    }
}
