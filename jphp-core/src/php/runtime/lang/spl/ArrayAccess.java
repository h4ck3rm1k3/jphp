package php.runtime.lang.spl;

import php.runtime.common.HintType;
import php.runtime.env.Environment;
import php.runtime.Memory;

import static php.runtime.annotation.Reflection.*;

@Name("ArrayAccess")
public interface ArrayAccess {

    @Signature(value = @Arg("offset"), result = @Arg(type = HintType.BOOLEAN))
    public Memory offsetExists(Environment env, Memory... args);

    @Signature(@Arg("offset"))
    public Memory offsetGet(Environment env, Memory... args);

    @Signature({@Arg("offset"), @Arg("value")})
    public Memory offsetSet(Environment env, Memory... args);

    @Signature(@Arg("offset"))
    public Memory offsetUnset(Environment env, Memory... args);

}
