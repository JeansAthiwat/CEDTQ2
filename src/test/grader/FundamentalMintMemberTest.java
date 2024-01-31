package test.grader;

import logic.members.BasicMember;
import logic.members.FundamentalMintMember;
import logic.store.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FundamentalMintMemberTest {

    @Test
    void testConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("Jeans", 65321, 60000, 9000);

        assertEquals("Jeans", member.getName());
        assertEquals(65321, member.getMemberID());
        assertEquals(60000, member.getPoint());
        assertEquals(9000, member.getDigitalMoney());
        assertEquals(0.05, member.getDiscountPercent());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testBadConstructor() {
        FundamentalMintMember member = new FundamentalMintMember("      ", -44444, -1111, -6000);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0, member.getPoint());
        assertEquals(0, member.getDigitalMoney());
        assertEquals(0.05, member.getDiscountPercent());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testConvertPoint() {
        FundamentalMintMember member = new FundamentalMintMember("Cena", 666, 1111, 500);
        member.convertPoint();

        assertEquals(11, member.getPoint());
        assertEquals(511, member.getDigitalMoney());
    }

    @Test
    void testEquals() {

        FundamentalMintMember member1 = new FundamentalMintMember("TheRock", 100, 100, 1000); // Same memberID as member1
        BasicMember member2 = new BasicMember("Dwayne", 100);
        BasicMember differentMember = new BasicMember("Haley", 55555);

        assertTrue(member1.equals(member1));// Same object should be equal to itself
        assertTrue(member1.equals(member2));// Different objects with the same memberID should be equal
        assertFalse(member1.equals(differentMember)); // Different objects with different memberID should not be equal
        assertFalse(member1.equals(null)); // Should not be equal to null
    }

    @Test
    void testTotalCartPrice() {
        FundamentalMintMember member1 = new FundamentalMintMember("mem1", 10, 0, 100);
        FundamentalMintMember member2 = new FundamentalMintMember("mem2", 20, 200, 20000);
        FundamentalMintMember member3 = new FundamentalMintMember("mem3", 30, 300, 30000);
        Item item1 = new Item("I1", 100, 50); //5000
        Item item2 = new Item("I2", 200, 4);//800
        Item item3 = new Item("I3", 3, 10);//30

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(5510, member2.totalCartPrice());
        assertEquals(5539, member3.totalCartPrice());
    }

    @Test
    void testCheckout() {
        FundamentalMintMember member = new FundamentalMintMember("Chelsea", 1, 10, 10000);
        Item item1 = new Item("I1", 20, 50);//100
        Item item2 = new Item("I2", 50, 100);//5000
        member.getShoppingCart().add(item1);
        member.getShoppingCart().add(item2);

        member.checkout();
        // Verify that the actual points match the expected points
        assertEquals(5710, member.getPoint());
        assertTrue(member.getShoppingCart().isEmpty());
        assertTrue(member.getPurchaseHistory().contains(item1));
        assertTrue(member.getPurchaseHistory().contains(item2));

    }

    @Test
    void testSetPoint() {
        FundamentalMintMember member = new FundamentalMintMember("JJ", 80, 100, 20);
        member.setPoint(100);
        assertEquals(100, member.getPoint());

        /*not in toStudent (ใส่เพิ่มจาร testcase จาร)*/
        member.setPoint(-9699);
        assertEquals(0, member.getPoint());
    }


    @Test
    void testSetDigitalMoney() {
        FundamentalMintMember member = new FundamentalMintMember("Kelly", 800, 1000, 10000);
        member.setDigitalMoney(800);
        assertEquals(800, member.getDigitalMoney());

        /*not in toStudent (ใส่เพิ่มจาร testcase จาร)*/
        member.setDigitalMoney(-9696);
        assertEquals(0, member.getDigitalMoney());
    }


}
