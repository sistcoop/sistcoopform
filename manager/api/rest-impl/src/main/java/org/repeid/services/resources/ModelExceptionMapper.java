package org.repeid.services.resources;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.repeid.messages.MessagesProviderFactory;
import org.repeid.models.ModelException;
import org.repeid.services.ErrorResponse;

/**
 * @author <a href="mailto:leonardo.zanivan@gmail.com">Leonardo Zanivan</a>
 */
@Provider
public class ModelExceptionMapper implements ExceptionMapper<ModelException> {

    private static final Logger logger = Logger.getLogger(ModelExceptionMapper.class);

    @Inject
    private MessagesProviderFactory messagesProviderFactory;

    @Override
    public Response toResponse(ModelException ex) {
        String message = messagesProviderFactory.create().getMessage(ex.getMessage(), ex.getParameters());

        logger.error(message, ex);
        return ErrorResponse.error(message, Response.Status.BAD_REQUEST);
    }
}
