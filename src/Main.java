import Auction.Auction;
import User.Buyer;
import User.IUser;
import User.Seller;
import Auction.IAuction;

public class Main {
    public static void main(String[] args) {
        // Test Case 1
        IUser buyer1 = new Buyer("buyer1");
        IUser buyer2 = new Buyer("buyer2");
        IUser buyer3 = new Buyer("buyer3");
        IUser seller1 = new Seller("seller1");

        IAuction auction1 = new Auction("A1", 10, 50, 1.0, seller1);

        auction1.createBid(buyer1, 17);
        auction1.createBid(buyer2, 15);
        auction1.updateBid(buyer2, 19);
        auction1.createBid(buyer3, 19);

        IUser winner1 = auction1.closeAuction();
        if (winner1 != null) {
            System.out.println("The winning bidder is: " + winner1.getName());
        } else {
            System.out.println("No winner found.");
        }

        try {
            double profit = auction1.getProfit(seller1);
            System.out.println("Seller's profit: " + profit);
        } catch (IllegalArgumentException e) {
            System.out.println("Error calculating profit: " + e.getMessage());
        }

        // Test Case 2
        IUser seller2 = new Seller("seller2");

        IAuction auction2 = new Auction("A2", 5, 20, 2, seller2);
        auction2.createBid(buyer3, 25);
        auction2.createBid(buyer2, 5);
        auction2.withdrawBid(buyer2);

        IUser winner2 = auction2.closeAuction();
        if (winner2 != null) {
            System.out.println("The winning bidder is: " + winner2.getName());
        } else {
            System.out.println("No winner found.");
        }

        try {
            double profit = auction2.getProfit(seller2);
            System.out.println("Seller's profit: " + profit);
        } catch (IllegalArgumentException e) {
            System.out.println("Error calculating profit: " + e.getMessage());
        }
    }
}