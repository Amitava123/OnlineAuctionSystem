package Auction;

import User.IUser;

public interface IAuction {
    void createBid(IUser buyer, int amount);
    void updateBid(IUser buyer, int amount);
    void withdrawBid(IUser buyer);
    IUser closeAuction();
    double getProfit(IUser seller);
}
