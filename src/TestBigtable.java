import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests data loading and querying on Google Bigtable.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBigtable {
	/**
	 * Class being tested
	 */
	private static Bigtable db;

	/**
	 * Requests a connection to the server.
	 * 
	 * @throws Exception
	 *                   if an error occurs
	 */
	@BeforeAll
	public static void init() throws Exception {
		db = new Bigtable();
		db.connect();
	}

	/**
	 * Tests connect
	 */
	@Test
	@Order(1)
	public void testConnect() {
		try {
			db.connect();
			if (db.dataClient == null || db.adminClient == null)
				fail("Failed to connect");
		} catch (Exception e) {
			System.out.println(e);
			fail("Connection failed");
		}
	}

	/**
	 * Tests load command.
	 */
	@Test
	@Order(3)
	public void testLoad() {
		// See if data is loaded. Test would depend on schema.
		try {
			// Verify row count to test load?
			// Note: No current test for this.
		} catch (Exception e) {
			System.out.println(e);
			fail(e.toString());
		}
	}

	/**
	 * Tests first query.
	 */
	@Test
	@Order(4)
	public void testQuery1() throws Exception {
		int temp = db.query1();
		System.out.println("Temperature: " + temp);
		assertEquals(52, temp);
	}

	/**
	 * Tests second query.
	 */
	@Test
	@Order(5)
	public void testQuery2() throws Exception {
		int windspeed = db.query2();
		System.out.println("Windspeed: " + windspeed);
		assertEquals(25, windspeed);
	}

	/**
	 * Tests third query.
	 */
	@Test
	@Order(6)
	public void testQuery3() throws Exception {
		ArrayList<Object[]> data = db.query3();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < data.size(); i++) {
			Object[] vals = data.get(i);

			for (int j = 0; j < vals.length; j++)
				buf.append(vals[j].toString() + " ");
			buf.append("\n");
		}
		System.out.println(buf.toString());

		String answer = "2022-10-02 00 74 53 47.8 9 1014.1 "
				+ "\n2022-10-02 01 69 53 56.7 7 1014.1 "
				+ "\n2022-10-02 02 67 53 60.7 7 1014.3 "
				+ "\n2022-10-02 03 66 53 62.9 7 1014.4 "
				+ "\n2022-10-02 04 64 53 67.4 7 1014.2 "
				+ "\n2022-10-02 05 63 52 67.3 7 1014.1 "
				+ "\n2022-10-02 06 61 52 72.2 8 1014.3 "
				+ "\n2022-10-02 07 63 51 64.8 9 1014.2 "
				+ "\n2022-10-02 08 61 53 74.9 4 1014 "
				+ "\n2022-10-02 09 59 52 77.5 0 1014.2 "
				+ "\n2022-10-02 10 58 52 80.4 0 1014.3 "
				+ "\n2022-10-02 11 55 51 86.3 3 1014.3 "
				+ "\n2022-10-02 12 57 52 83.3 4 1014.7 "
				+ "\n2022-10-02 13 56 52 86.4 3 1015.2 "
				+ "\n2022-10-02 14 57 52 83.3 0 1015.6 "
				+ "\n2022-10-02 15 62 53 72.3 5 1015.9 "
				+ "\n2022-10-02 16 66 53 62.9 8 1016.2 "
				+ "\n2022-10-02 17 70 53 54.8 5 1016.4 "
				+ "\n2022-10-02 18 72 54 53.1 3 1016.2 "
				+ "\n2022-10-02 19 76 52 43.1 6 1016 "
				+ "\n2022-10-02 20 77 53 43.3 5 1015.7 "
				+ "\n2022-10-02 21 78 53 41.9 5 1015.3 "
				+ "\n2022-10-02 22 79 52 39.1 5 1015.3 "
				+ "\n2022-10-02 23 79 51 37.6 4 1015.2 \n";

		assertEquals(answer, buf.toString());
	}

	/**
	 * Tests fourth query.
	 */
	@Test
	@Order(7)
	public void testQuery4() throws Exception {
		int temp = db.query4();
		System.out.println("Temperature: " + temp);
		assertEquals(101, temp);
	}
}
