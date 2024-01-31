package test.grader;


import logic.members.BasicMember;
import logic.store.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BasicMemberTest {

    @Test
    void testConstructor() {
        BasicMember member = new BasicMember("MrBasic", 11111);
        assertEquals("MrBasic", member.getName());
        assertEquals(11111, member.getMemberID());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testBadConstructor() {
        BasicMember member = new BasicMember("       ", -999999);
        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testEquals() {
        BasicMember member1 = new BasicMember("SirVishnu", 99999);
        BasicMember member2 = new BasicMember("ajarntoe", 99999); // Same memberID as member1
        BasicMember differentMember = new BasicMember("SirPeerapol", 55555);

        assertTrue(member1.equals(member1));// Same object should be equal to itself
        assertTrue(member1.equals(member2));// Different objects with the same memberID should be equal
        assertFalse(member1.equals(differentMember)); // Different objects with different memberID should not be equal
        assertFalse(member1.equals(null)); // Should not be equal to null
    }

    @Test
    void testTotalCartPrice() {
        BasicMember member1 = new BasicMember("Juanito", 100);
        assertTrue(member1.getShoppingCart().isEmpty());
        BasicMember member2 = new BasicMember("William", 200);
        assertTrue(member2.getShoppingCart().isEmpty());
        Item lactasoy = new Item("Lactasoy", 5, 10);
        Item ferrero = new Item("Ferrero", 100, 4);
        member2.getShoppingCart().add(lactasoy);
        member2.getShoppingCart().add(ferrero);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(450, member2.totalCartPrice());
    }

    @Test
    void testAddToPurchaseHistoryNewItem() {
        BasicMember member = new BasicMember("Peter", 3101);
        assertTrue(member.getPurchaseHistory().isEmpty());
        Item newItem = new Item("AK47", 4700, 10);
        member.addToPurchaseHistory(newItem);

        assertTrue(member.getPurchaseHistory().contains(newItem));
        Item newItem2 = new Item("HK416", 4000, 20);
        assertFalse(member.getPurchaseHistory().contains(newItem2));
        member.addToPurchaseHistory(newItem2);
        assertTrue(member.getPurchaseHistory().contains(newItem2));
        assertEquals(2,member.getPurchaseHistory().size());
    }

    @Test
    void testAddToPurchaseHistoryExistingItem() {
        BasicMember member = new BasicMember("LilJohn", 1);
        assertTrue(member.getPurchaseHistory().isEmpty());
        Item existingItem1 = new Item("ExistingItem1", 300, 100);
        Item existingItem2 = new Item("ExistingItem2", 300, 10);
        member.addToPurchaseHistory(existingItem1);
        member.addToPurchaseHistory(existingItem2);
        assertTrue(member.getPurchaseHistory().contains(existingItem1));
        assertTrue(member.getPurchaseHistory().contains(existingItem2));
        Item updatedItem = new Item("ExistingItem2", 450, 2);
        member.addToPurchaseHistory(updatedItem);

        assertEquals(100, member.getPurchaseHistory().get(0).getAmount());
        assertEquals(12, member.getPurchaseHistory().get(1).getAmount());
    }



}

