package io;

import java.util.Arrays;
import java.io.*;

public class IoStream extends ByteArrayOutputStream {

    private StringBuffer stringBuffer;
    private PrintStream oldPrintStream;

    public IoStream(PrintStream oldPrintStream) {
        this.oldPrintStream = oldPrintStream;
        this.stringBuffer = new StringBuffer();
    }

    public void println(String x) {
        oldPrintStream.println(x);
    }

    public void println() {
        oldPrintStream.println();
    }

}
