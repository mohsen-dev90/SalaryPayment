package core;

import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;

public class Payment {

    private String type;
    private String accountId;
    private int amount;



    public Payment(String type , String accountId , int amount){

        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
    }

    public Payment(String type , String accountId , String amount){

        this(type,accountId,Integer.valueOf(amount));

    }

    public boolean isDebtor(){

        return this.type.equals("debtor");
    }

    public String getType(){

        return  type;
    }

    public String getAccountId(){

        return getAccountId();
    }

    public int getAmount(){

        return amount;
    }




}
