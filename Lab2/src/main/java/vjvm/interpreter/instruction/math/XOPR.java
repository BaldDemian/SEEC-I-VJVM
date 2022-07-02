package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XOPR extends Instruction {
  private final String name;
  public static final XOPR IADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("iadd");
  }
  public static final XOPR LADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ladd");
  }
  public static final XOPR FADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fadd");
  }
  public static final XOPR DADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dadd");
  }
  public static final XOPR ISUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("isub");
  }
  public static final XOPR LSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lsub");
  }
  public static final XOPR DSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dsub");
  }
  public static final XOPR FSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fsub");
  }
  public static final XOPR IMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("imul");
  }
  public static final XOPR DMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dmul");
  }
  public static final XOPR FMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fmul");
  }
  public static final XOPR LMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lmul");
  }
  public static final XOPR IDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("idiv");
  }
  public static final XOPR LDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ldiv");
  }
  public static final XOPR FDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fdiv");
  }
  public static final XOPR DDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ddiv");
  }
  public static final XOPR IREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("irem");
  }
  public static final XOPR DREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("drem");
  }
  public static final XOPR FREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("frem");
  }
  public static final XOPR LREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lrem");
  }


  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("iadd")) {
      int i1 = stack.popInt();
      int i2 = stack.popInt();
      stack.pushInt(i1 + i2);
    } else if (name.equals("dadd")) {
      double d1 = stack.popDouble();
      double d2 = stack.popDouble();
      stack.pushDouble(d1 + d2);
    } else if (name.equals("fadd")) {
      float f1 = stack.popFloat();
      float f2 = stack.popFloat();
      stack.pushFloat(f1 + f2);
    } else if (name.equals("ladd")) {
      long l1 = stack.popLong();
      long l2 = stack.popLong();
      stack.pushLong(l1 + l2);
    } else if (name.equals("isub")) {
      int i2 = stack.popInt();
      int i1 = stack.popInt();
      stack.pushInt(i1 - i2);
    } else if (name.equals("lsub")) {
      long l2 = stack.popLong();
      long l1 = stack.popLong();
      stack.pushLong(l1 - l2);
    } else if (name.equals("fsub")) {
      float f2 = stack.popFloat();
      float f1 = stack.popFloat();
      stack.pushFloat(f1 - f2);
    } else if (name.equals("dsub")) {
      double d2 = stack.popDouble();
      double d1 = stack.popDouble();
      stack.pushDouble(d1 - d2);
    } else if (name.equals("imul")) {
      int i1 = stack.popInt();
      int i2 = stack.popInt();
      stack.pushInt(i1 * i2);
    } else if (name.equals("dmul")) {
      double d1 = stack.popDouble();
      double d2 = stack.popDouble();
      stack.pushDouble(d1 * d2);
    } else if (name.equals("fmul")) {
      float f1 = stack.popFloat();
      float f2 = stack.popFloat();
      stack.pushFloat(f1 * f2);
    } else if (name.equals("lmul")) {
      long l1 = stack.popLong();
      long l2 = stack.popLong();
      stack.pushLong(l1 * l2);
    } else if (name.equals("idiv")) {
      int i2 = stack.popInt();
      int i1 = stack.popInt();
      stack.pushInt(i1 / i2);
    } else if (name.equals("ldiv")) {
      long l2 = stack.popLong();
      long l1 = stack.popLong();
      stack.pushLong(l1 / l2);
    } else if (name.equals("fdiv")) {
      float f2 = stack.popFloat();
      float f1 = stack.popFloat();
      stack.pushFloat(f1 / f2);
    } else if (name.equals("ddiv")) {
      double d2 = stack.popDouble();
      double d1 = stack.popDouble();
      stack.pushDouble(d1 / d2);
    } else if (name.equals("irem")) {
      int i2 = stack.popInt();
      int i1 = stack.popInt();
      stack.pushInt(i1 % i2);
    }else if (name.equals("lrem")) {
      long l2 = stack.popLong();
      long l1 = stack.popLong();
      stack.pushLong(l1 % l2);
    }else if (name.equals("frem")) {
      float f2 = stack.popFloat();
      float f1 = stack.popFloat();
      stack.pushFloat(f1 % f2);
    }else if (name.equals("drem")) {
      double d2 = stack.popDouble();
      double d1 = stack.popDouble();
      stack.pushDouble(d1 % d2);
    } else {
      System.err.println("Wrong type in XORP!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
