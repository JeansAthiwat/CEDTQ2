package logic.members;

import logic.store.Item;
import utils.ItemUtils;

public class FundamentalMintMember extends BasicMember {
    private int point;
    protected double discountPercent;
    private int digitalMoney;

    public FundamentalMintMember (String name, int memberID, int point, int digitalMoney){
        //TODO: Write Constructor
        super(name,memberID);
        setDiscountPercent(0.05);
        setPoint(point);
        setDigitalMoney(digitalMoney);
    }

    public void convertPoint(){
        //TODO: Implement this method
        int convertedMoney = point/100;
        int remainingPoint = point%100;
        setDigitalMoney(getDigitalMoney()+convertedMoney);
        setPoint(remainingPoint);
    }

    @Override
    public String toString() {
        return "(" + getTierName() + ")" + " " + getMemberID() + "-" + getName() + " DMoney: " + getDigitalMoney() + " Pts: " + getPoint();
    }

    public String getTierName(){
        return "FundamentalMint";
    }

    public int totalCartPrice(){
        //TODO: implement this method
        return ItemUtils.calculateTotalPrice(getShoppingCart() , discountPercent);
    }

    public void checkout() {
        //TODO: implement this method
        setPoint(getPoint()+totalCartPrice());
        super.checkout();
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
        if(this.point <0) this.point =0;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDigitalMoney() {
        return digitalMoney;
    }

    public void setDigitalMoney(int digitalMoney) {
        this.digitalMoney = digitalMoney;
        if(this.digitalMoney <0) this.digitalMoney =0;
    }
}
