package Gym_cards;

import Interview_exercises.TrainTicket;

import java.util.Date;

public class GymCard extends MemberShipCard{
    private CardType memberShipType;
    public GymCard(){
        super();
        this.memberShipType = null;
    }

    public GymCard(String cardHolder, float loyaltyPoints, Date expiryDate, CardType memberShipType) throws Exception {
        super(cardHolder, loyaltyPoints, expiryDate);
        memberShipType = memberShipType;
    }
    public CardType getMemberShipType() {
        return memberShipType;
    }
    public void setMemberShipType(CardType memberShipType) {
        memberShipType = memberShipType;
    }

    @Override
    public String getCardHolder() {
        return super.getCardHolder();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainTicket that)) return false;
        GymCard obj = (GymCard) o;
        return (super.equals(obj) && this.memberShipType.equals(obj.memberShipType));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isExpired() {
       Date currentdate = new Date();
       return super.getExpiryDate().before(currentdate);
    }

}
