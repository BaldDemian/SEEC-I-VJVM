package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;

import java.io.DataInput;

public class LongConstant extends Constant{
  private final long value;
  @SneakyThrows
  public LongConstant(DataInput input) {
    value = input.readLong();
  }
  @Override
  public String toString() {
    return String.format("Long: %d", value);
  }
}
