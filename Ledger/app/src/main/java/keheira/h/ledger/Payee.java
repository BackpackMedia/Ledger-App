package keheira.h.ledger;

import java.util.Date;

/**
 * Created by Keheira on 5/26/2017.
 */

public class Payee {
    private Integer id;
    private String name;
    private double amount;
    private String number;
    private Date date;
    private String reason;

    public Payee(){}

    public Payee(Integer i, String n, String num, double a, String r){
        id = i;
        name = n;
        number = num;
        amount = a;
        //date = d;
        reason = r;
    }

    public Payee(String n, String num, double a, String r){
        name = n;
        number = num;
        amount = a;
        //date = d;
        reason = r;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
