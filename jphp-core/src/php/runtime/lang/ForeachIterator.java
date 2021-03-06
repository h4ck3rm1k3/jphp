package php.runtime.lang;


import php.runtime.memory.LongMemory;
import php.runtime.memory.StringMemory;
import php.runtime.Memory;

abstract public class ForeachIterator {
    protected Object currentKey;
    protected Memory currentKeyMemory;
    protected Memory currentValue;
    protected boolean init = false;
    protected final boolean getReferences;
    protected final boolean getKeyReferences;
    protected final boolean withPrevious;
    protected boolean plainReferences = false;

    abstract protected boolean init();
    abstract protected boolean nextValue();
    abstract protected boolean prevValue();

    public ForeachIterator(boolean getReferences, boolean getKeyReferences, boolean withPrevious) {
        this.getReferences = getReferences;
        this.withPrevious = withPrevious;
        this.getKeyReferences = getKeyReferences;
    }

    public void setPlainReferences(boolean plainReferences) {
        this.plainReferences = plainReferences;
    }

    public boolean prev(){
        currentKeyMemory = null;
        if (!init || !withPrevious) {
            this.currentKey = null;
            this.currentValue = null;
            return false;
        } else
            return prevValue();
    }

    public boolean next(){
        currentKeyMemory = null;
        if (!init){
            init = true;
            if (!init())
                return false;
        }

        return nextValue();
    }

    public boolean end(){
        return false;
    }

    public Object getKey() {
        return currentKey;
    }

    public Memory getMemoryKey(){
        if (currentKeyMemory != null)
            return currentKeyMemory;

        if (currentKey instanceof String)
            return currentKeyMemory = new StringMemory((String)currentKey);
        if (currentKey instanceof Long)
            return currentKeyMemory = LongMemory.valueOf((Long)currentKey);
        if (currentKey instanceof Memory)
            return currentKeyMemory = (Memory) currentKey;

        return currentKeyMemory = Memory.NULL;
    }

    public Memory getValue() {
        return currentValue;
    }
}
