package fr.didier.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OSValidator {

	private static String OS = "";

	public static enum Type {
		PI, DEBIAN, WIN;
	}

	public static void main(String[] args) {

		System.out.println(getFullOS());

	}

	public static boolean isWindows() {
		return (getFullOS().contains("win"));
	}

	public static boolean isPi() {
		return (getFullOS().contains("pi"));
	}

	public static boolean isLinux() {
		return (OS.contains("linux") || OS.contains("Linux"));
	}

	public static Type getOS() {
		if (isWindows()) {
			return Type.WIN;
		} else if (isPi()) {
			return Type.PI;
		} else if (isLinux()) {
			return Type.DEBIAN;
		} else {
			return null;
		}
	}

	public static String getFullOS() {
		if (OS.isEmpty()) {
			try {
				OS = System.getProperty("os.name", "generic") + "-" + InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				System.out.println("can't get system");
			}
		}
		return OS;
	}

}
