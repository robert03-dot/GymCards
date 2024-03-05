package Gym_cards;

import Interview_exercises.TrainTicket;

import java.io.Serializable;
import java.util.Date;

public abstract class MemberShipCard implements ICard,Cloneable, Serializable {
    private static int id;
    private String cardHolder;
    private float loyaltyPoints;
    private Date expiryDate;

    public MemberShipCard() {
        id = -1;
        this.cardHolder = " ";
        this.loyaltyPoints = 0;
        this.expiryDate = null;
    }
    public MemberShipCard(String cardHolder,float loyaltyPoints,Date expiryDate) throws Exception {
        ++id;
        if(cardHolder == null){
            throw new Exception("CardHolder nu poate fi null");
        }
        if(loyaltyPoints == 0 ){
            throw new Exception("Loyalty points nu poate fi 0");
        }
        this.cardHolder = cardHolder;
        this.loyaltyPoints = loyaltyPoints;//nu e nevoie de this pentru campuri statice
        this.expiryDate = expiryDate;
    }

    public static int getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public float getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setCardHolder(String cardHolder) throws Exception {
        if(cardHolder == null){
            throw new Exception("CardHolder nu poate fi null");
        }
        this.cardHolder = cardHolder;
    }

    public void setLoyaltyPoints(float loyaltyPoints) throws Exception {
        if(loyaltyPoints == 0){
            throw new Exception("Loyaltypoints nu poate fi 0");
        }
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MemberShipCard copy = (MemberShipCard) super.clone();
        copy.cardHolder = this.cardHolder;
        copy.expiryDate = this.expiryDate;
        copy.loyaltyPoints = this.loyaltyPoints;
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainTicket that)) return false;
        MemberShipCard obj = (MemberShipCard) o;
        return (getCardHolder().equals(obj.getCardHolder()) && getExpiryDate().equals(obj.getExpiryDate()) && getLoyaltyPoints() == obj.getLoyaltyPoints());
    }

    @Override
    public int hashCode() {
        int prim = 7;
        int result = super.hashCode();
        result = result*prim+((cardHolder == null) ? 0 : cardHolder.hashCode());
        result = result*prim+((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = (int) (result*prim + loyaltyPoints);
        return result;
    }
}