package org.craft.atom.protocol.http;

import org.craft.atom.protocol.http.model.HttpHeader;
import org.craft.atom.protocol.http.model.HttpHeaderType;

/**
 * Factory and utility methods for {@link HttpHeader}.
 * 
 * @author mindwind
 * @version 1.0, Mar 14, 2013
 */
public class HttpHeaders {
	
	/**
	 * Creates a HTTP "Server" header with specified server name.
	 * 
	 * @param serverName
	 * @return the newly created header
	 */
	public static HttpHeader newServerHeader(String serverName) {
		return new HttpHeader(HttpHeaderType.SERVER.getName(), serverName);
	}
	
	/**
	 * Creates a HTTP "Connection" header with keep alive flag.
	 * 
	 * @param keepAlive is keep alive.
	 * @return the newly created header
	 */
	public static HttpHeader newConnectionHeader(boolean keepAlive) {
		if (keepAlive) {
			return new HttpHeader(HttpHeaderType.CONNECTION.getName(), HttpConstants.CONNECTION_KEEP_ALIVE);
		} else {
			return new HttpHeader(HttpHeaderType.CONNECTION.getName(), HttpConstants.CONNECTION_CLOSE);
		}
	}
	
	/**
	 * Creates a HTTP "Date" header with current date time.
	 * 
	 * @return the newly created header
	 */
	public static HttpHeader newDateHeader() {
		return new HttpHeader(HttpHeaderType.DATE.getName(), HttpDates.formatCurrentDate());
	}
	
	/**
	 * Creates a HTTP "Content-Length" header.
	 * 
	 * @param length
	 * @return the newly created header
	 */
	public static HttpHeader newContentLengthHeader(int length) {
		return new HttpHeader(HttpHeaderType.CONTENT_LENGTH.getName(), Integer.toString(length));
	}
	
	/**
	 * Creates a HTTP "Keep-Alive" header.
	 * 
	 * @param options
	 * @return the newly created header
	 */
	public static HttpHeader newKeepAliveHeader(String options) {
		return new HttpHeader(HttpHeaderType.KEEP_ALIVE.getName(), options);
	}
	
}
