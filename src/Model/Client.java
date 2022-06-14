package Model;

import java.util.ArrayList;

public class Client extends Person{

    private ArrayList<String> invitedFriendEmails = new ArrayList<>();

    public ArrayList<String> getInvitedFriendEmails() {
        return invitedFriendEmails;
    }

    public void setInvitedFriendEmails(ArrayList<String> invitedFriendEmails) {
        this.invitedFriendEmails = invitedFriendEmails;
    }

    private int wallet ;

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

}
