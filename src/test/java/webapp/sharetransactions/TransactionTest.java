package webapp.sharetransactions;

import basic.dbconnect.Model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void getShareNameReturnName(){
        assertEquals("ASBISC", Transaction.getShareName("Asbis"));
    }

    @Test
    void shouldHaveErrorMessageAboutFileNotFound(){

        try{
            Transaction.getShareName("Asbis");
        }
        catch (Exception e){
            assertEquals("No file to import transaction list", e.getMessage());
        }
    }
}
