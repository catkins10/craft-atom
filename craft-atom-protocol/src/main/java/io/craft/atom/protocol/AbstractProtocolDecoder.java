package io.craft.atom.protocol;

import io.craft.atom.util.ByteArrayBuffer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * A protocol decoder base class.
 * <p>
 * It contains three index for concrete protocol decoder using:
 * - splitIndex : The separator index position according to specific protocol, indicates next byte nearby last complete protocol object.<br>
 * - searchIndex: The cursor index position for protocol process, indicates next byte would be process by protocol codec.<br>
 * - stateIndex : The index position for protocol state machine process.
 * 
 * 
 * @author mindwind
 * @version 1.0, Feb 3, 2013
 */
@ToString(callSuper = true)
abstract public class AbstractProtocolDecoder extends AbstractProtocolCodec {
	
	
	protected static final int START = 0 ;
	protected static final int END   = -1;
	

	@Getter @Setter protected int             splitIndex        = 0                                     ;
	@Getter @Setter protected int             searchIndex       = 0                                     ;
	@Getter @Setter protected int             stateIndex        = 0                                     ;
	@Getter         protected int             state             = START                                 ;
	@Getter @Setter protected int             defaultBufferSize = 2048                                  ;
	@Getter @Setter protected int             maxSize           = defaultBufferSize * 1024              ;
	@Getter @Setter protected ByteArrayBuffer buf               = new ByteArrayBuffer(defaultBufferSize);
	
	
	// ~ ----------------------------------------------------------------------------------------------------------

	
	public void reset() {
		splitIndex  = 0                                     ;
		searchIndex = 0                                     ;
		stateIndex  = 0                                     ; 
		state       = START                                 ;
		buf         = new ByteArrayBuffer(defaultBufferSize);
	}
	
	protected void adapt() {
		if (splitIndex > 0 && splitIndex < buf.length()) {
			byte[] tailBytes = buf.array(splitIndex, buf.length());
			buf.clear();
			buf.append(tailBytes);
			splitIndex = 0;
			searchIndex = buf.length();
		}
		
		if (splitIndex > 0 && splitIndex == buf.length()) {
			buf.clear();
			splitIndex = searchIndex = 0;
		}
		
		if (buf.length() == 0 && buf.capacity() > maxSize * 2) {
			buf.reset(defaultBufferSize);
		}
	}
	
}
