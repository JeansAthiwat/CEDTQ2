package test.grader;

import logic.members.PhaiThongCasanovaMember;
import logic.store.Item;
import logic.store.Store;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PhaiThongCasanovaMemberTest {
    @Test
    void testConstructor() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("Wayne", 420, 6900, 777);

        assertEquals("Wayne", member.getName());
        assertEquals(420, member.getMemberID());
        assertEquals(6900, member.getPoint());
        assertEquals(777, member.getDigitalMoney());
        assertEquals(0.10, member.getDiscountPercent());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testBadConstructor() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("   ", -5055, -101010, -55555);

        assertEquals("UnknownMember", member.getName());
        assertEquals(0, member.getMemberID());
        assertEquals(0, member.getPoint());
        assertEquals(0, member.getDigitalMoney());
        assertEquals(0.10, member.getDiscountPercent());
        assertEquals(new ArrayList<Item>(), member.getPurchaseHistory());
        assertEquals(new ArrayList<Item>(), member.getShoppingCart());
    }

    @Test
    void testConvertPoint() {
        PhaiThongCasanovaMember member = new PhaiThongCasanovaMember("CasanovaPlatano", 555447, 1078, 1000);
        member.convertPoint();

        assertEquals(28, member.getPoint());
        assertEquals(1021, member.getDigitalMoney());
    }

    @Test
    void testTotalCartPrice() {
        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("LilJohn", 1, 0, 1000);
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("LilWayne", 2, 10000, 2000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("Biggie", 2, 10000, 2000);
        Item item1 = new Item("Item1", 200, 10);
        Item item2 = new Item("Item2", 100, 4);
        Item item3 = new Item("Item3", 1, 7);

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(2160, member2.totalCartPrice());
        assertEquals(2167, member3.totalCartPrice());
    }

    @Test
    void testGiveRandomItemFromStore() {
        Store store = Store.getInstance();
        store.getStock().clear();

        Item item1 = new Item("Item1", 200, 1);
        Item item2 = new Item("Item2", 100, 1);
        Item item3 = new Item("Item3", 3, 1);
        Item item4 = new Item("Item4", 4, 1);


        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("LilWayne", 1, 10000, 2000);
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("LilPain", 2, 999, 2000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("LilJohn", 3, 3333, 1000);

        member1.giveRandomItemFromStore();

        store.addItemToStock(item1);
        store.addItemToStock(item2);
        store.addItemToStock(item3);
        store.addItemToStock(item4);

        for (int i = 0; i < 4; i++) {
            member2.giveRandomItemFromStore();
            member3.giveRandomItemFromStore();
        }

        assertTrue(member1.getPurchaseHistory().isEmpty());
        assertTrue(member2.getPurchaseHistory().isEmpty());
        assertEquals(3, member3.getPurchaseHistory().size());
        assertEquals(333,member3.getPoint());

        member1.giveRandomItemFromStore();
        assertEquals(1, member1.getPurchaseHistory().size());
        assertNull(member1.giveRandomItemFromStore());
        assertEquals(1, member1.getPurchaseHistory().size());

    }
}
