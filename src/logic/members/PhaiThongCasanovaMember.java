package logic.members;

import logic.store.Item;
import logic.store.Store;

public class PhaiThongCasanovaMember extends FundamentalMintMember {
    public PhaiThongCasanovaMember(String name, int memberID, int point, int digitalMoney) {
        //TODO: Write Constructor
        super(name, memberID, point, digitalMoney);
        setDiscountPercent(0.10);
    }

    public void convertPoint(){
        //TODO: Implement this method
        int convertedMoney = getPoint()/50;
        int remainingPoint = getPoint()%50;
        setDigitalMoney(getDigitalMoney()+convertedMoney);
        setPoint(remainingPoint);
    }

    public String getTierName(){
        return "PhaiThongCasanova";
    }

    public Item giveRandomItemFromStore(){
        Item i;
        if(getPoint()>=1000 && !Store.getInstance().getStock().isEmpty()){
            //TODO: Fill code below this line
            i = Store.getInstance().takeRandomItemFromStock();
            setPoint(getPoint()-1000);
            addToPurchaseHistory(i);
            return i;
        }
        //TODO: Fill code below this line
        return null;

    }


}
