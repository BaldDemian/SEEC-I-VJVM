package vjvm.runtime.classdata.constant;

import lombok.Setter;
import lombok.var;
import lombok.SneakyThrows;

import java.io.DataInput;

public class UnknownConstant extends Constant {
  private final byte[] data;
  private final String type;
  @SneakyThrows
  UnknownConstant(DataInput input, int length, String type) {
    data = new byte[length];
    this.type = type;
    input.readFully(data);
  }

  public byte[] value() {
    return data;
  }

  @Override
  public String toString() {
    return "Unknown:";
  }
}
