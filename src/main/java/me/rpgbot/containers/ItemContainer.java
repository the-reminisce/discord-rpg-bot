package me.rpgbot.containers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Seth on June 6/19/2020, 2020 at 2:18 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class ItemContainer {

    public final int maxContainerSize;

    public final String containerName;

    public final List<Item> itemList;

    public ItemContainer(final String containerName, final int maxContainerSize) {
        this.containerName = containerName;
        this.maxContainerSize = maxContainerSize;
        this.itemList = new ArrayList<>(maxContainerSize);
    }

    public boolean addItem(final Item item) {
        if (full() && !item.stackable) {
            return false;
        } else if (full() && item.stackable && !containsItem(item)) {
            return false;
        }
        if (item.stackable && containsItem(item)) {
            itemList.get(slotForItem(item)).quantity += item.quantity;
        } else {
            itemList.add(item);
        }
        return true;
    }

    /**
     * Removes all instances of the specified item in the container
     * @param item
     * @return
     */
    public boolean removeAllItem(final Item item) {
        if (!containsItem(item)) {
            return false;
        }
        HashSet<Item> remove = new HashSet<>();
        for (Item i : itemList) {
            if (i.equals(item)) {
                remove.add(item);
            }
        }
        for (Item i : remove) {
            itemList.remove(i);
        }
        return true;
    }

    /**
     * Removes the first occurrence of the specified item
     * @param item
     * @return
     */
    public boolean removeItem(final Item item) {
        if (!containsItem(item)) {
            return false;
        }
        itemList.remove(slotForItem(item));
        return true;
    }

    public boolean removeItem(final Item checkItem, final int amountToRemove) {
        if (!containsItem(checkItem, amountToRemove)) {
            return false;
        }
        int amountRemoved = 0;
        for (Item i : itemList) {
            if (!i.equals(checkItem)) {
                continue;
            }
            int quantityToDeduct = i.quantity;
            if (amountRemoved + quantityToDeduct > amountToRemove) {
                quantityToDeduct = amountToRemove - amountRemoved;
            }
            i.quantity -= quantityToDeduct;
            amountRemoved += quantityToDeduct;
            if (amountRemoved >= amountToRemove) {
                break;
            }
        }
        HashSet<Item> remove = new HashSet<>();
        for (Item i : itemList) {
            if (!i.equals(checkItem)) {
                continue;
            }
            if (i.quantity <= 0) {
                remove.add(i);
            }
        }
        for (Item i : remove) {
            itemList.remove(i);
        }
        return true;
    }

    public int size() {
        return Math.min(itemList.size(), maxContainerSize);
    }

    public boolean full() {
        return size() >= maxContainerSize;
    }

    /**
     * Returns if the container has any amount of the specified item
     * @param item
     * @return
     */
    public boolean containsItem(final Item item) {
        return itemList.contains(item);
    }

    /**
     * Returns if the container has the specified item and specified amount of it
     * @param checkItem
     * @param quantityNeeded
     * @return
     */
    public boolean containsItem(final Item checkItem, final int quantityNeeded) {
        if (!itemList.contains(checkItem)) {
            return false;
        }
        return amountOf(checkItem) >= quantityNeeded;
    }

    /**
     * Returns the quantity of the specified item inside of the container
     * @param checkItem
     * @return
     */
    public int amountOf(final Item checkItem) {
        int amountFound = 0;
        for (Item i : itemList) {
            if (!i.equals(checkItem)) {
                continue;
            }
            amountFound += i.quantity;
        }
        return amountFound;
    }

    public int slotForItem(final Item item) {
        int curr = 0;
        for (Item i : itemList) {
            if (i.equals(item)) {
                return curr;
            }
            curr++;
        }
        return -1;
    }

}
