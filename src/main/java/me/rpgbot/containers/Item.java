package me.rpgbot.containers;

import java.util.Objects;

/**
 * Created by Seth on June 6/19/2020, 2020 at 2:21 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Item {

    public String name;

    public boolean stackable;

    public int quantity;

    public Item(String name) {
        this(name, 1, false);
    }

    public Item(String name, int quantity) {
        this(name, quantity, false);
    }

    public Item(String name, int quantity, boolean stackable) {
        this.name = name;
        this.quantity = quantity;
        this.stackable = stackable;
        if (!stackable && quantity > 1) {
            this.stackable = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return stackable == item.stackable && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stackable);
    }

    @Override
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
