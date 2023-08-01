package eist;

import java.text.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Client {

    private Client() {
    }

    /**
     * Main method.
     * Add code to test your implementation here.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws ParseException {
        var a = new FactoryFacade();
//        System.out.println(a.addProduct("SalesManager", 34));

        var ACL = new AccessControlList();
        ACL.grantAccess("SalesManager", "Add");
        ACL.grantAccess("SalesManager", "Sell");
        ACL.grantAccess("SalesManager", "Check");
        ACL.grantAccess("SalesIntern", "Add");
        ACL.grantAccess("SalesIntern", "Check");
        ACL.grantAccess("MarketingManager", "Check");

        System.out.println(ACL.hasAccess("SalesManager", "Add"));;
    }
}
