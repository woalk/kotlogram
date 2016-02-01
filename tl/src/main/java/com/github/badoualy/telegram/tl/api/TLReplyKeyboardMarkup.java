package com.github.badoualy.telegram.tl.api;

import com.github.badoualy.telegram.tl.TLContext;
import com.github.badoualy.telegram.tl.core.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.badoualy.telegram.tl.StreamUtils.readInt;
import static com.github.badoualy.telegram.tl.StreamUtils.readTLVector;
import static com.github.badoualy.telegram.tl.StreamUtils.writeBoolean;
import static com.github.badoualy.telegram.tl.StreamUtils.writeInt;
import static com.github.badoualy.telegram.tl.StreamUtils.writeTLVector;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLReplyKeyboardMarkup extends TLAbsReplyMarkup {
    public static final int CONSTRUCTOR_ID = 0x3502758c;

    protected boolean resize;

    protected boolean singleUse;

    protected TLVector<TLKeyboardButtonRow> rows;

    public TLReplyKeyboardMarkup() {
    }

    public TLReplyKeyboardMarkup(int flags, boolean resize, boolean singleUse, boolean selective, TLVector<TLKeyboardButtonRow> rows) {
        this.flags = flags;
        this.resize = resize;
        this.singleUse = singleUse;
        this.selective = selective;
        this.rows = rows;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        flags = 0;
        flags = resize ? (flags | 1) : (flags &~ 1);
        flags = singleUse ? (flags | 2) : (flags &~ 2);
        flags = selective ? (flags | 4) : (flags &~ 4);

        writeInt(flags, stream);
        if ((flags & 1) != 0) writeBoolean(resize, stream);
        if ((flags & 2) != 0) writeBoolean(singleUse, stream);
        if ((flags & 4) != 0) writeBoolean(selective, stream);
        writeTLVector(rows, stream);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = readInt(stream);
        resize = (flags & 1) != 0;
        singleUse = (flags & 2) != 0;
        selective = (flags & 4) != 0;
        rows = readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "replyKeyboardMarkup#3502758c";
    }

    @Override
    public int getConstructorId() {
        return CONSTRUCTOR_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean getResize() {
        return resize;
    }

    public void setResize(boolean resize) {
        this.resize = resize;
    }

    public boolean getSingleUse() {
        return singleUse;
    }

    public void setSingleUse(boolean singleUse) {
        this.singleUse = singleUse;
    }

    public boolean getSelective() {
        return selective;
    }

    public void setSelective(boolean selective) {
        this.selective = selective;
    }

    public TLVector<TLKeyboardButtonRow> getRows() {
        return rows;
    }

    public void setRows(TLVector<TLKeyboardButtonRow> rows) {
        this.rows = rows;
    }
}
