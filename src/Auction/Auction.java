package Auction;

import User.IUser;

import java.util.HashMap;
import java.util.Map;

public class Auction implements IAuction {
    private final String id;
    private final int lowestBidLimit;
    private final int highestBidLimit;
    private final double participationCost;
    private final IUser seller;
    private final Map<IUser, Integer> bids = new HashMap<>();

    public Auction(String id, int lowestBidLimit, int highestBidLimit, double participationCost, IUser seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
    }

    @Override
    public void createBid(IUser buyer, int amount) {
        if (amount < lowestBidLimit || amount > highestBidLimit) {
            System.out.println("Bid amount "+ amount + "is out of bounds for " + buyer.getName());
            return;
        }
        if (bids.containsKey(buyer)) {
            throw new IllegalStateException("Bid already exists for this buyer. Use updateBid to modify the bid.");
        }
        bids.put(buyer, amount);
        System.out.println(buyer.getName() + " placed a bid of " + amount + " in auction " + id);
    }

    @Override
    public void updateBid(IUser buyer, int amount) {
        if (!bids.containsKey(buyer)) {
            throw new IllegalStateException("Bid does not exist for this buyer. Use createBid to place a new bid.");
        }
        if (amount < lowestBidLimit || amount > highestBidLimit) {
            System.out.println("Bid amount "+ amount + "is out of bounds for " + buyer.getName());
            return;
        }
        bids.put(buyer, amount);
        System.out.println(buyer.getName() + " updated their bid to " + amount + " in auction " + id);
    }

    @Override
    public void withdrawBid(IUser buyer) {
        if (bids.containsKey(buyer)) {
            bids.put(buyer, 0);
            System.out.println(buyer.getName() + " withdrew their bid in auction " + id);
        } else {
            System.out.println("No bid found for " + buyer.getName() + " to withdraw in auction " + id);
        }
    }

    @Override
    public IUser closeAuction() {
        Integer highestUniqueBid = null;
        IUser winner = null;

        Map<Integer, Integer> bidCount = new HashMap<>();
        for (int bid : bids.values()) {
            bidCount.put(bid, bidCount.getOrDefault(bid, 0) + 1);
        }

        for (Map.Entry<IUser, Integer> entry : bids.entrySet()) {
            int bid = entry.getValue();
            if (bidCount.get(bid) == 1 && (highestUniqueBid == null || bid > highestUniqueBid)) {
                highestUniqueBid = bid;
                if (highestUniqueBid == 0) {
                    return null;
                }
                winner = entry.getKey();
            }
        }

        return winner;
    }

    @Override
    public double getProfit(IUser seller) {
        if (!this.seller.equals(seller)) {
            throw new IllegalArgumentException("Invalid seller for this auction.");
        }

        int noOfBidders = bids.size();
        double participationShare = noOfBidders * participationCost * 0.2;

        IUser winner = closeAuction();

        double bidProfit = (lowestBidLimit + highestBidLimit) / 2.0;

        if (winner == null) {
            return participationShare;
        }

        double winningBidAmount = bids.get(winner);
        return winningBidAmount + participationShare - bidProfit;
    }



}
