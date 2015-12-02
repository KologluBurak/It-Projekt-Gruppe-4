package de.hdm.itProjektGruppe4.server;

import java.util.logging.Logger;
import de.hdm.itProjektGruppe4.shared.*;

/**
 * Erstellung des serverseitigen Loggers.
 * 
 * @author Oikonomou 
 *
 */
public class ServersideSettings extends CommonSettings {
	/**
	 * Serverseitigen Logger erstellen.
	 */
	private static final Logger logger = Logger.getLogger("RRS-Server");

	/**
	 * Instanz des serverseitigen Loggers holen.
	 *
	 * Die Logging-Level sind in der <code>java.util.logging</code> Doku zu finden. Für diese Anwendung sollen nur die
	 * folgenden 3 verwendet werden:
	 * <ul>
	 * <li>SEVERE (höchste Stufe, wird immer gezeigt)</li>
	 * <li>WARNING</li>
	 * <li>INFO</li>
	 * </ul>
	 *
	 *
	 * <h2>Anwendungsbeispiel:</h2> Logger verwenden:
	 * 
	 * <pre>
	 * Logger logger = ServerSideSettings.getLogger();
	 * </pre>
	 * 
	 * Meldung ins Log schreiben und dabei Log-Level festlegen:
	 * 
	 * <pre>
	 * logger.info(&quot;DB-Eintrag erfolgreich.&quot;);
	 * </pre>
	 * 
	 * @return Instanz des clientseitigen Loggers
	 */
	public static Logger getLogger() {
		return logger;
	}
}
