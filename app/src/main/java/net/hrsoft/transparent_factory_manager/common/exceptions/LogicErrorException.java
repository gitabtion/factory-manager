package net.hrsoft.transparent_factory_manager.common.exceptions;

/**
 * @author abtion.
 * @since 17/8/25 17:38.
 * email caiheng@hrsoft.net
 */

public class LogicErrorException extends Throwable {
    public LogicErrorException() {
        super("逻辑上不可达的代码，请检查！！！");
    }
}
