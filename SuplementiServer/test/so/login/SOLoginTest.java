/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package so.login;

import db.DBBroker;
import domen.Administrator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author necam
 */
public class SOLoginTest {
    
    private static Administrator admin;
    
    public SOLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        // Kreiram novog admina i ubacujem ga u bazu
        admin = new Administrator();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setIme("Admin");
        admin.setPrezime("Admin");
        PreparedStatement ps = DBBroker.getInstance().insert(admin);
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                // Čuvam generisani ID u admin objektu
                admin.setAdministratorID(rs.getLong(1));
            } else {
                throw new SQLException("Generisani ID nije pronađen.");
            }
        }
        DBBroker.getInstance().getConnection().commit();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
         // Čišćenje baze nakon testova
        if (admin != null) {
            DBBroker.getInstance().delete(admin); // Koristim admin objekat za brisanje
            DBBroker.getInstance().getConnection().commit();
        }
    }

    /**
     * Test of validacija method, of class SOLogin.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoginPogresniKredencijali() throws Exception {
        SOLogin so = new SOLogin();
        
        Administrator pogresniAdmin = new Administrator();
        pogresniAdmin.setUsername("lazar");
        pogresniAdmin.setPassword("lazar");
        pogresniAdmin.setIme("Lazar");
        pogresniAdmin.setPrezime("Nikolic");
        
        Exception exception = assertThrows(Exception.class, () -> {
            so.izvrsi(pogresniAdmin);
        });

        assertEquals("Ne postoji administrator sa tim kredencijalima.", exception.getMessage());

    }

    /**
     * Test of izvrsi method, of class SOLogin.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoginTacniKredencijali() throws Exception {
        SOLogin so = new SOLogin();
        
        so.izvrsi(admin);
        
        Administrator ulogovani = so.ulogovani;
        assertNotNull(ulogovani);
        assertEquals(admin.getUsername(), ulogovani.getUsername());
        assertEquals(admin.getPassword(), ulogovani.getPassword());
    }
    
    @Test
    public void testLoginVecUlogovanAdministrator() throws Exception {
        SOLogin so = new SOLogin();

        // Prvi login - uspešan
        so.izvrsi(admin);
        
        // Kreiram novi SOLogin objekat za pokušaj ponovne prijave istog administratora
        SOLogin soDrugi = new SOLogin();

        // Pokušaj ponovnog login-a sa istim kredencijalima - trebalo bi da baci izuzetak
        Exception exception = assertThrows(Exception.class, () -> {
            soDrugi.validacija(admin);
            soDrugi.izvrsi(admin);
        });

        assertEquals("Ovaj administrator je vec ulogovan na sistem!", exception.getMessage());
    }

}
