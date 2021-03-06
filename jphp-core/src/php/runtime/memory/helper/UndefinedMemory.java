package php.runtime.memory.helper;

import php.runtime.memory.NullMemory;
import php.runtime.Memory;

public class UndefinedMemory extends NullMemory {

    public static final UndefinedMemory INSTANCE = new UndefinedMemory();

    @Override
    public Memory toImmutable() {
        return NULL;
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
