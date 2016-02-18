package org.sistcoopform.manager.api.rest.messages;

/**
 * @author <a href="mailto:leonardo.zanivan@gmail.com">Leonardo Zanivan</a>
 */
public interface MessagesProvider {

    String getMessage(String messageKey, Object... parameters);

}
