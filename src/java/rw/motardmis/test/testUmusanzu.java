package rw.motardmis.test;

import java.text.SimpleDateFormat;
import rw.motardmis.dao.UmusanzuDao;
import rw.motardmis.domain.Umusanzu;

public class testUmusanzu {

    public static void main(String[] args) {

        Umusanzu umus = new Umusanzu();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        umus.setUmusanzuNo("1");
        //umus.setAmount(3000);
        umus.setUwishyura("Kamana");
        umus.setDeposit_Date(null);
        umus.setPlateNo("R511B");
        umus.setReceiver("Uwase");
       // umus.setMotari("1234567654321234");

        new UmusanzuDao().Create(umus);

    }
}
