package org.repeid.models.jpa;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class DropboxProvider {

	private DbxClient dbxClient;

	public DropboxProvider(DbxClient dbxClient) {
		this.dbxClient = dbxClient;
	}

	public DbxEntry.File upload(byte[] file) {
		// TODO Auto-generated method stub
		return null;
	}

}
