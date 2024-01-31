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
        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("John", 10, 0, 10000);
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("Wayne", 20, 20000, 20000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("Big", 30, 30000, 30000);
        Item item1 = new Item("I1", 500, 10);//5000
        Item item2 = new Item("I2", 70, 5);//350
        Item item3 = new Item("I3", 31, 7);//217

        member2.getShoppingCart().add(item1);
        member2.getShoppingCart().add(item2);

        member3.getShoppingCart().add(item1);
        member3.getShoppingCart().add(item2);
        member3.getShoppingCart().add(item3);

        assertEquals(0, member1.totalCartPrice());
        assertEquals(4815, member2.totalCartPrice());
        assertEquals(5011, member3.totalCartPrice());
    }

    @Test
    void testGiveRandomItemFromStore() {
        Store store = Store.getInstance();
        store.getStock().clear();

        Item item1 = new Item("It1", 2000, 1);
        Item item2 = new Item("It2", 1000, 1);
        Item item3 = new Item("It3", 30, 1);
        Item item4 = new Item("It4", 40, 1);


        PhaiThongCasanovaMember member1 = new PhaiThongCasanovaMember("Wayne", 10, 3000, 101010);
        PhaiThongCasanovaMember member2 = new PhaiThongCasanovaMember("Pain", 20, 998, 202020000);
        PhaiThongCasanovaMember member3 = new PhaiThongCasanovaMember("John", 30, 3999, 300000);

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
        assertEquals(999,member3.getPoint());

        member1.giveRandomItemFromStore();
        assertEquals(1, member1.getPurchaseHistory().size());
        assertNull(member1.giveRandomItemFromStore());
        assertEquals(1, member1.getPurchaseHistory().size());

    }
}
