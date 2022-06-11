package vjvm.runtime;

import lombok.var;
import java.nio.ByteBuffer;

public class ProgramCounter {
  private final ByteBuffer buf;

  public ProgramCounter(byte[] code) {
    buf = ByteBuffer.wrap(code);
  }

  public byte byte_() {
    // increase the pc
    return buf.get();
  }

  public int ubyte() {
    // transfer the byte into an int and then increase the pc
    return Byte.toUnsignedInt(buf.get());
  }

  public short short_() {
    return buf.getShort();
  }

  public int ushort() {
    return Short.toUnsignedInt(buf.getShort());
  }

  public void move(int offset) {
    buf.position(buf.position() + offset);
  }

  public void position(int pos) {
    buf.position(pos);
  }

  public int position() {
    return buf.position();
  }
}
