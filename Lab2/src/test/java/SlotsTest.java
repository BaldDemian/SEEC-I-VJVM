import vjvm.runtime.OperandStack;
import vjvm.runtime.Slots;

public class SlotsTest {
  public static void main(String[] args) {
//    OperandStack stack = new OperandStack(10);
//    stack.pushLong(4L);
//    stack.pushDouble(1.0);
//    var val1 = stack.popSlots(1);
//    var val2 = stack.popSlots(1);
//    var val3 = stack.popSlots(1);
//    var val4 = stack.popSlots(1);
//    stack.pushSlots(val2);
//    stack.pushSlots(val1);
//    stack.pushSlots(val4);
//    stack.pushSlots(val3);
//    stack.pushSlots(val2);
//    stack.pushSlots(val1);
    int val2 = 51;
    String binaryVal2 = Integer.toBinaryString(val2);
    if (binaryVal2.length() > 5) {
      binaryVal2 = binaryVal2.substring(binaryVal2.length() - 5, binaryVal2.length());
    }
    binaryVal2 = "0" + binaryVal2;
    int moveBits = Integer.valueOf(binaryVal2, 2);
    System.out.println(moveBits);
  }
}
