package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant extends Constant{
  private final float value;
  @SneakyThrows
  public FloatConstant(DataInput input) {
    value = input.readFloat();
  }
  @Override
  public String toString() {
    return String.format("Float: %a", value);
  }
}
