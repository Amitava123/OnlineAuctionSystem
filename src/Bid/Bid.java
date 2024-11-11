package Bid;

import User.IUser;

import java.util.Objects;

public class Bid {
    private final String bidName;
    private final int amount;

    public Bid(String bidName, int amount) {
        this.bidName = bidName;
        this.amount = amount;
    }

    public String getBidName() {
        return bidName;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bid bid = (Bid) obj;
        return amount == bid.amount && bidName.equals(bid.bidName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidName, amount);
    }
}
