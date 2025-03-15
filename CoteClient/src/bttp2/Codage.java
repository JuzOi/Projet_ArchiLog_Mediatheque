package bttp2;

public class Codage {
	public static String coder(String message) {
		return message == null ? null : message.replace("\n", "##");
	}
	
	public static String decoder(String message) {
		return message == null ? null : message.replace("##", "\n");
	}
}
