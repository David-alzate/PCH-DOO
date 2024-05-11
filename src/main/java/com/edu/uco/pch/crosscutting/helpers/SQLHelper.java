package com.edu.uco.pch.crosscutting.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import com.edu.uco.pch.crosscutting.exceptions.custom.CroscuttingPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

public final class SQLHelper {

	private SQLHelper() {
		super();
	}

	public static final boolean isNull(final Connection connection) {
		return ObjectHelper.getObjecHelper().isNull(connection);
	}

	public static final boolean isOpen(final Connection connection) {
		try {
			return !isNull(connection) && !connection.isClosed();
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00007);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00008);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void close(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00009);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			connection.close();
		} catch (final CroscuttingPCHException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00010);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00011);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void commit(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00012);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			if (connection.getAutoCommit()) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00013);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			connection.commit();
		} catch (final CroscuttingPCHException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00014);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00015);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void rollback(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00016);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			if (connection.getAutoCommit()) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00017);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			connection.rollback();
		} catch (final CroscuttingPCHException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00018);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00019);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void initTransaction(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00020);

				throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
			}

			connection.setAutoCommit(false);
		} catch (final CroscuttingPCHException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00021);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00022);

			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario, exception);
		}
	}
}
